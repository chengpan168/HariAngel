package com.eden.view;

import org.junit.Test;

import com.eden.fxview.PositionView;

public class DataTableViewTest {
	@Test
	public void testTableViewAnnotation(){
		DataTableView<PositionView> v = new DataTableView<PositionView>(PositionView.class) ;
		
	}
}
