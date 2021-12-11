package com.ite5pjtbackoffice.backoffice.dto;

import java.util.List;

import com.ite5pjtbackoffice.backoffice.vo.Event;
import com.ite5pjtbackoffice.backoffice.vo.ProductCommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PagerAndProductList {
	private Pager pager;
	private List<ProductCommon> productList;
}
