package tc;

/*
 * Copyright Medtronic, Inc. 2020
 * MEDTRONIC CONFIDENTIAL - This document is the property of Medtronic,
 * Inc., and must be accounted for. Information herein is confidential. Do
 * not reproduce it, reveal it to unauthorized persons, or send it outside
 * Medtronic without proper authorization.
 */

import org.testcontainers.containers.MySQLContainer;

import java.util.Collections;

public class MySQLTestUtils {

    public static MySQLContainer<?> mysqlContainerFactory(
        String imageName,   // e.g. "mysql:5.7"
        String initScriptClasspath
    ) {
        return new MySQLContainer<>(imageName)
            .withInitScript(initScriptClasspath)
            .withTmpFs(Collections.singletonMap("/var/lib/mysql", "rw"));
    }

}
