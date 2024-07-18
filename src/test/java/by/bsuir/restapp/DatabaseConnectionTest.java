package by.bsuir.restapp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDatabaseConnection() {
        try {
            Connection connection = dataSource.getConnection();
            assertNotNull(connection, "Database connection should not be null");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
