package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.CheckListRepository;
import com.mycompany.myapp.service.CheckListQueryService;
import com.mycompany.myapp.service.CheckListService;
import com.mycompany.myapp.service.criteria.CheckListCriteria;
import com.mycompany.myapp.service.dto.CheckListDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.errors.ElasticsearchExceptionMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.CheckList}.
 */
@RestController
@RequestMapping("/api/check-lists")
@ConditionalOnMissingClass(value = "com.mycompany.myapp.web.rest.CheckListResourceExtend")
public class CheckListResource {

    protected static final Logger log = LoggerFactory.getLogger(CheckListResource.class);

    protected static final String ENTITY_NAME = "checkList";

    @Value("${jhipster.clientApp.name}")
    protected String applicationName;

    protected final CheckListService checkListService;

    protected final CheckListRepository checkListRepository;

    protected final CheckListQueryService checkListQueryService;

    public CheckListResource(
        CheckListService checkListService,
        CheckListRepository checkListRepository,
        CheckListQueryService checkListQueryService
    ) {
        this.checkListService = checkListService;
        this.checkListRepository = checkListRepository;
        this.checkListQueryService = checkListQueryService;
    }

    /**
     * {@code POST  /check-lists} : Create a new checkList.
     *
     * @param checkListDTO the checkListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkListDTO, or with status {@code 400 (Bad Request)} if the checkList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CheckListDTO> createCheckList(@RequestBody CheckListDTO checkListDTO) throws URISyntaxException {
        log.debug("REST request to save CheckList : {}", checkListDTO);
        if (checkListDTO.getId() != null) {
            throw new BadRequestAlertException("A new checkList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        checkListDTO = checkListService.save(checkListDTO);
        return ResponseEntity.created(new URI("/api/check-lists/" + checkListDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, checkListDTO.getId().toString()))
            .body(checkListDTO);
    }

    /**
     * {@code PUT  /check-lists/:id} : Updates an existing checkList.
     *
     * @param id the id of the checkListDTO to save.
     * @param checkListDTO the checkListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkListDTO,
     * or with status {@code 400 (Bad Request)} if the checkListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CheckListDTO> updateCheckList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckListDTO checkListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CheckList : {}, {}", id, checkListDTO);
        if (checkListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        checkListDTO = checkListService.update(checkListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkListDTO.getId().toString()))
            .body(checkListDTO);
    }

    /**
     * {@code PATCH  /check-lists/:id} : Partial updates given fields of an existing checkList, field will ignore if it is null
     *
     * @param id the id of the checkListDTO to save.
     * @param checkListDTO the checkListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkListDTO,
     * or with status {@code 400 (Bad Request)} if the checkListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the checkListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the checkListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CheckListDTO> partialUpdateCheckList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckListDTO checkListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CheckList partially : {}, {}", id, checkListDTO);
        if (checkListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CheckListDTO> result = checkListService.partialUpdate(checkListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkListDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /check-lists} : get all the checkLists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkLists in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CheckListDTO>> getAllCheckLists(
        CheckListCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get CheckLists by criteria: {}", criteria);

        Page<CheckListDTO> page = checkListQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /check-lists/count} : count all the checkLists.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCheckLists(CheckListCriteria criteria) {
        log.debug("REST request to count CheckLists by criteria: {}", criteria);
        return ResponseEntity.ok().body(checkListQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /check-lists/:id} : get the "id" checkList.
     *
     * @param id the id of the checkListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CheckListDTO> getCheckList(@PathVariable("id") Long id) {
        log.debug("REST request to get CheckList : {}", id);
        Optional<CheckListDTO> checkListDTO = checkListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkListDTO);
    }

    /**
     * {@code DELETE  /check-lists/:id} : delete the "id" checkList.
     *
     * @param id the id of the checkListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckList(@PathVariable("id") Long id) {
        log.debug("REST request to delete CheckList : {}", id);
        checkListService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /check-lists/_search?query=:query} : search for the checkList corresponding
     * to the query.
     *
     * @param query the query of the checkList search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public ResponseEntity<List<CheckListDTO>> searchCheckLists(
        @RequestParam("query") String query,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of CheckLists for query {}", query);
        try {
            Page<CheckListDTO> page = checkListService.search(query, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            return ResponseEntity.ok().headers(headers).body(page.getContent());
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
