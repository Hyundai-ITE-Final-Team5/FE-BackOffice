package com.ite5pjtbackoffice.backoffice.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.EventSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndEvents;
import com.ite5pjtbackoffice.backoffice.service.PromotionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/promotion")
public class PromotionController {
	
	@Resource
	private PromotionService promotionService;
	
	@RequestMapping("/eventlist")
	public String eventlist(EventSearchOption eventSearchOption, Model model) {
		log.info("---------------------------");
		log.info(eventSearchOption.toString());
		log.info("---------------------------");
		//customer에서는 ajax로 보내기 때문에 아래와 같은 처리가 필요가 없었다.
		if(eventSearchOption.getEissuedate() != null && eventSearchOption.getEissuedate().equals("")) {
			eventSearchOption.setEissuedate(null);
		}
		if(eventSearchOption.getEexpiredate() != null && eventSearchOption.getEexpiredate().equals("")) {
			eventSearchOption.setEexpiredate(null);
		}
		if(eventSearchOption.getEstatus() != null && eventSearchOption.getEstatus().equals("")) {
			eventSearchOption.setEstatus(null);
		}
		if(eventSearchOption.getSort() != null && eventSearchOption.getSort().equals("")) {
			eventSearchOption.setSort(null);
		}
		
		if(eventSearchOption.getEsearchOption() != null && !eventSearchOption.getEsearchOption().equals("")) {
			if(eventSearchOption.getEsearchOption().equals("eno")) {
				eventSearchOption.setEno(eventSearchOption.getEsearchContent());
			}else if(eventSearchOption.getEsearchOption().equals("etitle")) {
				eventSearchOption.setEtitle(eventSearchOption.getEsearchContent());
			}else if(eventSearchOption.getEsearchOption().equals("econtent")) {
				eventSearchOption.setEcontent(eventSearchOption.getEsearchContent());
			}
		}
		
		PagerAndEvents pagerAndEvents = null;
		pagerAndEvents = promotionService.getEventList(eventSearchOption);
		
		model.addAttribute("eventList", pagerAndEvents.getEvents());
		model.addAttribute("pager", pagerAndEvents.getPager());
		return "promotion/eventlist";
	}
	
	/*
	@RequestMapping("/eventdetail")
	public String eventDetail(String eno, Model model) {
		if(eno != null) {
			Event event = promotionService.getEvent(eno);
			model.addAttribute("event", event);
		}
		return "promotion/eventdetail";
	}
	
	@RequestMapping("/eventupdate")
	public String eventUpdate(Event event, String raweissuedate, String raweexpiredate) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		if(!raweissuedate.equals("")) {
			Date eissuedate= format.parse(raweissuedate);
			event.setEissuedate(eissuedate);
		}
		if(!raweexpiredate.equals("")) {
			Date eexpiredate= format.parse(raweexpiredate);
			event.setEexpiredate(eexpiredate);
		}
		return "redirect:/admin/promotion/eventdetail";
	}
	*/
}
