package org.myfm.team.models.survey;

import org.myfm.team.commons.Utils;
import org.myfm.team.commons.exceptions.AlertBackException;
import org.springframework.http.HttpStatus;

public class ResultNotFoundException extends AlertBackException {
    public ResultNotFoundException() {
        super(Utils.getMessage("NotFound.survey.result", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}
