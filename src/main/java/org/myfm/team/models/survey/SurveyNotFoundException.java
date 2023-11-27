package org.myfm.team.models.survey;

import org.myfm.team.commons.Utils;
import org.myfm.team.commons.exceptions.AlertBackException;
import org.springframework.http.HttpStatus;

public class SurveyNotFoundException extends AlertBackException {
    public SurveyNotFoundException() {
        super(Utils.getMessage("NotFound.survey", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}
