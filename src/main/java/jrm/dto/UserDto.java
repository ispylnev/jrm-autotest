package jrm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.util.Date;

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

    //additional fields

    private Long id;
    private String provider;
    private String providerId;
    private String sessionId;
    private String createdOn;
    private String status;

}

