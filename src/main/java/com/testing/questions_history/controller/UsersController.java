package com.testing.questions_history.controller;

import com.testing.questions_history.dto.UsersDto;
import com.testing.questions_history.model.Users;
import com.testing.questions_history.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @GetMapping("/create-form")
    public String showFormForCreateUser(Model model){
        model.addAttribute("user", new Users());
        return "user-create";
    }

    @PostMapping("/create")
    public String createUser(UsersDto usersDto){
        usersService.createUser(usersDto);
        return "redirect:/users?success";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Users user = usersService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, UsersDto usersDto){
        usersService.updateUser(id, usersDto);
        return "redirect:/users?update_success";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        usersService.deleteUser(id);
        return "redirect:/users?delete_success";
    }
}
