package com.emp.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpVO;

@WebServlet("/emp/emp.do")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String ename = request.getParameter("ename");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(ename == null || ename.trim().length() == 0) {
				errorMsgs.add("ename should not be empty");
			} else if(!ename.trim().matches(enameReg)) {
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			String job = request.getParameter("job").trim();
			if(job == null || job.trim().length() == 0) {
				errorMsgs.add("職位請勿空白");
			}
			
			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(request.getParameter("hiredate").trim());
			} catch(IllegalArgumentException ie) {
				hiredate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			Double sal = null;
			try {
				sal = new Double(request.getParameter("sal").trim());
			} catch(NumberFormatException ne) {
				sal = 0.0;
				errorMsgs.add("薪水請填數字");
			}
			
			Double comm = null;
			try {
				comm = new Double(request.getParameter("comm").trim());
			} catch(NumberFormatException ne) {
				comm = 0.0;
				errorMsgs.add("獎金請填數字");
			}
			
			Integer deptno = new Integer(request.getParameter("deptno").trim());
			
			EmpVO empVO = new EmpVO();
			empVO.setEname(ename);
			empVO.setJob(job);
			empVO.setHiredate(hiredate);
			empVO.setSal(sal);
			empVO.setComm(comm);
			empVO.setDeptno(deptno);
			
			if(!errorMsgs.isEmpty()) {
				request.setAttribute("empVO", empVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(request, response);
			}
			
			EmpDAO dao = new EmpDAO();
			dao.insert(empVO);
			
			String url = "/emp/addEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
			
		}
	}

}
