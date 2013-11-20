package com.eden.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.derby.jdbc.EmbeddedDataSource40;

public class DerbyTest {
	public static void main(String[] args) {
		
		String path ;
		path = Thread.currentThread().getContextClassLoader().getResource("").getPath() ;
		System.out.println(path) ;
		
		EmbeddedDataSource40 dataSource = new EmbeddedDataSource40() ;
		dataSource.setDatabaseName("data/hairangel") ;
//		dataSource.setConnectionAttributes("create=true") ;
		dataSource.setCreateDatabase("create") ;
		System.out.println("derby start...") ;
		try {
			Connection conn = dataSource.getConnection() ;
			PreparedStatement ps = conn.prepareStatement("select 1 from dual ") ; 
			
			ps.execute() ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		JFrame f = new JFrame() ;
//		f.setVisible(true) ;
//		dataSource.setShutdownDatabase("shutdown") ;
	}
}
