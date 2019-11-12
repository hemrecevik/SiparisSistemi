package com.example.siparissistemi;

import android.app.Application;

import com.parse.Parse;

public class ParseBaslatma extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        //SUNUCU BAĞLANTISI (BACK4APP)
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KYya7ek4EChhQ6z4RafOUNiaNEP2h9VEd4Ya3T1k")
                .clientKey("CpzUGen6j85QgwAzwVHuMkQm8dLmoGKvXOwkKdmk")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }


}