package com.eden.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eden.component.table.CgTextTableCell;

import javafx.scene.control.TableCell;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableViewAnnotation {
	/**
	 * table title type
	 * @author eden
	 * string 普通文本
	 * checkBox 选择框
	 *
	 */
	public enum TitleType {
		string , checkBox
	}
	
	String title() default "属性" ;
	//当为默认值时，宽度为剩余的所有
	double prefWidthPercent() default -1 ;
	double minWidth() default 30 ; 
	
	boolean orderEnable() default true ;
	String action() default "" ;
	/**
	 * 排序时，指定列
	 * @return
	 */
	String columnName() default "" ;
	TitleType titleType() default TitleType.string ;
	
	@SuppressWarnings("rawtypes")
	Class<? extends TableCell> cellType() default CgTextTableCell.class ;
}
