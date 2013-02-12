
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="contentTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="page"/>
 *     &lt;xs:enumeration value="tiles"/>
 *     &lt;xs:enumeration value="link"/>
 *     &lt;xs:enumeration value="navigation"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum ContentTypeEnum {
    PAGE("page"), TILES("tiles"), LINK("link"), NAVIGATION("navigation");
    private final String value;

    private ContentTypeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static ContentTypeEnum convert(String value) {
        for (ContentTypeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
