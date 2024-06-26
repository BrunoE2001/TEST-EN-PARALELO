package org.skyline.example.testing.util;

public interface IUniqueValueChecker {
    boolean existsGameWithTitle(String tableName, String columnName, String value);
}
