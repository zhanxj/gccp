package com.srh.gccp.common.exception;

/**
 * Created by hokin.jim on 2014/12/18.
 */
public class GccpException extends Exception {
    public GccpException(String message) {
        super(message);
    }

    public GccpException(String message, Throwable cause) {
        super(message, cause);
    }
}
