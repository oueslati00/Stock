package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import com.mycompany.myapp.domain.enumeration.TaskStatus;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.CheckList} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.CheckListResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /check-lists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CheckListCriteria implements Serializable, Criteria {

    /**
     * Class for filtering TaskStatus
     */
    public static class TaskStatusFilter extends Filter<TaskStatus> {

        public TaskStatusFilter() {}

        public TaskStatusFilter(TaskStatusFilter filter) {
            super(filter);
        }

        @Override
        public TaskStatusFilter copy() {
            return new TaskStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter client;

    private StringFilter contractNumber;

    private StringFilter adress;

    private StringFilter technicienDef;

    private InstantFilter date;

    private TaskStatusFilter tableDetectionTaskStatus;

    private TaskStatusFilter diDMTaskStatus;

    private TaskStatusFilter detecteursPonctuelsTaskStatus;

    private TaskStatusFilter declencheurManuelsTaskStatus;

    private TaskStatusFilter tableMiseSecurityIncendieTaskStatus;

    private TaskStatusFilter alimentationSecoursTaskStatus;

    private TaskStatusFilter equipementAlarmeTaskStatus;

    private TaskStatusFilter dasTaskStatus;

    private TaskStatusFilter arretTechniqueTaskStatus;

    private TaskStatusFilter baiePcsTaskStatus;

    private TaskStatusFilter superviseurTaskStatus;

    private BooleanFilter validate;

    private StringFilter createdBy;

    private Boolean distinct;

    public CheckListCriteria() {}

    public CheckListCriteria(CheckListCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.client = other.optionalClient().map(StringFilter::copy).orElse(null);
        this.contractNumber = other.optionalContractNumber().map(StringFilter::copy).orElse(null);
        this.adress = other.optionalAdress().map(StringFilter::copy).orElse(null);
        this.technicienDef = other.optionalTechnicienDef().map(StringFilter::copy).orElse(null);
        this.date = other.optionalDate().map(InstantFilter::copy).orElse(null);
        this.tableDetectionTaskStatus = other.optionalTableDetectionTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.diDMTaskStatus = other.optionalDiDMTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.detecteursPonctuelsTaskStatus = other.optionalDetecteursPonctuelsTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.declencheurManuelsTaskStatus = other.optionalDeclencheurManuelsTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.tableMiseSecurityIncendieTaskStatus = other
            .optionalTableMiseSecurityIncendieTaskStatus()
            .map(TaskStatusFilter::copy)
            .orElse(null);
        this.alimentationSecoursTaskStatus = other.optionalAlimentationSecoursTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.equipementAlarmeTaskStatus = other.optionalEquipementAlarmeTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.dasTaskStatus = other.optionalDasTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.arretTechniqueTaskStatus = other.optionalArretTechniqueTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.baiePcsTaskStatus = other.optionalBaiePcsTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.superviseurTaskStatus = other.optionalSuperviseurTaskStatus().map(TaskStatusFilter::copy).orElse(null);
        this.validate = other.optionalValidate().map(BooleanFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CheckListCriteria copy() {
        return new CheckListCriteria(this);
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

    public StringFilter getClient() {
        return client;
    }

    public Optional<StringFilter> optionalClient() {
        return Optional.ofNullable(client);
    }

    public StringFilter client() {
        if (client == null) {
            setClient(new StringFilter());
        }
        return client;
    }

    public void setClient(StringFilter client) {
        this.client = client;
    }

    public StringFilter getContractNumber() {
        return contractNumber;
    }

    public Optional<StringFilter> optionalContractNumber() {
        return Optional.ofNullable(contractNumber);
    }

    public StringFilter contractNumber() {
        if (contractNumber == null) {
            setContractNumber(new StringFilter());
        }
        return contractNumber;
    }

    public void setContractNumber(StringFilter contractNumber) {
        this.contractNumber = contractNumber;
    }

    public StringFilter getAdress() {
        return adress;
    }

    public Optional<StringFilter> optionalAdress() {
        return Optional.ofNullable(adress);
    }

    public StringFilter adress() {
        if (adress == null) {
            setAdress(new StringFilter());
        }
        return adress;
    }

    public void setAdress(StringFilter adress) {
        this.adress = adress;
    }

    public StringFilter getTechnicienDef() {
        return technicienDef;
    }

    public Optional<StringFilter> optionalTechnicienDef() {
        return Optional.ofNullable(technicienDef);
    }

    public StringFilter technicienDef() {
        if (technicienDef == null) {
            setTechnicienDef(new StringFilter());
        }
        return technicienDef;
    }

    public void setTechnicienDef(StringFilter technicienDef) {
        this.technicienDef = technicienDef;
    }

    public InstantFilter getDate() {
        return date;
    }

    public Optional<InstantFilter> optionalDate() {
        return Optional.ofNullable(date);
    }

    public InstantFilter date() {
        if (date == null) {
            setDate(new InstantFilter());
        }
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public TaskStatusFilter getTableDetectionTaskStatus() {
        return tableDetectionTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalTableDetectionTaskStatus() {
        return Optional.ofNullable(tableDetectionTaskStatus);
    }

    public TaskStatusFilter tableDetectionTaskStatus() {
        if (tableDetectionTaskStatus == null) {
            setTableDetectionTaskStatus(new TaskStatusFilter());
        }
        return tableDetectionTaskStatus;
    }

    public void setTableDetectionTaskStatus(TaskStatusFilter tableDetectionTaskStatus) {
        this.tableDetectionTaskStatus = tableDetectionTaskStatus;
    }

    public TaskStatusFilter getDiDMTaskStatus() {
        return diDMTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalDiDMTaskStatus() {
        return Optional.ofNullable(diDMTaskStatus);
    }

    public TaskStatusFilter diDMTaskStatus() {
        if (diDMTaskStatus == null) {
            setDiDMTaskStatus(new TaskStatusFilter());
        }
        return diDMTaskStatus;
    }

    public void setDiDMTaskStatus(TaskStatusFilter diDMTaskStatus) {
        this.diDMTaskStatus = diDMTaskStatus;
    }

    public TaskStatusFilter getDetecteursPonctuelsTaskStatus() {
        return detecteursPonctuelsTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalDetecteursPonctuelsTaskStatus() {
        return Optional.ofNullable(detecteursPonctuelsTaskStatus);
    }

    public TaskStatusFilter detecteursPonctuelsTaskStatus() {
        if (detecteursPonctuelsTaskStatus == null) {
            setDetecteursPonctuelsTaskStatus(new TaskStatusFilter());
        }
        return detecteursPonctuelsTaskStatus;
    }

    public void setDetecteursPonctuelsTaskStatus(TaskStatusFilter detecteursPonctuelsTaskStatus) {
        this.detecteursPonctuelsTaskStatus = detecteursPonctuelsTaskStatus;
    }

    public TaskStatusFilter getDeclencheurManuelsTaskStatus() {
        return declencheurManuelsTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalDeclencheurManuelsTaskStatus() {
        return Optional.ofNullable(declencheurManuelsTaskStatus);
    }

    public TaskStatusFilter declencheurManuelsTaskStatus() {
        if (declencheurManuelsTaskStatus == null) {
            setDeclencheurManuelsTaskStatus(new TaskStatusFilter());
        }
        return declencheurManuelsTaskStatus;
    }

    public void setDeclencheurManuelsTaskStatus(TaskStatusFilter declencheurManuelsTaskStatus) {
        this.declencheurManuelsTaskStatus = declencheurManuelsTaskStatus;
    }

    public TaskStatusFilter getTableMiseSecurityIncendieTaskStatus() {
        return tableMiseSecurityIncendieTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalTableMiseSecurityIncendieTaskStatus() {
        return Optional.ofNullable(tableMiseSecurityIncendieTaskStatus);
    }

    public TaskStatusFilter tableMiseSecurityIncendieTaskStatus() {
        if (tableMiseSecurityIncendieTaskStatus == null) {
            setTableMiseSecurityIncendieTaskStatus(new TaskStatusFilter());
        }
        return tableMiseSecurityIncendieTaskStatus;
    }

    public void setTableMiseSecurityIncendieTaskStatus(TaskStatusFilter tableMiseSecurityIncendieTaskStatus) {
        this.tableMiseSecurityIncendieTaskStatus = tableMiseSecurityIncendieTaskStatus;
    }

    public TaskStatusFilter getAlimentationSecoursTaskStatus() {
        return alimentationSecoursTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalAlimentationSecoursTaskStatus() {
        return Optional.ofNullable(alimentationSecoursTaskStatus);
    }

    public TaskStatusFilter alimentationSecoursTaskStatus() {
        if (alimentationSecoursTaskStatus == null) {
            setAlimentationSecoursTaskStatus(new TaskStatusFilter());
        }
        return alimentationSecoursTaskStatus;
    }

    public void setAlimentationSecoursTaskStatus(TaskStatusFilter alimentationSecoursTaskStatus) {
        this.alimentationSecoursTaskStatus = alimentationSecoursTaskStatus;
    }

    public TaskStatusFilter getEquipementAlarmeTaskStatus() {
        return equipementAlarmeTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalEquipementAlarmeTaskStatus() {
        return Optional.ofNullable(equipementAlarmeTaskStatus);
    }

    public TaskStatusFilter equipementAlarmeTaskStatus() {
        if (equipementAlarmeTaskStatus == null) {
            setEquipementAlarmeTaskStatus(new TaskStatusFilter());
        }
        return equipementAlarmeTaskStatus;
    }

    public void setEquipementAlarmeTaskStatus(TaskStatusFilter equipementAlarmeTaskStatus) {
        this.equipementAlarmeTaskStatus = equipementAlarmeTaskStatus;
    }

    public TaskStatusFilter getDasTaskStatus() {
        return dasTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalDasTaskStatus() {
        return Optional.ofNullable(dasTaskStatus);
    }

    public TaskStatusFilter dasTaskStatus() {
        if (dasTaskStatus == null) {
            setDasTaskStatus(new TaskStatusFilter());
        }
        return dasTaskStatus;
    }

    public void setDasTaskStatus(TaskStatusFilter dasTaskStatus) {
        this.dasTaskStatus = dasTaskStatus;
    }

    public TaskStatusFilter getArretTechniqueTaskStatus() {
        return arretTechniqueTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalArretTechniqueTaskStatus() {
        return Optional.ofNullable(arretTechniqueTaskStatus);
    }

    public TaskStatusFilter arretTechniqueTaskStatus() {
        if (arretTechniqueTaskStatus == null) {
            setArretTechniqueTaskStatus(new TaskStatusFilter());
        }
        return arretTechniqueTaskStatus;
    }

    public void setArretTechniqueTaskStatus(TaskStatusFilter arretTechniqueTaskStatus) {
        this.arretTechniqueTaskStatus = arretTechniqueTaskStatus;
    }

    public TaskStatusFilter getBaiePcsTaskStatus() {
        return baiePcsTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalBaiePcsTaskStatus() {
        return Optional.ofNullable(baiePcsTaskStatus);
    }

    public TaskStatusFilter baiePcsTaskStatus() {
        if (baiePcsTaskStatus == null) {
            setBaiePcsTaskStatus(new TaskStatusFilter());
        }
        return baiePcsTaskStatus;
    }

    public void setBaiePcsTaskStatus(TaskStatusFilter baiePcsTaskStatus) {
        this.baiePcsTaskStatus = baiePcsTaskStatus;
    }

    public TaskStatusFilter getSuperviseurTaskStatus() {
        return superviseurTaskStatus;
    }

    public Optional<TaskStatusFilter> optionalSuperviseurTaskStatus() {
        return Optional.ofNullable(superviseurTaskStatus);
    }

    public TaskStatusFilter superviseurTaskStatus() {
        if (superviseurTaskStatus == null) {
            setSuperviseurTaskStatus(new TaskStatusFilter());
        }
        return superviseurTaskStatus;
    }

    public void setSuperviseurTaskStatus(TaskStatusFilter superviseurTaskStatus) {
        this.superviseurTaskStatus = superviseurTaskStatus;
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

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public Optional<StringFilter> optionalCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            setCreatedBy(new StringFilter());
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
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
        final CheckListCriteria that = (CheckListCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(client, that.client) &&
            Objects.equals(contractNumber, that.contractNumber) &&
            Objects.equals(adress, that.adress) &&
            Objects.equals(technicienDef, that.technicienDef) &&
            Objects.equals(date, that.date) &&
            Objects.equals(tableDetectionTaskStatus, that.tableDetectionTaskStatus) &&
            Objects.equals(diDMTaskStatus, that.diDMTaskStatus) &&
            Objects.equals(detecteursPonctuelsTaskStatus, that.detecteursPonctuelsTaskStatus) &&
            Objects.equals(declencheurManuelsTaskStatus, that.declencheurManuelsTaskStatus) &&
            Objects.equals(tableMiseSecurityIncendieTaskStatus, that.tableMiseSecurityIncendieTaskStatus) &&
            Objects.equals(alimentationSecoursTaskStatus, that.alimentationSecoursTaskStatus) &&
            Objects.equals(equipementAlarmeTaskStatus, that.equipementAlarmeTaskStatus) &&
            Objects.equals(dasTaskStatus, that.dasTaskStatus) &&
            Objects.equals(arretTechniqueTaskStatus, that.arretTechniqueTaskStatus) &&
            Objects.equals(baiePcsTaskStatus, that.baiePcsTaskStatus) &&
            Objects.equals(superviseurTaskStatus, that.superviseurTaskStatus) &&
            Objects.equals(validate, that.validate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            client,
            contractNumber,
            adress,
            technicienDef,
            date,
            tableDetectionTaskStatus,
            diDMTaskStatus,
            detecteursPonctuelsTaskStatus,
            declencheurManuelsTaskStatus,
            tableMiseSecurityIncendieTaskStatus,
            alimentationSecoursTaskStatus,
            equipementAlarmeTaskStatus,
            dasTaskStatus,
            arretTechniqueTaskStatus,
            baiePcsTaskStatus,
            superviseurTaskStatus,
            validate,
            createdBy,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckListCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalClient().map(f -> "client=" + f + ", ").orElse("") +
            optionalContractNumber().map(f -> "contractNumber=" + f + ", ").orElse("") +
            optionalAdress().map(f -> "adress=" + f + ", ").orElse("") +
            optionalTechnicienDef().map(f -> "technicienDef=" + f + ", ").orElse("") +
            optionalDate().map(f -> "date=" + f + ", ").orElse("") +
            optionalTableDetectionTaskStatus().map(f -> "tableDetectionTaskStatus=" + f + ", ").orElse("") +
            optionalDiDMTaskStatus().map(f -> "diDMTaskStatus=" + f + ", ").orElse("") +
            optionalDetecteursPonctuelsTaskStatus().map(f -> "detecteursPonctuelsTaskStatus=" + f + ", ").orElse("") +
            optionalDeclencheurManuelsTaskStatus().map(f -> "declencheurManuelsTaskStatus=" + f + ", ").orElse("") +
            optionalTableMiseSecurityIncendieTaskStatus().map(f -> "tableMiseSecurityIncendieTaskStatus=" + f + ", ").orElse("") +
            optionalAlimentationSecoursTaskStatus().map(f -> "alimentationSecoursTaskStatus=" + f + ", ").orElse("") +
            optionalEquipementAlarmeTaskStatus().map(f -> "equipementAlarmeTaskStatus=" + f + ", ").orElse("") +
            optionalDasTaskStatus().map(f -> "dasTaskStatus=" + f + ", ").orElse("") +
            optionalArretTechniqueTaskStatus().map(f -> "arretTechniqueTaskStatus=" + f + ", ").orElse("") +
            optionalBaiePcsTaskStatus().map(f -> "baiePcsTaskStatus=" + f + ", ").orElse("") +
            optionalSuperviseurTaskStatus().map(f -> "superviseurTaskStatus=" + f + ", ").orElse("") +
            optionalValidate().map(f -> "validate=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
