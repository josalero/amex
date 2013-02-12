
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="hrefLink">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="link-text" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="link-url" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:targetEnum" name="link-target" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="screen-reader-text" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class HrefLink
{
    private String linkText;
    private String linkUrl;
    private TargetEnum linkTarget;
    private String screenReaderText;

    /** 
     * Get the 'link-text' element value.
     * 
     * @return value
     */
    public String getLinkText() {
        return linkText;
    }

    /** 
     * Set the 'link-text' element value.
     * 
     * @param linkText
     */
    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    /** 
     * Get the 'link-url' element value.
     * 
     * @return value
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /** 
     * Set the 'link-url' element value.
     * 
     * @param linkUrl
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /** 
     * Get the 'link-target' element value.
     * 
     * @return value
     */
    public TargetEnum getLinkTarget() {
        return linkTarget;
    }

    /** 
     * Set the 'link-target' element value.
     * 
     * @param linkTarget
     */
    public void setLinkTarget(TargetEnum linkTarget) {
        this.linkTarget = linkTarget;
    }

    /** 
     * Get the 'screen-reader-text' element value.
     * 
     * @return value
     */
    public String getScreenReaderText() {
        return screenReaderText;
    }

    /** 
     * Set the 'screen-reader-text' element value.
     * 
     * @param screenReaderText
     */
    public void setScreenReaderText(String screenReaderText) {
        this.screenReaderText = screenReaderText;
    }
}
