package com.maxim.application.dao;


public class pageStockDayKRequest extends stockDayK{
    private Integer pageSize = 10;
    private Integer pageNumber = 1;

    public pageStockDayKRequest(){
        super();
    }

    public pageStockDayKRequest(Integer pageSize, Integer pageNumber){
        super();
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
