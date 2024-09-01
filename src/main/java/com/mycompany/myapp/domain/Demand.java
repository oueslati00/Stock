package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.Status;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Demand.
 */
@Entity
@Table(name = "Demand")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "demand")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Demand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "q_t")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer qT;

    @Column(name = "demand_by")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String demandBy;

    @Column(name = "demand_date")
    private Instant demandDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private Status status;

    @Column(name = "validate")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean validate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product name;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Demand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getqT() {
        return this.qT;
    }

    public Demand qT(Integer qT) {
        this.setqT(qT);
        return this;
    }

    public void setqT(Integer qT) {
        this.qT = qT;
    }

    public String getDemandBy() {
        return this.demandBy;
    }

    public Demand demandBy(String demandBy) {
        this.setDemandBy(demandBy);
        return this;
    }

    public void setDemandBy(String demandBy) {
        this.demandBy = demandBy;
    }

    public Instant getDemandDate() {
        return this.demandDate;
    }

    public Demand demandDate(Instant demandDate) {
        this.setDemandDate(demandDate);
        return this;
    }

    public void setDemandDate(Instant demandDate) {
        this.demandDate = demandDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public Demand status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getValidate() {
        return this.validate;
    }

    public Demand validate(Boolean validate) {
        this.setValidate(validate);
        return this;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public Product getName() {
        return this.name;
    }

    public void setName(Product product) {
        this.name = product;
    }

    public Demand name(Product product) {
        this.setName(product);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demand)) {
            return false;
        }
        return getId() != null && getId().equals(((Demand) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demand{" +
            "id=" + getId() +
            ", qT=" + getqT() +
            ", demandBy='" + getDemandBy() + "'" +
            ", demandDate='" + getDemandDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", validate='" + getValidate() + "'" +
            "}";
    }
}
