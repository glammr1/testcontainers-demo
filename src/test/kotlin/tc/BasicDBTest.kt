package tc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.sql.DriverManager
import java.time.LocalDate

/*
 * Copyright Medtronic, Inc. 2020
 * MEDTRONIC CONFIDENTIAL - This document is the property of Medtronic,
 * Inc., and must be accounted for. Information herein is confidential. Do
 * not reproduce it, reveal it to unauthorized persons, or send it outside
 * Medtronic without proper authorization.
 */

data class Foo(val id: Long, val text: String)
data class Bar(val id: Long, val sample: Int, val sampleDate: LocalDate)

@Testcontainers
class BasicDBTest {

    companion object {
        @Container
        @JvmStatic
        val mysql = MySQLTestUtils.mysqlContainerFactory("mysql:5.7", "db/init.sql")
    }

    @Test
    fun fooTest() {
        val jdbcUrl = mysql.getJdbcUrl()
        val username = mysql.getUsername()
        val password = mysql.getPassword()

        val connection = DriverManager.getConnection(jdbcUrl, username, password)
        val stmt = connection.createStatement()
        val rs = stmt.executeQuery("SELECT id, text FROM foo")
        val foos = sequence {
            while (rs.next()) {
                yield(Foo(rs.getLong("id"), rs.getString("text")))
            }
        }.toList()
        assertEquals(2, foos.size)
        foos.forEach { f -> println("Foo: $f") }
        rs.close()
        stmt.close()
        connection.close()
    }

    @Test
    fun barTest() {
        val jdbcUrl = mysql.getJdbcUrl()
        val username = mysql.getUsername()
        val password = mysql.getPassword()

        val connection = DriverManager.getConnection(jdbcUrl, username, password)
        val stmt = connection.createStatement()
        val rs = stmt.executeQuery("SELECT id, sample, sample_date FROM bar WHERE sample > 245")
        val bars = sequence {
            while (rs.next()) {
                yield(Bar(rs.getLong("id"), rs.getInt("sample"), LocalDate.parse(rs.getString("sample_date"))))
            }
        }.toList()
        assertEquals(1, bars.size)
        bars.forEach { b -> println("Bar: $b") }
        rs.close()
        stmt.close()
        connection.close()
    }
}
