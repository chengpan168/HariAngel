package com.eden.component;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class ListViewCell<T> extends ListCell<T>{
	public ListViewCell() {
		init() ;
	}
	
	public void init() {
	}

	@Override
	protected void updateItem(T item, boolean paramBoolean) {
		super.updateItem(item, paramBoolean);
		if (item == null ) {
            setGraphic(null);
        } else {
        	Label cell = (Label) item ;
            setGraphic(cell);
        }
	}
}
