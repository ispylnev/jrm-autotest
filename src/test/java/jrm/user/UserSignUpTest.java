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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserSignUpTest implements CommonTest {

    @Autowired
    UserAuthApiService userAuthApiService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Description("201 ок Базовый сценарий регистрации.")
    void successfulRegistrationTest() {
        UserDto user = UserDto.builder()
                .name("test123")
                .email("test123")
                .password("test123")
                .username("test123")
                .build();

        ValidatableResponse response = given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getUrlSignUp())
                .then()
                .log().all();

        assertEquals(201, response.extract().statusCode(), "ответ не 201");
        User userAfterSelect = userRepository.fetchUserByUsername(user.getUsername());
        assertNotNull(userAfterSelect, "пользователя нет в БД");
    }


    @Test
    @Description("check sign up for already existing user, the same user can't go through registration twice")
    void SingUpForAlreadyExistingUser() {
        UserDto user = UserDto.builder()
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

        assertEquals(409, response.extract().statusCode(), "conflict has not found, NEW user has been created");
        assertTrue(userRepository.existsByUsername(user.getUsername()), "checking user does not exist");
    }

    @Test
    @Description("signing up with null fields")
    void registrationWithNullFields() {
        UserDto user = UserDto.builder()
                .name(null)
                .email(null)
                .username(null)
                .password(null)
                .build();

        ValidatableResponse response = given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getUrlSignUp())
                .then()
                .log().all();

        assertEquals(409, response.extract().statusCode(), "status code is not 409 conflict");

    }


}

