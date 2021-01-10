package jrm.utils;

import lombok.extern.java.Log;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.stereotype.Component;

import java.io.File;


@Component
@Log
public class ArchiveFile {

    private final String PLACEHOLDER_FOR_ZIP_NAME = ".zip";
    private final String TARGET_FOLDER = "javaTask/";
    private final String DIR_FOR_SAVE = "src/main/resources/javatask/";

    public String createArchiveForJavaTask(String folderNameOfTask) {
        String fullPathForSave = DIR_FOR_SAVE + folderNameOfTask + PLACEHOLDER_FOR_ZIP_NAME;
        try {
            new ZipFile(fullPathForSave)
                    .addFolder(new File(TARGET_FOLDER + folderNameOfTask));
            log.severe("ARCHIVE HAVE BEEN DONE");
            return fullPathForSave;
        } catch (ZipException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
