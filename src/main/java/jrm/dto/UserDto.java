package jrm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * UserDto˚
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {

    private String username;

    private String name;

    private String email;

    String password;



}

