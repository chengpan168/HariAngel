package com.eden.component.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public interface TableCellAware<S, T> {
	public TableCell<S, T> newInstance(TableColumn<S, T> column) ;
}
