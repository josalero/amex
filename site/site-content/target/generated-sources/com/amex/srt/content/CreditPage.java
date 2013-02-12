
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="creditPage">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns:page">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:table" name="table" minOccurs="0" maxOccurs="1"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class CreditPage extends Page
{
    private Table table;

    /** 
     * Get the 'table' element value.
     * 
     * @return value
     */
    public Table getTable() {
        return table;
    }

    /** 
     * Set the 'table' element value.
     * 
     * @param table
     */
    public void setTable(Table table) {
        this.table = table;
    }
}
