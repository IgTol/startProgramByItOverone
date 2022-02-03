package by.itoverone.startProgram.controller;

import by.itoverone.startProgram.entity.User;
import by.itoverone.startProgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable(name = "id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userview";
    }

    @GetMapping("index")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/")
    public String getIndexPage2(){
        return "index";
    }

    @GetMapping("/loginMe")
    public String loginMe(@RequestParam(name = "name") String userName, Model model, HttpSession session){
        Optional<User> userOptional = userService.findByUserName(userName);
        if (userOptional.isEmpty()){
            model.addAttribute("message", "Invalid Username");
            return "index";
        }
        User user = userOptional.get();
        session .setAttribute("user", user);
        model.addAttribute("user", user);
        return "userhome";
    }

    @PostMapping("/login")
    public String loginMePost(@RequestBody MultiValueMap<String, String> params, Model model, HttpSession session){
        String userName = params.getFirst ("username");
        Optional<User> userOptional = userService.findByUserName(userName);
        if (userOptional.isEmpty()){
            model.addAttribute("message", "Invalid Username");
            return "index";
        }
        User user = userOptional.get();
        session .setAttribute("user", user);
        model.addAttribute("user", user);
        return "userhome";
    }
}
