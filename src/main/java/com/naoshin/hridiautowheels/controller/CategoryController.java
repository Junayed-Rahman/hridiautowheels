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
import java.util.List;

@Controller
public class CategoryController {
   @Autowired
   private CarRepository carRepository;
   @Autowired
   private CategoryRepository categoryRepository;

   @PostMapping("/addcategory")
   public String addCategory(@ModelAttribute("category") CarCategory category,
                             HttpServletRequest request, Model m){
      HttpSession httpSession = request.getSession();
      CarCategory category1= categoryRepository.save(category);

      List<Car> productList = (List<Car>) this.carRepository.findAll();
      List<CarCategory> categoryList = (List<CarCategory>) this.categoryRepository.findAll();
      m.addAttribute("carList",productList);
      m.addAttribute("categoryList",categoryList);
      httpSession.setAttribute("msg",category1.getTitle()+" Added Succesfully!");
      return "admin";
   }



   @GetMapping("/category")
   public String categoryWiseProductList(Model model, @RequestParam("id") int categoryId){
      List<Car> productList = this.carRepository.findProductsByCategoryId(categoryId);
      List<CarCategory> categoryList = (List<CarCategory>) this.categoryRepository.findAll();
      model.addAttribute("carList",productList);
      model.addAttribute("categoryList",categoryList);
      return "home";
   }


}
