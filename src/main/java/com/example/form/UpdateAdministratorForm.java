package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateAdministratorForm {
   /** 名前 */
  @NotBlank(message = "名前は必須です")
  @Size(min = 1, max = 20, message = "名前は1文字以上20文字以内で記載してください")
  private String name;
  /** メールアドレス */
  @NotBlank(message = "メールアドレスは必須です")
  @Email(message = "Eメールの形式が不正です")
  private String mailAddress;
  /** パスワード */
  @NotBlank(message = "パスワードは必須です")
  @Size(min = 8, message = "パスワードは8文字以上で記載してください")
  @Pattern(regexp = "[a-zA-Z0-9]*", message = "パスワードは英数字で記載してください")
  private String password;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getMailAddress() {
    return mailAddress;
  }
  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  @Override
  public String toString() {
    return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
  }

}
