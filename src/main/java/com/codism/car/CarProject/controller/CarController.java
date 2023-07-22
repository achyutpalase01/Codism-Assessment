package com.codism.car.CarProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codism.car.CarProject.service.CarService;

@Controller
public class CarController {
	
	@Autowired
	private CarService carService;
	
		@RequestMapping("/home")
		public String home() {
			return "Car";
		}
	
	
	  @GetMapping("/")
	    public String index(Model model) {

		  	return carService.getCarDetail(model);
	    }
	  
	  @PostMapping("/upload")
	    public String uploadFile(@RequestParam("file") MultipartFile file,
	                             @RequestParam("name") String name,
	                             @RequestParam("model") String model,
	                             @RequestParam("variant") String variant,
	                             RedirectAttributes redirectAttributes) {
		  
		  return carService.uploadFile(file, name, model, variant, redirectAttributes);
		  
	  }
}
