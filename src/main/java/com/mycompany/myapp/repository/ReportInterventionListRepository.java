package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ReportInterventionList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportInterventionList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportInterventionListRepository
    extends JpaRepository<ReportInterventionList, Long>, JpaSpecificationExecutor<ReportInterventionList> {}
