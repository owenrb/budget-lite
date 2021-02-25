package io.owenrbee.budget.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.owenrbee.budget.dao.CategoryDao;
import io.owenrbee.budget.dto.CategoryVO;
import io.owenrbee.budget.model.Category;

@Service
/**
 * CRUD operations for Category
 * 
 * @author owenrb
 *
 */
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public CategoryVO create(CategoryVO category) {
		
		categoryDao.save(transform(category));
		
		return category;
	}
	

	public CategoryVO read(String name) {
		
		Optional<Category> category = categoryDao.findById(name);
		
		return category.isPresent() ? transform(category.get()) : null;
	}
	
	public List<CategoryVO> list() {

		Iterable<Category> iterable =  categoryDao.findAll();
		
		return StreamSupport.stream(iterable.spliterator(), false)
				.map(category -> transform(category))
				.collect(Collectors.toList());
	}
	
	public CategoryVO update(CategoryVO category) {
		
		if(read(category.getName()) == null) {
			return null;
		}
		
		categoryDao.save(transform(category));
		
		return category;
	}
	
	public CategoryVO delete(String name) {
		CategoryVO category = read(name);
		
		if(category != null) {
			categoryDao.deleteById(category.getName());
		}
		
		return category;
			
	}
	
	public static Category transform(CategoryVO vo) {
		Category category = new Category();
		
		category.setName(vo.getName());
		category.setIncome(vo.isIncome());
		category.setProperty(vo.getProperty());
		
		return category;
	}
	
	public static CategoryVO transform(Category category) {
		CategoryVO vo = new CategoryVO();
		
		vo.setName(category.getName());
		vo.setIncome(category.isIncome());
		vo.setProperty(category.getProperty());
		
		return vo;
	}
}
