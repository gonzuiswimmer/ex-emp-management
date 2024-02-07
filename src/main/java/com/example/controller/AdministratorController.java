package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/")
public class AdministratorController {
  @Autowired
  private AdministratorService service;

  /**
   * ログイン画面にフォワード.
   * 
   * @param form
   * @return ログイン画面
   */
  @GetMapping("/")
  public String toLogin(LoginForm form) {
    return "administrator/login";
  }
  

  /**
   * フォーム情報を登録し、管理者登録画面へフォワード.
   * 
   * @param form
   * @return 管理者登録画面
   */
  @GetMapping("/toInsert")
  public String toInsert(InsertAdministratorForm form) {
    return "administrator/insert";
  }

  /**
   * 管理者情報を登録し、/ にリダイレクト.
   * 
   * @param form
   * @return / リダイレクト
   */
  @PostMapping("/insert")
  public String insert(InsertAdministratorForm form) {
    Administrator admin = new Administrator();
    BeanUtils.copyProperties(form, admin);

    service.insert(admin);
    return "redirect:/";
  }
  
  
}
