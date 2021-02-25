package io.owenrbee.budget.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.owenrbee.budget.common.CacheName;
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

	@CacheEvict(value = CacheName.CATEGORIES, allEntries = true)
	public CategoryVO create(CategoryVO category) {

		categoryDao.save(transform(category));

		return category;
	}

	@Cacheable(CacheName.CATEGORY)
	public CategoryVO read(String name) {

		Optional<Category> category = categoryDao.findById(name);

		return category.isPresent() ? transform(category.get()) : null;
	}

	@Cacheable(CacheName.CATEGORIES)
	public List<CategoryVO> list() {

		Iterable<Category> iterable = categoryDao.findAll();

		return StreamSupport.stream(iterable.spliterator(), false).map(category -> transform(category))
				.collect(Collectors.toList());
	}

	@CachePut(value = CacheName.CATEGORY, key = "#category.name")
	@CacheEvict(value = CacheName.CATEGORIES, allEntries = true)
	public CategoryVO update(CategoryVO category) {

		if (read(category.getName()) == null) {
			return null;
		}

		categoryDao.save(transform(category));

		return category;
	}

	@Caching(evict = { @CacheEvict(value = CacheName.CATEGORY),
			@CacheEvict(value = CacheName.CATEGORIES, allEntries = true) })
	public CategoryVO delete(String name) {
		CategoryVO category = read(name);

		if (category != null) {
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
