package com.likhith.fashion.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.fashion.dto.Attribute;
import com.likhith.fashion.dto.Inventory;
import com.likhith.fashion.dto.Price;

import lombok.Data;

@Document(collection = "jeans")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Jeans {

	@Id
	@JsonIgnore
	private String id;
	private String name;
	private String brand;
	private String description;
	private Price price;
	private Inventory inventory;
	private List<Attribute> attributes;

}