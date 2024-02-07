package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/employee")
public class EmployeeController {
  @Autowired
  private EmployeeService service;
  
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
    return "employee/list";
  }

  @GetMapping("/showDetail")
  public String showDetail(String id, Model model, UpdateEmployeeForm form) {
    Employee emp = service.showDetail(Integer.parseInt(id));
    model.addAttribute("employee", emp);
    return "employee/detail";
  }
  
  /**
   * 扶養人数を更新し、一覧画面にリダイレクト.
   * 
   * @param form
   * @return 従業員一覧画面へリダイレクト
   */
  @GetMapping("/update")
  public String update(UpdateEmployeeForm form) {
    Employee emp = service.showDetail(Integer.parseInt(form.getId()));
    emp.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
    service.Update(emp);
    return "redirect:/employee/showList";
  }
  
    
}
