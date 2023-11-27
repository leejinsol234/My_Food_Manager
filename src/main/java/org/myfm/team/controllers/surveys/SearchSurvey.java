package org.myfm.team.controllers.surveys;

import lombok.Data;

@Data
public class SearchSurvey {
    private int page = 1;
    private int limit = 20;
}
