package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * 管理者のDBを操作するRepository.
 * 
 */
@Repository
public class AdministratorRepository {

  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
    Administrator admin = new Administrator();
    admin.setId(rs.getInt("id"));
    admin.setName(rs.getString("name"));
    admin.setMailAddress(rs.getString("mail_address"));
    admin.setPassword(rs.getString("password"));

    return admin;
  };

  private static final String INSERT_SQL = """
    INSERT INTO administrators
      (name, mail_address, password)
        VALUES (:name, :mail_address, :password);
  """;

  private static final String SEARCH_SQL = """
    SELECT id, name, mail_address, password
      FROM administrators
        WHERE mail_address = :mail_address
          AND password = :password;
  """;

  private static final String UPDATE_SQL = """
    UPDATE administrators SET
      name = :name,
      mail_address = :mail_address,
      password = :password
        WHERE id = :id;
  """;

  /**
   * 管理者登録処理.
   * 
   * @param admin 管理者クラス.
   * @return void
   */
  public void insert(Administrator admin){
    SqlParameterSource param = new MapSqlParameterSource().addValue("name", admin.getName()).addValue("mail_address", admin.getMailAddress()).addValue("password", admin.getPassword());
    template.update(INSERT_SQL, param);
  }

  /**
   * メールアドレスとパスワードが合致する管理者を検索して返す.
   * 
   * @param mailAddress String
   * @param password String
   * @return Administrator or void
   */
  public Administrator findByMailAddressAndPassword(String mailAddress, String password){
    SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress).addValue("password", password);
    List<Administrator> admin_list = template.query(SEARCH_SQL, param, ADMINISTRATOR_ROW_MAPPER);
    if(admin_list.size() == 0) {
      return null;
    }
    return admin_list.get(0);
  }

    /**
   * 管理者更新処理.
   * 
   * @param admin 管理者クラス.
   * @return void
   */
  public void update(Administrator admin){
    SqlParameterSource param = new MapSqlParameterSource()
    .addValue("id", admin.getId())
    .addValue("name", admin.getName())
    .addValue("mail_address", admin.getMailAddress())
    .addValue("password", admin.getPassword());
    
    template.update(UPDATE_SQL, param);
  }
}
