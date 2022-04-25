package com.naoshin.hridiautowheels.controller;


import com.naoshin.hridiautowheels.model.Car;
import com.naoshin.hridiautowheels.model.CarCategory;
import com.naoshin.hridiautowheels.repository.CarRepository;
import com.naoshin.hridiautowheels.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = {"/buy"})
    public String buy(Model m){
        List<Car> carList = (List<Car>) this.carRepository.findAll();
        List<CarCategory> categoryList = (List<CarCategory>) this.categoryRepository.findAll();
        m.addAttribute("carList",carList);
        m.addAttribute("categoryList",categoryList);
        return "home";
    }

    @GetMapping(value = {"/","/home"})
    public String home(Model m){
        return "newHome";
    }



    @GetMapping(value = {"/checkout"})
    public String checkout(Model m){
        return "checkout";
    }

    @GetMapping(value = {"/success"})
    public String success(Model m){
        return "success";
    }

}
