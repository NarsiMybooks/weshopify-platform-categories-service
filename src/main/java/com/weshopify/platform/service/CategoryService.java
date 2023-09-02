package com.weshopify.platform.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.weshopify.platform.bean.CategoryBean;
import com.weshopify.platform.bean.CategoryPageInfo;
import com.weshopify.platform.entity.Category;
import com.weshopify.platform.exceptions.CategoryNotFoundException;
import com.weshopify.platform.repo.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
	public static final int ROOT_CATEGORIES_PER_PAGE = 4;
	
	@Autowired
	private CategoryRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryBean save(CategoryBean categoryBean) {
		String parentCatId = categoryBean.getParentId();
		
		Category category = modelMapper.map(categoryBean, Category.class);
		if(StringUtils.hasText(parentCatId)) {
			Category parentCat = repo.findById(Integer.valueOf(parentCatId)).get();
			category.setParent(parentCat);
		}
		category = repo.save(category);
		
		return modelMapper.map(category, CategoryBean.class);
	}
	
	public String checkUnique(Integer id, String name, String alias) {
		boolean isCreatingNew = (id == null || id == 0);
		
		Category categoryByName = repo.findByName(name);
		
		if (isCreatingNew) {
			if (categoryByName != null) {
				return "DuplicateName";
			} else {
				Category categoryByAlias = repo.findByAlias(alias);
				if (categoryByAlias != null) {
					return "DuplicateAlias";	
				}
			}
		} else {
			if (categoryByName != null && categoryByName.getId() != id) {
				return "DuplicateName";
			}
			
			Category categoryByAlias = repo.findByAlias(alias);
			if (categoryByAlias != null && categoryByAlias.getId() != id) {
				return "DuplicateAlias";
			}
			
		}
		
		return "OK";
	}
	
	public CategoryBean findCategoryById(int catId) {
		return modelMapper.map(repo.findById(catId).get(),CategoryBean.class);
	}
}
