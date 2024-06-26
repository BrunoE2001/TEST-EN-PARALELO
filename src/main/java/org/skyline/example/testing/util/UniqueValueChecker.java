package org.skyline.example.testing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UniqueValueChecker implements IUniqueValueChecker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsGameWithTitle(String tableName, String columnName, String value) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, value);

        return count != null && count > 0;
    }
}
