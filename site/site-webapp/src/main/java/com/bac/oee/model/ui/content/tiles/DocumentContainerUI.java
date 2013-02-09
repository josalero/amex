package com.bac.oee.model.ui.content.tiles;

// TODO: Auto-generated Javadoc
/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.bac.com/oee/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="documentContainer">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="doc-id" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="doc-name" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="doc-path" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="doc-type" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="doc-size" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="doc-description" minOccurs="1" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DocumentContainerUI {

	/** The doc id. */
	private String docId;

	/** The doc name. */
	private String docName;

	/** The doc path. */
	private String docPath;

	/** The doc type. */
	private String docType;

	/** The doc size. */
	private String docSize;

	/** The doc description. */
	private String docDescription;

	/**
	 * Get the 'doc-id' element value.
	 * 
	 * @return value
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * Set the 'doc-id' element value.
	 * 
	 * @param docId
	 *            the new doc id
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

	/**
	 * Get the 'doc-name' element value.
	 * 
	 * @return value
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * Set the 'doc-name' element value.
	 * 
	 * @param docName
	 *            the new doc name
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * Get the 'doc-path' element value.
	 * 
	 * @return value
	 */
	public String getDocPath() {
		return docPath;
	}

	/**
	 * Set the 'doc-path' element value.
	 * 
	 * @param docPath
	 *            the new doc path
	 */
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	/**
	 * Get the 'doc-type' element value.
	 * 
	 * @return value
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * Set the 'doc-type' element value.
	 * 
	 * @param docType
	 *            the new doc type
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * Get the 'doc-size' element value.
	 * 
	 * @return value
	 */
	public String getDocSize() {
		return docSize;
	}

	/**
	 * Set the 'doc-size' element value.
	 * 
	 * @param docSize
	 *            the new doc size
	 */
	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	/**
	 * Get the 'doc-description' element value.
	 * 
	 * @return value
	 */
	public String getDocDescription() {
		return docDescription;
	}

	/**
	 * Set the 'doc-description' element value.
	 * 
	 * @param docDescription
	 *            the new doc description
	 */
	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}
}
