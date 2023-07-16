package com.qa.automation.services;



import java.util.Map;


public interface SignupService {

    <T> T signup(Map<String, String> userInfo);

    boolean isSignupSuccessful(String name);

    boolean isSignUpErrorMessageDisplayed();


}
