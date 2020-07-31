package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {

		String sql = "SELECT * FROM jspservletjdbc.news where categoryid = ?";

		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {

		// Dùng StringBuilder để cộng chuỗi => Mục đích không phải cấp phát bộ nhớ nhiều lần thay vì công chuỗi thông thường
		// Cấp phát địa chỉ trong bộ nhớ 1 lần và giãn ra khi công thêm dữ liệu vào.
		StringBuilder sql = new StringBuilder("INSERT INTO `jspservletjdbc`.`news`");
				sql.append(" (`title`, `thumbnail`, `shortdescription`, `content`, `categoryid`, `createddate`, `createdby`)");
				sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		return insert(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(), 
										newModel.getContent(), newModel.getCategoryId(), newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public NewModel findOne(Long id) {
		
		String sql = "SELECT * FROM news where id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
				
		// Dùng StringBuilder để cộng chuỗi => Mục đích không phải cấp phát bộ nhớ nhiều lần thay vì công chuỗi thông thường
		// Cấp phát địa chỉ trong bộ nhớ 1 lần và giãn ra khi công thêm dữ liệu vào.
		StringBuilder sql = new StringBuilder("UPDATE `jspservletjdbc`.`news` SET");
		sql.append(" `title` = ?,");
		sql.append(" `thumbnail` = ?,");
		sql.append(" `shortdescription` = ?,");
		sql.append(" `content` = ?,");
		sql.append(" `categoryid` = ?,");
		sql.append(" `createddate` = ?,");
		sql.append(" `createdby` = ?,");
		sql.append(" `modifieddate` = ?,");
		sql.append(" `modifiedby` = ?");
		sql.append(" WHERE `id` = ?");
		
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(), 
								updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(), 
								updateNew.getCreatedBy(), updateNew.getModifiedDate(), updateNew.getModifiedBy(), updateNew.getId());
	}

	@Override
	public void delete(long id) {
		
		String sql = "DELETE FROM `jspservletjdbc`.`news` WHERE (`id` = ?);";
		update(sql, id);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		
		StringBuilder sql = new StringBuilder("SELECT * FROM jspservletjdbc.news");
		// Dùng thư viện Apache Commons Lang
		// Kiểm tra null và empty
		if(pageble.getSorter() != null
				&& StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortName())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
		}

		if(pageble.getOffset() != null && pageble.getLimit() != null) {	
			sql.append(" limit " + pageble.getOffset() + ", " + pageble.getLimit() + " ");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {

		String sql = "SELECT count(*) FROM jspservletjdbc.news";
		return count(sql);
	}
}
