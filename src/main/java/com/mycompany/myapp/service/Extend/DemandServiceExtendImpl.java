package com.mycompany.myapp.service.Extend;

import com.mycompany.myapp.domain.Demand;
import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.repository.DemandRepository;
import com.mycompany.myapp.repository.ProductRepository;
import com.mycompany.myapp.repository.search.DemandSearchRepository;
import com.mycompany.myapp.service.DemandService;
import com.mycompany.myapp.service.dto.DemandDTO;
import com.mycompany.myapp.service.dto.ProductDTO;
import com.mycompany.myapp.service.impl.DemandServiceImpl;
import com.mycompany.myapp.service.mapper.DemandMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class DemandServiceExtendImpl extends DemandServiceImpl implements DemandService {

    private static final Logger log = LoggerFactory.getLogger(DemandServiceExtendImpl.class);
    protected final ProductRepository productRepository;

    public DemandServiceExtendImpl(
        DemandRepository demandRepository,
        DemandMapper demandMapper,
        DemandSearchRepository demandSearchRepository,
        ProductRepository productRepository
    ) {
        super(demandRepository, demandMapper, demandSearchRepository);
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        super.demandRepository.findAll().forEach(super.demandSearchRepository::index);
    }

    @Override
    public DemandDTO update(DemandDTO demandDTO) {
        log.debug("Request to update Demand : {}", demandDTO);

        Demand demand = demandMapper.toEntity(demandDTO);
        addProduct(demand);
        updateQT(demand);
        demand = demandRepository.save(demand);
        demandSearchRepository.index(demand);
        return demandMapper.toDto(demand);
    }

    // update the quantity only if status was in progress and the validation was true
    private void updateQT(Demand demand) {
        if (demand.getValidate() != null && demand.getValidate() && demand.getStatus().equals(Status.IN_PROGRESS)) {
            // set demand status to approved
            // update QT
            demand.setStatus(Status.APPROVED);
            Product product = demand.getName();
            int restQt = product.getqT() - demand.getqT();
            product.setqT(restQt);
        }
        if (demand.getValidate() != null && !demand.getValidate() && demand.getStatus().equals(Status.IN_PROGRESS)) {
            demand.setStatus(Status.REJECTED);
        }
    }

    private void addProduct(Demand demand) {
        var id = demand.getName().getId();
        Product product = productRepository.findById(id).orElse(null);
        demand.setName(product);
    }
}
