package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ReportInterventionListDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ReportInterventionList}.
 */
public interface ReportInterventionListService {
    /**
     * Save a reportInterventionList.
     *
     * @param reportInterventionListDTO the entity to save.
     * @return the persisted entity.
     */
    ReportInterventionListDTO save(ReportInterventionListDTO reportInterventionListDTO);

    /**
     * Updates a reportInterventionList.
     *
     * @param reportInterventionListDTO the entity to update.
     * @return the persisted entity.
     */
    ReportInterventionListDTO update(ReportInterventionListDTO reportInterventionListDTO);

    /**
     * Partially updates a reportInterventionList.
     *
     * @param reportInterventionListDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportInterventionListDTO> partialUpdate(ReportInterventionListDTO reportInterventionListDTO);

    /**
     * Get the "id" reportInterventionList.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportInterventionListDTO> findOne(Long id);

    /**
     * Delete the "id" reportInterventionList.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the reportInterventionList corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportInterventionListDTO> search(String query, Pageable pageable);
}
