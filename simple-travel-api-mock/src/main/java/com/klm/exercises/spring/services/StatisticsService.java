package com.klm.exercises.spring.services;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
	private ConcurrentMap<Object, Long> statisticsMap;
	private static final String OK_RESPONSES = "OkResponses";
	private static final String TOTAL_REQUEST_PROCESSED = "totalReqProcessed";
	private static final String AVERATE_RESPONSE_TIME_FOR_ALL_REQUESTS = "averageResponseTimeForAllRequests";
	private static final String MIN_RESPONSE_TIME = "minResponseTime";
	private static final String MAX_RESPONSE_TIME = "maxResponseTime";

	public StatisticsService() {
		statisticsMap = new ConcurrentHashMap<Object, Long>();
	}

	public void gatherStatistics(String request, int status, final long totalTimeForAllResponse, final long responseTime, final long totalApiRequests) {

		Long statusCount = Optional.ofNullable(statisticsMap.get(status)).orElse(0l)+1;

		statisticsMap.put(OK_RESPONSES, statusCount);

		statisticsMap.put(TOTAL_REQUEST_PROCESSED, statusCount);
		statisticsMap.put(AVERATE_RESPONSE_TIME_FOR_ALL_REQUESTS, totalTimeForAllResponse / totalApiRequests);

		Long minResTime = Optional.ofNullable(statisticsMap.get(MIN_RESPONSE_TIME)).orElse(responseTime);

		if (responseTime <= minResTime) {
			statisticsMap.put(MIN_RESPONSE_TIME, responseTime);
		}

		Long maxResTime = Optional.ofNullable(statisticsMap.get(MAX_RESPONSE_TIME)).orElse(responseTime);

		if (responseTime >= maxResTime) {
			statisticsMap.put(MAX_RESPONSE_TIME, responseTime);

		}

	}

	public Map<Object, Long> getStatistics() {
		return statisticsMap;
	}

}
