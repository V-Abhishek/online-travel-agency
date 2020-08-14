/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author Abhishek
 */
public class DataValidationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(DataValidationFilter.class);
    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sr;
        Enumeration params = httpServletRequest.getParameterNames();
        String input = "";
        while (params.hasMoreElements()) {
            String name = params.nextElement().toString();
            String[] value = httpServletRequest.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                input += value[i];
            }
        }
        String sqlInjectStrList = "‘|or|and|;|--|+|=|like";
        if (sqlValidate(input, sqlInjectStrList) || isHtml(input)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) sr1;
            HttpServletRequest request = (HttpServletRequest) sr;
            httpServletResponse.sendRedirect(request.getContextPath()+"/invalid-input-error.jsp");
        } else {
            fc.doFilter(sr, sr1);
        }
    }

    @Override
    public void destroy() {
    }

    protected static boolean sqlValidate(String str, String sqlInjectStrList) {
        str = str.toLowerCase();
        String[] badStrs = sqlInjectStrList.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (isContain(str, badStrs[i])) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isHtml(String input) {
        boolean isHtml = false;
        if (input != null) {
            if (!input.equals(HtmlUtils.htmlEscape(input))) {
                isHtml = true;
            }
        }
        return isHtml;
    }

    private static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + Pattern.quote(subItem) + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

}
