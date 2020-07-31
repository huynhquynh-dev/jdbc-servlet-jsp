package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewService {

	NewModel findOne(Long id);

	List<NewModel> findByCategoryId(Long categoryId);
	
	NewModel save(NewModel newModel);
	
	NewModel update(NewModel updateNew);
	
	void delete(long[] ids);
	
	List<NewModel> findAll(Pageble pageble);
	
	int getTotalItem();
}
