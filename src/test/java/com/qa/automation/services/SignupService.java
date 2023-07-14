package com.qa.automation.services;


import com.qa.automation.annotation.Page;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface SignupService {

    void signup(Map<String, String> userInfo);

    boolean isSignupSuccessful(String name);

    boolean isSignUpErrorMessageDisplayed();


}
