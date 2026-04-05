package com.example.ThymeLeafProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ThymeLeafProject.Model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/variableExpression")
    public String variableExpression(Model model){
        User user = new User("Nakul","poonianakul@gmail.com","Admin","male");
        model.addAttribute("user", user);
        return "variableExpression";
    }
    @GetMapping("/selectionExpression")
    public String selectionExpression(Model model){
        User user = new User("Nakul","poonianakul@gmail.com","Admin","male");
        model.addAttribute("user",user);
        return "selectionExpression";

    }
    @GetMapping("/messageExpression")
    public String messageExpression(Model model){
//        User user = new User("Nakul","poonianakul@gmail.com","Admin","male");

        return "messageExpression";


    }
    @GetMapping("linkExpression")
    public String linkExpression(Model model){
        model.addAttribute("id",1);
        return "linkExpression";

    }
    @GetMapping("/fragmentExpression")
    public String fragmentExpression(){
        return "fragmentExpression";
    }

    @GetMapping("/users")
    public String users(Model model){
        User admin =new User("nakul","abc","admin","male");
        User admin1 =new User("joshi","abcd","admin","male");
        User admin2 =new User("nikhil","abcde","admin","male");
        User admin3 =new User("vishal","abcdef","admin","male");
        List<User> users=new ArrayList<>();
        users.add(admin);
        users.add(admin1);
        users.add(admin2);
        users.add(admin3);
        model.addAttribute("users",users);
        return "users";


    }
    @GetMapping("/ifUnless")
    public String ifUnless(Model model){
        User admin =new User("nakul","abc","admin","male");
        User admin1 =new User("joshi","abcd","admin","male");
        User admin2 =new User("nikhil","abcde","admin","male");
        User admin3 =new User("vishal","abcdef","admin","male");
        List<User> users=new ArrayList<>();
        users.add(admin);
        users.add(admin1);
        users.add(admin2);
        users.add(admin3);
        model.addAttribute("users",users);
        return "ifUnless";

    }
    @GetMapping("/switchCase")
    public String switchCase(Model model){
        User user =new User("nakul","abc","admin","male");
        model.addAttribute("user",user);
        return "switchCase";
    }
}

