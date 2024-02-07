package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;


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
    
}
