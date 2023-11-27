package org.myfm.team.commons.exceptions;


import org.myfm.team.commons.Utils;

public class BadRequestException extends AlertBackException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super(Utils.getMessage("BadRequest", "error"));
    }
}