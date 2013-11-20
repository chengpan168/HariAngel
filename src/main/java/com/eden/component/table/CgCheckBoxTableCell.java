package com.eden.component.table;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;

import com.eden.constant.Constant;
import com.eden.fxmvc.bean.DefaultBeanWraper;
import com.eden.fxmvc.bean.SpringBeanWraper;
/**
 * 表格的单元格，复选按钮
 * @author eden
 *
 * @param <S>
 */
public class CgCheckBoxTableCell<S> extends CheckBoxTableCell<S , Boolean> {

	public CgCheckBoxTableCell(){
		super() ;
		setAlignment(Pos.CENTER) ;
    	((CheckBox)getGraphic()).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				List<?> items = getTableView().getItems() ;
				if(items == null ) return ;
				
				boolean all = true ;
				for(Object o : items){
					SpringBeanWraper beanWraper = new DefaultBeanWraper(o) ;
					if(!((BooleanProperty)beanWraper.getPropertyValue(Constant.IS_SELECT_PROPERTY)).get()){
						all = false ;
						break;
					}
				}
				((CgTableCheckBox) getTableColumn().getGraphic()).setSelected(all);
				
			}
		}) ;
	}
	
	@Override
	public void updateItem(Boolean paramT, boolean paramBoolean) {
		super.updateItem(paramT, paramBoolean);
	}
}