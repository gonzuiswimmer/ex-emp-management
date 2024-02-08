package com.example.domain;

public class Pagination {
  /** どこから表示するか */
  private int pageFrom;
  /** どこまで表示するか */
  private int pageTo;
  /** 現在のページ */
  private int currentPage;
  /** 1ページ当たりの表示件数 */
  private int recordPerPage;
  /** 表示するアイテムの総数 */
  private int totalRecordCount;
  /** ページの総数 */
  private int totalPageCount;

  public Pagination(int recordPerPage, int totalRecordCount) {
    this.recordPerPage = recordPerPage;
    this.totalRecordCount = totalRecordCount;

    // 計算の余りが0異常であれば、1を足して総ページ数を求める
    if(this.totalRecordCount % this.recordPerPage > 0){
      this.totalPageCount = this.totalRecordCount / this.recordPerPage + 1;
    } else {
      this.totalPageCount = this.totalRecordCount / this.recordPerPage;
    }
  }
  public int getPageFrom() {
    return pageFrom;
  }
  public void setPageFrom(int pageFrom) {
    this.pageFrom = pageFrom;
  }
  public int getPageTo() {
    return pageTo;
  }
  public void setPageTo(int pageTo) {
    this.pageTo = pageTo;
  }
  public int getCurrentPage() {
    return currentPage;
  }
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
  public int getRecordPerPage() {
    return recordPerPage;
  }
  public void setRecordPerPage(int recordPerPage) {
    this.recordPerPage = recordPerPage;
  }
  public int getTotalRecordCount() {
    return totalRecordCount;
  }
  public void setTotalRecordCount(int totalRecordCount) {
    this.totalRecordCount = totalRecordCount;
  }
  public int getTotalPageCount() {
    return totalPageCount;
  }
  public void setTotalPageCount(int totalPageCount) {
    this.totalPageCount = totalPageCount;
  }
  @Override
  public String toString() {
    return "Pagination [pageFrom=" + pageFrom + ", pageTo=" + pageTo + ", currentPage=" + currentPage
        + ", recordPerPage=" + recordPerPage + ", totalRecordCount=" + totalRecordCount + ", totalPageCount="
        + totalPageCount + "]";
  }

  public void moveTo(int page){
    this.currentPage = page;

    this.pageFrom = Math.max(page-1, 2);
    if(this.totalPageCount > 3){
      this.pageTo = Math.min(this.pageFrom + 2, this.totalPageCount - 1);
    } else {
      this.pageTo = this.totalPageCount;
    }
    this.pageFrom = Math.max(this.pageTo - 2, 2);
  }

  
}
