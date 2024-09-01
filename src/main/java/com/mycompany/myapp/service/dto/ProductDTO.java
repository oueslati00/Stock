package com.mycompany.myapp.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private Long id;

    @NotNull
    private String qrCode;

    private String name;

    @Lob
    private byte[] imageData;

    private String imageDataContentType;

    private String imageUrl;

    private Integer qT;

    private Boolean shouldBeNotification;

    private Boolean notificationDeleted;

    private Integer minQT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageDataContentType() {
        return imageDataContentType;
    }

    public void setImageDataContentType(String imageDataContentType) {
        this.imageDataContentType = imageDataContentType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getqT() {
        return qT;
    }

    public void setqT(Integer qT) {
        this.qT = qT;
    }

    public Boolean getShouldBeNotification() {
        return shouldBeNotification;
    }

    public void setShouldBeNotification(Boolean shouldBeNotification) {
        this.shouldBeNotification = shouldBeNotification;
    }

    public Boolean getNotificationDeleted() {
        return notificationDeleted;
    }

    public void setNotificationDeleted(Boolean notificationDeleted) {
        this.notificationDeleted = notificationDeleted;
    }

    public Integer getMinQT() {
        return minQT;
    }

    public void setMinQT(Integer minQT) {
        this.minQT = minQT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", qrCode='" + getQrCode() + "'" +
            ", name='" + getName() + "'" +
            ", imageData='" + getImageData() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", qT=" + getqT() +
            ", shouldBeNotification='" + getShouldBeNotification() + "'" +
            ", notificationDeleted='" + getNotificationDeleted() + "'" +
            ", minQT=" + getMinQT() +
            "}";
    }
}
