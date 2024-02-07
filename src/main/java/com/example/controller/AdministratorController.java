package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class AdministratorController {
  @Autowired
  private AdministratorService service;
  @Autowired
  private HttpSession session;

  /**
   * ログイン画面にフォワード.
   * 
   * @param form
   * @return ログイン画面
   */
  @GetMapping("/")
  public String toLogin(LoginForm forml) {
    return "administrator/login";
  }
  

  /**
   * フォーム情報を登録し、管理者登録画面へフォワード.
   * 
   * @param form
   * @return 管理者登録画面
   */
  @GetMapping("/toInsert")
  public String toInsert(InsertAdministratorForm form, Model model) {
    return "administrator/insert";
  }

  /**
   * 管理者情報を登録し、/ にリダイレクト.
   * 
   * @param form
   * @return / リダイレクト
   */
  @PostMapping("/insert")
  public String insert(
    @Validated
    InsertAdministratorForm form,
    BindingResult rs,
    Model model
  ) {
    if(rs.hasErrors()){
      return toInsert(form, model);
    }
    Administrator admin = new Administrator();
    BeanUtils.copyProperties(form, admin);

    service.insert(admin);
    return "redirect:/";
  }
  
  /**
   * ログイン処理.
   * 
   * @param form
   * @param model
   * @return
   */
  @PostMapping("/login")
  public String login(LoginForm form, Model model) {
    Administrator admin = service.login(form.getMailAddress(), form.getPassword());
    if(admin == null){
      model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
      System.out.println("エラー発生");
      return "administrator/login";
    } else {
      session.setAttribute("administratorName", admin.getName());
      return "redirect:/employee/showList";
    }
  }

  /**
   * ログアウト処理.
   * 
   * @param form
   * @return
   */
  @GetMapping("/logout")
  public String logout(LoginForm form) {
    session.invalidate();
    return "redirect:/";
  }
  
}
