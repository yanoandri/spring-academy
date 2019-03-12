package com.example.academy.model.custom.response;

import java.util.List;

public class PaginateResponse {
    private int totalRow;
    private List<Object> lists;


    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }
}
