package com.apigateway.resource;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apigateway.Entity.FullOrderDetails;
import com.apigateway.Entity.OrderDetails;
import com.apigateway.Entity.Product;

@RestController
@CrossOrigin
@RequestMapping("/usercarthistory")
public class Resources {
	@Autowired
	private RestTemplate restTemplate;
	@GetMapping("/{userName}")
	public List<Product> getProductFromUserName(@PathVariable("userName") String userName){
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		hm=restTemplate.getForObject("http://localhost:8905/usercart/getProductFromCart/"+userName,HashMap.class);
		List<Product> list=new ArrayList<Product>();
		for(String itemId:hm.keySet()) {
			Product productObj=restTemplate.getForObject("http://localhost:8901/product/"+itemId,Product.class);
		list.add(productObj);
		}
		return list;
	}
	@GetMapping("fullOrder/{userName}")
	public List<FullOrderDetails> getFullOrderDetails(@PathVariable("userName") String userName){
		HashMap<String, String> hm=new HashMap<String, String>();
		hm=restTemplate.getForObject("http://localhost:8907/orderhistory/getorderhistory/"+userName,HashMap.class);
		List<FullOrderDetails> list=new ArrayList<FullOrderDetails>();
		for (Map.Entry<String,String> entry : hm.entrySet()) {
			Product productObj=restTemplate.getForObject("http://localhost:8901/product/"+entry.getValue(),Product.class);
			OrderDetails orderDetailsObj=restTemplate.getForObject("http://localhost:8908/orderdetails/getorderdetails/"+entry.getKey(),OrderDetails.class);
			FullOrderDetails fOD=new FullOrderDetails(orderDetailsObj.getOrderId(),orderDetailsObj.getCustomerName(),orderDetailsObj.getEmail(),orderDetailsObj.getPhoneNumber(),orderDetailsObj.getAddress(),orderDetailsObj.getAmount(),productObj.getProductId(),productObj.getProductName(),productObj.getProductDescription(),productObj.getProductDiscountedPrice(),productObj.getProductActualPrice(),productObj.getProductImage());
			list.add(fOD);
		}
		return list;
	}
	
}
