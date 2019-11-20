package com.vladimirpetrenkodev.railvayticketiffice.factory;

import com.vladimirpetrenkodev.railvayticketiffice.services.LoginService;

public class ServiceFactory {

    public static LoginService getLoginService(){
        return new LoginService();
    }

}
