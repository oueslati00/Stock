package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.Status;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Demand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DemandDTO implements Serializable {

    private Long id;

    private Integer qT;

    private String demandBy;

    private Instant demandDate;

    private Status status;

    private Boolean validate;

    @NotNull
    private ProductDTO name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getqT() {
        return qT;
    }

    public void setqT(Integer qT) {
        this.qT = qT;
    }

    public String getDemandBy() {
        return demandBy;
    }

    public void setDemandBy(String demandBy) {
        this.demandBy = demandBy;
    }

    public Instant getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Instant demandDate) {
        this.demandDate = demandDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public ProductDTO getName() {
        return name;
    }

    public void setName(ProductDTO name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandDTO)) {
            return false;
        }

        DemandDTO demandDTO = (DemandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, demandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandDTO{" +
            "id=" + getId() +
            ", qT=" + getqT() +
            ", demandBy='" + getDemandBy() + "'" +
            ", demandDate='" + getDemandDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", validate='" + getValidate() + "'" +
            ", name=" + getName() +
            "}";
    }
}
