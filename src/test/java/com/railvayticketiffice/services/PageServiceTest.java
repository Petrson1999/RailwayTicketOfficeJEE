package com.railvayticketiffice.services;

import com.railvayticketiffice.constant.PageUrlConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PageServiceTest {

    private PageService pageService;

    @Before
    public void setUp() {
        pageService = new PageService();
    }

    @Test
    public void shouldReturnTrueIfPageNameCorrect() {
        boolean result = pageService.isPage("/" + PageUrlConstants.PROFILE_PAGE);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfPageNameIncorrect() {
        boolean result = pageService.isPage("incorrect");

        assertFalse(result);
    }


}