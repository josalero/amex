package com.bac.oee.model.ui.content;

import com.bac.oee.content.ContentTypeEnum;
import com.bac.oee.content.Metadata;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterContentUIData.
 */
public class MasterContentUIData {

	/** The id. */
	private String id;

	/** The cms id. */
	private String cmsId;

	/** The template. */
	private String template;

	/** The content type. */
	private ContentTypeEnum contentType;

	/** The jcr path. */
	private String jcrPath;

	/** The metadata. */
	private Metadata metadata;

	/** The page path. */
	private String pagePath;

	/**
	 * Instantiates a new master content ui data.
	 */
	public MasterContentUIData() {

	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the cms id.
	 * 
	 * @return the cms id
	 */
	public String getCmsId() {
		return cmsId;
	}

	/**
	 * Sets the cms id.
	 * 
	 * @param cmsId
	 *            the new cms id
	 */
	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}

	/**
	 * Gets the template.
	 * 
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Sets the template.
	 * 
	 * @param template
	 *            the new template
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * Gets the content type.
	 * 
	 * @return the content type
	 */
	public ContentTypeEnum getContentType() {
		return contentType;
	}

	/**
	 * Sets the content type.
	 * 
	 * @param contentType
	 *            the new content type
	 */
	public void setContentType(ContentTypeEnum contentType) {
		this.contentType = contentType;
	}

	/**
	 * Gets the jcr path.
	 * 
	 * @return the jcr path
	 */
	public String getJcrPath() {
		return jcrPath;
	}

	/**
	 * Sets the jcr path.
	 * 
	 * @param jcrPath
	 *            the new jcr path
	 */
	public void setJcrPath(String jcrPath) {
		this.jcrPath = jcrPath;
	}

	/**
	 * Gets the metadata.
	 * 
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * Sets the metadata.
	 * 
	 * @param metadata
	 *            the new metadata
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * Gets the page path.
	 * 
	 * @return the page path
	 */
	public String getPagePath() {
		return pagePath;
	}

	/**
	 * Sets the page path.
	 * 
	 * @param pagePath
	 *            the new page path
	 */
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

}
