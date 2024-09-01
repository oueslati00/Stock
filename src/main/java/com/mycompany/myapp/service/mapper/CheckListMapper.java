package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.CheckList;
import com.mycompany.myapp.service.dto.CheckListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CheckList} and its DTO {@link CheckListDTO}.
 */
@Mapper(componentModel = "spring")
public interface CheckListMapper extends EntityMapper<CheckListDTO, CheckList> {}
