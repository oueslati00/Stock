package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.CheckList;
import com.mycompany.myapp.repository.CheckListRepository;
import com.mycompany.myapp.repository.search.CheckListSearchRepository;
import com.mycompany.myapp.service.CheckListService;
import com.mycompany.myapp.service.dto.CheckListDTO;
import com.mycompany.myapp.service.mapper.CheckListMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.CheckList}.
 */
@Service
@Transactional
public class CheckListServiceImpl implements CheckListService {

    private static final Logger log = LoggerFactory.getLogger(CheckListServiceImpl.class);

    protected final CheckListRepository checkListRepository;

    protected final CheckListMapper checkListMapper;

    protected final CheckListSearchRepository checkListSearchRepository;

    public CheckListServiceImpl(
        CheckListRepository checkListRepository,
        CheckListMapper checkListMapper,
        CheckListSearchRepository checkListSearchRepository
    ) {
        this.checkListRepository = checkListRepository;
        this.checkListMapper = checkListMapper;
        this.checkListSearchRepository = checkListSearchRepository;
    }

    @Override
    public CheckListDTO save(CheckListDTO checkListDTO) {
        log.debug("Request to save CheckList : {}", checkListDTO);
        CheckList checkList = checkListMapper.toEntity(checkListDTO);
        checkList = checkListRepository.save(checkList);
        checkListSearchRepository.index(checkList);
        return checkListMapper.toDto(checkList);
    }

    @Override
    public CheckListDTO update(CheckListDTO checkListDTO) {
        log.debug("Request to update CheckList : {}", checkListDTO);
        CheckList checkList = checkListMapper.toEntity(checkListDTO);
        checkList = checkListRepository.save(checkList);
        checkListSearchRepository.index(checkList);
        return checkListMapper.toDto(checkList);
    }

    @Override
    public Optional<CheckListDTO> partialUpdate(CheckListDTO checkListDTO) {
        log.debug("Request to partially update CheckList : {}", checkListDTO);

        return checkListRepository
            .findById(checkListDTO.getId())
            .map(existingCheckList -> {
                checkListMapper.partialUpdate(existingCheckList, checkListDTO);

                return existingCheckList;
            })
            .map(checkListRepository::save)
            .map(savedCheckList -> {
                checkListSearchRepository.index(savedCheckList);
                return savedCheckList;
            })
            .map(checkListMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CheckListDTO> findOne(Long id) {
        log.debug("Request to get CheckList : {}", id);
        return checkListRepository.findById(id).map(checkListMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CheckList : {}", id);
        checkListRepository.deleteById(id);
        checkListSearchRepository.deleteFromIndexById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CheckListDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CheckLists for query {}", query);
        return checkListSearchRepository.search(query, pageable).map(checkListMapper::toDto);
    }
}
