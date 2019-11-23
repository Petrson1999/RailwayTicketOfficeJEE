package com.railvayticketiffice.factory;

import com.railvayticketiffice.services.LoginService;

public class ServiceFactory {

    public static LoginService getLoginService(){
        return new LoginService();
    }

}
