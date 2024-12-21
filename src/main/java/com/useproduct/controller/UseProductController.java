package com.useproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.useproduct.productpojo.ProductPojo;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/useproduct")
public class UseProductController {
	@Autowired
	RestTemplate rt;

	@GetMapping(value = "/getuseproduct")
	public List<ProductPojo> getall() {
		String url1 = "http://localhost:8081/product/get";
		String url2 = "http://localhost:8082/gst/getgst/";
		ResponseEntity<List<ProductPojo>> list = rt.exchange(url1, HttpMethod.GET, null,new ParameterizedTypeReference<List<ProductPojo>>() {});
		List<ProductPojo> products = list.getBody();
		products.forEach(x -> {
			int hsn = x.getHsnCode();
			ResponseEntity<Integer> val = rt.exchange(url2 + hsn, HttpMethod.GET, null, Integer.class);
			int gst = val.getBody();
			x.setPrice(x.getPrice() + (x.getPrice() * gst / 100));
		});
		return products;
		
		}
	}
	
	

