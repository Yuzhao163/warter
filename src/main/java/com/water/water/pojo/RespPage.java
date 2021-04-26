package com.water.water.pojo;

import java.util.List;

public class RespPage {
    private List<?> data;
    private Long total;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
