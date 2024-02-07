package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepostitory;

@Service
@Transactional
public class EmployeeService {
  @Autowired
  private EmployeeRepostitory repostitory;

  /**
   * 従業員一覧を取得する.
   * 
   * @return List<Employee> 従業員リスト
   */
  public List<Employee> showList(){
    return repostitory.findAll();
  }

  /**
   * IDに紐づいた従業員情報を取得する.
   * 
   * @param id
   * @return Employee 従業員オブジェクト
   */
  public Employee showDetail(Integer id){
    return repostitory.load(id);
  }
}