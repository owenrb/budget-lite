package io.owenrbee.budget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static io.owenrbee.budget.common.CategoryTableConstraint.*;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(length = NAME_SIZE)
	private String name;
	
	@Column
	private boolean income;
	
	@Column(length = PROPERTY_SIZE)
	private String property;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIncome() {
		return income;
	}
	public void setIncome(boolean income) {
		this.income = income;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
}
