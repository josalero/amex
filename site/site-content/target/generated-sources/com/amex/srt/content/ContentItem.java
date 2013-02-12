
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="contentItem">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="id"/>
 *     &lt;xs:element type="xs:string" name="cms-id"/>
 *     &lt;xs:element type="xs:string" name="template" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:contentTypeEnum" name="content-type" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="jcr-path" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="url" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns:metadata" name="metadata" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="page-path" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element ref="ns:content"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ContentItem
{
    private String id;
    private String cmsId;
    private String template;
    private ContentTypeEnum contentType;
    private String jcrPath;
    private String url;
    private Metadata metadata;
    private String pagePath;
    private ContentBase content;

    /** 
     * Get the 'id' element value.
     * 
     * @return value
     */
    public String getId() {
        return id;
    }

    /** 
     * Set the 'id' element value.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /** 
     * Get the 'cms-id' element value.
     * 
     * @return value
     */
    public String getCmsId() {
        return cmsId;
    }

    /** 
     * Set the 'cms-id' element value.
     * 
     * @param cmsId
     */
    public void setCmsId(String cmsId) {
        this.cmsId = cmsId;
    }

    /** 
     * Get the 'template' element value.
     * 
     * @return value
     */
    public String getTemplate() {
        return template;
    }

    /** 
     * Set the 'template' element value.
     * 
     * @param template
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /** 
     * Get the 'content-type' element value.
     * 
     * @return value
     */
    public ContentTypeEnum getContentType() {
        return contentType;
    }

    /** 
     * Set the 'content-type' element value.
     * 
     * @param contentType
     */
    public void setContentType(ContentTypeEnum contentType) {
        this.contentType = contentType;
    }

    /** 
     * Get the 'jcr-path' element value.
     * 
     * @return value
     */
    public String getJcrPath() {
        return jcrPath;
    }

    /** 
     * Set the 'jcr-path' element value.
     * 
     * @param jcrPath
     */
    public void setJcrPath(String jcrPath) {
        this.jcrPath = jcrPath;
    }

    /** 
     * Get the 'url' element value.
     * 
     * @return value
     */
    public String getUrl() {
        return url;
    }

    /** 
     * Set the 'url' element value.
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 
     * Get the 'metadata' element value.
     * 
     * @return value
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /** 
     * Set the 'metadata' element value.
     * 
     * @param metadata
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /** 
     * Get the 'page-path' element value.
     * 
     * @return value
     */
    public String getPagePath() {
        return pagePath;
    }

    /** 
     * Set the 'page-path' element value.
     * 
     * @param pagePath
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /** 
     * Get the 'content' element value.
     * 
     * @return value
     */
    public ContentBase getContent() {
        return content;
    }

    /** 
     * Set the 'content' element value.
     * 
     * @param content
     */
    public void setContent(ContentBase content) {
        this.content = content;
    }
}
