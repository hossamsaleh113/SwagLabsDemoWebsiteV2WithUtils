package com.swaglabs.utils;


import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.apache.commons.io.FileUtils.copyFile;


public class FilesUtils {
    private FilesUtils() {
        super();
    }

    public static void deleteFiles(File dirPath) {
        if (dirPath == null || !dirPath.exists()) {
            LogsManager.warn("Directory doesn't exist" + dirPath);
            return;
        }
        File[] filesList = dirPath.listFiles();
        if (filesList == null) {
            LogsManager.warn("Directory is empty" + dirPath);
            return;
        }
        for (File file : filesList) {
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsManager.error("Failed to delete File" + file);
                }
            }
        }
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogsManager.warn("Folder is empty" + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void openFile(String filePath) {
        try {
            File file = new File(filePath);
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            LogsManager.error("File does not exist" + e.getMessage());
        }
    }

    public static void cleanDirectory(File file) {
        try {
            FileUtils.deleteQuietly(file);

        } catch (Exception e) {
            LogsManager.error("Failed to clean directory", file + e.getMessage());
        }
    }

//    public static void renameFile(File oldName, File newName) {
//        try {
//            File targetFile = oldName.getParentFile().getAbsoluteFile();
//            String targetDirectory = AllureConstants.REPORT_PATH + newName.getName();
//            FileUtils.copyFile(oldName, new File(targetDirectory));
//            FileUtils.deleteQuietly(oldName);
//            LogsManager.info("Target file path" , targetFile.getName() , "was renamed to" , newName.getName());
//        } catch (Exception e) {
//            LogsManager.error("Failed to copy file" + e.getMessage());
//        }
//    }
//

    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                FilesUtils.cleanDirectory(targetFile);
                LogsManager.info("Target File Path: " + oldName + ", file was renamed to " + newName + ".");
            } else {
                LogsManager.info(("Target File Path: " + oldName + ", already has the desired name " + newName + "."));
            }
        } catch (IOException e) {
        }
    }
}
