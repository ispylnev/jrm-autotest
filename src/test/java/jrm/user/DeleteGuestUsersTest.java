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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        assertTrue(userRepository.existsById(user.getId()), "new guest user was not created");


        String text = "2002-05-20 00:00:00";
        Timestamp ts = Timestamp.valueOf(text);
        userRepository.updateUserCreatedDate(ts, user.getId());


        // 3 manual start of deleting tool
        RestAssured.post(userAuthApiService.getDeleteGuestUsers());
        assertEquals(200, response.extract().statusCode(), "something went wrong");
        assertEquals(false, userRepository.existsById(user.getId()), "testing user was not deleted");


        //scenario for 25-hours old guest user
        ValidatableResponse response2 = given().contentType(ContentType.JSON)
                .when()
                .get(userAuthApiService.getInfoAboutCurrentUser())
                .then()
                .log().all();

        assertEquals(200, response2.extract().statusCode(), "server response code is not 200");

        UserDto user2 = response2.extract().as(UserDto.class);
        assertTrue(userRepository.existsById(user2.getId()), "guest user was not generated");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();

        Calendar cc = Calendar.getInstance();
        cc.setTime(currentDate);
        cc.add(Calendar.HOUR, -25);
        Date DateMinus25h = cc.getTime();

        Timestamp ts2 = Timestamp.valueOf(dateFormat.format(DateMinus25h));
        userRepository.updateUserCreatedDate(ts2, user2.getId());

        RestAssured.post(userAuthApiService.getDeleteGuestUsers());
        assertEquals(200, response2.extract().statusCode(), "something went wrong");
        assertEquals(false, userRepository.existsById(user2.getId()), "testing user was not deleted");



        //scenario for 23-hours old guest user

        ValidatableResponse response3 = given().contentType(ContentType.JSON)
                .when()
                .get(userAuthApiService.getInfoAboutCurrentUser())
                .then()
                .log().all();

        assertEquals(200, response3.extract().statusCode(), "server response code is not 200");

        UserDto user3 = response3.extract().as(UserDto.class);
        assertTrue(userRepository.existsById(user3.getId()), "guest user was not generated");

        cc.setTime(currentDate);
        cc.add(Calendar.HOUR, -23);
        Date DateMinus23h = cc.getTime();

        Timestamp ts3 = Timestamp.valueOf(dateFormat.format(DateMinus23h));
        userRepository.updateUserCreatedDate(ts3, user3.getId());

       RestAssured.post(userAuthApiService.getDeleteGuestUsers());
       assertEquals(200, response3.extract().statusCode(), "something went wrong");
       assertEquals(true, userRepository.existsById(user3.getId()), "testing user was deleted");

    }
}