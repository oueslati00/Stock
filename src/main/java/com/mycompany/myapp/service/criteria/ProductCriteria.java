package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Product} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ProductResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /products?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter qrCode;

    private StringFilter name;

    private StringFilter imageUrl;

    private IntegerFilter qT;

    private BooleanFilter shouldBeNotification;

    private BooleanFilter notificationDeleted;

    private IntegerFilter minQT;

    private LongFilter demandId;

    private Boolean distinct;

    public ProductCriteria() {}

    public ProductCriteria(ProductCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.qrCode = other.optionalQrCode().map(StringFilter::copy).orElse(null);
        this.name = other.optionalName().map(StringFilter::copy).orElse(null);
        this.imageUrl = other.optionalImageUrl().map(StringFilter::copy).orElse(null);
        this.qT = other.optionalqT().map(IntegerFilter::copy).orElse(null);
        this.shouldBeNotification = other.optionalShouldBeNotification().map(BooleanFilter::copy).orElse(null);
        this.notificationDeleted = other.optionalNotificationDeleted().map(BooleanFilter::copy).orElse(null);
        this.minQT = other.optionalMinQT().map(IntegerFilter::copy).orElse(null);
        this.demandId = other.optionalDemandId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public ProductCriteria copy() {
        return new ProductCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getQrCode() {
        return qrCode;
    }

    public Optional<StringFilter> optionalQrCode() {
        return Optional.ofNullable(qrCode);
    }

    public StringFilter qrCode() {
        if (qrCode == null) {
            setQrCode(new StringFilter());
        }
        return qrCode;
    }

    public void setQrCode(StringFilter qrCode) {
        this.qrCode = qrCode;
    }

    public StringFilter getName() {
        return name;
    }

    public Optional<StringFilter> optionalName() {
        return Optional.ofNullable(name);
    }

    public StringFilter name() {
        if (name == null) {
            setName(new StringFilter());
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getImageUrl() {
        return imageUrl;
    }

    public Optional<StringFilter> optionalImageUrl() {
        return Optional.ofNullable(imageUrl);
    }

    public StringFilter imageUrl() {
        if (imageUrl == null) {
            setImageUrl(new StringFilter());
        }
        return imageUrl;
    }

    public void setImageUrl(StringFilter imageUrl) {
        this.imageUrl = imageUrl;
    }

    public IntegerFilter getqT() {
        return qT;
    }

    public Optional<IntegerFilter> optionalqT() {
        return Optional.ofNullable(qT);
    }

    public IntegerFilter qT() {
        if (qT == null) {
            setqT(new IntegerFilter());
        }
        return qT;
    }

    public void setqT(IntegerFilter qT) {
        this.qT = qT;
    }

    public BooleanFilter getShouldBeNotification() {
        return shouldBeNotification;
    }

    public Optional<BooleanFilter> optionalShouldBeNotification() {
        return Optional.ofNullable(shouldBeNotification);
    }

    public BooleanFilter shouldBeNotification() {
        if (shouldBeNotification == null) {
            setShouldBeNotification(new BooleanFilter());
        }
        return shouldBeNotification;
    }

    public void setShouldBeNotification(BooleanFilter shouldBeNotification) {
        this.shouldBeNotification = shouldBeNotification;
    }

    public BooleanFilter getNotificationDeleted() {
        return notificationDeleted;
    }

    public Optional<BooleanFilter> optionalNotificationDeleted() {
        return Optional.ofNullable(notificationDeleted);
    }

    public BooleanFilter notificationDeleted() {
        if (notificationDeleted == null) {
            setNotificationDeleted(new BooleanFilter());
        }
        return notificationDeleted;
    }

    public void setNotificationDeleted(BooleanFilter notificationDeleted) {
        this.notificationDeleted = notificationDeleted;
    }

    public IntegerFilter getMinQT() {
        return minQT;
    }

    public Optional<IntegerFilter> optionalMinQT() {
        return Optional.ofNullable(minQT);
    }

    public IntegerFilter minQT() {
        if (minQT == null) {
            setMinQT(new IntegerFilter());
        }
        return minQT;
    }

    public void setMinQT(IntegerFilter minQT) {
        this.minQT = minQT;
    }

    public LongFilter getDemandId() {
        return demandId;
    }

    public Optional<LongFilter> optionalDemandId() {
        return Optional.ofNullable(demandId);
    }

    public LongFilter demandId() {
        if (demandId == null) {
            setDemandId(new LongFilter());
        }
        return demandId;
    }

    public void setDemandId(LongFilter demandId) {
        this.demandId = demandId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductCriteria that = (ProductCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(qrCode, that.qrCode) &&
            Objects.equals(name, that.name) &&
            Objects.equals(imageUrl, that.imageUrl) &&
            Objects.equals(qT, that.qT) &&
            Objects.equals(shouldBeNotification, that.shouldBeNotification) &&
            Objects.equals(notificationDeleted, that.notificationDeleted) &&
            Objects.equals(minQT, that.minQT) &&
            Objects.equals(demandId, that.demandId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qrCode, name, imageUrl, qT, shouldBeNotification, notificationDeleted, minQT, demandId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalQrCode().map(f -> "qrCode=" + f + ", ").orElse("") +
            optionalName().map(f -> "name=" + f + ", ").orElse("") +
            optionalImageUrl().map(f -> "imageUrl=" + f + ", ").orElse("") +
            optionalqT().map(f -> "qT=" + f + ", ").orElse("") +
            optionalShouldBeNotification().map(f -> "shouldBeNotification=" + f + ", ").orElse("") +
            optionalNotificationDeleted().map(f -> "notificationDeleted=" + f + ", ").orElse("") +
            optionalMinQT().map(f -> "minQT=" + f + ", ").orElse("") +
            optionalDemandId().map(f -> "demandId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
