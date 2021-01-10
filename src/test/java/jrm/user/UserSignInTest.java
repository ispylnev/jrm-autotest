package jrm.user;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import jrm.commonclasses.CommonTest;
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
    void successfulRegistrationTest() {
        UserDto user = UserDto.builder()
                .password("test123")
                .username("test123")
                .build();

        ValidatableResponse response = given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(userAuthApiService.getSignIn())
                .then()
                .log().all();

        assertEquals(response.extract().statusCode(), 200, "ответ не 200");
    }
}
