package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Demand} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DemandResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /demands?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DemandCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {

        public StatusFilter() {}

        public StatusFilter(StatusFilter filter) {
            super(filter);
        }

        @Override
        public StatusFilter copy() {
            return new StatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter qT;

    private StringFilter demandBy;

    private InstantFilter demandDate;

    private StatusFilter status;

    private BooleanFilter validate;

    private LongFilter nameId;

    private Boolean distinct;

    public DemandCriteria() {}

    public DemandCriteria(DemandCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.qT = other.optionalqT().map(IntegerFilter::copy).orElse(null);
        this.demandBy = other.optionalDemandBy().map(StringFilter::copy).orElse(null);
        this.demandDate = other.optionalDemandDate().map(InstantFilter::copy).orElse(null);
        this.status = other.optionalStatus().map(StatusFilter::copy).orElse(null);
        this.validate = other.optionalValidate().map(BooleanFilter::copy).orElse(null);
        this.nameId = other.optionalNameId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DemandCriteria copy() {
        return new DemandCriteria(this);
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

    public StringFilter getDemandBy() {
        return demandBy;
    }

    public Optional<StringFilter> optionalDemandBy() {
        return Optional.ofNullable(demandBy);
    }

    public StringFilter demandBy() {
        if (demandBy == null) {
            setDemandBy(new StringFilter());
        }
        return demandBy;
    }

    public void setDemandBy(StringFilter demandBy) {
        this.demandBy = demandBy;
    }

    public InstantFilter getDemandDate() {
        return demandDate;
    }

    public Optional<InstantFilter> optionalDemandDate() {
        return Optional.ofNullable(demandDate);
    }

    public InstantFilter demandDate() {
        if (demandDate == null) {
            setDemandDate(new InstantFilter());
        }
        return demandDate;
    }

    public void setDemandDate(InstantFilter demandDate) {
        this.demandDate = demandDate;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public Optional<StatusFilter> optionalStatus() {
        return Optional.ofNullable(status);
    }

    public StatusFilter status() {
        if (status == null) {
            setStatus(new StatusFilter());
        }
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
    }

    public BooleanFilter getValidate() {
        return validate;
    }

    public Optional<BooleanFilter> optionalValidate() {
        return Optional.ofNullable(validate);
    }

    public BooleanFilter validate() {
        if (validate == null) {
            setValidate(new BooleanFilter());
        }
        return validate;
    }

    public void setValidate(BooleanFilter validate) {
        this.validate = validate;
    }

    public LongFilter getNameId() {
        return nameId;
    }

    public Optional<LongFilter> optionalNameId() {
        return Optional.ofNullable(nameId);
    }

    public LongFilter nameId() {
        if (nameId == null) {
            setNameId(new LongFilter());
        }
        return nameId;
    }

    public void setNameId(LongFilter nameId) {
        this.nameId = nameId;
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
        final DemandCriteria that = (DemandCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(qT, that.qT) &&
            Objects.equals(demandBy, that.demandBy) &&
            Objects.equals(demandDate, that.demandDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(validate, that.validate) &&
            Objects.equals(nameId, that.nameId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qT, demandBy, demandDate, status, validate, nameId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalqT().map(f -> "qT=" + f + ", ").orElse("") +
            optionalDemandBy().map(f -> "demandBy=" + f + ", ").orElse("") +
            optionalDemandDate().map(f -> "demandDate=" + f + ", ").orElse("") +
            optionalStatus().map(f -> "status=" + f + ", ").orElse("") +
            optionalValidate().map(f -> "validate=" + f + ", ").orElse("") +
            optionalNameId().map(f -> "nameId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
