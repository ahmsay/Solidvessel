package com.solidvessel.inventory.adapter.in.product.rest.mapper;

import com.solidvessel.inventory.adapter.in.product.rest.response.ProductAvailabilityResponse;
import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductAvailability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductWebMapper {

    ProductResponse toResponse(Product product);

    ProductAvailabilityResponse toResponse(ProductAvailability productAvailability);
}
