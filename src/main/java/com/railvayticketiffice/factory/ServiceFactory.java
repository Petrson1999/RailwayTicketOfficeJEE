package com.railvayticketiffice.factory;

import com.railvayticketiffice.services.UserService;
import com.railvayticketiffice.services.PageService;

public class ServiceFactory {

    public static UserService getUserService(){
        return new UserService();
    }

    public static PageService getPageService(){
        return new PageService();
    }

}
