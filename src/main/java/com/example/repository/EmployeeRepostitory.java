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
  //   Employee emp = new Employee();
  //   emp.setName(rs.getString("name"));
  //   emp.setImage(rs.getString("iamge"));
  //   emp.setGender(rs.getString("gender"));
  //   emp.setHireDate(rs.getDate("hire_date"));
  //   emp.setMailAddress(rs.getString("mail_address"));
  //   emp.setZipCode(rs.getString("zip_code"));
  //   emp.setTelephone(rs.getString("telephone"));
  //   emp.setSalary(rs.getInt("saraly"));
  //   emp.setCharacteristics(rs.getString("characteristics"));
  //   emp.setDependentsCount(rs.getInt("dependents_count"));
  //   return emp;
  // };
  new BeanPropertyRowMapper<>(Employee.class);

  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final String FIND_ALL_SQL = """
    SELECT name, image, gender, hire_date, mail_address, zip_code, telephone, saraly, characteristics, dependents_count
      FROM employees
        ORDER BY hire_date DESC;
  """;
  
  private static final String LOAD_SQL = """
    SELECT name, image, gender, hire_date, mail_address, zip_code, telephone, saraly, characteristics, dependents_count
      FROM employees
        WHERE id = :id ;
  """;

  private static final String UPDATE_SQL = """
    INSERT INTO employees (name, image, gender, hire_date, mail_address, zip_code, telephone, saraly, characteristics, dependents_count)
      VALUES (:name, :image, :gender, :hire_date, :mail_address, :zip_code, :telephone, :saraly, :characteristics, :dependents_count)
        WHERE id = :id ;
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
    SqlParameterSource param = new BeanPropertySqlParameterSource(emp);
    template.update(UPDATE_SQL, param);
  }
}
