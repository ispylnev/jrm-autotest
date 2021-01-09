package jrm.task.java.tasks.task1;

import jrm.commonclasses.CommonTest;
import jrm.utils.ArchiveFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskCompleteTest implements CommonTest {

    @Autowired
   private ArchiveFile archiveFile;

    @Test
    void test(){
        archiveFile.archiveFile("javaTask/jrm-java-task1");
    }
}
