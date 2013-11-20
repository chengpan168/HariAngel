package com.eden.component.table;

import javafx.scene.control.TableCell;

public class TableCellFactory {
	
	@SuppressWarnings("rawtypes")
	public static TableCell createInstance(Class<? extends TableCell> clazz){
		try {
			TableCell cell = null ;
			
			try{
				cell = clazz.newInstance() ;
			} catch(Exception e) {
				e.printStackTrace() ;
			}
			return cell ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
