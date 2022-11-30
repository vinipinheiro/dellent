package com.challenge.alticci.dto;

import java.math.BigInteger;

public class AlticciResponseDTO {

    private BigInteger result;

    public AlticciResponseDTO(BigInteger result) {
        this.result = result;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }

}
