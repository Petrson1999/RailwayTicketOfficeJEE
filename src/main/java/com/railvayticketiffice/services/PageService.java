package com.railvayticketiffice.services;

import com.railvayticketiffice.constant.PageUrlConstants;

import java.util.ArrayList;
import java.util.List;

public class PageService {

    private List<String> pages = new ArrayList<>();

    public PageService() {
        this.pages.add("/");
        this.pages.add("/" + PageUrlConstants.PROFILE_PAGE);
        this.pages.add("/" + PageUrlConstants.TICKETS_PAGE);
        this.pages.add("/" + PageUrlConstants.ADMIN_FLIGHTS_PAGE);
        this.pages.add("/" + PageUrlConstants.ADMIN_STATIONS_PAGE);
        this.pages.add("/" + PageUrlConstants.NOT_FOUND_PAGE);
        this.pages.add("/" + PageUrlConstants.FORBIDDEN_PAGE);
    }

    public boolean isPage(String path) {
        return this.pages.stream().anyMatch(x -> x.equals(path));
    }


}
