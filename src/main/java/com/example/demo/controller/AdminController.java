package com.example.demo.controller;

import com.example.demo.model.EntryMainPage;
import com.example.demo.model.MetaFileDB;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.EntryServiceImpl;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.MetaFileDbService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController{


    @Autowired
    private UserService service;

    @Autowired
    private EntryServiceImpl serviceEntry;

    @Autowired
    private MetaFileDbService offerService;

    @Autowired
    private FileStorageService storeService;

    @RequestMapping
    public ModelAndView adminPage() {
        List<Role> listRoles = service.getAllRole();

        List<EntryMainPage> listEntry = serviceEntry.getAllEntry();
        List<User> users = service.getAllUser();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> rolesUser =(List<Role>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        user.setRoles(rolesUser);
        List<MetaFileDB> listOffer = offerService.getAllFiles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userList", users);
        modelAndView.addObject("listRoles", listRoles);
        modelAndView.addObject("listEntry", listEntry);
        modelAndView.addObject("listOffer", listOffer);
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        List<Role> listRoles = service.getAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listRoles", listRoles);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user, @RequestParam("checkboxRole") String[] checkboxRoles) {
        List<Role> listRoles = service.getAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        List<Role> oldRoles = new ArrayList<>();
        for (int i = 0; i < checkboxRoles.length; i++) {
            oldRoles.add(new Role(checkboxRoles[i]));
        }
        user.setRoles(oldRoles);
        service.edit(user, checkboxRoles);
        return modelAndView;
    }


}
