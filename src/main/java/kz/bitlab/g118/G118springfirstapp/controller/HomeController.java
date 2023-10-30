package kz.bitlab.g118.G118springfirstapp.controller;

import jdk.jfr.Category;
import kz.bitlab.g118.G118springfirstapp.db.DbManager;
import kz.bitlab.g118.G118springfirstapp.model.User;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        List<User> users = DbManager.getUsers();
        model.addAttribute("users", users);
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

//    @PostMapping("/update-user-details")
//    public String updateUserDetails(@RequestParam(name = "id") Long id, User user) {
//        user.setId(id);
//        DbManager.updateUser(user);
//        return "redirect:/";
//    }
//
//    @PostMapping("/delete-user-details")
//    public String deleteUserDetails(@RequestParam(name = "id") Long id) {
//        DbManager.deleteUserById(id);
//        return "redirect:/";
//    }

    @PostMapping("/user-edit/{id}")
    public String editUser(@RequestParam String email,
                           @RequestParam(name = "fullName") String fullName,
                           @PathVariable Long id) {
        if (fullName == "" || fullName.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        DbManager.editUser(id, email, fullName);
        return "redirect:/";
    }

    @PostMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        DbManager.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String search,
                         Model model) {
        List<User> users = DbManager.findUsers(search);
        model.addAttribute("users",users);
        return "home";
    }

    @GetMapping("/search-alt")
    public String searchAlt(@RequestParam String search,
                         Model model) {
        String users = DbManager.findUsersAlt(search);
        model.addAttribute("usersAlt",users);
        return "home";
    }
}
