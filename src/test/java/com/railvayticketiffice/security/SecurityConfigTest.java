package com.railvayticketiffice.security;

import com.railvayticketiffice.constant.PageUrlConstants;
import com.railvayticketiffice.enums.Role;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityConfigTest {

    @Test
    public void shouldReturnTrueIfSecurePage() {
        boolean result = SecurityConfig.isSecurePage("/" +
                PageUrlConstants.ADMIN_FLIGHTS_PAGE);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfNotSecurePage() {
        boolean result = SecurityConfig.isSecurePage("/" +
                PageUrlConstants.TICKETS_PAGE);

        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfRoleCanAccessToPage() {
        Role role = Role.ADMIN;
        String page = "/" + PageUrlConstants.ADMIN_FLIGHTS_PAGE;
        boolean result = SecurityConfig.hasPermission(page,role);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfRoleNotCanAccessToPage() {
        Role role = Role.USER;
        String page = "/" + PageUrlConstants.ADMIN_FLIGHTS_PAGE;
        boolean result = SecurityConfig.hasPermission(page,role);

        assertFalse(result);
    }
}