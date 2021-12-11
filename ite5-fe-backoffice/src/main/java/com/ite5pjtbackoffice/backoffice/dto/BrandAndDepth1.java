package com.ite5pjtbackoffice.backoffice.dto;

import java.util.List;

import com.ite5pjtbackoffice.backoffice.vo.Brand;

import lombok.Data;

@Data
public class BrandAndDepth1 {
	List<String> depth1;
	List<Brand> brand;
}
