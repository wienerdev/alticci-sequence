package com.altice.alticcisequence.vo;

import java.math.BigInteger;

public class AlticciResponseVO {

    private String msg;
    private BigInteger result;

    public AlticciResponseVO(String msg, BigInteger result) {
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }

}
