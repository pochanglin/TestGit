package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO implements EmpDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TESTBA105";
	String passwd = "TESTBA105";
	
	private static final String INSERT_STMT = 
			"INSERT INTO emp2 (empno,ename,job,hiredate,sal,comm,deptno) VALUES (emp2_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd')hiredate,sal,comm,deptno FROM emp2";
	
	@Override
	public void insert(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empVO.getEname());
			pstmt.setString(2, empVO.getJob());
			pstmt.setDate(3, empVO.getHiredate());
			pstmt.setDouble(4, empVO.getSal());
			pstmt.setDouble(5, empVO.getComm());
			pstmt.setInt(6, empVO.getDeptno());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(EmpVO empVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer empno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmpVO findByPrimaryKey(Integer empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<>();
		EmpVO empVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		return null;
	}
	
	public static void main(String[] args) {
		
		EmpDAO dao = new EmpDAO();
		
		EmpVO empVO = new EmpVO();
		empVO.setEname("555");
		empVO.setJob("HHH");
		empVO.setHiredate(java.sql.Date.valueOf("2002-01-01"));
		empVO.setSal(new Double(20000));
		empVO.setComm(new Double(200));
		empVO.setDeptno(20);
		dao.insert(empVO);
		

	}

}
