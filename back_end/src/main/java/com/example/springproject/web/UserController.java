package com.example.springproject.web;

import com.example.springproject.Bean.UserForm;
import com.example.springproject.domain.UserInfo;
import com.example.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(UserInfo userInfo, Model model) {
        if (userInfo.getUsername().equals("")) {
            model.addAttribute("checkUsernameNull", "Username shouldn't be null");
            return "login";
        }
        if (userInfo.getPassword().equals("")) {
            model.addAttribute("checkPasswordNull", "Password shouldn't be null");
            return "login";
        }
        if (userService.checkLogin(userInfo)) {
            return "redirect:/allRecord";
        } else {
            model.addAttribute("msg", "Wrong username or password");
            return "login";
        }

    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @RequestMapping(params = "back", method = RequestMethod.POST)
    @PostMapping("/register")
    public String registerBack() {
        return "login";
    }

    @RequestMapping(params = "clear", method = RequestMethod.POST)
    @PostMapping("/register")
    public String registerClear() {
        return "register";
    }

    @RequestMapping(params = "submit", method = RequestMethod.POST)
    @PostMapping("/register")
    public String registerUser(@Valid UserForm userForm, BindingResult result, RedirectAttributes attributes) {
        attributes.addFlashAttribute("username", userForm.getUsername());
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            attributes.addFlashAttribute("error_msg", errors.get(0).getDefaultMessage());
            return "redirect:/register";
        } else if (!userForm.checkPasswordEquals()) {
            attributes.addFlashAttribute("error_msg", "Two password should be same");
            return "redirect:/register";
        } else {
            if (userService.findByUsername(userForm.getUsername()) != null) {
                attributes.addFlashAttribute("error_msg", "username has been registered, please change");
                return "redirect:/register";
            } else {
                userService.save(userForm.convertToUser());
                return "redirect:/login";
            }

        }
    }
}
