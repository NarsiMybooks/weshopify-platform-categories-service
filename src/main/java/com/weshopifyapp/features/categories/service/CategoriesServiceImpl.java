package com.weshopifyapp.features.categories.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.categories.bean.CategoriesBean;
import com.weshopifyapp.features.categories.model.Categories;
import com.weshopifyapp.features.categories.repo.CategoriesRepo;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	private CategoriesRepo categoriesRepo;
	
	private ModelMapper modelMapper;
	
	public CategoriesServiceImpl(CategoriesRepo categoriesRepo,ModelMapper modelMapper) {
		this.categoriesRepo = categoriesRepo;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public CategoriesBean createCategories(CategoriesBean categoriesBean) {
		Categories categoriesEntity =  modelMapper.map(categoriesBean, Categories.class);
		categoriesEntity.setCreatedDate(new Date());
		categoriesEntity.setModifiedDate(new Date());
		categoriesRepo.save(categoriesEntity);
		categoriesBean = modelMapper.map(categoriesEntity, CategoriesBean.class);
		return categoriesBean;
	}

	@Override
	public CategoriesBean updateCategories(CategoriesBean categoriesBean) {
		Categories categoriesEntity = categoriesRepo.findById(categoriesBean.getId()).get();
		modelMapper.map(categoriesBean, categoriesEntity);
		categoriesEntity.setModifiedDate(new Date());
		categoriesRepo.save(categoriesEntity);
		categoriesBean = modelMapper.map(categoriesEntity, CategoriesBean.class);
		return categoriesBean;
	}

	@Override
	public CategoriesBean getCategoryById(int catId) {
		Categories categoriesEntity = categoriesRepo.findById(catId).get();
		CategoriesBean categoriesBean = modelMapper.map(categoriesEntity, CategoriesBean.class);
		return categoriesBean;
	}

	@Override
	public List<CategoriesBean> findAllCategories() {
		List<Categories> catList = categoriesRepo.findAll();
		List<CategoriesBean> catBeanList = new ArrayList<>();
		Optional.ofNullable(catList).ifPresentOrElse(catlist ->{
			catlist.stream().forEach(categoryEntity->{
				CategoriesBean categoriesBean = modelMapper.map(categoryEntity, CategoriesBean.class);
				catBeanList.add(categoriesBean);
			});
		}, ()->{
			throw new RuntimeException("Categories List is Empty");
		});
		return catBeanList;
	}

	@Override
	public List<CategoriesBean> deleteCategory(int catId) {
		categoriesRepo.deleteById(catId);
		return findAllCategories();
	}

	@Override
	public List<CategoriesBean> searchAllCategories(String name) {
		
		return null;
	}

	@Override
	public List<CategoriesBean> findAllChildCategories(int parentCatId) {
		List<Categories> catList = categoriesRepo.findChildCategories(parentCatId);
		List<CategoriesBean> catBeanList = new ArrayList<>();
		Optional.ofNullable(catList).ifPresentOrElse(catlist ->{
			catlist.stream().forEach(categoryEntity->{
				CategoriesBean categoriesBean = modelMapper.map(categoryEntity, CategoriesBean.class);
				catBeanList.add(categoriesBean);
			});
		}, ()->{
			throw new RuntimeException("No Child Categories were there for parent:\t"+parentCatId);
		});
		return catBeanList;
	}

}
