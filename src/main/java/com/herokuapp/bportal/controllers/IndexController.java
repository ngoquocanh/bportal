package com.herokuapp.bportal.controllers;

import com.herokuapp.bportal.entities.Account;
import com.herokuapp.bportal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        List<Account> accounts = accountRepository.findAll();
        mav.addObject("accounts", accounts);
        return mav;
    }
}
