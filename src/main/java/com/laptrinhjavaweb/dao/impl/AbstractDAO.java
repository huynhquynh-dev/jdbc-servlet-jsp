package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
	
//	Sử dụng lấy dữ liệu từ file properties
	ResourceBundle bundle = ResourceBundle.getBundle("db");  

	public Connection getConnection() 
	{
		try 
		{
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/jspservletjdbc";
//			String user = "root";
//			String password = "Hq2018";
			
			Class.forName(bundle.getString("driverName"));
			String url = bundle.getString("url");
			String user = bundle.getString("user");
			String password = bundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {

		List<T> results = new ArrayList<T>();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try 
		{
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//set parameters
			setParameters(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} 
		catch (Exception e) 
		{
			return null;
		}
		finally 
		{			
			try 
			{
				if(connection != null) 
				{
					connection.close();
				}
				if(preparedStatement != null) 
				{
					preparedStatement.close();
				}
				if(resultSet != null) 
				{
					resultSet.close();
				}					
			} 
			catch (SQLException e) 
			{
				return null;
			}			
		}
	}	

	@Override
	public void update(String sql, Object... parameters) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try 
		{
			connection = getConnection();			
			// Tắt tính năng commit tự động của java
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);			
			preparedStatement.executeUpdate();
			connection.commit();
		} 
		catch (SQLException e) 
		{
			if(connection != null)
			{
				try 
				{
					// Lệnh này cho phép DATABASE quay lại ban đầu trước khi thao tác dữ liệu bị lỗi giữa chừng
					connection.rollback();
				} 
				catch (SQLException ex) 
				{					
					ex.printStackTrace();
				}
			}
		}
		finally 
		{			
			try 
			{
				if(connection != null) 
				{
					connection.close();
				}
				if(preparedStatement != null) 
				{
					preparedStatement.close();
				}				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try 
		{
			Long id = null;
			connection = getConnection();			
			// Tắt tính năng commit tự động của java
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Trả về id
			setParameters(preparedStatement, parameters);			
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} 
		catch (SQLException e) 
		{
			if(connection != null)
			{
				try 
				{
					// Lệnh này cho phép DATABASE quay lại ban đầu trước khi thao tác dữ liệu bị lỗi giữa chừng
					connection.rollback();
				} 
				catch (SQLException ex) 
				{					
					ex.printStackTrace();
				}
			}
		}
		finally 
		{			
			try 
			{
				if(connection != null) 
				{
					connection.close();
				}
				if(preparedStatement != null) 
				{
					preparedStatement.close();
				}	
				if(resultSet != null) 
				{
					resultSet.close();
				}	
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	private void setParameters(PreparedStatement preparedStatement, Object... parameters) {
		
		try 
		{
			for (int i = 0; i < parameters.length; i++) 
			{
				int index = i + 1;
				Object parameter = parameters[i];
				
				if(parameter instanceof Long)
				{
					preparedStatement.setLong(index, (Long)parameter);
				}
				else if(parameter instanceof String)
				{
					preparedStatement.setString(index, (String)parameter);
				}
				else if(parameter instanceof Integer)
				{
					preparedStatement.setInt(index, (Integer)parameter);
				}
				else if(parameter instanceof Timestamp)
				{
					preparedStatement.setTimestamp(index, (Timestamp)parameter);
				}	
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
	}

	@Override
	public int count(String sql, Object... parameters) {

		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try 
		{
			int count = 0;
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//set parameters
			setParameters(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				count = resultSet.getInt(1);
			}
			return count;
		} 
		catch (Exception e) 
		{
			return 0;
		}
		finally 
		{			
			try 
			{
				if(connection != null) 
				{
					connection.close();
				}
				if(preparedStatement != null) 
				{
					preparedStatement.close();
				}
				if(resultSet != null) 
				{
					resultSet.close();
				}					
			} 
			catch (SQLException e) 
			{
				return 0;
			}			
		}
	}
}


