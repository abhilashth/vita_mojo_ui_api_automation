package com.qa.automation.services.impls;

import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.services.SignupService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile(ApplicationConstants.API)
public class SignupAPIServiceImpl implements SignupService {

    @Override
    public void signup(Map<String, String> userInfo) {

    }

    @Override
    public boolean isSignupSuccessful(String name) {
        return false;
    }

    @Override
    public boolean isSignUpErrorMessageDisplayed() {
        return false;
    }
}
