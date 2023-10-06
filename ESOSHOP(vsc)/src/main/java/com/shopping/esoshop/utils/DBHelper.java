package com.shopping.esoshop.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Connection makeConnection(){
		try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        //2. Create Connection String
		        String url="jdbc:sqlserver:"
		                + "//localhost:1433"
		                + ";databaseName=ESS_Store10";
		        //3. Open Connection
		        Connection con=DriverManager.getConnection(url,"sa","123456");
		        return con;
		} catch (Exception e) {
		    System.out.println("Canot connection database");
		}
        return null;
    }
}
