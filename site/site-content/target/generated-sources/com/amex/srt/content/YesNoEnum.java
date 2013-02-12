
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="yesNoEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="yes"/>
 *     &lt;xs:enumeration value="no"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum YesNoEnum {
    YES("yes"), NO("no");
    private final String value;

    private YesNoEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static YesNoEnum convert(String value) {
        for (YesNoEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
