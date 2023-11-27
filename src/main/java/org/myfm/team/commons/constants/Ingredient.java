package org.myfm.team.commons.constants;

import java.util.Arrays;
import java.util.List;

public enum Ingredient {
    CA("칼슘"),
    VA("레티놀"),
    FE("철분"),
    MG("마그네슘"),
    VB1("비타민 B1"),
    VB2("비타민 B2"),
    VC("비타민 C"),
    ZN("아연");

    private String title;

    Ingredient(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static List<String[]> getList() {

        return Arrays.asList(
                new String[] { CA.name(), CA.title },
                new String[] { VA.name(), VA.title },
                new String[] { FE.name(), FE.title },
                new String[] { MG.name(), MG.title },
                new String[] { VB1.name(), VB1.title },
                new String[] { VB2.name(), VB2.title },
                new String[] { VC.name(), VC.title },
                new String[] { ZN.name(), ZN.title }
        );
    }
}
