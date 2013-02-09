package com.bac.oee.assembler;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.Indexpage;
import com.bac.oee.content.IntroAnimation;
import com.bac.oee.content.Page;
import com.bac.oee.model.ui.content.IndexPageUIData;
import com.bac.oee.model.ui.content.IntroAnimationUIData;
import com.bac.oee.model.ui.content.PageUIData;
import com.bac.oee.model.ui.content.SegmentUIData;
import com.bac.oee.model.ui.content.common.PageTypeUIEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class PageUIDataAssembler.
 */
public class PageUIDataAssembler implements Assembler<ContentItem, PageUIData> {

	/**
	 * Instantiates a new page ui data assembler.
	 */
	public PageUIDataAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public PageUIData assemble(ContentItem objectToAssemble) {
		if (objectToAssemble == null) {
			return null;
		}
		PageUIData pageUIData = null;

		Page page = null;

		if (objectToAssemble.getContent() instanceof Indexpage) {
			pageUIData = new IndexPageUIData();
			pageUIData.setPageType(PageTypeUIEnum.INDEX);
			page = (Indexpage) objectToAssemble.getContent();

			List<IntroAnimation> introAnimationList = ((Indexpage) page)
					.getIntroAnimationList();

			if( introAnimationList != null ){
				for (IntroAnimation introAnimation : introAnimationList) {
					if (introAnimation != null) {
						IntroAnimationUIData introAnimationUIData = new IntroAnimationUIData();
	
						for (String segment : introAnimation.getSegmentList()) {
							SegmentUIData segmentUIData = new SegmentUIData();
							segmentUIData.setText(segment);
							if (introAnimationUIData.getSegmentText() == null) {
								introAnimationUIData.setSegmentText(segment);
							} else {
								introAnimationUIData
										.setSegmentText(introAnimationUIData
												.getSegmentText() + segment);
							}
	
							introAnimationUIData.getSegmentList()
									.add(segmentUIData);
						}
						if (introAnimationUIData.getSegmentText() != null) {
							String formattedString = discardWhitespace(toByteArray(introAnimationUIData
									.getSegmentText().toCharArray()));
							introAnimationUIData.setSegmentText(formattedString);
						}
						pageUIData.getIntroAnimationList()
								.add(introAnimationUIData);
					}
				}
			}

		} else if (objectToAssemble.getContent() instanceof Page) {
			pageUIData = new PageUIData();
			pageUIData.setPageType(PageTypeUIEnum.PAGE);
			page = (Page) objectToAssemble.getContent();
		}

		pageUIData.setName(page.getPageName());
		pageUIData.setParentName(page.getPageParentName());

		pageUIData.setCmsId(objectToAssemble.getCmsId());
		pageUIData.setContentType(objectToAssemble.getContentType());
		pageUIData.setId(objectToAssemble.getId());
		pageUIData.setJcrPath(objectToAssemble.getJcrPath());
		pageUIData.setMetadata(objectToAssemble.getMetadata());
		pageUIData.setPagePath(objectToAssemble.getPagePath());
		pageUIData.setTemplate(objectToAssemble.getTemplate());

		return pageUIData;
	}

	/**
	 * To byte array.
	 * 
	 * @param array
	 *            the array
	 * @return the byte[]
	 */
	public static byte[] toByteArray(char[] array) {
		return toByteArray(array, Charset.defaultCharset());
	}

	/**
	 * To byte array.
	 * 
	 * @param array
	 *            the array
	 * @param charset
	 *            the charset
	 * @return the byte[]
	 */
	public static byte[] toByteArray(char[] array, Charset charset) {
		CharBuffer cbuf = CharBuffer.wrap(array);
		ByteBuffer bbuf = charset.encode(cbuf);
		return bbuf.array();
	}

	/**
	 * Discard whitespace.
	 * 
	 * @param data
	 *            the data
	 * @return the string
	 */
	static String discardWhitespace(byte[] data) {
		byte groomedData[] = new byte[data.length];
		int bytesCopied = 0;

		for (int i = 0; i < data.length; i++) {
			switch (data[i]) {
			case (byte) '\n':
			case (byte) '\t':
			case (byte) '\r':
				break;
			default:
				groomedData[bytesCopied++] = data[i];
			}
		}

		byte packedData[] = new byte[bytesCopied];

		System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);

		return new String(packedData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public ContentItem disAssemble(PageUIData objectToAssemble) {
		// TODO Auto-generated method stub
		return null;
	}

}
