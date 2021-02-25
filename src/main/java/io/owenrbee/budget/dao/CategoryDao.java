package io.owenrbee.budget.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.owenrbee.budget.model.Category;

public interface CategoryDao extends JpaRepository<Category, String>{

}
