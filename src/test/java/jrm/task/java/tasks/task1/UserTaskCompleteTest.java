package jrm.task.java.tasks.task1;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import jrm.commonclasses.CommonTest;
import jrm.dto.UserDto;
import jrm.helper.AuthTokenResponse;
import jrm.service.UserAuthApiService;
import jrm.service.UserTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class UserTaskCompleteTest implements CommonTest {

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private UserAuthApiService userAuthApiService;

    @Test
    void test() {
        String pathArchive = userTaskService.createArchiveForTask1();
        AuthTokenResponse token = userAuthApiService.auth(
                UserDto.builder().username("task123").password("task123").build());


        RestAssured.given()
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart("file", new File(pathArchive))
                .when()
                .post(userTaskService.getProcessingTaskUrl("TASK ID NEED!!!!!!"))
                .then()
                .assertThat();
    }
}
