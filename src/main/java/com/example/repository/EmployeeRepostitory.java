package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepostitory {
  private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =
  //  (rs, i) -> {
    // Employee emp = new Employee();
    // emp.setId(rs.getInt("id"));
    // emp.setName(rs.getString("name"));
    // emp.setImage(rs.getString("iamge"));
    // emp.setGender(rs.getString("gender"));
    // emp.setHireDate(rs.getDate("hire_date"));
    // emp.setMailAddress(rs.getString("mail_address"));
    // emp.setZipCode(rs.getString("zip_code"));
    // emp.setAddress(rs.getString("address"));
    // emp.setTelephone(rs.getString("telephone"));
    // emp.setSalary(rs.getInt("salary"));
    // emp.setCharacteristics(rs.getString("characteristics"));
    // emp.setDependentsCount(rs.getInt("dependents_count"));
  //   return emp;
  // };
  new BeanPropertyRowMapper<>(Employee.class);

  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final String FIND_ALL_SQL = """
    SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count
      FROM employees
        ORDER BY hire_date DESC;
  """;
  
  private static final String LOAD_SQL = """
    SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count
      FROM employees
        WHERE id = :id;
  """;

  private static final String UPDATE_SQL = """
    UPDATE employees SET 
      name = :name,
      image = :image,
      gender = :gender,
      hire_date = :hire_date,
      mail_address = :mail_address,
      zip_code = :zip_code,
      address = :address,
      telephone = :telephone,
      salary = :salary,
      characteristics = :characteristics,
      dependents_count = :dependents_count
        WHERE id = :id;
  """;

  /**
   * 管理者一覧を取得する.
   * 
   * @return List<Employee> 管理者リスト
   */
  public List<Employee> findAll(){
    SqlParameterSource param = new MapSqlParameterSource();
    return template.query(FIND_ALL_SQL, param, EMPLOYEE_ROW_MAPPER);
  }

  /**
   * idをもとに該当の管理者情報を返す.
   * 
   * @param id ID
   * @return  Employee 管理者オブジェクト
   */
  public Employee load(Integer id){
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    return template.queryForObject(LOAD_SQL, param, EMPLOYEE_ROW_MAPPER);
  }

  /**
   * 管理者情報を更新する.
   * 
   * @param emp 管理者オブジェクト
   * @return void
   */
  public void update(Employee emp){
    SqlParameterSource param = new MapSqlParameterSource()
    .addValue("id", emp.getId())
    .addValue("name", emp.getName())
    .addValue("image", emp.getImage())
    .addValue("gender", emp.getGender())
    .addValue("hire_date", emp.getHireDate())
    .addValue("mail_address", emp.getMailAddress())
    .addValue("zip_code", emp.getZipCode())
    .addValue("address", emp.getAddress())
    .addValue("telephone", emp.getTelephone())
    .addValue("salary", emp.getSalary())
    .addValue("characteristics", emp.getCharacteristics())
    .addValue("dependents_count", emp.getDependentsCount());
    template.update(UPDATE_SQL, param);
  }
}
