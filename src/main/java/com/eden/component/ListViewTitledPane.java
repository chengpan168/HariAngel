package com.eden.component;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ListViewTitledPane<T> extends ListView<T>{
	
	/**
	 * 用于显示左侧节点树
	 */
	public ListViewTitledPane() {
		this.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(ListView<T> paramP) {
				return new ListViewCell<T>();
			}
		}) ;
	}

}
