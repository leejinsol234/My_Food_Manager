package org.myfm.team.models.member;

import org.myfm.team.commons.Utils;
import org.myfm.team.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends CommonException {

    public MemberNotFoundException() {
        super(Utils.getMessage("NotFound.member", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}
