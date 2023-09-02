package com.weshopify.platform.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Server;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weshopify.platform.bean.CategoryBean;
import com.weshopify.platform.entity.Category;
import com.weshopify.platform.service.CategoryService;

@RestController
public class CategoryRestController {

	@Autowired
	private CategoryService service;
	
	@Value("${server.port}")
	private String appPort;
	
	@PostMapping("/categories/unique")
	public String checkUnique(@Param("id") Integer id, @Param("name") String name,
			@Param("alias") String alias) {
		return service.checkUnique(id, name, alias);
	}
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryBean> saveCategory(@RequestBody CategoryBean categoryBean)  {
		if (!categoryBean.getImage().isEmpty()) {
			File imageFile = new File(categoryBean.getImage());
			String fileName = StringUtils.cleanPath(imageFile.getName());
			categoryBean.setImage(fileName);

			categoryBean = service.save(categoryBean);
			String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/" + fileName;
			
			try {
				IOUtils.copy(new FileInputStream(imageFile), new FileOutputStream(new File(uploadDir)));
				String imageServerUrl ="http://"+
				InetAddress.getLocalHost().getHostName()+
				":"+
				appPort+"/"+fileName;
				
				categoryBean.setImage(imageServerUrl);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			service.save(categoryBean);
		}
		
		return ResponseEntity.ok(categoryBean);
	}
	
	@GetMapping(value = {"/categories/{catId}"})
	public ResponseEntity<CategoryBean> findCategoryById(@PathVariable("catId") int catId){
		CategoryBean categoryBean = service.findCategoryById(catId);
		try {
			String imageServerUrl ="http://"+
					InetAddress.getLocalHost().getHostName()+
					":"+
					appPort+"/"+categoryBean.getImage();
			categoryBean.setImage(imageServerUrl);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(categoryBean);
	}
}
