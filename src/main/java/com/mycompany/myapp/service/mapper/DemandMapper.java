package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Demand;
import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.service.dto.DemandDTO;
import com.mycompany.myapp.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Demand} and its DTO {@link DemandDTO}.
 */
@Mapper(componentModel = "spring")
public interface DemandMapper extends EntityMapper<DemandDTO, Demand> {
    @Mapping(target = "name", source = "name", qualifiedByName = "productName")
    DemandDTO toDto(Demand s);

    @Named("productName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductDTO toDtoProductName(Product product);
}
