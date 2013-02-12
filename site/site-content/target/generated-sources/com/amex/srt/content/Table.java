
package com.amex.srt.content;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="table">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:tablerow" name="row" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Table
{
    private List<Tablerow> rowList = new ArrayList<Tablerow>();

    /** 
     * Get the list of 'row' element items.
     * 
     * @return list
     */
    public List<Tablerow> getRowList() {
        return rowList;
    }

    /** 
     * Set the list of 'row' element items.
     * 
     * @param list
     */
    public void setRowList(List<Tablerow> list) {
        rowList = list;
    }
}
