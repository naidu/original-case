package com.klm.exercises.spring.locations;

import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.klm.exercises.spring.services.StatisticsService;

import lombok.RequiredArgsConstructor;

@RestController

@RequiredArgsConstructor
@Secured("USER")
public class StatisticsController {
	
	 private final StatisticsService statisticsService;
	 
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ResponseBody
    public Map getStatistics() {
    	
        return statisticsService.getStatistics();
    }
}

