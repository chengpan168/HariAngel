package com.eden.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eden.annotation.TableViewAnnotation;
import com.eden.annotation.TableViewAnnotation.TitleType;
import com.eden.component.NormalTableColumn;
import com.eden.component.table.CgTableCheckBox;
import com.eden.component.table.CgTableColumn;
import com.eden.component.table.CgTableTitle;
import com.eden.component.table.TableCellFactory;
import com.eden.fxmvc.bean.DefaultBeanWraper;
import com.eden.fxmvc.bean.SpringBeanWraper;
import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.dao.support.Page;

public class ComponentUtil {
	private static Log log = LogFactory.getLog(ComponentUtil.class);

	/**
	 * 生成表格列
	 * @param viewClass 用于取得annotation
	 * @param tableView 目前用到的表格的宽度
	 * @return
	 */
	public static <S  , T> List<CgTableColumn<S , T >> generateTableColumn(
			Class<S> viewClass, TableView<S> tableView) {
		try{
			List<CgTableColumn<S , T >> columns = new ArrayList<CgTableColumn<S , T >>();
	
			Field[] fields = viewClass.getDeclaredFields();
	
			for (final Field field : fields) {
				final TableViewAnnotation a = field.getAnnotation(TableViewAnnotation.class);
				log.info("find " + viewClass.getSimpleName()+ " annotation table column " + a);
				if(a==null) continue ;
				
				final CgTableColumn<S , T> column = new NormalTableColumn<S , T>();
				
				//设置排序
				column.setSortable(a.orderEnable()) ;
				column.setSortType(null) ;
				if(column.isSortable())
					column.sortTypeProperty().addListener(new ChangeListener<SortType>() {
						@Override
						public void changed(ObservableValue<? extends SortType> value ,
								SortType oType, SortType nType) {
							Page p = new Page();
							if(StringUtils.isNotBlank(a.columnName())){
								p.setSortType(a.columnName()) ;
							} else {
								p.setSortType(field.getName()) ;
							}
							p.setSortValue(nType == SortType.ASCENDING ? "asc" : "desc") ;
							column.getTableView().getProperties().put(FXConstant.PAGE, p) ;
						}
					}) ;
				column.setComparator(new Comparator<T>() {
					@Override public int compare(T o1, T o2) {
						return 0;
					}
				}) ;
				//表头
				if(a.titleType() == TitleType.string){
					column.setGraphic(new CgTableTitle(a.title(), column));
				} else if(a.titleType() == TitleType.checkBox ){
					//如果表格列标题为组件
					column.setGraphic(new CgTableCheckBox(column)) ;
				}
				//表格工厂
				column.setCellFactory(new Callback<TableColumn<S,T>, TableCell<S,T>>() {
					@Override
					public TableCell<S, T> call(TableColumn<S, T> paramP) {
						try {
							@SuppressWarnings("unchecked")
							TableCell<S,T> cell = TableCellFactory.createInstance(a.cellType()) ;
							return cell ;
						} catch (Exception e) {
							log.error(e) ;
						}
						return null; 
					}
				}) ;
				
				column.setCellValueFactory(new PropertyValueFactory<S , T>(field.getName()) {
					@Override
					public ObservableValue<T> call(CellDataFeatures<S , T> cell) {
						SpringBeanWraper wraper = new DefaultBeanWraper(cell.getValue());
						@SuppressWarnings("unchecked")
						ObservableValue<T> va = 
							(ObservableValue<T>) wraper.getPropertyValue(field.getName());
						return va;
					}
				});
	
				column.prefWidthProperty().bind(
						tableView.prefWidthProperty().subtract(15).multiply(
								a.prefWidthPercent() / 100));
	
				columns.add(column);
			}
			return columns;
		}catch(Exception e) {
			log.error(e); 
		}
		return null ;
	}
	
	/**
	 * 去除组件的首尾空格
	 * @param node
	 * @return 返回此组件的文本
	 */
	public static String trimText(Node node) {
		String text = null ;
		if(node==null){
			return text ;
		} else if(node instanceof TextField){
			TextField t = (TextField) node ;
			t.setText(StringUtils.trim(t.getText())) ;
			text = t.getText() ;
		} else if(node instanceof TextArea) {
			TextArea t = (TextArea) node ;
			t.setText(StringUtils.trim(t.getText())) ;
			text = t.getText() ;
		}
		if(text==null) return "" ;
		return text ;
	}
}
