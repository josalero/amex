package com.amex.srt.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.jcr.RepositoryException;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

import com.amex.srt.content.repo.ContentRepoAccessDao;
import com.amex.srt.content.repo.NodeRef;
import com.amex.srt.content.repo.RepoFileData;
import com.whirlycott.cache.Cache;
import com.whirlycott.cache.CacheConfiguration;
import com.whirlycott.cache.CacheException;
import com.whirlycott.cache.CacheManager;

public class ContentManager implements InitializingBean,
		ApplicationContextAware {
	private static final Log log = LogFactory.getLog(ContentManager.class);

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
 
	private CacheConfiguration cacheConfiguration;

	private Cache runtimeContentCache;
	private Cache runtimePageCache;

	protected ContentRepoAccessDao contentRepoAccessDao;

	protected Unmarshaller contentItemUnmarshaller;

	public ContentManager() {
		super();
	}

	public void init() {

		log.debug("ContentManager.init(): Entry ...");

		cacheConfiguration = new CacheConfiguration();

		cacheConfiguration.setName("EnterpriseRuntimeContentCache");
		cacheConfiguration
				.setBackend("com.whirlycott.cache.impl.ConcurrentHashMapImpl");
		cacheConfiguration.setTunerSleepTime(60);
		cacheConfiguration
				.setPolicy("com.whirlycott.cache.policy.LFUMaintenancePolicy");
		cacheConfiguration.setMaxSize(10000);

		try {
			runtimeContentCache = CacheManager.getInstance().createCache(
					cacheConfiguration);
			runtimePageCache = CacheManager.getInstance().createCache(
					cacheConfiguration);
		} catch (CacheException cacheException) {
			log.error("Failed to initialize Runtime Content Cache",
					cacheException);
		}
	}

	public ContentRepoAccessDao getContentRepoAccessDao() {
		return contentRepoAccessDao;
	}

	public void setContentRepoAccessDao(
			ContentRepoAccessDao contentRepoAccessDao) {
		this.contentRepoAccessDao = contentRepoAccessDao;
	}


	public void setContentItemUnmarshaller(Unmarshaller contentItemUnmarshaller) {
		this.contentItemUnmarshaller = contentItemUnmarshaller;
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public Map<String, NodeRef> getAllContentNodes(String rootFolderPath) {

		Set<String> propNames = new HashSet<String>();
		propNames.add("amex:CMS_ID");

		return contentRepoAccessDao.buildRepoNodeMap(propNames, "/xml");
	}

	public synchronized void addOrUpdateFiles(List<RepoFileData> newAndModFiles) {

		Map<String, RepoFileData> updateFileMap = new HashMap<String, RepoFileData>();
		List<NodeRef> addedOrUpdatedNodeRefs;

		for (RepoFileData aReporFileData : newAndModFiles) {
			if (aReporFileData.isUpdate()) {
				updateFileMap.put(aReporFileData.getFilePath(), aReporFileData);
			}

			// Add one by one

			// addedOrUpdatedNodeRefs = new ArrayList<NodeRef>();
			//
			// List<RepoFileData> filesToAdd = new ArrayList<RepoFileData>(1);
			// filesToAdd.add(aReporFileData);
			//
			// try {
			//
			// log.debug("FILE: " + aReporFileData.getFilePath());
			//
			// List<NodeRef> anAddedOrUpdatedNodeRefs =
			// contentRepoAccessDao.addOrUpdateFiles(filesToAdd);
			//
			// addedOrUpdatedNodeRefs.addAll(anAddedOrUpdatedNodeRefs);
			// }
			// catch (Exception exception) {
			// log.error(exception);
			// }
		}

		long start = System.currentTimeMillis();

		log.debug("Starting content adding/updating in the REPO ...");

		addedOrUpdatedNodeRefs = contentRepoAccessDao
				.addOrUpdateFiles(newAndModFiles);

		log.debug("Added/updated " + addedOrUpdatedNodeRefs.size()
				+ " files: elapsed = " + (System.currentTimeMillis() - start));

		for (NodeRef nodeRef : addedOrUpdatedNodeRefs) {

			if (updateFileMap.get(nodeRef.getPath()) != null) {

				// Update runtime cache if object is there

				String typeName = (String) nodeRef.getProperties().get(
						"amex:Type");

				if (typeName != null && typeName.equals("PAGE")) {

					String pageCacheKey = generatePageCacheKey(nodeRef);

					if (runtimePageCache.retrieve(pageCacheKey) != null) {
						updatePageInRuntimePageCache(pageCacheKey, nodeRef);
					}
				} else if (typeName != null && typeName.equals("NAVIGATION")) {

					String pageCacheKey = nodeRef.getPath();

					if (runtimePageCache.retrieve(pageCacheKey) != null) {
						updatePageInRuntimePageCache(pageCacheKey, nodeRef);
					}
				} else {
					if (runtimeContentCache != null && nodeRef != null
							&& nodeRef.getCmsId() != null) {
						// if (runtimeContentCache.retrieve(nodeRef.getCmsId())
						// != null) {
						// updateContentInRuntimeCache(nodeRef);
						// }
						Object cachedObject = runtimeContentCache
								.retrieve(nodeRef.getCmsId());
						if (cachedObject != null) {
							updateContentInRuntimeCache(nodeRef);
						}
					}
				}
			}
		}
	}

	private String generatePageCacheKey(NodeRef pageNodeRef) {

		String locale = (String) pageNodeRef.getProperties().get(
				"amex:Locale");
		String pagePath = (String) pageNodeRef.getProperties().get(
				"amex:PagePath");

		String pageCacheKey = locale + "/" + pagePath;

		return pageCacheKey;
	}

	public void deleteFiles(Collection<NodeRef> nodeRefList,
			List<ContentLoadReportEntry> list) {

		for (NodeRef node : nodeRefList) {

			ContentLoadReportEntry contentLoadReportEntry = new ContentLoadReportEntry();

			contentLoadReportEntry.setContentItemPath(node.getPath());
			contentLoadReportEntry.setOperation("DELETE");
			contentLoadReportEntry.setOutcome("SUCCESS");

			log.debug("To-be-removed repo file name: " + node.getPath()
					+ ": identified = " + node.getIdentifier());

			runtimeContentCache.remove(node.getCmsId());

			list.add(contentLoadReportEntry);
		}

		contentRepoAccessDao.deleteFiles(nodeRefList);
	}

	public void deleteFiles(Collection<NodeRef> nodeRefList,
			Map<String, String> cmsIDMap, List<ContentLoadReportEntry> list) {

		for (NodeRef node : nodeRefList) {

			log.debug("To-be-removed repo file name: " + node.getPath()
					+ ": identified = " + node.getIdentifier());

			if (StringUtils.isNotEmpty(node.getCmsId())) {
				cmsIDMap.remove(node.getCmsId());
			}

			runtimeContentCache.remove(node.getCmsId());
		}

		contentRepoAccessDao.deleteFiles(nodeRefList);
	}

	public void updateContentInRuntimeCache(NodeRef nodeRef) {

		log.debug("Updating " + nodeRef.getPath() + ", identifier = "
				+ nodeRef.getIdentifier() + " in Runtime Cache");

		log.debug("    => type = "
				+ nodeRef.getProperties().get("amex:Type"));

		ContentItem contentItem = unmarshalContentItem(nodeRef);
		runtimeContentCache.store(nodeRef.getCmsId(), contentItem);
	}

	public void updatePageInRuntimePageCache(String pageCacheKey,
			NodeRef nodeRef) {

		log.debug("Updating " + nodeRef.getPath() + ", identifier = "
				+ nodeRef.getIdentifier() + " in Runtime Page Cache");

		log.debug("    => type = "
				+ nodeRef.getProperties().get("amex:Type"));

		Object currentCachedObject = runtimePageCache.retrieve(pageCacheKey);

		if (currentCachedObject instanceof ContentItem) {

			ContentItem contentItem = unmarshalContentItem(nodeRef);
			runtimePageCache.store(pageCacheKey, contentItem);
		} else {

			throw new IllegalStateException("Invalid cached object of type: "
					+ currentCachedObject.getClass().getName());
		}
	}

	/*public Navigation retrieveNavigation(String locale) {
		@SuppressWarnings("unused")
		String typeName = NAVIGATION_TYPE;
		Navigation navigation = null;
		String jcrPath = "/xml/" + locale + "/navigation/navigation.xml";
		String pageCacheKey = jcrPath;

		// Lookup in cache
		navigation = (Navigation) runtimePageCache.retrieve(pageCacheKey);

		if (navigation == null) {
			long timestamp = System.currentTimeMillis();
			ContentItem contentItem = retrieveNavigationContentItem(locale);

			if (contentItem == null) {
				log.warn("Unable to find a navigation content item!");
				return null;
			}

			log.debug("navigationNodeRef: elapsed = "
					+ (System.currentTimeMillis() - timestamp));

			// Populate and cache
			timestamp = System.currentTimeMillis();
			navigation = (Navigation) contentItem.getContent();
			runtimePageCache.store(pageCacheKey, navigation);
			log.debug("Navigation Unmarshal: elapsed = "
					+ (System.currentTimeMillis() - timestamp));
		}

		return navigation;
	}*/

	/*private Navigation unmarshalNavigation(NodeRef navigationNodeRef) {

		Navigation navigationJCRObject = null;

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				navigationNodeRef.getContent());
		StreamSource streamSource = new StreamSource(byteArrayInputStream);

		try {
			navigationJCRObject = (Navigation) navigationUnmarshaller
					.unmarshal(streamSource);
		} catch (XmlMappingException xmlMappingException) {
			log.error(xmlMappingException);
		} catch (IOException ioException) {
			log.error(ioException);
		}

		return navigationJCRObject;
	}*/


	public String getPageCategory(String filePath) {

		String localeString = filePath.substring("/xml/".length());
		localeString = localeString.substring(0, localeString.indexOf('/'));

		String prefix = "/xml/" + localeString + "/";

		String category = filePath.substring(prefix.length());
		int pos = category.lastIndexOf('.');
		category = category.substring(0, pos);

		StringTokenizer tokenizer = new StringTokenizer(category, "/");

		category = tokenizer.nextToken();

		return category;
	}

	public String getPagePath(String filePath) {
		String localeString = filePath.substring("/xml/".length());
		localeString = localeString.substring(0, localeString.indexOf('/'));

		String prefix = "/xml/" + localeString + "/";

		String category = filePath.substring(prefix.length());
		int pos = category.lastIndexOf('.');
		category = category.substring(0, pos);

		StringTokenizer tokenizer = new StringTokenizer(category, "/");

		category = tokenizer.nextToken();
		String pagePath = category;
		while (tokenizer.hasMoreTokens()) {
			pagePath = tokenizer.nextToken();
		}

		if (!pagePath.equals(category)) {
			pagePath = category + "/" + pagePath;
		}

		return pagePath;
	}


	public ContentItem retrievePage(NodeRef pageNodeRef, String locale) {

		String pageCategory = getPageCategory(pageNodeRef.getPath());
		String pagePath = getPagePath(pageNodeRef.getPath());

		return retrievePage(pageCategory, pagePath, locale);
	}
	
	public ContentItem retrievePage(String cmsID) {
		ContentItem pageContentJCRObject = null;


		pageContentJCRObject = (ContentItem) runtimePageCache
				.retrieve(cmsID);

		if (pageContentJCRObject == null) {
			long timestamp = System.currentTimeMillis();
			
			Set<String> props = new TreeSet<String>();

			props.add("amex:CMS_ID");
			
			NodeRef pageNodeRef = findContentItemByCmsId(cmsID, props);
			log.debug("findPageNodeRef: elapsed = "
					+ (System.currentTimeMillis() - timestamp));

			// Populate and cache
			timestamp = System.currentTimeMillis();

			// returning null when the pageNode not found
			if (null == pageNodeRef) {
				return null;
			}
			pageContentJCRObject = unmarshalContentItem(pageNodeRef);
			pageContentJCRObject.setJcrPath(pageNodeRef.getPath());

			// Process Page data to verify/populate pageName and pageParentName

			Page pageJCRObject = (Page) pageContentJCRObject.getContent();

			if (pageJCRObject.getPageName() == null) {

				String pageName = obtainPageNameFromPageJCRObject(
						pageJCRObject, pageNodeRef);
				pageJCRObject.setPageName(pageName);
			}

			runtimePageCache.store(cmsID, pageContentJCRObject);

			log.debug("Page Unmarshal: elapsed = "
					+ (System.currentTimeMillis() - timestamp));
		}

		return pageContentJCRObject;
	}
	

	public ContentItem retrievePageByURL(String URL) {
		ContentItem pageContentJCRObject = null;


		pageContentJCRObject = (ContentItem) runtimePageCache.retrieve(URL);

		List<ContentItem> pageList = new ArrayList<ContentItem>();

		if (pageContentJCRObject == null){
			StringBuilder query = new StringBuilder();
	
			query.append("SELECT * FROM [amex:Page] WHERE ");
			
			query.append("([amex:URL] = '").append(URL).append("')");
	
			log.info ("QUERY ->" + query.toString());
			
			Set<String> propNames = new HashSet<String>();
			propNames.add("amex:CMS_ID");
			propNames.add("amex:URL");
			
			List<NodeRef> nodeRefList = contentRepoAccessDao.findFiles(propNames, query.toString(), null, null);
			
			
			for (NodeRef nodeRef : nodeRefList) {
				ContentItem contentItem = unmarshalContentItem(nodeRef);
	
				if (contentItem.getJcrPath() == null) {
					contentItem.setJcrPath(nodeRef.getPath());
				}
	
				runtimeContentCache.store(nodeRef.getCmsId(), contentItem);
	
				pageList.add(contentItem);
			}			
		}
		return pageList.get(0);
	}
		
	public ContentItem retrievePage(String category, String pagePath,
			String locale) {
		ContentItem pageContentJCRObject = null;

		// Lookup in cache
		String pageCacheKey = locale + "/" + pagePath;

		pageContentJCRObject = (ContentItem) runtimePageCache
				.retrieve(pageCacheKey);

		if (pageContentJCRObject == null) {
			long timestamp = System.currentTimeMillis();
			NodeRef pageNodeRef = findPageNodeRef(category, pagePath, locale);
			log.debug("findPageNodeRef: elapsed = "
					+ (System.currentTimeMillis() - timestamp));

			// Populate and cache
			timestamp = System.currentTimeMillis();

			// returning null when the pageNode not found
			if (null == pageNodeRef) {
				return null;
			}
			pageContentJCRObject = unmarshalContentItem(pageNodeRef);
			pageContentJCRObject.setJcrPath(pageNodeRef.getPath());

			// Process Page data to verify/populate pageName and pageParentName

			Page pageJCRObject = (Page) pageContentJCRObject.getContent();

			if (pageJCRObject.getPageName() == null) {

				String pageName = obtainPageNameFromPageJCRObject(
						pageJCRObject, pageNodeRef);
				pageJCRObject.setPageName(pageName);
			}

			runtimePageCache.store(pageCacheKey, pageContentJCRObject);

			log.debug("Page Unmarshal: elapsed = "
					+ (System.currentTimeMillis() - timestamp));
		}

		return pageContentJCRObject;
	}



	public ContentItem retrieveTile(String cmsId, String locale) {
		ContentItem contentItem = null;

		// Lookup in cache
		String pageCacheKey = locale + "/" + cmsId;
		contentItem = (ContentItem) runtimePageCache.retrieve(pageCacheKey);

		if (contentItem == null) {
			long timestamp = System.currentTimeMillis();
			NodeRef nodeRef = findTileNodeRef(cmsId, locale);

			if (nodeRef == null) {
				log.error("Unable to find a tile node for the ID: " + cmsId
						+ "!");
				return null;
			}

			log.debug("findPageNodeRef: elapsed = "
					+ (System.currentTimeMillis() - timestamp));

			// Populate and cache
			timestamp = System.currentTimeMillis();

			contentItem = unmarshalContentItem(nodeRef);
			contentItem.setJcrPath(nodeRef.getPath());

			runtimePageCache.store(pageCacheKey, contentItem);
			log.debug("Poll Unmarshal: elapsed = "
					+ (System.currentTimeMillis() - timestamp));
		}

		return contentItem;
	}

	private ContentItem getParentPageJCRObject(String locale, String jcrPath) {
		boolean isAnArticle = jcrPath.contains("articles");
		String[] elements = jcrPath.split("/");
		String parentPageRelativeName = elements[elements.length - 2];

		if (parentPageRelativeName.equals(locale)) {
			return null;
		}

		parentPageRelativeName = parentPageRelativeName.replace(".xml", "");
		String parentPagePath = null;

		if (!isAnArticle) {
			parentPagePath = elements[3];
		} else {
			parentPagePath = elements[4];
		}

		if (!parentPagePath.equals(parentPageRelativeName)) {
			parentPagePath += "/" + parentPageRelativeName;
		}

		String parentPageCategory = null;

		if (!isAnArticle) {
			parentPageCategory = elements[3];
		} else {
			parentPageCategory = elements[4];
		}

		ContentItem parentPageContentItem = retrievePage(parentPageCategory,
				parentPagePath, locale);

		return parentPageContentItem;
	}

	private String obtainPageNameFromPageJCRObject(Page pageJCRObject,
			NodeRef pageNodeRef) {

		String pageName = null;

		String path = pageNodeRef.getPath();

		String[] elements = path.split("/");

		pageName = elements[elements.length - 1];
		pageName = pageName.replace(".xml", "");
		pageName = pageName.replace('-', ' ');

		pageName = pageName.toUpperCase();

		return pageName;
	}

	/**
	 * @param localeString
	 * @param limit
	 * @param whereClause
	 * @param jcrContentType
	 * @return
	 */
	public List<ContentItem> retrievePublishedItems(String localeString,
			Integer limit, Integer offset, String whereClause,
			String orderByClause, String jcrContentType) {

		List<ContentItem> contentItems = new ArrayList<ContentItem>();

		long timestamp = System.currentTimeMillis();

		// Example: SELECT * FROM [nt:base] AS s WHERE s.[jcr:created] >
		// CAST('2012-01-05T00:00:00.000Z' AS DATE)

		if (StringUtils.isEmpty(orderByClause)) {
			orderByClause = "ORDER BY [amex:PublishedDate] DESC";
		}

		List<NodeRef> nodeRefList = findContentItemsByTypeAndQuery(
				localeString, jcrContentType, whereClause, orderByClause,
				limit, offset);

		log.debug("findContentItemsByTypeAndQuery: elapsed = "
				+ (System.currentTimeMillis() - timestamp));

		// Populate and cache
		timestamp = System.currentTimeMillis();

		for (NodeRef nodeRef : nodeRefList) {
			ContentItem contentItem = (ContentItem) runtimeContentCache
					.retrieve(nodeRef.getCmsId());

			if (contentItem == null) {
				contentItem = unmarshalContentItem(nodeRef);
				runtimeContentCache.store(nodeRef.getCmsId(), contentItem);
			}

			contentItems.add(contentItem);
		}

		log.debug("Content Items Unmarshal/Retrieve from Cache: elapsed = "
				+ (System.currentTimeMillis() - timestamp));
		return contentItems;
	}


	public ContentItem retrieveContentItemByCmsId(String cmsId) {
		ContentItem contentItem = (ContentItem) runtimeContentCache
				.retrieve(cmsId);

		if (contentItem == null) {
			NodeRef nodeRef = findContentItemByCmsId(cmsId, null);

			if (nodeRef != null) {
				contentItem = unmarshalContentItem(nodeRef);
				runtimeContentCache.store(contentItem.getCmsId(), contentItem);
			}
		}

		return contentItem;
	}

	private ContentItem unmarshalContentItem(NodeRef contentItemNodeRef) {

		ContentItem contentItemJCRObject = null;

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				contentItemNodeRef.getContent());
		StreamSource streamSource = new StreamSource(byteArrayInputStream);

		try {
			contentItemJCRObject = (ContentItem) contentItemUnmarshaller
					.unmarshal(streamSource);
		} catch (XmlMappingException xmlMappingException) {
			log.error(xmlMappingException);
		} catch (IOException ioException) {
			log.error(ioException);
		}

		return contentItemJCRObject;
	}

	private NodeRef findContentItemByCmsId(String cmsId,
			Set<String> propNamesAttr) {

		Set<String> propNames = propNamesAttr;

		if (propNames == null) {
			propNames = new HashSet<String>();
		}

		String sqlQueryString = "SELECT * FROM [amex:ContentItem] WHERE [amex:CMS_ID] = '"
				+ cmsId + "'";

		propNames.add("amex:Locale");
		propNames.add("amex:Type");
		propNames.add("amex:CMS_ID");

		List<NodeRef> nodeList = contentRepoAccessDao.findFiles(propNames,
				sqlQueryString, null, null);

		if (nodeList == null || nodeList.isEmpty()) {
			return null;
		}

		return nodeList.get(0);
	}

	private List<NodeRef> findContentItemsByTypeAndQuery(String locale,
			String jcrTypeName, String whereClause, String orderBy,
			Integer limit, Integer offset) {

		String sqlQueryString = "SELECT * FROM [" + jcrTypeName + "]";

		if (whereClause != null && !whereClause.isEmpty()) {
			sqlQueryString += " WHERE " + whereClause;
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			sqlQueryString += " ";
			sqlQueryString += orderBy;
		}

		log.debug("findContentItemsByTypeAndQuery: SQL2 query = "
				+ sqlQueryString);

		Set<String> propNames = new HashSet<String>();

		propNames.add("amex:Locale");
		propNames.add("amex:Type");
		propNames.add("amex:CMS_ID");

		List<NodeRef> nodeList = contentRepoAccessDao.findFiles(propNames,
				sqlQueryString, limit, offset);

		return nodeList;
	}

	public NodeRef findPageNodeRef(String category, String pagePath,
			String locale) {

		Set<String> propNames = new HashSet<String>();

		String sqlQueryString = "SELECT * FROM [amex:Page] WHERE [amex:Category] = '"
				+ category
				+ "' AND [amex:PagePath] = '"
				+ pagePath
				+ "' AND [amex:Locale] = '" + locale + "'";

		propNames.add("amex:Category");
		propNames.add("amex:PagePath");

		List<NodeRef> nodeList = contentRepoAccessDao.findFiles(propNames,
				sqlQueryString, null, null);

		if (nodeList == null) {
			log.warn("There were no JCR nodes found for category "
					+ category + " and page path " + pagePath);
		}
		if (nodeList.isEmpty()) {

			if (log.isDebugEnabled()) {
				log.warn("There were no JCR nodes found for category "
						+ category + " and page path " + pagePath);
			}

			return null;
		} else if (nodeList.size() > 1) {
			// TODO: Throw an exception
			if (log.isDebugEnabled()) {
				log.warn("There was more than one JCR node found for the category "
						+ category + " and page path " + pagePath);
			}

			throw new IllegalStateException(
					"There was more than one JCR node found for the category "
							+ category + " and page path " + pagePath);
		}

		return nodeList.get(0);
	}

	public List<NodeRef> findPageNodeRefs(String locale) {
		return findPageNodeRefs(locale, null);
	}

	public List<NodeRef> findPageNodeRefs(String locale, String orderByClause) {
		Set<String> propNames = new HashSet<String>();
		propNames.add("amex:Category");
		propNames.add("amex:PagePath");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT * FROM [amex:Page] WHERE [amex:Locale] = '")
				.append(locale).append("'");

		if (StringUtils.isNotEmpty(orderByClause)) {
			stringBuilder.append(" ORDER BY ").append(orderByClause);
		}

		String sql = stringBuilder.toString();
		List<NodeRef> nodeList = contentRepoAccessDao.findFiles(propNames, sql,
				null, null);

		if (nodeList == null) {
			log.warn("There were no JCR nodes found for locale " + locale);
			return null;
		}
		
		if (nodeList.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.warn("There were no JCR nodes found for locale " + locale);
			}

			return null;
		}

		return nodeList;
	}


	public NodeRef findTileNodeRef(String cmsId, String locale) {
		String sql = "SELECT * FROM [amex:Tile] WHERE [amex:CMS_ID] = '"
				+ cmsId + "' AND [amex:Locale] = '" + locale + "'";

		List<NodeRef> nodeList = contentRepoAccessDao.findFiles(null, sql,
				null, null);

		if (nodeList == null || nodeList.isEmpty()) {
			log.warn("There were no JCR tile nodes found!");
			return null;
		}

		if (nodeList.size() > 1) {
			log.warn("There was more than one TILE node found in the JCR!");
		}

		return nodeList.get(0);
	}

	public void clearRuntimeContentCache() {
		runtimeContentCache.clear();
	}

	public void clearRuntimePageCache() {
		runtimePageCache.clear();
	}

	public void removeItemFromRuntimeContentCache(String cacheKey) {
		runtimeContentCache.remove(cacheKey);
	}

	public void removeItemFromRuntimePageCache(String cacheKey) {
		runtimeContentCache.remove(cacheKey);
	}

	public void clearAll() {

		contentRepoAccessDao.deleteFolder("/xml");
	}

	public void listFolder(String folderPath) {

		try {
			contentRepoAccessDao.listFolder(folderPath, log, "amex:");
		} catch (RepositoryException repositoryException) {
			log.error(repositoryException);
		}
	}
  
	public List<ContentItem> retrievePages(String localeString) {
		return retrieveOrderedPages(localeString, null);
	}

	@SuppressWarnings("unchecked")
	public List<ContentItem> retrieveOrderedPages(String localeString,
			String orderByClause) {
		List<ContentItem> pages = null;

		// Lookup in cache
		String pageCacheKey = "pages_" + localeString;
		pages = (List<ContentItem>) runtimePageCache.retrieve(pageCacheKey);

		if (pages == null) {
			pages = new ArrayList<ContentItem>();
			long timestamp = System.currentTimeMillis();
			List<NodeRef> nodeRefs = findPageNodeRefs(localeString,
					orderByClause);
			log.debug("findPageNodeRef: elapsed = "
					+ (System.currentTimeMillis() - timestamp));

			// Populate and cache
			timestamp = System.currentTimeMillis();

			for (NodeRef nodeRef : nodeRefs) {
				ContentItem contentItem = unmarshalContentItem(nodeRef);
				contentItem.setJcrPath(nodeRef.getPath());
				String pagePath = (String) nodeRef.getProperties().get(
						"amex:PagePath");
				contentItem.setPagePath(pagePath);

				// Process Page data to verify/populate pageName and
				// pageParentName
				Page page = (Page) contentItem.getContent();

				if (page.getPageName() == null) {
					String pageName = obtainPageNameFromPageJCRObject(page,
							nodeRef);
					page.setPageName(pageName);
				}

				pages.add(contentItem);
			}

			runtimePageCache.store(pageCacheKey, pages);
			log.debug("Page Unmarshal: elapsed = "
					+ (System.currentTimeMillis() - timestamp));
		}

		return pages;
	}

	public List<ContentItem> retrievePages(String localeString, Integer limit) {
		return retrievePublishedItems(localeString, limit, null, "", "",
				ContentRepoAccessDao.REPO_AMEX_PAGE_TYPE);
	}

	public List<ContentItem> retrievePages(String localeString, String topic,
			String program) {
		Map<String, String> whereClauseMap = new LinkedHashMap<String, String>();

		if (StringUtils.isNotEmpty(topic)) {
			whereClauseMap.put("[amex:TopicTaxonomy]", topic);
		}

		if (StringUtils.isNotEmpty(program)) {
			whereClauseMap.put("[amex:ProgramTaxonomy]", program);
		}

		StringBuilder stringBuilder = new StringBuilder();
		int counter = 1;

		if (!whereClauseMap.isEmpty()) {
			for (Map.Entry<String, String> entry : whereClauseMap.entrySet()) {
				if (StringUtils.isNotEmpty(entry.getValue())) {
					stringBuilder.append("(").append(entry.getKey())
							.append(" = '").append(entry.getValue())
							.append("')");
				}

				if (counter < whereClauseMap.size()) {
					stringBuilder.append(" AND ");
				}

				counter++;
			}
		}

		String whereClause = stringBuilder.toString();
		return retrievePublishedItems(localeString, null, null, whereClause,
				null, ContentRepoAccessDao.REPO_AMEX_PAGE_TYPE);
	}

	/*public TagContainerItem retrieveTagItem(String tagName) {
		TagContainerItem tagItem = null;
		String jcrPath = "/xml/" + "en_US"
				+ "/tagging/coremetrics-tag-mapping.xml";
		String pageCacheKey = jcrPath;
		// Lookup in cache
		TagContainer tagContainer = (TagContainer) runtimePageCache
				.retrieve(pageCacheKey);

		if (tagContainer == null) {
			long timestamp = System.currentTimeMillis();
			NodeRef nodeRef = contentRepoAccessDao.getFile(jcrPath);
			log.debug("navigationNodeRef: elapsed = "
					+ (System.currentTimeMillis() - timestamp));
			// Populate and cache
			timestamp = System.currentTimeMillis();
			if (nodeRef != null) {
				tagContainer = unmarshalTagContainer(nodeRef);
				runtimePageCache.store(pageCacheKey, tagContainer);
				log.debug("Navigation Unmarshal: elapsed = "
						+ (System.currentTimeMillis() - timestamp));
				if (this.tagItemMap == null && tagContainer != null) {
					tagItemMap = new HashMap<String, TagContainerItem>();
					String key = "";
					pdfDownloadList = new ArrayList<TagContainerItem>();
					for (TagContainerItem item : tagContainer.getTagItemList()) {
						if (item != null) {
							if (item.getTagType() != null
									&& item.getTagType().equalsIgnoreCase(
											"Dart")) {
								key = item.getTagType() + item.getCmsPath()
										+ item.getType();
							} else {
								key = item.getTagType() + item.getCmsPath();
							}
							tagItemMap.put(key, item);
							if (item.getCmsPath() != null
									&& item.getCmsPath().endsWith(".pdf")) {
								pdfDownloadList.add(item);
							}
						}
					}
				}
			}
		}
		if (tagItemMap != null) {
			tagItem = tagItemMap.get(tagName);
		}

		return tagItem;
	}*/

/*	private TagContainer unmarshalTagContainer(NodeRef nodeRef) {
		if (nodeRef == null) {
			return null;
		}

		TagContainer jcrObject = null;
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				nodeRef.getContent());
		StreamSource streamSource = new StreamSource(byteArrayInputStream);

		try {
			jcrObject = (TagContainer) this.tagContainerUnmarshaller
					.unmarshal(streamSource);
		} catch (XmlMappingException xmlMappingException) {
			log.error(xmlMappingException);
		} catch (IOException ioException) {
			log.error(ioException);
		}

		return jcrObject;
	}
*/
/*	private void updateTagContainer(String pageCacheKey, NodeRef nodeRef) {
		if (pageCacheKey != null
				&& pageCacheKey.contains("coremetrics-tag-mapping")) {
			TagContainer tagContainer = unmarshalTagContainer(nodeRef);

			if (tagContainer != null) {
				runtimePageCache.store(pageCacheKey, tagContainer);

				if (this.tagItemMap == null) {
					tagItemMap = new HashMap<String, TagContainerItem>();
				}
				tagItemMap.clear();
				if (this.pdfDownloadList == null) {
					pdfDownloadList = new ArrayList<TagContainerItem>();
				}
				pdfDownloadList.clear();

				String key = "";
				for (TagContainerItem item : tagContainer.getTagItemList()) {
					if (item != null) {
						if (item.getTagType() != null
								&& item.getTagType().equalsIgnoreCase("Dart")) {
							key = item.getTagType() + item.getCmsPath()
									+ item.getType();
						} else {
							key = item.getTagType() + item.getCmsPath();
						}
						tagItemMap.put(key, item);
						if (item.getCmsPath() != null
								&& item.getCmsPath().endsWith(".pdf")) {
							pdfDownloadList.add(item);
						}
					}
				}
			}
		}
	}*/
	


	public List<ContentItem> retrieveReferencedPages(ContentItem cItem){
		List<ContentItem> contentItemList=new ArrayList<ContentItem>();
		if(cItem==null){
			return contentItemList;
		}
		CreditPage page=(CreditPage) cItem.getContent();
		
		return contentItemList;
	} 
	
	
	public List<ContentItem> retrieveContentItemByCmsId(List<String> cmsIdList){
		List<ContentItem> contentItemList=new ArrayList<ContentItem>();
		if(cmsIdList==null){
			return contentItemList;
		}
		
		for(String cmsId:cmsIdList){
			contentItemList.add(retrieveContentItemByCmsId(cmsId));
		}
		return contentItemList;
	}
 
	
}
