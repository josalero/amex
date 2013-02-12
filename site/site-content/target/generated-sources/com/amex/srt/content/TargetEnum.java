
package com.amex.srt.content;

/** 
 * Enumeration that defines link target types
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="targetEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="_top"/>
 *     &lt;xs:enumeration value="_self"/>
 *     &lt;xs:enumeration value="_blank"/>
 *     &lt;xs:enumeration value="_new"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum TargetEnum {
    _TOP("_top"), _SELF("_self"), _BLANK("_blank"), _NEW("_new");
    private final String value;

    private TargetEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static TargetEnum convert(String value) {
        for (TargetEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
