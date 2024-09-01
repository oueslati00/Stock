package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.TaskStatus;
import jakarta.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CheckList} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CheckListDTO implements Serializable {

    private Long id;

    private String client;

    private String contractNumber;

    private String adress;

    private String technicienDef;

    private Instant date;

    private TaskStatus tableDetectionTaskStatus;

    @Lob
    private String tableDetectionComment;

    private TaskStatus diDMTaskStatus;

    @Lob
    private String diDMComment;

    private TaskStatus detecteursPonctuelsTaskStatus;

    @Lob
    private String detecteursPonctuelsComment;

    private TaskStatus declencheurManuelsTaskStatus;

    @Lob
    private String declencheurManuelsComment;

    private TaskStatus tableMiseSecurityIncendieTaskStatus;

    @Lob
    private String tableMiseSecurityIncendieComment;

    private TaskStatus alimentationSecoursTaskStatus;

    @Lob
    private String alimentationSecoursComment;

    private TaskStatus equipementAlarmeTaskStatus;

    @Lob
    private String equipementAlarmeComment;

    private TaskStatus dasTaskStatus;

    @Lob
    private String dasComment;

    private TaskStatus arretTechniqueTaskStatus;

    @Lob
    private String arretTechniqueComment;

    private TaskStatus baiePcsTaskStatus;

    @Lob
    private String baiePCScomment;

    private TaskStatus superviseurTaskStatus;

    @Lob
    private String superviseurComment;

    private Boolean validate;

    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTechnicienDef() {
        return technicienDef;
    }

    public void setTechnicienDef(String technicienDef) {
        this.technicienDef = technicienDef;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public TaskStatus getTableDetectionTaskStatus() {
        return tableDetectionTaskStatus;
    }

    public void setTableDetectionTaskStatus(TaskStatus tableDetectionTaskStatus) {
        this.tableDetectionTaskStatus = tableDetectionTaskStatus;
    }

    public String getTableDetectionComment() {
        return tableDetectionComment;
    }

    public void setTableDetectionComment(String tableDetectionComment) {
        this.tableDetectionComment = tableDetectionComment;
    }

    public TaskStatus getDiDMTaskStatus() {
        return diDMTaskStatus;
    }

    public void setDiDMTaskStatus(TaskStatus diDMTaskStatus) {
        this.diDMTaskStatus = diDMTaskStatus;
    }

    public String getDiDMComment() {
        return diDMComment;
    }

    public void setDiDMComment(String diDMComment) {
        this.diDMComment = diDMComment;
    }

    public TaskStatus getDetecteursPonctuelsTaskStatus() {
        return detecteursPonctuelsTaskStatus;
    }

    public void setDetecteursPonctuelsTaskStatus(TaskStatus detecteursPonctuelsTaskStatus) {
        this.detecteursPonctuelsTaskStatus = detecteursPonctuelsTaskStatus;
    }

    public String getDetecteursPonctuelsComment() {
        return detecteursPonctuelsComment;
    }

    public void setDetecteursPonctuelsComment(String detecteursPonctuelsComment) {
        this.detecteursPonctuelsComment = detecteursPonctuelsComment;
    }

    public TaskStatus getDeclencheurManuelsTaskStatus() {
        return declencheurManuelsTaskStatus;
    }

    public void setDeclencheurManuelsTaskStatus(TaskStatus declencheurManuelsTaskStatus) {
        this.declencheurManuelsTaskStatus = declencheurManuelsTaskStatus;
    }

    public String getDeclencheurManuelsComment() {
        return declencheurManuelsComment;
    }

    public void setDeclencheurManuelsComment(String declencheurManuelsComment) {
        this.declencheurManuelsComment = declencheurManuelsComment;
    }

    public TaskStatus getTableMiseSecurityIncendieTaskStatus() {
        return tableMiseSecurityIncendieTaskStatus;
    }

    public void setTableMiseSecurityIncendieTaskStatus(TaskStatus tableMiseSecurityIncendieTaskStatus) {
        this.tableMiseSecurityIncendieTaskStatus = tableMiseSecurityIncendieTaskStatus;
    }

    public String getTableMiseSecurityIncendieComment() {
        return tableMiseSecurityIncendieComment;
    }

    public void setTableMiseSecurityIncendieComment(String tableMiseSecurityIncendieComment) {
        this.tableMiseSecurityIncendieComment = tableMiseSecurityIncendieComment;
    }

    public TaskStatus getAlimentationSecoursTaskStatus() {
        return alimentationSecoursTaskStatus;
    }

    public void setAlimentationSecoursTaskStatus(TaskStatus alimentationSecoursTaskStatus) {
        this.alimentationSecoursTaskStatus = alimentationSecoursTaskStatus;
    }

    public String getAlimentationSecoursComment() {
        return alimentationSecoursComment;
    }

    public void setAlimentationSecoursComment(String alimentationSecoursComment) {
        this.alimentationSecoursComment = alimentationSecoursComment;
    }

    public TaskStatus getEquipementAlarmeTaskStatus() {
        return equipementAlarmeTaskStatus;
    }

    public void setEquipementAlarmeTaskStatus(TaskStatus equipementAlarmeTaskStatus) {
        this.equipementAlarmeTaskStatus = equipementAlarmeTaskStatus;
    }

    public String getEquipementAlarmeComment() {
        return equipementAlarmeComment;
    }

    public void setEquipementAlarmeComment(String equipementAlarmeComment) {
        this.equipementAlarmeComment = equipementAlarmeComment;
    }

    public TaskStatus getDasTaskStatus() {
        return dasTaskStatus;
    }

    public void setDasTaskStatus(TaskStatus dasTaskStatus) {
        this.dasTaskStatus = dasTaskStatus;
    }

    public String getDasComment() {
        return dasComment;
    }

    public void setDasComment(String dasComment) {
        this.dasComment = dasComment;
    }

    public TaskStatus getArretTechniqueTaskStatus() {
        return arretTechniqueTaskStatus;
    }

    public void setArretTechniqueTaskStatus(TaskStatus arretTechniqueTaskStatus) {
        this.arretTechniqueTaskStatus = arretTechniqueTaskStatus;
    }

    public String getArretTechniqueComment() {
        return arretTechniqueComment;
    }

    public void setArretTechniqueComment(String arretTechniqueComment) {
        this.arretTechniqueComment = arretTechniqueComment;
    }

    public TaskStatus getBaiePcsTaskStatus() {
        return baiePcsTaskStatus;
    }

    public void setBaiePcsTaskStatus(TaskStatus baiePcsTaskStatus) {
        this.baiePcsTaskStatus = baiePcsTaskStatus;
    }

    public String getBaiePCScomment() {
        return baiePCScomment;
    }

    public void setBaiePCScomment(String baiePCScomment) {
        this.baiePCScomment = baiePCScomment;
    }

    public TaskStatus getSuperviseurTaskStatus() {
        return superviseurTaskStatus;
    }

    public void setSuperviseurTaskStatus(TaskStatus superviseurTaskStatus) {
        this.superviseurTaskStatus = superviseurTaskStatus;
    }

    public String getSuperviseurComment() {
        return superviseurComment;
    }

    public void setSuperviseurComment(String superviseurComment) {
        this.superviseurComment = superviseurComment;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckListDTO)) {
            return false;
        }

        CheckListDTO checkListDTO = (CheckListDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, checkListDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckListDTO{" +
            "id=" + getId() +
            ", client='" + getClient() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", adress='" + getAdress() + "'" +
            ", technicienDef='" + getTechnicienDef() + "'" +
            ", date='" + getDate() + "'" +
            ", tableDetectionTaskStatus='" + getTableDetectionTaskStatus() + "'" +
            ", tableDetectionComment='" + getTableDetectionComment() + "'" +
            ", diDMTaskStatus='" + getDiDMTaskStatus() + "'" +
            ", diDMComment='" + getDiDMComment() + "'" +
            ", detecteursPonctuelsTaskStatus='" + getDetecteursPonctuelsTaskStatus() + "'" +
            ", detecteursPonctuelsComment='" + getDetecteursPonctuelsComment() + "'" +
            ", declencheurManuelsTaskStatus='" + getDeclencheurManuelsTaskStatus() + "'" +
            ", declencheurManuelsComment='" + getDeclencheurManuelsComment() + "'" +
            ", tableMiseSecurityIncendieTaskStatus='" + getTableMiseSecurityIncendieTaskStatus() + "'" +
            ", tableMiseSecurityIncendieComment='" + getTableMiseSecurityIncendieComment() + "'" +
            ", alimentationSecoursTaskStatus='" + getAlimentationSecoursTaskStatus() + "'" +
            ", alimentationSecoursComment='" + getAlimentationSecoursComment() + "'" +
            ", equipementAlarmeTaskStatus='" + getEquipementAlarmeTaskStatus() + "'" +
            ", equipementAlarmeComment='" + getEquipementAlarmeComment() + "'" +
            ", dasTaskStatus='" + getDasTaskStatus() + "'" +
            ", dasComment='" + getDasComment() + "'" +
            ", arretTechniqueTaskStatus='" + getArretTechniqueTaskStatus() + "'" +
            ", arretTechniqueComment='" + getArretTechniqueComment() + "'" +
            ", baiePcsTaskStatus='" + getBaiePcsTaskStatus() + "'" +
            ", baiePCScomment='" + getBaiePCScomment() + "'" +
            ", superviseurTaskStatus='" + getSuperviseurTaskStatus() + "'" +
            ", superviseurComment='" + getSuperviseurComment() + "'" +
            ", validate='" + getValidate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
