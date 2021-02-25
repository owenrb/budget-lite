package io.owenrbee.budget.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import io.owenrbee.budget.dao.CategoryDao;
import io.owenrbee.budget.dto.CategoryVO;
import io.owenrbee.budget.model.Category;

@SpringBootTest
public class CategoryServiceTest {

	@Mock
	private CategoryDao categoryDao;
	
	@InjectMocks
	private CategoryService categoryService;
	
	private void setupData() {
		
		Category food = new Category();
		food.setName("Food");
		
		Category utility = new Category();
		utility.setName("Utility");
		
		Category salary = new Category();
		salary.setName("Salary");
		salary.setIncome(true);
		
		List<Category> list = new ArrayList<>();
		list.add(food);
		list.add(utility);
		list.add(salary);
		
		when(categoryDao.findAll()).thenReturn(list);
	}
	
	
	@Test
	public void list() {
		setupData();
		
		List<CategoryVO> result = categoryService.list();
		
		assertNotNull(result);
		Assertions.assertFalse(result.isEmpty());
	}
}
