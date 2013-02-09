/**
 * 
 */
package com.bac.oee.model;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.bac.oee.content.ContentItem;
import com.bac.oee.model.level.PageBaseLevel;
import com.bac.oee.struts.ViewConstants;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Page objects.
 * 
 * @author Jose Aleman
 */
@Component
public class PageFactory implements InitializingBean {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageFactory.class);

	/** The levels map. */
	@Resource
	private Map<String, PageBaseLevel> levelsMap;

	/**
	 * Gets the page.
	 * 
	 * @param contentItem
	 *            the content item
	 * @param locale
	 *            the locale
	 * @param request
	 *            the request
	 * @return the page
	 */
	public synchronized PageBaseLevel getPage(ContentItem contentItem,
			String locale, HttpServletRequest request) {

		if (contentItem == null) {
			return null;
		}

		PageBaseLevel page = (PageBaseLevel) request
				.getAttribute(ViewConstants.PAGE_LEVEL);

		if (page == null) {

			String template = contentItem.getTemplate().toLowerCase();
			log.info("Template: " + template);

			PageBaseLevel staticPageBaseLevel = levelsMap.get(template);

			if (staticPageBaseLevel != null) {

				Class<? extends PageBaseLevel> pageLevelClass = staticPageBaseLevel
						.getClass();

				try {

					log.debug("Instantiating new object for "
							+ pageLevelClass.getName() + ", template ["
							+ template + "]");

					page = (PageBaseLevel) pageLevelClass.newInstance();

					page.setFrom(staticPageBaseLevel);

					page.setId(contentItem.getTemplate());
					page.setContentItem(contentItem);

					page.setLocale(locale);

					page.init(request);
					// NOT GOOD: page.setServletRequest(request);
				} catch (Exception exception) {

					log.error("Failed to instantiate PageLevel object for "
							+ pageLevelClass.getName(), exception);
				}
			}
		}

		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		levelsMap = Collections.unmodifiableMap(levelsMap);
	}
}
