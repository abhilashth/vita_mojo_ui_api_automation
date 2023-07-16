package com.qa.automation.services.impls;

import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.pages.SignInPage;
import com.qa.automation.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile(ApplicationConstants.UI)
public class SignInUIServiceImpl implements SignInService {

    @Autowired
    private SignInPage signInPage;

    @Override
    public <T> T signIn(Map<String, String> userInfo) {
        return (T) signInPage.signIn(userInfo);
    }

    @Override
    public boolean isSignInSuccessful(String name) {
        return signInPage.isSignInSuccessful(name);
    }

    @Override
    public boolean isSignInErrorMessageDisplayed() {
        return signInPage.isSignInErrorMessageDisplayed();
    }
}
