package com.klm.exercises.spring.common;

import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.klm.exercises.spring.services.StatisticsService;

public class StatisticsFilter implements Filter {

    private StatisticsService statisticsService;
    private static Long totalTimeForAllRequests;
    private static Long totalApiRequests;

    @Override
    public void init(FilterConfig config) throws ServletException {
    	statisticsService = (StatisticsService) WebApplicationContextUtils
         .getRequiredWebApplicationContext(config.getServletContext())
         .getBean("statisticsService");
    }

    
	/*
	 * public void doApiFilter(HttpServletRequest request, HttpServletResponse
	 * response, FilterChain chain) throws java.io.IOException, ServletException {
	 * if(request.getRequestURI().contains("statistics")) { return; }
	 * 
	 * doFilter(request, response, chain); }
	 */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws java.io.IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
      System.out.println(httpRequest.getRequestURI());

      if(httpRequest.getRequestURI().contains("statistics"))
    	{
    		chain.doFilter(request, response);
    		return;
    	}
 
    	totalApiRequests = Optional.ofNullable(totalApiRequests).orElse(1l);
    	System.out.println("totalApiRequests = " + totalApiRequests);
    	totalTimeForAllRequests = Optional.ofNullable(totalTimeForAllRequests).orElse(1l);
    	final long requestInTime = System.currentTimeMillis();
    	System.out.println("totalTimeForAllRequests = " + totalTimeForAllRequests);
    	
      String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

      chain.doFilter(request, response);        

      int status = ((HttpServletResponse) response).getStatus();
      System.out.println("status = " + status);
      final long responseTime = System.currentTimeMillis();
        
      final long requestProcessedTime = responseTime - requestInTime;
      totalTimeForAllRequests = totalTimeForAllRequests + requestProcessedTime;
        
      statisticsService.gatherStatistics(req, status, totalTimeForAllRequests, requestProcessedTime, totalApiRequests++);
    }
}