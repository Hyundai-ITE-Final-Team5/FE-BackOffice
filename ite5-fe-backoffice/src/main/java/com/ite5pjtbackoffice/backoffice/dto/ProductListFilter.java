package com.ite5pjtbackoffice.backoffice.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductListFilter {
	String pname;
	String pid;
	String depth1name;
	String depth2name;
	String depth3name;
	List<Integer> cateno;
	String startdate;
	String enddate;
	int pstatus = 1;
	int pageNo = 1;
}
