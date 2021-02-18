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

@SpringBootTest
public class UserSignInTest implements CommonTest {

    @Autowired
    UserAuthApiService userAuthApiService;


    @Test
    @Description("200 ок Базовый сценарий авторизации")
    void successfulAuthorizationTest() {
        UserDto user = UserDto.builder()
                .password("lalala")
                .username("lalala")
                .build();

        ValidatableResponse response = given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getSignIn())
                .then()
                .log().all();

        assertEquals(200, response.extract().statusCode(), "ответ не 200");

    }

    @Test
    @Description("signing in with null fields")
    void authorizationWithNullFields() {
        UserDto user = UserDto.builder()
                .username(null)
                .password(null)
                .build();

        ValidatableResponse response = given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getSignIn())
                .then()
                .log().all();

        assertEquals(401, response.extract().statusCode(), "status code is not 401");


    }
}
