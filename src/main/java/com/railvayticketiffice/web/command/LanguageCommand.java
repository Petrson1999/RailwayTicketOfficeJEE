package com.railvayticketiffice.web.command;

import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.web.filters.LocalizationFilter.PAGE_NAME;


public class LanguageCommand implements Command {

    public static final String LOCALE = "locale";

    @Override
    public Page perform(HttpServletRequest request) {
        String locale = request.getParameter(LOCALE);
        request.getSession().setAttribute(LOCALE, locale);
        String url = request.getSession().getAttribute(PAGE_NAME).toString();
        return new Page(url, true);
    }

}
