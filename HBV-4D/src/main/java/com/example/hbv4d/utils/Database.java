package com.example.hbv4d.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final String location = "src/main/resources/com/example/hbv4d/db/hbv4d.db";
    public static Connection connect() {
        // Get the current working directory (project directory)
        String currentDir = System.getProperty("user.dir");

        // Get the parent directory of the current working directory
        File currentFile = new File(currentDir);
        File parentDir = currentFile.getParentFile();
        String parentDirPath = parentDir.getAbsolutePath().replace("\\hugT","");
        Path dbPath = Paths.get(parentDirPath,"hugT", "HBV-4D", location).toAbsolutePath();

        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        String absolutePath = dbPath.toString();
        try {
            connection = DriverManager.getConnection(dbPrefix + absolutePath);
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not connect to SQLite DB at " +
                            location);
            return null;
        }
        return connection;
    }

    private static String getAbsolutePath(String path) {
        String projectDir = System.getProperty("user.dir");
        File file = new File(projectDir, path);
        return file.getAbsolutePath();
    }
}
