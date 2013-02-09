package com.bac.oee.assembler;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.DocumentTile;
import com.bac.oee.content.Image;
import com.bac.oee.content.ImageTextTile;
import com.bac.oee.content.ImageTile;
import com.bac.oee.content.SeeItInActionTile;
import com.bac.oee.content.TextTile;
import com.bac.oee.content.TileTypeEnum;
import com.bac.oee.content.VideoTile;
import com.bac.oee.model.ui.content.PageUIData;
import com.bac.oee.model.ui.content.common.TargetUIEnum;
import com.bac.oee.model.ui.content.common.TileTypeUIEnum;
import com.bac.oee.model.ui.content.tiles.ImageTextUITile;
import com.bac.oee.model.ui.content.tiles.ImageUITile;
import com.bac.oee.model.ui.content.tiles.SeeItInActionUITile;
import com.bac.oee.model.ui.content.tiles.TextUITile;
import com.bac.oee.model.ui.content.tiles.UITile;
import com.bac.oee.model.ui.content.tiles.VideoUITile;
import com.sun.xml.txw2.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class TileUIDataAssembler.
 */
public class UITileAssembler implements Assembler<ContentItem, UITile> {

	/**
	 * Instantiates a new tile ui data assembler.
	 */
	public UITileAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public UITile assemble(ContentItem objectToAssemble) {
		UITile uiTile = null;
		if (objectToAssemble.getContent() != null) {
			if (objectToAssemble.getContent() instanceof VideoTile) {
				uiTile = assembleVideoTile(objectToAssemble);
			} else if (objectToAssemble.getContent() instanceof ImageTile) {
				uiTile = assembleImageTile(objectToAssemble);
			} else if (objectToAssemble.getContent() instanceof ImageTextTile) {
				uiTile = assembleImageTextTile(objectToAssemble);
			} else if (objectToAssemble.getContent() instanceof DocumentTile) {
				uiTile = assembleDocumentTile(objectToAssemble);
			} else if (objectToAssemble.getContent() instanceof TextTile) {
				uiTile = assembleTextTile(objectToAssemble);
			} else if (objectToAssemble.getContent() instanceof SeeItInActionTile) {
				uiTile = assembleSeeItInActionTile(objectToAssemble);
			}

		}

		return uiTile;
	}

	/**
	 * Assemble see it in action tile.
	 *
	 * @param objectToAssemble the object to assemble
	 * @return the uI tile
	 */
	private UITile assembleSeeItInActionTile(ContentItem objectToAssemble) {

		UITile uiTile = null;
		if (objectToAssemble.getContent() != null) {
			if (objectToAssemble.getContent() instanceof SeeItInActionTile) {
				SeeItInActionTile seeItInActionTile = (SeeItInActionTile) objectToAssemble
						.getContent();
				SeeItInActionUITile seeItInActionUITile = new SeeItInActionUITile();
				// videoUITile = setHeaders(objectToAssemble, videoUITile);
				seeItInActionUITile.setText(seeItInActionTile.getText());
				seeItInActionUITile.setCaption(seeItInActionTile.getCaption());
				seeItInActionUITile.setName(seeItInActionTile.getName());
				seeItInActionUITile
						.setLocation(seeItInActionTile.getLocation());
				seeItInActionUITile.setLogo(new UITileAssembler()
						.assembleImageUITile(seeItInActionTile.getLogo()));
				seeItInActionUITile.setType(TileTypeUIEnum.SEEITINACTIONTILE);
				seeItInActionUITile.setHeadline(seeItInActionTile.getHeadline());
				seeItInActionUITile.setPrimaryHeadline(seeItInActionTile.getPrimaryHeadline());
				seeItInActionUITile.setShortHeadline(seeItInActionTile.getShortHeadline());
				uiTile = (UITile) seeItInActionUITile;
				assembleParentParameters(uiTile, objectToAssemble);
			}
		}
		return uiTile;
	}

	/**
	 * Assemble text tile.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * @return the uI tile
	 */
	private UITile assembleTextTile(ContentItem objectToAssemble) {
		return null;
	}

	/**
	 * Assemble text tile.
	 *
	 * @param textTile the text tile
	 * @return the text ui tile
	 */
	private TextUITile assembleTextTile(TextTile textTile) {
		// TODO Auto-generated method stub
		TextUITile textUITile = new TextUITile();
		textUITile.setParagraph(textTile.getParagraph());
		textUITile.setHeading(textTile.getHeading());
		return textUITile;
	}

	/**
	 * Assemble document tile.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * @return the uI tile
	 */
	private UITile assembleDocumentTile(ContentItem objectToAssemble) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Assemble image text tile.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * @return the uI tile
	 */
	private UITile assembleImageTextTile(ContentItem objectToAssemble) {
		UITile uiTile = null;
		if (objectToAssemble.getContent() != null) {
			if (objectToAssemble.getContent() instanceof ImageTextTile) {
				ImageTextTile imageTextTile = (ImageTextTile) objectToAssemble
						.getContent();

				ImageTextUITile imageTextUITile = new ImageTextUITile();
				imageTextUITile.setType(TileTypeUIEnum.IMAGETEXTTILE);

				if( imageTextTile.getImages() != null && imageTextTile.getImages().getImageList() != null){
					for (Image image : imageTextTile.getImages().getImageList()) {
						ImageUITile imageUITile = assembleImageUITile(image);
						imageTextUITile.getImageList().add(imageUITile);
						if (imageTextTile.getTextTile() != null) {
							imageTextUITile
									.setTextTile(assembleTextTile(imageTextTile
											.getTextTile()));
						}
					}
				}
				uiTile = (UITile) imageTextUITile;
				assembleParentParameters(uiTile, objectToAssemble);
			}
		}

		return uiTile;
	}

	/**
	 * Assemble image ui tile.
	 *
	 * @param image the image
	 * @return the image ui tile
	 */
	public ImageUITile assembleImageUITile(Image image) {
		if( image == null){
			return null;
		}
		ImageUITile imageUITile = new ImageUITile();
		if (image.getLink() != null) {
			imageUITile.setLinkText(image.getLink().getLinkText());
			imageUITile.setLinkUrl(image.getLink().getLinkUrl());
			imageUITile.setTarget(TargetUIEnum.convert(image.getLink()
					.getLinkTarget().toString()));
			imageUITile.setScreenReaderText(image.getLink()
					.getScreenReaderText());
		}

		imageUITile.setCaption(image.getAltText());
		imageUITile.setPath(image.getPath());
		imageUITile.setAltText(image.getAltText());
		imageUITile.setType(TileTypeUIEnum.IMAGETILE);

		return imageUITile;
	}

	/**
	 * Assemble parent parameters.
	 * 
	 * @param uiTile
	 *            the ui tile
	 * @param objectToAssemble
	 *            the object to assemble
	 * @return the uI tile
	 */
	public UITile assembleParentParameters(UITile uiTile,
			ContentItem objectToAssemble) {
		uiTile.setCmsId(objectToAssemble.getCmsId());
		uiTile.setContentType(objectToAssemble.getContentType());
		uiTile.setId(objectToAssemble.getId());
		uiTile.setJcrPath(objectToAssemble.getJcrPath());
		uiTile.setMetadata(objectToAssemble.getMetadata());
		uiTile.setPagePath(objectToAssemble.getPagePath());
		uiTile.setTemplate(objectToAssemble.getTemplate());
		return uiTile;
	}

	/**
	 * Imageileassemble.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * @return the uI tile
	 */
	public UITile assembleImageTile(ContentItem objectToAssemble) {
		UITile uiTile = null;
		if (objectToAssemble.getContent() != null) {
			if (objectToAssemble.getContent() instanceof ImageTile) {
				ImageTile imageTile = (ImageTile) objectToAssemble.getContent();

				ImageUITile imageUITile = assembleImageUITile(imageTile
						.getImage());
				imageUITile.setType(TileTypeUIEnum.IMAGETILE);

				imageUITile.setHeadline(imageTile.getHeadline());
				imageUITile.setPrimaryHeadline(imageTile.getPrimaryHeadline());
				imageUITile.setShortHeadline(imageTile.getShortHeadline());
				
				uiTile = (UITile) imageUITile;
				assembleParentParameters(uiTile, objectToAssemble);

			}
		}

		return uiTile;
	}

	/**
	 * Video tileassemble.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * @return the uI tile
	 */
	public UITile assembleVideoTile(ContentItem objectToAssemble) {
		UITile uiTile = null;
		if (objectToAssemble.getContent() != null) {
			if (objectToAssemble.getContent() instanceof VideoTile) {
				VideoTile videoTile = (VideoTile) objectToAssemble.getContent();
				VideoUITile videoUITile = new VideoUITile();
				// videoUITile = setHeaders(objectToAssemble, videoUITile);
				// videoUITile.setVideoSize(videoSize)
				videoUITile.setBrightcoveId(videoTile.getBrightcoveId());
				videoUITile.setRelatedVideoIdList(videoTile
						.getRelatedVideoContainer().getVideoIdList());
				// videoUITile.setRelatedPageList(relatedContentItemList)
				videoUITile.setType(TileTypeUIEnum.VIDEOTILE);
				videoUITile.setHeadline(videoTile.getHeadline1());
				videoUITile.setPrimaryHeadline(videoTile.getPrimaryHeadline());
				videoUITile.setShortHeadline(videoTile.getShortHeadline());
				videoUITile.setTranscript( videoTile.getTranscript());
				
				// add tiles
				if( videoTile.getImages().getImageList() != null ){
					for( Image image : videoTile.getImages().getImageList()){
						videoUITile.getImageList().add(assembleImageUITile(image));
					}
				}
					
				uiTile = (UITile) videoUITile;
				uiTile = assembleParentParameters(uiTile, objectToAssemble);
			
			}
		}

		return uiTile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public ContentItem disAssemble(UITile objectToAssemble) {
		// TODO Auto-generated method stub
		return null;
	}

}
