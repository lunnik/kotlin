package com.odn.kotlinretrofi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by EDGAR ARANA on 06/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity{

        public static final String TAG = "Test";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(myView());
            activityCreated();
        }

        public void printMessage(String message){
            System.out.print(message);
        }


        public abstract int myView();
        public abstract void activityCreated();
}
