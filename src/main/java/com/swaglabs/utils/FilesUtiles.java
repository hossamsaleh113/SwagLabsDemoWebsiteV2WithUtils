package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtiles {
    private FilesUtiles(){
        super();
    }

    public static void deleteFiles(File dirPath){
        if(dirPath == null || !dirPath.exists()){
            LogsUtils.warn("Directory doesn't exist" + dirPath);
            return;
        }
        File[] filesList = dirPath.listFiles();
        if(filesList == null){
            LogsUtils.warn("Directory is empty" + dirPath);
            return;
        }
        for (File file : filesList){
            if(file.isDirectory()){
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());
                }catch (IOException e){
                    LogsUtils.error("Failed to delete File" + file);
                }
            }
        }
    }

    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File [] files = folder.listFiles();
        if(files == null || files.length == 0){
            LogsUtils.warn("Folder is empty" + folderPath);
            return null;
        }
        File latestFile = files[0];
        for(File file : files){
            if(file.lastModified() > latestFile.lastModified()){
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void cleanDirectory(File file){
        try{
            FileUtils.deleteQuietly(file);

        } catch (Exception e) {
            LogsUtils.error("Failed to clean directory" , file + e.getMessage());
        }
    }
}
