package jrm.user;

import io.restassured.RestAssured;
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

import java.sql.Timestamp;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DeleteGuestUsersTest implements CommonTest {

    @Autowired
    UserAuthApiService userAuthApiService;

    @Autowired
    UserRepository userRepository;


    @Test
    @Description("manual deleting for old guest user")
    void deleteGuestUsersTest(){

        // 1 create new guest user, enter via /user/me endpoint
        ValidatableResponse response = given().contentType(ContentType.JSON)
                .when()
                .get(userAuthApiService.getInfoAboutCurrentUser())
                .then()
                .log().all();

        assertEquals(200, response.extract().statusCode(), "server response code is not 200, smth went wrong");



        // 2 place JSON into user object, extract ID and update field

        UserDto user = response.extract().as(UserDto.class);

        System.out.println("id of the new guest is " + user.getId()); // checking
        assertTrue(userRepository.existsById(user.getId()), "new guest user was not created");


        String text = "2002-05-20 00:00:00";
        Timestamp ts = Timestamp.valueOf(text);
        userRepository.updateUserCreatedDate(ts, user.getId());


        // 3 manual start of deleting tool

        RestAssured.post(userAuthApiService.getDeleteGuestUsers());
        assertEquals(200, response.extract().statusCode(), "something went wrong");
        assertEquals(false, userRepository.existsById(user.getId()), "testing user was not deleted");




    }

}