package com.herokuapp.bportal.controllers;

import com.herokuapp.bportal.common.Pager;
import com.herokuapp.bportal.entities.Account;
import com.herokuapp.bportal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController extends AbstractController {

    public static final String HOME_VIEW = "index";

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public ModelAndView index(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order", required = false) String sortName,
            @RequestParam(value = "direction", required = false) String sortDir) {
        ModelAndView mav = new ModelAndView("index");
        List<Account> accounts = accountRepository.findAll();
        Pager<Account> pager = manualBuildPager(accounts, pageIndex, pageSize);
        mav.addObject("accounts", accounts);
        mav.addObject("pager", pager);
        return mav;
    }

}
