package jrm.user;


import io.restassured.http.ContentType;
import jrm.commonclasses.CommonTest;
import jrm.dao.UserRepository;
import jrm.dto.UserDto;
import jrm.service.UserAuthApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static io.restassured.RestAssured.given;

@SpringBootTest

public class UserSignUpTest implements CommonTest {

    @Autowired
    UserAuthApiService userAuthApiService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Description("200 ок Базовый сценарий регистрации.")
    void test() {
        UserDto user = UserDto.builder()
                .name("test123")
                .email("test123@mail.ru")
                .password("test123")
                .username("test123")
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getUrlSignUp())
                .then()
                .log().all();

        User userAfterSelect = userRepository.fetchUserByUsername(user.getUsername());
    }
}

