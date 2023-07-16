package com.qa.automation.services;

import java.util.Map;

public interface SignInService {

    <T> T signIn(Map<String, String> userInfo);

    boolean isSignInSuccessful(String name);

    boolean isSignInErrorMessageDisplayed();
}
