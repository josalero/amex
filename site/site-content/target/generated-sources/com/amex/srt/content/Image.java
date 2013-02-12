
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="image">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="path" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="alt-text" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="caption" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:imageTypeEnum" name="image-type" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:hrefLink" name="link" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:targetEnum" name="target" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Image
{
    private String path;
    private String altText;
    private String caption;
    private ImageTypeEnum imageType;
    private HrefLink link;
    private TargetEnum target;

    /** 
     * Get the 'path' element value.
     * 
     * @return value
     */
    public String getPath() {
        return path;
    }

    /** 
     * Set the 'path' element value.
     * 
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /** 
     * Get the 'alt-text' element value.
     * 
     * @return value
     */
    public String getAltText() {
        return altText;
    }

    /** 
     * Set the 'alt-text' element value.
     * 
     * @param altText
     */
    public void setAltText(String altText) {
        this.altText = altText;
    }

    /** 
     * Get the 'caption' element value.
     * 
     * @return value
     */
    public String getCaption() {
        return caption;
    }

    /** 
     * Set the 'caption' element value.
     * 
     * @param caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /** 
     * Get the 'image-type' element value.
     * 
     * @return value
     */
    public ImageTypeEnum getImageType() {
        return imageType;
    }

    /** 
     * Set the 'image-type' element value.
     * 
     * @param imageType
     */
    public void setImageType(ImageTypeEnum imageType) {
        this.imageType = imageType;
    }

    /** 
     * Get the 'link' element value.
     * 
     * @return value
     */
    public HrefLink getLink() {
        return link;
    }

    /** 
     * Set the 'link' element value.
     * 
     * @param link
     */
    public void setLink(HrefLink link) {
        this.link = link;
    }

    /** 
     * Get the 'target' element value.
     * 
     * @return value
     */
    public TargetEnum getTarget() {
        return target;
    }

    /** 
     * Set the 'target' element value.
     * 
     * @param target
     */
    public void setTarget(TargetEnum target) {
        this.target = target;
    }
}
