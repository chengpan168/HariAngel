package com.eden.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eden.annotation.TableViewAnnotation;
import com.eden.component.NormalTableColumn;
import com.eden.component.table.CgTableView;
import com.eden.fxmvc.bean.DefaultBeanWraper;
import com.eden.fxmvc.bean.SpringBeanWraper;


public class DataTableView<T> extends CgTableView<T>{
	private static final Log log = LogFactory.getLog(DataTableView.class) ;
	
	public DataTableView(Class<T> viewClass){
		
		List<TableColumn<T , String>> columns = new ArrayList<TableColumn<T , String>>() ;
		
		Field[] fields = viewClass.getDeclaredFields() ;
		
		for(final Field field : fields){
			TableViewAnnotation a = field.getAnnotation(TableViewAnnotation.class) ;
			log.info("find " + viewClass.getSimpleName() + " annotation table column " + a ) ;
			TableColumn<T, String> column = new NormalTableColumn<T, String>(); 
			column.setText(a.title()) ;
			
			column.setCellValueFactory(new PropertyValueFactory<T , String>(field.getName()) {
				@Override
				public ObservableValue<String> call(
						CellDataFeatures<T, String> cell) {
					SpringBeanWraper wraper = new DefaultBeanWraper(cell.getValue()) ;
					@SuppressWarnings("unchecked")
					ObservableValue<String> va = (ObservableValue<String>) wraper.getPropertyValue(field.getName()) ;
					return va;
				}
			}) ;
			
			column.prefWidthProperty().bind(this.prefWidthProperty().multiply(a.prefWidthPercent() / 100 )) ;
			
			columns.add(column) ;
		}
		
		this.getColumns().addAll(columns) ;
	}
}
