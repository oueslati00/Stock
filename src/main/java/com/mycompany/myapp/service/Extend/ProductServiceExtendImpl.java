package com.mycompany.myapp.service.Extend;

import com.mycompany.myapp.repository.ProductRepository;
import com.mycompany.myapp.repository.search.ProductSearchRepository;
import com.mycompany.myapp.service.ProductService;
import com.mycompany.myapp.service.impl.ProductServiceImpl;
import com.mycompany.myapp.service.mapper.ProductMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class ProductServiceExtendImpl extends ProductServiceImpl implements ProductService {

    public ProductServiceExtendImpl(
        ProductRepository productRepository,
        ProductMapper productMapper,
        ProductSearchRepository productSearchRepository
    ) {
        super(productRepository, productMapper, productSearchRepository);
    }

    // update all the data from mysql
    @PostConstruct
    public void init() {
        super.productRepository.findAll().forEach(super.productSearchRepository::index);
    }
}
