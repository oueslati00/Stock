package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ReportInterventionList;
import com.mycompany.myapp.repository.ReportInterventionListRepository;
import com.mycompany.myapp.repository.search.ReportInterventionListSearchRepository;
import com.mycompany.myapp.service.ReportInterventionListService;
import com.mycompany.myapp.service.dto.ReportInterventionListDTO;
import com.mycompany.myapp.service.mapper.ReportInterventionListMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.ReportInterventionList}.
 */
@Service
@Transactional
public class ReportInterventionListServiceImpl implements ReportInterventionListService {

    private static final Logger log = LoggerFactory.getLogger(ReportInterventionListServiceImpl.class);

    protected final ReportInterventionListRepository reportInterventionListRepository;

    protected final ReportInterventionListMapper reportInterventionListMapper;

    protected final ReportInterventionListSearchRepository reportInterventionListSearchRepository;

    public ReportInterventionListServiceImpl(
        ReportInterventionListRepository reportInterventionListRepository,
        ReportInterventionListMapper reportInterventionListMapper,
        ReportInterventionListSearchRepository reportInterventionListSearchRepository
    ) {
        this.reportInterventionListRepository = reportInterventionListRepository;
        this.reportInterventionListMapper = reportInterventionListMapper;
        this.reportInterventionListSearchRepository = reportInterventionListSearchRepository;
    }

    @Override
    public ReportInterventionListDTO save(ReportInterventionListDTO reportInterventionListDTO) {
        log.debug("Request to save ReportInterventionList : {}", reportInterventionListDTO);
        ReportInterventionList reportInterventionList = reportInterventionListMapper.toEntity(reportInterventionListDTO);
        reportInterventionList = reportInterventionListRepository.save(reportInterventionList);
        reportInterventionListSearchRepository.index(reportInterventionList);
        return reportInterventionListMapper.toDto(reportInterventionList);
    }

    @Override
    public ReportInterventionListDTO update(ReportInterventionListDTO reportInterventionListDTO) {
        log.debug("Request to update ReportInterventionList : {}", reportInterventionListDTO);
        ReportInterventionList reportInterventionList = reportInterventionListMapper.toEntity(reportInterventionListDTO);
        reportInterventionList = reportInterventionListRepository.save(reportInterventionList);
        reportInterventionListSearchRepository.index(reportInterventionList);
        return reportInterventionListMapper.toDto(reportInterventionList);
    }

    @Override
    public Optional<ReportInterventionListDTO> partialUpdate(ReportInterventionListDTO reportInterventionListDTO) {
        log.debug("Request to partially update ReportInterventionList : {}", reportInterventionListDTO);

        return reportInterventionListRepository
            .findById(reportInterventionListDTO.getId())
            .map(existingReportInterventionList -> {
                reportInterventionListMapper.partialUpdate(existingReportInterventionList, reportInterventionListDTO);

                return existingReportInterventionList;
            })
            .map(reportInterventionListRepository::save)
            .map(savedReportInterventionList -> {
                reportInterventionListSearchRepository.index(savedReportInterventionList);
                return savedReportInterventionList;
            })
            .map(reportInterventionListMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportInterventionListDTO> findOne(Long id) {
        log.debug("Request to get ReportInterventionList : {}", id);
        return reportInterventionListRepository.findById(id).map(reportInterventionListMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportInterventionList : {}", id);
        reportInterventionListRepository.deleteById(id);
        reportInterventionListSearchRepository.deleteFromIndexById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportInterventionListDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ReportInterventionLists for query {}", query);
        return reportInterventionListSearchRepository.search(query, pageable).map(reportInterventionListMapper::toDto);
    }
}
