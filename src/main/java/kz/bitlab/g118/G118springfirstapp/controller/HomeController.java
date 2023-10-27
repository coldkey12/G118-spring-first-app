package kz.bitlab.g118.G118springfirstapp.controller;

import jdk.jfr.Category;
import kz.bitlab.g118.G118springfirstapp.db.DbManager;
import kz.bitlab.g118.G118springfirstapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        List<User> users = DbManager.getUsers();
        model.addAttribute("users",users);
        return "home";
    }

    @PostMapping("/add-user")
    public String addUser(User user) {
        DbManager.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/user-details")
    public String getUserDetails(@RequestParam(name = "user_id") Long id,
                                 Model model) {
        User user = DbManager.getUserById(id);
        model.addAttribute("user", user);
        return "userDetails";
    }
}
