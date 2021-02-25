package io.owenrbee.budget.dto;


import static io.owenrbee.budget.common.CategoryTableConstraint.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryVO {

    @NotBlank(message = "{category.name.required")
    @Size(max = NAME_SIZE, message="{category.name.size}" )
	private String name;
	private boolean income;
	@Size(max = PROPERTY_SIZE, message="{category.property.size}" )
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
