package com.codism.car.CarProject.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codism.car.CarProject.dao.CarDao;
import com.codism.car.CarProject.entity.Car;

@Service
public class CarService {
	
	@Autowired
	private CarDao carDao;
	
	public String getCarDetail(Model model) {
		List<Car> car = carDao.getCarDetail();
		model.addAttribute("cars", car);
        return "Car";
	}
	
	public String uploadFile(MultipartFile file, String name, String model, String variant,
			RedirectAttributes redirectAttributes) {
		
		  System.out.println(file.getOriginalFilename());
		  System.out.println(name);
		  System.out.println(model);
		  System.out.println(variant);
		  System.out.println(redirectAttributes);
		  System.out.println("DONE");
		
		
		
		if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "First select a file to upload.");
            return "redirect:/";
        }

        try {
            // Save the file to the designated folder
            String fileName = file.getOriginalFilename();
        
            String Path_Directory = new ClassPathResource("static/image/").getFile().getAbsolutePath();

    		Files.copy(file.getInputStream(), Paths.get(Path_Directory+java.io.File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

    		System.out.println("before save");
            // Create a new car object and save it to the database
            Car car = new Car();
            car.setCarName(name);
            car.setCarModel(model);
            car.setCarVarient(variant);
            car.setCarImage(fileName);
            carDao.saveCar(car);
            System.out.println("saved");
            

            redirectAttributes.addFlashAttribute("message", "File uploaded successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload the file: " + e.getMessage());
        }

        return "redirect:/";

	}

}
