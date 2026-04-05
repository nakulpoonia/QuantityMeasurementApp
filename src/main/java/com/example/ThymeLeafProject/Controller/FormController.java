package com.example.ThymeLeafProject.Controller;

import com.example.ThymeLeafProject.Model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class FormController {

    @GetMapping("register")
    public String userRegistration(Model model){
        UserForm userform =new UserForm();
        model.addAttribute("userForm",userform);

        List<String> listProf= Arrays.asList("developer","tester","architect");
        model.addAttribute("listProfession",listProf);
        return "registerForm";


    }
    @PostMapping("register/save")
    public String submitForm(Model model, @ModelAttribute("UserForm") UserForm userForm){
        model.addAttribute("userForm",userForm);
        return "registerSucceess";




    }




}
