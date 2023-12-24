package com.likhith.fashion.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.fashion.exception.ErrorMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FashionResponse {

	private String name;
	private List<Product> products;

	private ErrorMessage message;

	public FashionResponse(String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public FashionResponse(ErrorMessage message) {
		super();
		this.message = message;
	}

}