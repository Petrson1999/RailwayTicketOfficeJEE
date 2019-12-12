package com.railvayticketiffice.security;

import com.railvayticketiffice.constant.PageUrlConstants;
import com.railvayticketiffice.enums.Role;

import java.util.*;

public class SecurityConfig {

    private static Map<Role, List<String>> securityPages = new HashMap<>();

    static {
        securityPages.put(Role.ADMIN, Arrays.asList("/" +
                PageUrlConstants.ADMIN_FLIGHTS_PAGE, "/" +
                PageUrlConstants.ADMIN_STATIONS_PAGE, "/" +
                PageUrlConstants.ADMIN_WAGONS_PAGE, "/" +
                PageUrlConstants.ADMIN_TRAINS_PAGE, "/" +
                PageUrlConstants.PROFILE_PAGE));
        securityPages.put(Role.USER, Arrays.asList("/" +
                PageUrlConstants.PROFILE_PAGE));
    }

    public static boolean isSecurePage(String page) {
        return securityPages.values().stream()
                .anyMatch(list -> list.stream()
                        .anyMatch(pageValue -> pageValue.equals(page)));
    }

    public static boolean hasPermission(String page, Role role) {
        return securityPages.getOrDefault(role, Collections.EMPTY_LIST)
                .stream()
                .anyMatch(securePage -> securePage.equals(page));
    }

}
