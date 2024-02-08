package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
  @Autowired
  private EmployeeService service;
  @Autowired
  private HttpSession session;
  
  /**
   * 従業員一覧画面にフォワード.
   * 
   * @param model
   * @return 従業員一覧画面
   */
  @GetMapping("/showList")
  public String showList(Model model) {
    List<Employee> emp_list = service.showList();
    model.addAttribute("employeeList", emp_list);

    if(isLogin()){
      return "employee/list";
    } else{
      return "redirect:/";
    }
  }

  @GetMapping("/showDetail")
  public String showDetail(String id, Model model, UpdateEmployeeForm form) {
    Employee emp = service.showDetail(Integer.parseInt(id));
    BeanUtils.copyProperties(emp, form);
    form.setDependentsCount(emp.getDependentsCount().toString());
    
    if(isLogin()){
      return "employee/detail";
    } else{
      return "redirect:/";
    }
  }
  
  /**
   * 扶養人数を更新し、一覧画面にリダイレクト.
   * 
   * @param form
   * @return 従業員一覧画面へリダイレクト
   */
  @PostMapping("/update")
  public String update(
    @Validated
    UpdateEmployeeForm form,
    BindingResult rs,
    Model model) {
      if (rs.hasErrors()) {
        System.out.println("エラーの発生");
        return showDetail(form.getId(),model,form);
      }
      Employee emp = service.showDetail(Integer.parseInt(form.getId()));
      emp.setName(form.getName());
      emp.setGender(form.getGender());
      emp.setHireDate(form.getHireDate());
      emp.setMailAddress(form.getMailAddress());
      emp.setZipCode(form.getZipCode());
      emp.setAddress(form.getAddress());
      emp.setTelephone(form.getTelephone());
      emp.setSalary(form.getSalary());
      emp.setCharacteristics(form.getCharacteristics());
      emp.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
      
      service.Update(emp);

      if(isLogin()){
        return "redirect:/employee/showList";
      }else{
        return "redirect:/";
      }
    }

  /**
   * ログイン済みかどうかを判定する.
   * 
   * @return boolean
   */
  public boolean isLogin(){
    Administrator admin = (Administrator) session.getAttribute("admin");
    if(admin == null){
      return false;
    }
    return true;
  }
}
