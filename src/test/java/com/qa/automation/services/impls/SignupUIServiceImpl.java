package com.qa.automation.services.impls;

import com.qa.automation.annotation.LazyAutowired;
import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.pages.SignUpPage;
import com.qa.automation.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile(ApplicationConstants.UI)
public class SignupUIServiceImpl implements SignupService {

    @Autowired
    private SignUpPage signUpPage;


    @Override
    public <T> T signup(Map<String, String> userInfo) {
        return (T) signUpPage.signup(userInfo);
    }

    @Override
    public boolean isSignupSuccessful(String name) {
        return signUpPage.isSignupSuccessful(name);
    }

    @Override
    public boolean isSignUpErrorMessageDisplayed() {
        return signUpPage.isSignUpErrorMessageDisplayed();
    }
}
