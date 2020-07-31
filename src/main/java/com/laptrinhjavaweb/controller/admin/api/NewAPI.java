package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService newService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Thêm
		ObjectMapper mapper = new ObjectMapper();
		
		// Đặt dữ liệu có dấu lấy về và truyền đi
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");


		// Định nghĩa kiểu dữ liệu trả về cho client - json
		resp.setContentType("application/json"); 
		
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newModel.setCreatedBy(((UserModel)SessionUtil.getInstance().getValue(req,"USERMODEL")).getUserName());
		newModel = newService.save(newModel);		
		
		// Trả lại dữ liệu dạng json maping từ model
		mapper.writeValue(resp.getOutputStream(), newModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Cập nhật
		ObjectMapper mapper = new ObjectMapper();
		
		// Đặt dữ liệu có dấu lấy về và truyền đi
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// Định nghĩa kiểu dữ liệu trả về cho client - json
		resp.setContentType("application/json"); 
		
		NewModel updateNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		updateNew = newService.update(updateNew);
		
		// Trả lại dữ liệu dạng json maping từ model
		mapper.writeValue(resp.getOutputStream(), updateNew);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Xóa
		ObjectMapper mapper = new ObjectMapper();

		// Đặt dữ liệu có dấu lấy về và truyền đi
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// Định nghĩa kiểu dữ liệu trả về cho client - json
		resp.setContentType("application/json"); 
		
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());

		// Trả lại dữ liệu dạng json maping từ model
		mapper.writeValue(resp.getOutputStream(), "{}"); // Trả về dữ liệu rỗng
	}
}
