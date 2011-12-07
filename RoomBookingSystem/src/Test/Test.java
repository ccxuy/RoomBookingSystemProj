package Test;

import java.io.*;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import jxl.*;
import java.util.*;

public class Test {
	static String createTableSql = "";// 创建数据库的sql
	static String colType = "varchar";// 字段类型
	static String key = "id";// 主键
	static String charSet = "utf8";// 表格字符类型
	static String ENGINE = "InnoDB";// 表格类型
	static String tableName = "roominfo";// 表名称
	static String colName = "col";// 默认字段名
	static Connection conn = null;

	public void upload(String Filename) {
		try {
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook
			// 从输入流创建Workbook   E:\\test.xls
			String str = new String();
			str = Filename.substring(0,3) + "\\" + Filename.substring(3);
			System.out.println("start loadfile-------------------------" + str);
			InputStream is = new FileInputStream(str);// 创建输入
			//InputStream is = new FileInputStream("E:\\test.xls");// 创建输入
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0); // 读取第一个sheet
			int colNum = rs.getColumns();// 列数
			int rowNum = rs.getRows();// 行数
			System.out.println("colNumrowNum------------------" + rowNum + "行,"
					+ colNum + "列+");
			System.out.println("start create-------------------------");
			getConntion();

			String sql = getColName(rowNum, colNum);
			//String sql0 = "delete from" + tableName;
			PreparedStatement ps = null;
			//Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//stmt.executeUpdate(sql0);
			String strValue = "";
			String strTemp="";
			ps = conn.prepareStatement(sql);
			System.out.println(sql);
			for (int i = 0; i < rowNum; i++) {
				strValue = "";
				for (int j = 0; j < colNum; j++) {
					Cell c = rs.getCell(j, i);
					if(j==2||j==3){
						strTemp = c.getContents();
						strValue=stringConventor(strTemp);
						System.out.println(strValue);
					}
					else{
					strValue = c.getContents();
					System.out.println(strValue);
					}
					ps.setString(j + 1, strValue);
				}
				ps.addBatch();
			}
			ps.executeBatch();
			// conn.commit();
			
			if (ps != null) {
				ps.close();
			}
			System.out.println(" insertend-------------------------");
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String stringConventor(String str){
		String s[]=str.split("/");
		//System.out.println(s[0]);
		//System.out.println(s[1]);
		//System.out.println(s[2]);
		return s[2]+"-"+s[1]+"-"+s[0];
	}

	static String getColName(int rowNum, int colNum) {
		// 可以做成可配置文件
		String colSql = "";
		String colValue = "";
		for (int j = 0; j < colNum; j++) {
			colSql = colSql + "'" + colName + j + "',";
			colValue = colValue + "" + "?,";
		}
		return  "insert into " + tableName + "(roominfoid,roomid,datebegin,dateend,week,timebegin,timeend)values("
				+ colValue.substring(0, colValue.lastIndexOf(",")) + ");";
	}

	static void getConntion() {
		try {
			String driver_class = "com.mysql.jdbc.Driver";
			String connection_url = "jdbc:mysql://localhost:3306/rbsdb";
			String user_name = "root";
			String db_password = "123123";
			Class.forName(driver_class);
			conn = DriverManager.getConnection(connection_url, user_name,
					db_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
