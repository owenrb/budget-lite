package io.owenrbee.budget.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.owenrbee.budget.dto.CategoryVO;
import io.owenrbee.budget.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CategoryVO category) {

		CategoryVO result = categoryService.create(category);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getName()).toUri();
		return ResponseEntity.created(location).build();
	}


	@GetMapping("{name}")
	public ResponseEntity<?> get(@PathVariable String name) {
		
		CategoryVO category = categoryService.read(name);

		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(category);
	}
	
	@GetMapping
	public  List<CategoryVO> list()  {
		return categoryService.list();
	}
	
	@PutMapping
	public ResponseEntity<?>  update(@Valid @RequestBody CategoryVO category) {

		return categoryService.update(category) != null ?
				ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@DeleteMapping("{name}")
	public ResponseEntity<?> delete(@PathVariable String name) {
		
		CategoryVO category = categoryService.delete(name);

		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(category);
	}
}
