package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Demand;
import com.mycompany.myapp.repository.DemandRepository;
import com.mycompany.myapp.repository.search.DemandSearchRepository;
import com.mycompany.myapp.service.DemandService;
import com.mycompany.myapp.service.dto.DemandDTO;
import com.mycompany.myapp.service.mapper.DemandMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Demand}.
 */
@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    private static final Logger log = LoggerFactory.getLogger(DemandServiceImpl.class);

    protected final DemandRepository demandRepository;

    protected final DemandMapper demandMapper;

    protected final DemandSearchRepository demandSearchRepository;

    public DemandServiceImpl(DemandRepository demandRepository, DemandMapper demandMapper, DemandSearchRepository demandSearchRepository) {
        this.demandRepository = demandRepository;
        this.demandMapper = demandMapper;
        this.demandSearchRepository = demandSearchRepository;
    }

    @Override
    public DemandDTO save(DemandDTO demandDTO) {
        log.debug("Request to save Demand : {}", demandDTO);
        Demand demand = demandMapper.toEntity(demandDTO);
        demand = demandRepository.save(demand);
        demandSearchRepository.index(demand);
        return demandMapper.toDto(demand);
    }

    @Override
    public DemandDTO update(DemandDTO demandDTO) {
        log.debug("Request to update Demand : {}", demandDTO);
        Demand demand = demandMapper.toEntity(demandDTO);
        demand = demandRepository.save(demand);
        demandSearchRepository.index(demand);
        return demandMapper.toDto(demand);
    }

    @Override
    public Optional<DemandDTO> partialUpdate(DemandDTO demandDTO) {
        log.debug("Request to partially update Demand : {}", demandDTO);

        return demandRepository
            .findById(demandDTO.getId())
            .map(existingDemand -> {
                demandMapper.partialUpdate(existingDemand, demandDTO);

                return existingDemand;
            })
            .map(demandRepository::save)
            .map(savedDemand -> {
                demandSearchRepository.index(savedDemand);
                return savedDemand;
            })
            .map(demandMapper::toDto);
    }

    public Page<DemandDTO> findAllWithEagerRelationships(Pageable pageable) {
        return demandRepository.findAllWithEagerRelationships(pageable).map(demandMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DemandDTO> findOne(Long id) {
        log.debug("Request to get Demand : {}", id);
        return demandRepository.findOneWithEagerRelationships(id).map(demandMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demand : {}", id);
        demandRepository.deleteById(id);
        demandSearchRepository.deleteFromIndexById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DemandDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Demands for query {}", query);
        return demandSearchRepository.search(query, pageable).map(demandMapper::toDto);
    }
}
