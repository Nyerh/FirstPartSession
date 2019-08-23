package com.nyerh.MyFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public abstract class BaseFilter implements Filter {
    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
