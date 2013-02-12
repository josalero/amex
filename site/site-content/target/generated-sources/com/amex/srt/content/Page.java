
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="page">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns:contentBase">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:string" name="page-name" minOccurs="0" maxOccurs="1"/>
 *         &lt;xs:element type="xs:string" name="page-description" minOccurs="0" maxOccurs="1"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Page extends ContentBase
{
    private String pageName;
    private String pageDescription;

    /** 
     * Get the 'page-name' element value.
     * 
     * @return value
     */
    public String getPageName() {
        return pageName;
    }

    /** 
     * Set the 'page-name' element value.
     * 
     * @param pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /** 
     * Get the 'page-description' element value.
     * 
     * @return value
     */
    public String getPageDescription() {
        return pageDescription;
    }

    /** 
     * Set the 'page-description' element value.
     * 
     * @param pageDescription
     */
    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }
}
