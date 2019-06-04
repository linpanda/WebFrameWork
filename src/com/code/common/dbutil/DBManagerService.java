package com.code.common.dbutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class DBManagerService {
	private Properties prop;
	private Connection conn;

	public Connection getMysqlConnection() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("dbcon.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		String driver = prop.getProperty("mysqldriver");
		String url = prop.getProperty("mysqlurl");
		String user = prop.getProperty("mysqluser");
		String passwd = prop.getProperty("mysqlpassword");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
			//System.out.println("Mysql Connected Success!");
		} catch (SQLException e) {
			//System.err.println("sql exception:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			//System.err.println("Driver Class not found " + e.getMessage());
		}
		return conn;
	}

	public Connection getOracleConnection() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("dbcon.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String driver = prop.getProperty("oracledriver");
		String url = prop.getProperty("oracleurl");
		String user = prop.getProperty("oracleuser");
		String passwd = prop.getProperty("oraclepassword");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
			//System.out.println("Oracle Connected Success!");
		} catch (SQLException e) {
			//System.err.println("sql exception:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			//System.err.println("Driver Class not found " + e.getMessage());
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 *
	 * @param connect
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void closeMysqlConnection() throws ClassNotFoundException, SQLException {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("dbcon.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String driver = prop.getProperty("mysqldriver");
		String url = prop.getProperty("mysqlurl");
		String user = prop.getProperty("mysqluser");
		String passwd = prop.getProperty("mysqlpassword");
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, passwd);
		try {
			if (conn != null) {
				/** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
				if (!conn.isClosed()) {
					conn.close();
					//System.out.println("connection closed");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void closeOracleConnection() throws ClassNotFoundException, SQLException {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("dbcon.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String driver = prop.getProperty("oracledriver");
		String url = prop.getProperty("oracleurl");
		String user = prop.getProperty("oracleuser");
		String passwd = prop.getProperty("oraclepassword");
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, passwd);
		try {
			if (conn != null) {
				/** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
				if (!conn.isClosed()) {
					conn.close();
					//System.out.println("connection closed");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}