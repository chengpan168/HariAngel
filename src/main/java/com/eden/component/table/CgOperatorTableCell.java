package com.eden.component.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;

import com.eden.constant.Constant;
import com.eden.fxmvc.context.AppContext;
import com.eden.fxmvc.mvc.action.ActionContext;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.pane.BaseUpdatePane;
import com.eden.fxmvc.ui.Location;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.fxview.PositionView;
import com.eden.view.MessageBox;
/**
 * 表格的单元格，复选按钮
 * @author eden
 *
 * @param <S>
 */
public class CgOperatorTableCell<S> extends TableCell<S , String> {
	public CgOperatorTableCell(){
		super() ;
		setAlignment(Pos.CENTER) ;
	}
	
	@Override
	public void updateItem(final String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			Hyperlink update = new Hyperlink("修改") ;
			update.getStyleClass().add("cellHyperlink") ;
			update.setUnderline(true) ;
	    	setGraphic(update) ;
	    	
	    	update.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent paramT) {
					String action  = TypeConvertUtil.toStr(getTableColumn().getTableView().getProperties().get(Constant.TO_UPDATE_ACTION) );
					Object obj = getTableColumn().getTableView().getItems().get(getIndex()) ;
					ActionMessage actionMessage = new ActionMessage() ;
					actionMessage.addAttribute(Constant.UPDATE_DATA , obj) ;
					Location.go(new ActionContext(action, actionMessage)) ;
				}
			}) ;
		}

	}
}