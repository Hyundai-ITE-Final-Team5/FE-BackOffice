package com.ite5pjtbackoffice.backoffice.dto;

import java.util.List;

import lombok.Data;

@Data
public class DailyTotalPrice {
	List<Statistics> dailyTotalPrice;
	Statistics todayStatistics;
	Statistics cancelTodatStatistics;
}
