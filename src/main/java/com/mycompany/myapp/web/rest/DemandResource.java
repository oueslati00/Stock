package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.DemandRepository;
import com.mycompany.myapp.service.DemandQueryService;
import com.mycompany.myapp.service.DemandService;
import com.mycompany.myapp.service.criteria.DemandCriteria;
import com.mycompany.myapp.service.dto.DemandDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Demand}.
 */
@RestController
@RequestMapping("/api/demands")
@ConditionalOnMissingClass(value = "com.mycompany.myapp.web.rest.DemandResourceExtend")
public class DemandResource {

    protected static final Logger log = LoggerFactory.getLogger(DemandResource.class);

    protected static final String ENTITY_NAME = "demand";

    @Value("${jhipster.clientApp.name}")
    protected String applicationName;

    protected final DemandService demandService;

    protected final DemandRepository demandRepository;

    protected final DemandQueryService demandQueryService;

    public DemandResource(DemandService demandService, DemandRepository demandRepository, DemandQueryService demandQueryService) {
        this.demandService = demandService;
        this.demandRepository = demandRepository;
        this.demandQueryService = demandQueryService;
    }

    /**
     * {@code POST  /demands} : Create a new demand.
     *
     * @param demandDTO the demandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandDTO, or with status {@code 400 (Bad Request)} if the demand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DemandDTO> createDemand(@RequestBody DemandDTO demandDTO) throws URISyntaxException {
        log.debug("REST request to save Demand : {}", demandDTO);
        if (demandDTO.getId() != null) {
            throw new BadRequestAlertException("A new demand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        demandDTO = demandService.save(demandDTO);
        return ResponseEntity.created(new URI("/api/demands/" + demandDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, demandDTO.getId().toString()))
            .body(demandDTO);
    }

    /**
     * {@code PUT  /demands/:id} : Updates an existing demand.
     *
     * @param id the id of the demandDTO to save.
     * @param demandDTO the demandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandDTO,
     * or with status {@code 400 (Bad Request)} if the demandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DemandDTO> updateDemand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DemandDTO demandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Demand : {}, {}", id, demandDTO);
        if (demandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, demandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!demandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        demandDTO = demandService.update(demandDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandDTO.getId().toString()))
            .body(demandDTO);
    }

    /**
     * {@code PATCH  /demands/:id} : Partial updates given fields of an existing demand, field will ignore if it is null
     *
     * @param id the id of the demandDTO to save.
     * @param demandDTO the demandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandDTO,
     * or with status {@code 400 (Bad Request)} if the demandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the demandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the demandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DemandDTO> partialUpdateDemand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DemandDTO demandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Demand partially : {}, {}", id, demandDTO);
        if (demandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, demandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!demandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DemandDTO> result = demandService.partialUpdate(demandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /demands} : get all the demands.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demands in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DemandDTO>> getAllDemands(
        DemandCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Demands by criteria: {}", criteria);

        Page<DemandDTO> page = demandQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /demands/count} : count all the demands.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDemands(DemandCriteria criteria) {
        log.debug("REST request to count Demands by criteria: {}", criteria);
        return ResponseEntity.ok().body(demandQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /demands/:id} : get the "id" demand.
     *
     * @param id the id of the demandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DemandDTO> getDemand(@PathVariable("id") Long id) {
        log.debug("REST request to get Demand : {}", id);
        Optional<DemandDTO> demandDTO = demandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandDTO);
    }

    /**
     * {@code DELETE  /demands/:id} : delete the "id" demand.
     *
     * @param id the id of the demandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemand(@PathVariable("id") Long id) {
        log.debug("REST request to delete Demand : {}", id);
        demandService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /demands/_search?query=:query} : search for the demand corresponding
     * to the query.
     *
     * @param query the query of the demand search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public ResponseEntity<List<DemandDTO>> searchDemands(
        @RequestParam("query") String query,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of Demands for query {}", query);
        try {
            Page<DemandDTO> page = demandService.search(query, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            return ResponseEntity.ok().headers(headers).body(page.getContent());
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
