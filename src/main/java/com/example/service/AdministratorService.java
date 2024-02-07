package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
  @Autowired
  private AdministratorRepository repository;

  /**
   * 管理者情報の登録処理.
   * 
   * @param admin 管理者オブジェクト
   * @return void
   */
  public void insert(Administrator admin){
    repository.insert(admin);
  }
  
  /**
   * メールアドレスとパスワードをもとにログイン処理を行う.
   * 
   * @param mailAddress
   * @param password
   * @return Administrator 条件に合致した管理者オブジェクト
   */
  public Administrator login(String mailAddress, String password){
    return repository.findByMailAddressAndPassword(mailAddress, password);
  }
}
