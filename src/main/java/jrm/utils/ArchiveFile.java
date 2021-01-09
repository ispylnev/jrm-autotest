package jrm.utils;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.ZipFile;

@Component
@Log
public class ArchiveFile {

    private final String placeHolderForZipName = ".zip";

    public void archiveFile(String filePath) {
        try {
            new ZipFile("javaTask/jrm-java-task1");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
