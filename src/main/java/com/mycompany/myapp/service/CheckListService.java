package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CheckListDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CheckList}.
 */
public interface CheckListService {
    /**
     * Save a checkList.
     *
     * @param checkListDTO the entity to save.
     * @return the persisted entity.
     */
    CheckListDTO save(CheckListDTO checkListDTO);

    /**
     * Updates a checkList.
     *
     * @param checkListDTO the entity to update.
     * @return the persisted entity.
     */
    CheckListDTO update(CheckListDTO checkListDTO);

    /**
     * Partially updates a checkList.
     *
     * @param checkListDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CheckListDTO> partialUpdate(CheckListDTO checkListDTO);

    /**
     * Get the "id" checkList.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CheckListDTO> findOne(Long id);

    /**
     * Delete the "id" checkList.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the checkList corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CheckListDTO> search(String query, Pageable pageable);
}
