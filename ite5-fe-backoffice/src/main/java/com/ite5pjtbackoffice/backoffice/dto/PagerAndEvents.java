package com.ite5pjtbackoffice.backoffice.dto;

import java.util.List;

import com.ite5pjtbackoffice.backoffice.vo.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PagerAndEvents {
	private Pager pager;
	private List<Event> events;
}
