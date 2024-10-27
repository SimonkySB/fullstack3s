package com.simonky.fullstacks.dtos;

import java.math.BigDecimal;

public class ComprarProductosResponse {
    private BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
