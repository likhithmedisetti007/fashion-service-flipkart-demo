package com.likhith.fashion.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.likhith.fashion.document.Shirt;
import com.likhith.fashion.dto.Product;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FashionMapper {

	List<Product> mapToSubCategoryFromShirt(List<Shirt> shirtList);

}