package org.myfm.team;

import org.junit.jupiter.api.Test;
import org.myfm.team.commons.exports.InsertData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImportPublicDB {
    @Autowired
    private InsertData insertData;


    @Test
    public void process() {
        insertData.process();
    }
}
