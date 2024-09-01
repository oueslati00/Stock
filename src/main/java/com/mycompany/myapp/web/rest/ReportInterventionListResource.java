package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ReportInterventionListRepository;
import com.mycompany.myapp.service.ReportInterventionListQueryService;
import com.mycompany.myapp.service.ReportInterventionListService;
import com.mycompany.myapp.service.criteria.ReportInterventionListCriteria;
import com.mycompany.myapp.service.dto.ReportInterventionListDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ReportInterventionList}.
 */
@RestController
@RequestMapping("/api/report-intervention-lists")
@ConditionalOnMissingClass(value = "com.mycompany.myapp.web.rest.ReportInterventionListResourceExtend")
public class ReportInterventionListResource {

    protected static final Logger log = LoggerFactory.getLogger(ReportInterventionListResource.class);

    protected static final String ENTITY_NAME = "reportInterventionList";

    @Value("${jhipster.clientApp.name}")
    protected String applicationName;

    protected final ReportInterventionListService reportInterventionListService;

    protected final ReportInterventionListRepository reportInterventionListRepository;

    protected final ReportInterventionListQueryService reportInterventionListQueryService;

    public ReportInterventionListResource(
        ReportInterventionListService reportInterventionListService,
        ReportInterventionListRepository reportInterventionListRepository,
        ReportInterventionListQueryService reportInterventionListQueryService
    ) {
        this.reportInterventionListService = reportInterventionListService;
        this.reportInterventionListRepository = reportInterventionListRepository;
        this.reportInterventionListQueryService = reportInterventionListQueryService;
    }

    /**
     * {@code POST  /report-intervention-lists} : Create a new reportInterventionList.
     *
     * @param reportInterventionListDTO the reportInterventionListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportInterventionListDTO, or with status {@code 400 (Bad Request)} if the reportInterventionList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportInterventionListDTO> createReportInterventionList(
        @RequestBody ReportInterventionListDTO reportInterventionListDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ReportInterventionList : {}", reportInterventionListDTO);
        if (reportInterventionListDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportInterventionList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        reportInterventionListDTO = reportInterventionListService.save(reportInterventionListDTO);
        return ResponseEntity.created(new URI("/api/report-intervention-lists/" + reportInterventionListDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, reportInterventionListDTO.getId().toString()))
            .body(reportInterventionListDTO);
    }

    /**
     * {@code PUT  /report-intervention-lists/:id} : Updates an existing reportInterventionList.
     *
     * @param id the id of the reportInterventionListDTO to save.
     * @param reportInterventionListDTO the reportInterventionListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportInterventionListDTO,
     * or with status {@code 400 (Bad Request)} if the reportInterventionListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportInterventionListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportInterventionListDTO> updateReportInterventionList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReportInterventionListDTO reportInterventionListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ReportInterventionList : {}, {}", id, reportInterventionListDTO);
        if (reportInterventionListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportInterventionListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportInterventionListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        reportInterventionListDTO = reportInterventionListService.update(reportInterventionListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportInterventionListDTO.getId().toString()))
            .body(reportInterventionListDTO);
    }

    /**
     * {@code PATCH  /report-intervention-lists/:id} : Partial updates given fields of an existing reportInterventionList, field will ignore if it is null
     *
     * @param id the id of the reportInterventionListDTO to save.
     * @param reportInterventionListDTO the reportInterventionListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportInterventionListDTO,
     * or with status {@code 400 (Bad Request)} if the reportInterventionListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the reportInterventionListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportInterventionListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportInterventionListDTO> partialUpdateReportInterventionList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReportInterventionListDTO reportInterventionListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportInterventionList partially : {}, {}", id, reportInterventionListDTO);
        if (reportInterventionListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportInterventionListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportInterventionListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportInterventionListDTO> result = reportInterventionListService.partialUpdate(reportInterventionListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportInterventionListDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /report-intervention-lists} : get all the reportInterventionLists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportInterventionLists in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportInterventionListDTO>> getAllReportInterventionLists(
        ReportInterventionListCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ReportInterventionLists by criteria: {}", criteria);

        Page<ReportInterventionListDTO> page = reportInterventionListQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-intervention-lists/count} : count all the reportInterventionLists.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countReportInterventionLists(ReportInterventionListCriteria criteria) {
        log.debug("REST request to count ReportInterventionLists by criteria: {}", criteria);
        return ResponseEntity.ok().body(reportInterventionListQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /report-intervention-lists/:id} : get the "id" reportInterventionList.
     *
     * @param id the id of the reportInterventionListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportInterventionListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportInterventionListDTO> getReportInterventionList(@PathVariable("id") Long id) {
        log.debug("REST request to get ReportInterventionList : {}", id);
        Optional<ReportInterventionListDTO> reportInterventionListDTO = reportInterventionListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportInterventionListDTO);
    }

    /**
     * {@code DELETE  /report-intervention-lists/:id} : delete the "id" reportInterventionList.
     *
     * @param id the id of the reportInterventionListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportInterventionList(@PathVariable("id") Long id) {
        log.debug("REST request to delete ReportInterventionList : {}", id);
        reportInterventionListService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /report-intervention-lists/_search?query=:query} : search for the reportInterventionList corresponding
     * to the query.
     *
     * @param query the query of the reportInterventionList search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public ResponseEntity<List<ReportInterventionListDTO>> searchReportInterventionLists(
        @RequestParam("query") String query,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of ReportInterventionLists for query {}", query);
        try {
            Page<ReportInterventionListDTO> page = reportInterventionListService.search(query, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            return ResponseEntity.ok().headers(headers).body(page.getContent());
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
