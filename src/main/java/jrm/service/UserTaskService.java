package jrm.service;

import jrm.utils.ArchiveFile;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserTaskService {

    @Autowired
    UserAuthApiService userAuthApiService;
    @Value("${javaroadmap.api.base-v1-url}processing/run/taskId")
    private String processingTaskUrl;

    public String getProcessingTaskUrl(String taskId) {
        return taskId.replace("taskId", taskId);
    }

    @Value("${javaroadmap.api.base-v1-url}tasks")
    @Getter
    private String getUserTasksUrl;

    @Autowired
    ArchiveFile archiveFile;

    public String createArchiveForTask1() {
        String task1 = "jrm-java-task1";
        String fullPathWhereWeSaveArchive = archiveFile.createArchiveForJavaTask(task1);
        return fullPathWhereWeSaveArchive;
    }



}
