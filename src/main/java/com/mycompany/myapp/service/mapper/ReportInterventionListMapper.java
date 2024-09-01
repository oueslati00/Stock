package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ReportInterventionList;
import com.mycompany.myapp.service.dto.ReportInterventionListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReportInterventionList} and its DTO {@link ReportInterventionListDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReportInterventionListMapper extends EntityMapper<ReportInterventionListDTO, ReportInterventionList> {}
