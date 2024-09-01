package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "Product")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "qr_code", nullable = false)
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String qrCode;

    @Column(name = "name")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String name;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @Column(name = "image_data_content_type")
    private String imageDataContentType;

    @Column(name = "image_url")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String imageUrl;

    @Column(name = "q_t")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer qT;

    @Column(name = "should_be_notification")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean shouldBeNotification;

    @Column(name = "notification_deleted")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean notificationDeleted;

    @Column(name = "min_qt")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer minQT;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "name", cascade = CascadeType.REMOVE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "name" }, allowSetters = true)
    private Set<Demand> demands = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return this.qrCode;
    }

    public Product qrCode(String qrCode) {
        this.setQrCode(qrCode);
        return this;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public Product imageData(byte[] imageData) {
        this.setImageData(imageData);
        return this;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageDataContentType() {
        return this.imageDataContentType;
    }

    public Product imageDataContentType(String imageDataContentType) {
        this.imageDataContentType = imageDataContentType;
        return this;
    }

    public void setImageDataContentType(String imageDataContentType) {
        this.imageDataContentType = imageDataContentType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Product imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getqT() {
        return this.qT;
    }

    public Product qT(Integer qT) {
        this.setqT(qT);
        return this;
    }

    public void setqT(Integer qT) {
        this.qT = qT;
    }

    public Boolean getShouldBeNotification() {
        return this.shouldBeNotification;
    }

    public Product shouldBeNotification(Boolean shouldBeNotification) {
        this.setShouldBeNotification(shouldBeNotification);
        return this;
    }

    public void setShouldBeNotification(Boolean shouldBeNotification) {
        this.shouldBeNotification = shouldBeNotification;
    }

    public Boolean getNotificationDeleted() {
        return this.notificationDeleted;
    }

    public Product notificationDeleted(Boolean notificationDeleted) {
        this.setNotificationDeleted(notificationDeleted);
        return this;
    }

    public void setNotificationDeleted(Boolean notificationDeleted) {
        this.notificationDeleted = notificationDeleted;
    }

    public Integer getMinQT() {
        return this.minQT;
    }

    public Product minQT(Integer minQT) {
        this.setMinQT(minQT);
        return this;
    }

    public void setMinQT(Integer minQT) {
        this.minQT = minQT;
    }

    public Set<Demand> getDemands() {
        return this.demands;
    }

    public void setDemands(Set<Demand> demands) {
        if (this.demands != null) {
            this.demands.forEach(i -> i.setName(null));
        }
        if (demands != null) {
            demands.forEach(i -> i.setName(this));
        }
        this.demands = demands;
    }

    public Product demands(Set<Demand> demands) {
        this.setDemands(demands);
        return this;
    }

    public Product addDemand(Demand demand) {
        this.demands.add(demand);
        demand.setName(this);
        return this;
    }

    public Product removeDemand(Demand demand) {
        this.demands.remove(demand);
        demand.setName(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", qrCode='" + getQrCode() + "'" +
            ", name='" + getName() + "'" +
            ", imageData='" + getImageData() + "'" +
            ", imageDataContentType='" + getImageDataContentType() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", qT=" + getqT() +
            ", shouldBeNotification='" + getShouldBeNotification() + "'" +
            ", notificationDeleted='" + getNotificationDeleted() + "'" +
            ", minQT=" + getMinQT() +
            "}";
    }
}
