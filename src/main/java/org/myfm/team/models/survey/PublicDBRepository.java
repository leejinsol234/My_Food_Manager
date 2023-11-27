package org.myfm.team.models.survey;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.myfm.team.entities.PublicDB;
import org.myfm.team.entities.QPublicDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PublicDBRepository extends JpaRepository<PublicDB, Long>, QuerydslPredicateExecutor<PublicDB> {
    /**
     * 부족 영양소별 추천 음식 조회
     *
     */
    default Map<String, String[]> getRecommendFoods(List<String> ingredients) {
        Map<String, String[]> data = new HashMap<>();
        QPublicDB publicDB = QPublicDB.publicDB;
        for (String ingredient : ingredients) {
            BooleanExpression conds = null;
            if (ingredient.equals("CA")) {
                 conds = publicDB.ca.goe(600);
            } else if (ingredient.equals("FE")) {
                conds = publicDB.fe.goe(20);
            } else if (ingredient.equals("MG")) {
                conds = publicDB.mg.goe(150);
            } else if (ingredient.equals("ZN")) {
                conds = publicDB.zn.goe(10);
            } else if (ingredient.equals("VA")) {
                conds = publicDB.va.goe(200);
            } else if (ingredient.equals("VB1")) {
                conds = publicDB.vb1.goe(3);
            } else if (ingredient.equals("VB2")) {
                conds = publicDB.vb2.goe(1.5);
            } else if (ingredient.equals("VC")) {
                conds = publicDB.vc.goe(40);
            }

            List<PublicDB> items = (List<PublicDB>)findAll(conds);
            Collections.shuffle(items);
            String[] foods = items.stream().limit(5).map(PublicDB::getFoodNm).toArray(String[]::new);

            data.put(ingredient, foods);
        }

        return data;
    }
}
