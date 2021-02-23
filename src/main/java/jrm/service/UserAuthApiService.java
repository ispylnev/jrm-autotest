package jrm.service;

import io.restassured.http.ContentType;
import jrm.dto.UserDto;
import jrm.helper.AuthTokenResponse;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
@Data
public class UserAuthApiService {

    @Value("${javaroadmap.api.base-v1-url}signup")
    @Getter
    private String urlSignUp;

    @Value("${javaroadmap.api.base-v1-url}signin")
    private String signIn;

    @Value("${javaroadmap.api.base-v1-url}schedulers/delete-guests-users")
    private String deleteGuestUsers;

    @Value("${javaroadmap.api.base-v1-url}modules")
    private String mainPage;

    @Value("${javaroadmap.api.base-v1-url}users/me")
    private String infoAboutCurrentUser;

    public AuthTokenResponse auth(UserDto user){
       return given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(signIn)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract().response().as(AuthTokenResponse.class);
    }
}

