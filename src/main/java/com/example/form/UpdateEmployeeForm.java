package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public class UpdateEmployeeForm {
  /** ID */
  private String id;
  /** 扶養人数 */
  @NotEmpty(message = "扶養人数を指定してください")
  private String dependentsCount;
  /** 名前 */
  @NotEmpty(message = "名前は必須です")
  private String name;
  /** 画像 */
  private String image;
  /** 性別 */
  private String gender;
  /** 入社日 */
  private Date hireDate;
  /** メールアドレス */
  @NotEmpty(message = "メールアドレスは必須です")
  @Email
  private String mailAddress;
  /** 郵便番号 */
  @NotEmpty(message = "郵便番号は必須です")
  @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号形式にしてください")
  private String zipCode;
  /** 住所 */
  @NotEmpty(message = "住所は必須です")
  private String address;
  /** 電話番号 */
  @NotEmpty(message = "電話番号は必須です")
  private String telephone;
  /** 給料 */
  @NotNull(message = "給料は必須です")
  private Integer salary;
  /** 特性 */
  @NotEmpty(message = "特徴を記入してください")
  private String characteristics;


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getDependentsCount() {
    return dependentsCount;
  }
  public void setDependentsCount(String dependentsCount) {
    this.dependentsCount = dependentsCount;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public Date getHireDate() {
    return hireDate;
  }
  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }
  public String getMailAddress() {
    return mailAddress;
  }
  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }
  public String getZipCode() {
    return zipCode;
  }
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getTelephone() {
    return telephone;
  }
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
  public Integer getSalary() {
    return salary;
  }
  public void setSalary(Integer salary) {
    this.salary = salary;
  }
  public String getCharacteristics() {
    return characteristics;
  }
  public void setCharacteristics(String characteristics) {
    this.characteristics = characteristics;
  }
  @Override
  public String toString() {
    return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + ", name=" + name + ", image="
        + image + ", gender=" + gender + ", hireDate=" + hireDate + ", mailAddress=" + mailAddress + ", zipCode="
        + zipCode + ", address=" + address + ", telephone=" + telephone + ", salary=" + salary + ", characteristics="
        + characteristics + "]";
  }
  
  
}
