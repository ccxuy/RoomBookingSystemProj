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
	static String createTableSql = "";// �������ݿ��sql
	static String colType = "varchar";// �ֶ�����
	static String key = "id";// ����
	static String charSet = "utf8";// ����ַ�����
	static String ENGINE = "InnoDB";// �������
	static String tableName = "roominfo";// ������
	static String colName = "col";// Ĭ���ֶ���
	static Connection conn = null;

	public void upload(String Filename) {
		try {
			// ����Workbook����, ֻ��Workbook����
			// ֱ�Ӵӱ����ļ�����Workbook
			// ������������Workbook   E:\\test.xls
			String str = new String();
			str = Filename.substring(0,3) + "\\" + Filename.substring(3);
			System.out.println("start loadfile-------------------------" + str);
			InputStream is = new FileInputStream(str);// ��������
			//InputStream is = new FileInputStream("E:\\test.xls");// ��������
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0); // ��ȡ��һ��sheet
			int colNum = rs.getColumns();// ����
			int rowNum = rs.getRows();// ����
			System.out.println("colNumrowNum------------------" + rowNum + "��,"
					+ colNum + "��+");
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
		// �������ɿ������ļ�
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
