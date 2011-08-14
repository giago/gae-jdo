package com.giago.appengine.commons.util;

public class ImageUtil {
	
	public class Rect {
		public int x;
		public int y;
		public int width;
		public int height;
		public Rect(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}
	
    public Rect computeCroppingBox(int maxSize, int imageWidth, int imageHeight) {
        if(imageWidth <= maxSize && imageHeight <= maxSize) {
        	return new Rect(0, 0, imageWidth, imageHeight);
        }
        int scaledImageWidth = imageWidth;
        int scaledImageHeight = imageHeight;
        int offsetX = 0;
        int offsetY = 0;
        if(imageWidth > imageHeight) {
        	scaledImageWidth = Math.round((((float)imageWidth)/((float)imageHeight))*((float)maxSize));
        	scaledImageHeight = maxSize;
        	offsetX = Math.round((scaledImageWidth-maxSize)/2);
        } else if (imageWidth == imageHeight) {
        	scaledImageWidth = maxSize;
        	scaledImageHeight = maxSize;	
        } else if(imageWidth < imageHeight) {
        	scaledImageWidth = maxSize;
        	scaledImageHeight = Math.round((((float)imageHeight)/((float)imageWidth))*((float)maxSize));
        	offsetY = (scaledImageHeight-maxSize)/2;
        }
        return new Rect(offsetX, offsetY, scaledImageWidth, scaledImageHeight);
    }

}
