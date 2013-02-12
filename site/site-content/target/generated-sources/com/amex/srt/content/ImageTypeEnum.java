
package com.amex.srt.content;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.amex.com/srt/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="imageTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="image-type-87x87"/>
 *     &lt;xs:enumeration value="image-type-111x167"/>
 *     &lt;xs:enumeration value="image-type-127x87"/>
 *     &lt;xs:enumeration value="image-type-139x139"/>
 *     &lt;xs:enumeration value="image-type-155x73"/>
 *     &lt;xs:enumeration value="image-type-195x"/>
 *     &lt;xs:enumeration value="image-type-242x167"/>
 *     &lt;xs:enumeration value="image-type-266x126"/>
 *     &lt;xs:enumeration value="image-type-440x248"/>
 *     &lt;xs:enumeration value="image-type-480x353"/>
 *     &lt;xs:enumeration value="image-type-878x494"/>
 *     &lt;xs:enumeration value="image-type-180x90"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum ImageTypeEnum {
    IMAGETYPE87X87("image-type-87x87"), IMAGETYPE111X167("image-type-111x167"), IMAGETYPE127X87(
            "image-type-127x87"), IMAGETYPE139X139("image-type-139x139"), IMAGETYPE155X73(
            "image-type-155x73"), IMAGETYPE195X("image-type-195x"), IMAGETYPE242X167(
            "image-type-242x167"), IMAGETYPE266X126("image-type-266x126"), IMAGETYPE440X248(
            "image-type-440x248"), IMAGETYPE480X353("image-type-480x353"), IMAGETYPE878X494(
            "image-type-878x494"), IMAGETYPE180X90("image-type-180x90");
    private final String value;

    private ImageTypeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static ImageTypeEnum convert(String value) {
        for (ImageTypeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
