package com.naoshin.hridiautowheels.controller;


import com.naoshin.hridiautowheels.model.Car;
import com.naoshin.hridiautowheels.model.CarCategory;
import com.naoshin.hridiautowheels.repository.CarRepository;
import com.naoshin.hridiautowheels.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addproduct")
    public String addProduct(Model model){
        return "admin";
    }

    @PostMapping("/registerproduct")
    public String registerProduct(@ModelAttribute("car") Car car,
//                                  @RequestParam("Photo") MultipartFile file,
                                  @RequestParam("categoryId") int categoryId,
                                  HttpServletRequest request, Model m) throws IOException {

        HttpSession httpSession = request.getSession();
//        if(file.isEmpty()){
//            System.out.println("File doesn't exist");
//        }else{
//            car.setImageURL(file.getOriginalFilename());
//            //String file1 = "G:\\Naoshin\\Project\\Project\\src\\main\\resources\\static\\images";
//            String file1 = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images";
//            Path path = Paths.get(file1 + File.separator + file.getOriginalFilename());
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//            Optional<CarCategory> category = categoryRepository.findById(categoryId);
//            car.setCarCategory(category.get());
//
//            Car car1 = carRepository.save(car);
//            System.out.println("Image Uploaded");
//            httpSession.setAttribute("msg",car1.getImageURL()+" Added successfully!");
//
//        }
        Optional<CarCategory> category = categoryRepository.findById(categoryId);
        car.setCarCategory(category.get());
        Car car1 = carRepository.save(car);
        httpSession.setAttribute("msg",car1.getName()+" Added successfully!");
        List<Car> carList = (List<Car>) this.carRepository.findAll();
        List<CarCategory> categoryList = (List<CarCategory>) this.categoryRepository.findAll();
        m.addAttribute("carList",carList);
        m.addAttribute("categoryList",categoryList);

        m.addAttribute("allProduct",carList.size());
        m.addAttribute("allCategory",categoryList.size());

        return "admin";
    }

    @GetMapping("/rent")
    public String rentCar(Model m){
        List<Car> carList = (List<Car>) this.carRepository.findAll();
        List<CarCategory> categoryList = (List<CarCategory>) this.categoryRepository.findAll();
        m.addAttribute("carList",carList);
        m.addAttribute("categoryList",categoryList);
        return "rentCar";
    }
}
