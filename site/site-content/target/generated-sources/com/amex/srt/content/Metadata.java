
package com.amex.srt.content;

import java.util.Date;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="metadata">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="htmltitle" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="ada-friendly-name" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="canonical-url" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="metakeywords" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="metadescription" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:dateTime" name="authored-date" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:dateTime" name="published-date" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:dateTime" name="expiration-date" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="geo-taxonomy" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Metadata
{
    private String htmltitle;
    private String adaFriendlyName;
    private String canonicalUrl;
    private String metakeywords;
    private String metadescription;
    private Date authoredDate;
    private Date publishedDate;
    private Date expirationDate;
    private String geoTaxonomy;

    /** 
     * Get the 'htmltitle' element value.
     * 
     * @return value
     */
    public String getHtmltitle() {
        return htmltitle;
    }

    /** 
     * Set the 'htmltitle' element value.
     * 
     * @param htmltitle
     */
    public void setHtmltitle(String htmltitle) {
        this.htmltitle = htmltitle;
    }

    /** 
     * Get the 'ada-friendly-name' element value.
     * 
     * @return value
     */
    public String getAdaFriendlyName() {
        return adaFriendlyName;
    }

    /** 
     * Set the 'ada-friendly-name' element value.
     * 
     * @param adaFriendlyName
     */
    public void setAdaFriendlyName(String adaFriendlyName) {
        this.adaFriendlyName = adaFriendlyName;
    }

    /** 
     * Get the 'canonical-url' element value.
     * 
     * @return value
     */
    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    /** 
     * Set the 'canonical-url' element value.
     * 
     * @param canonicalUrl
     */
    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    /** 
     * Get the 'metakeywords' element value. SEO Meta-Keywords to be inserted into the HEAD
    					section of the HTML document.
    				
     * 
     * @return value
     */
    public String getMetakeywords() {
        return metakeywords;
    }

    /** 
     * Set the 'metakeywords' element value. SEO Meta-Keywords to be inserted into the HEAD
    					section of the HTML document.
    				
     * 
     * @param metakeywords
     */
    public void setMetakeywords(String metakeywords) {
        this.metakeywords = metakeywords;
    }

    /** 
     * Get the 'metadescription' element value.
     * 
     * @return value
     */
    public String getMetadescription() {
        return metadescription;
    }

    /** 
     * Set the 'metadescription' element value.
     * 
     * @param metadescription
     */
    public void setMetadescription(String metadescription) {
        this.metadescription = metadescription;
    }

    /** 
     * Get the 'authored-date' element value.
     * 
     * @return value
     */
    public Date getAuthoredDate() {
        return authoredDate;
    }

    /** 
     * Set the 'authored-date' element value.
     * 
     * @param authoredDate
     */
    public void setAuthoredDate(Date authoredDate) {
        this.authoredDate = authoredDate;
    }

    /** 
     * Get the 'published-date' element value.
     * 
     * @return value
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

    /** 
     * Set the 'published-date' element value.
     * 
     * @param publishedDate
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /** 
     * Get the 'expiration-date' element value.
     * 
     * @return value
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /** 
     * Set the 'expiration-date' element value.
     * 
     * @param expirationDate
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /** 
     * Get the 'geo-taxonomy' element value.
     * 
     * @return value
     */
    public String getGeoTaxonomy() {
        return geoTaxonomy;
    }

    /** 
     * Set the 'geo-taxonomy' element value.
     * 
     * @param geoTaxonomy
     */
    public void setGeoTaxonomy(String geoTaxonomy) {
        this.geoTaxonomy = geoTaxonomy;
    }
}
