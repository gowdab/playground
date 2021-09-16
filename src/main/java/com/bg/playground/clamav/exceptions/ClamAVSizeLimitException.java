package com.bg.playground.clamav.exceptions;

public class ClamAVSizeLimitException extends  RuntimeException{

    public ClamAVSizeLimitException(String msg) {
        super(msg);
    }
}