package com.eden.component.table;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.eden.component.CgCheckBox;
import com.eden.constant.Constant;
import com.eden.fxmvc.bean.DefaultBeanWraper;
import com.eden.fxmvc.bean.SpringBeanWraper;

public class CgTableCheckBox extends CgCheckBox {
	public <S ,T> CgTableCheckBox(final TableColumn<S, T> column ){
		super() ;
		setStyle("-fx-cursor:hand ") ;
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				TableView<?> tableView = column.getTableView() ;
				if(tableView != null){
					List<?> items = tableView.getItems() ;
					if(items != null){
						for(Object o : items){
							SpringBeanWraper bean = new DefaultBeanWraper(o) ;
							Object isSelectObj = bean.getPropertyValue(Constant.IS_SELECT_PROPERTY) ;
							if(isSelectObj instanceof BooleanProperty){
								((BooleanProperty)isSelectObj).setValue(isSelected()) ;
							}
						}
					}
				}
			}
		}) ;
	}

}
