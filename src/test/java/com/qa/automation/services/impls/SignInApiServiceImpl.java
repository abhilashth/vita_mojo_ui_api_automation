package com.qa.automation.services.impls;

import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.services.SignInService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile(ApplicationConstants.API)
public class SignInApiServiceImpl implements SignInService {

    @Override
    public void signIn(Map<String, String> userInfo) {

    }

    @Override
    public boolean isSignInSuccessful(String name) {
        return false;
    }

    @Override
    public boolean isSignInErrorMessageDisplayed() {
        return false;
    }
}
