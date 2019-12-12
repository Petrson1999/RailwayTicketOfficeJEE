package com.railvayticketiffice.services;

import com.railvayticketiffice.constant.PageUrlConstants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PageService {
    private static final Logger LOG = Logger.getLogger(PageService.class);
    private List<String> pages = new ArrayList<>();

    public PageService() {
        this.pages.add("/");
        this.pages.add("/" + PageUrlConstants.PROFILE_PAGE);
        this.pages.add("/" + PageUrlConstants.TICKETS_PAGE);
        this.pages.add("/" + PageUrlConstants.ADMIN_FLIGHTS_PAGE);
        this.pages.add("/" + PageUrlConstants.ADMIN_STATIONS_PAGE);
        this.pages.add("/" + PageUrlConstants.ADMIN_WAGONS_PAGE);
        this.pages.add("/" + PageUrlConstants.ADMIN_TRAINS_PAGE);
        this.pages.add("/" + PageUrlConstants.NOT_FOUND_PAGE);
        this.pages.add("/" + PageUrlConstants.FORBIDDEN_PAGE);
    }

    public boolean isPage(String path) {
        return this.pages.stream().anyMatch(x -> x.equals(path));
    }

    public String getPath(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        int lastPath = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastPath);
        LOG.info("Path: " + path);
        return path;
    }


}
