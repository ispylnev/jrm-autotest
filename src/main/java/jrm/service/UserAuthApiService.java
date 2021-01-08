package jrm.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class UserAuthApiService {

    @Value("${javaroadmap.api.base-v1-url}signup")
    @Getter
    private String urlSignUp;


}

