package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DemandDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Demand}.
 */
public interface DemandService {
    /**
     * Save a demand.
     *
     * @param demandDTO the entity to save.
     * @return the persisted entity.
     */
    DemandDTO save(DemandDTO demandDTO);

    /**
     * Updates a demand.
     *
     * @param demandDTO the entity to update.
     * @return the persisted entity.
     */
    DemandDTO update(DemandDTO demandDTO);

    /**
     * Partially updates a demand.
     *
     * @param demandDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DemandDTO> partialUpdate(DemandDTO demandDTO);

    /**
     * Get all the demands with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DemandDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" demand.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DemandDTO> findOne(Long id);

    /**
     * Delete the "id" demand.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the demand corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DemandDTO> search(String query, Pageable pageable);
}
