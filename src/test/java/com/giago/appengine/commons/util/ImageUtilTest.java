package com.giago.appengine.commons.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.giago.appengine.commons.util.ImageUtil.Rect;

public class ImageUtilTest {
	
	@Test
	public void shouldCropImageSmallerImage() {
		Rect r = new ImageUtil().computeCroppingBox(100, 78, 45);
		assertEquals(r.x, 0);
		assertEquals(r.y, 0);
		assertEquals(r.width, 78);
		assertEquals(r.height, 45);
	}
	
	@Test
	public void shouldCropImage() {
		Rect r = new ImageUtil().computeCroppingBox(100, 100, 100);
		assertEquals(r.x, 0);
		assertEquals(r.y, 0);
		assertEquals(r.width, 100);
		assertEquals(r.height, 100);
	}
	
	@Test
	public void shouldCropImageExactDimension() {
		Rect r = new ImageUtil().computeCroppingBox(50, 100, 100);
		assertEquals(r.x, 0);
		assertEquals(r.y, 0);
		
		assertEquals(r.width, 50);
		assertEquals(r.height, 50);
	}
	
	@Test
	public void shouldCropImageExactDimensionWidthIsBigger() {
		Rect r = new ImageUtil().computeCroppingBox(50, 200, 100);
		assertEquals(r.x, 25);
		assertEquals(r.y, 0);
		
		assertEquals(r.width, 100);
		assertEquals(r.height, 50);
	}
	
	@Test
	public void shouldCropImageExactDimensionHeightIsBigger() {
		Rect r = new ImageUtil().computeCroppingBox(50, 100, 200);
		assertEquals(r.x, 0);
		assertEquals(r.y, 25);
		
		assertEquals(r.width, 50);
		assertEquals(r.height, 100);
	}
	
	@Test
	public void shouldCropImageExactDimensionHeightIsBiggerTest1() {
		Rect r = new ImageUtil().computeCroppingBox(112, 634, 397);
		assertEquals(r.x, 33);
		assertEquals(r.y, 0);
		
		assertEquals(r.width, 179);
		assertEquals(r.height, 112);
	}
	
	
	
	
	
	
	
	
}
