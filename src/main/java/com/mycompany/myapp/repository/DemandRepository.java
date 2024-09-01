package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Demand;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Demand entity.
 */
@Repository
public interface DemandRepository extends JpaRepository<Demand, Long>, JpaSpecificationExecutor<Demand> {
    default Optional<Demand> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Demand> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Demand> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(value = "select demand from Demand demand left join fetch demand.name", countQuery = "select count(demand) from Demand demand")
    Page<Demand> findAllWithToOneRelationships(Pageable pageable);

    @Query("select demand from Demand demand left join fetch demand.name")
    List<Demand> findAllWithToOneRelationships();

    @Query("select demand from Demand demand left join fetch demand.name where demand.id =:id")
    Optional<Demand> findOneWithToOneRelationships(@Param("id") Long id);
}
