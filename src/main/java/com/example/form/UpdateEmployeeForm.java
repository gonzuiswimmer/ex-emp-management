package com.example.form;

import jakarta.validation.constraints.NotEmpty;

public class UpdateEmployeeForm {
  /** ID */
  private String id;
  /** 扶養人数 */
  @NotEmpty(message = "扶養人数を指定してください")
  private String dependentsCount;

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
  @Override
  public String toString() {
    return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
  }
  
}
