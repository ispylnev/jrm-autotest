package jrm.user;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import jrm.commonclasses.CommonTest;
import jrm.dao.UserRepository;
import jrm.dto.UserDto;
import jrm.service.UserAuthApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserSignUpTest implements CommonTest {

    @Autowired
    UserAuthApiService userAuthApiService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Description("200 ок Базовый сценарий регистрации.")
    void successfulRegistrationTest() {
        UserDto user = UserDto.builder()
                .name("test123")
                .email("test123@mail.ru")
                .password("test123")
                .username("test123")
                .build();

        ValidatableResponse response = given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getUrlSignUp())
                .then()
                .log().all();

        assertEquals(response.extract().statusCode(), 200, "ответ не 200");
        User userAfterSelect = userRepository.fetchUserByUsername(user.getUsername());
        assertNotNull(userAfterSelect,"пользователя нет в БД");
    }
}

