package com.naoshin.hridiautowheels.controller;

import com.naoshin.hridiautowheels.model.Car;
import com.naoshin.hridiautowheels.model.CarCategory;
import com.naoshin.hridiautowheels.model.User;
import com.naoshin.hridiautowheels.repository.CarRepository;
import com.naoshin.hridiautowheels.repository.CategoryRepository;
import com.naoshin.hridiautowheels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CarRepository carRepository;


    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("title", "login");
        return "userLoginForm";
    }


    @GetMapping("/signup")
    public String register(Model m) {
        m.addAttribute("title", "Sign up!");
        return "signUp";
    }

    @PostMapping("/loginHandler")
    public String loginhandler(@ModelAttribute("user") User user, HttpServletRequest request, Model model){
        Optional<User> user1 = Optional.ofNullable(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()));
        HttpSession httpSession = request.getSession();
        if(!user1.isPresent())
            httpSession.setAttribute("msg","Invalid details!! Try Again!!");
        else{
            httpSession.setAttribute("msg","Welcome "+user1.get().getName());

            httpSession.setAttribute("user",user1.get());

            if(user1.get().getUserType().equals("admin")){

                List<Car> carList = (List<Car>) this.carRepository.findAll();
                List<CarCategory> categoryList = (List<CarCategory>) this.categoryRepository.findAll();
                model.addAttribute("carList",carList);
                model.addAttribute("categoryList",categoryList);


//                List<User> userList = (List<User>) this.userRepository.findAll();
                List <User> userList = (List<User>) this.userRepository.findAll();
                int allProduct = (int) this.carRepository.count();
//                int allCategory = (int) this.categoryRepository.count();
                int allUsers = (int) this.userRepository.count();
                model.addAttribute("allUser",userList.size());
                model.addAttribute("allProduct",carList.size());
                model.addAttribute("allCategory",categoryList.size());

                return "admin";
            }
            else if(user1.get().getUserType().equals("normal")){
                return "userLoginForm";
            }
            else
            {
                httpSession.setAttribute("msg","We have not identified userType!!");
            }

        }


        return "userLoginForm";

    }

    @PostMapping("/registerHandler")
    public String registerHandler(@ModelAttribute("user") User user, HttpServletRequest request){
        user.setUserType("normal");
        this.userRepository.save(user);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("msg","Registration Successful!! "+user.getName());
        return "userLoginForm";
    }

    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("user");
        httpSession.setAttribute("msg","logged out successful!");
        return "userLoginForm";
    }
}



