package com.ownlabs.prem.mylinkedinauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;


import com.ownlabs.prem.mylinkedinauth.model.LinkedinUserModel;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

public class LoginActivity extends Activity {

    ImageButton liLoginButton;
    private static final String TAG = "LoginActivity";
    public static final String LI_AUTH_TOKEN = "auth_token";

    public static final String LI_AUTH_TOKEN_EXPIRES_ON = "expires_on";

    private static final String userInfoGetURL = "https://api.linkedin.com/v1/people/~:(id,first-name," +
            "last-name,maiden-name,email-address,formatted-name,phonetic-last-name,location:(name)" +
            ",picture-url,industry,distance,current-status,network,skills,phone-numbers," +
            "picture-urls::(original),date-of-birth,main-address,positions:(title)," +
            "educations:(school-name,field-of-study,start-date,end-date,degree,activities))?format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LISessionManager.getInstance(getApplicationContext())
                .onActivityResult(this, requestCode, resultCode, data);
    }

    private void initialize(){
        liLoginButton =(ImageButton) findViewById(R.id.liLoginButton);

        liLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LISessionManager.getInstance(getApplicationContext()).init(LoginActivity.this,
                        buildScope(), new AuthListener() {
                            @Override
                            public void onAuthSuccess() {
                                Log.i(TAG, LISessionManager.getInstance(getApplicationContext())
                                        .getSession().getAccessToken().toString());
//
                                getLinkedInUserProfile();
                            }

                            @Override
                            public void onAuthError(LIAuthError error) {
                                Log.e(TAG, error.toString());
                            }
                        }, true);
            }
        });
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS, Scope.RW_COMPANY_ADMIN);
    }



    public void getLinkedInUserProfile() {
        APIHelper apiHelper = APIHelper.getInstance(this);
        apiHelper.getRequest(this, userInfoGetURL, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse s) {
                Log.i(TAG, s.getResponseDataAsString());
                LinkedinUserModel liModel = new Gson().fromJson(s.getResponseDataAsString(),
                        LinkedinUserModel.class);
//
            }

            @Override
            public void onApiError(LIApiError error) {
                Log.e(TAG, error.toString());
            }
        });

    }


}
