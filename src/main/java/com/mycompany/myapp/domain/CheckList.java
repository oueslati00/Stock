package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.TaskStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A CheckList.
 */
@Entity
@Table(name = "check_list")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "checklist")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CheckList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String client;

    @Column(name = "contract_number")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String contractNumber;

    @Column(name = "adress")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String adress;

    @Column(name = "technicien_def")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String technicienDef;

    @Column(name = "date")
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "table_detection_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus tableDetectionTaskStatus;

    @Lob
    @Column(name = "table_detection_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String tableDetectionComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "di_dm_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus diDMTaskStatus;

    @Lob
    @Column(name = "di_dm_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String diDMComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "detecteurs_ponctuels_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus detecteursPonctuelsTaskStatus;

    @Lob
    @Column(name = "detecteurs_ponctuels_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String detecteursPonctuelsComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "declencheur_manuels_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus declencheurManuelsTaskStatus;

    @Lob
    @Column(name = "declencheur_manuels_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String declencheurManuelsComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "table_mise_security_incendie_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus tableMiseSecurityIncendieTaskStatus;

    @Lob
    @Column(name = "table_mise_security_incendie_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String tableMiseSecurityIncendieComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "alimentation_secours_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus alimentationSecoursTaskStatus;

    @Lob
    @Column(name = "alimentation_secours_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String alimentationSecoursComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "equipement_alarme_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus equipementAlarmeTaskStatus;

    @Lob
    @Column(name = "equipement_alarme_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String equipementAlarmeComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "das_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus dasTaskStatus;

    @Lob
    @Column(name = "das_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String dasComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "arret_technique_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus arretTechniqueTaskStatus;

    @Lob
    @Column(name = "arret_technique_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String arretTechniqueComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "baie_pcs_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus baiePcsTaskStatus;

    @Lob
    @Column(name = "baie_pc_scomment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String baiePCScomment;

    @Enumerated(EnumType.STRING)
    @Column(name = "superviseur_task_status")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private TaskStatus superviseurTaskStatus;

    @Lob
    @Column(name = "superviseur_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String superviseurComment;

    @Column(name = "validate")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean validate;

    @Column(name = "created_by")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String createdBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CheckList id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return this.client;
    }

    public CheckList client(String client) {
        this.setClient(client);
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public CheckList contractNumber(String contractNumber) {
        this.setContractNumber(contractNumber);
        return this;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getAdress() {
        return this.adress;
    }

    public CheckList adress(String adress) {
        this.setAdress(adress);
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTechnicienDef() {
        return this.technicienDef;
    }

    public CheckList technicienDef(String technicienDef) {
        this.setTechnicienDef(technicienDef);
        return this;
    }

    public void setTechnicienDef(String technicienDef) {
        this.technicienDef = technicienDef;
    }

    public Instant getDate() {
        return this.date;
    }

    public CheckList date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public TaskStatus getTableDetectionTaskStatus() {
        return this.tableDetectionTaskStatus;
    }

    public CheckList tableDetectionTaskStatus(TaskStatus tableDetectionTaskStatus) {
        this.setTableDetectionTaskStatus(tableDetectionTaskStatus);
        return this;
    }

    public void setTableDetectionTaskStatus(TaskStatus tableDetectionTaskStatus) {
        this.tableDetectionTaskStatus = tableDetectionTaskStatus;
    }

    public String getTableDetectionComment() {
        return this.tableDetectionComment;
    }

    public CheckList tableDetectionComment(String tableDetectionComment) {
        this.setTableDetectionComment(tableDetectionComment);
        return this;
    }

    public void setTableDetectionComment(String tableDetectionComment) {
        this.tableDetectionComment = tableDetectionComment;
    }

    public TaskStatus getDiDMTaskStatus() {
        return this.diDMTaskStatus;
    }

    public CheckList diDMTaskStatus(TaskStatus diDMTaskStatus) {
        this.setDiDMTaskStatus(diDMTaskStatus);
        return this;
    }

    public void setDiDMTaskStatus(TaskStatus diDMTaskStatus) {
        this.diDMTaskStatus = diDMTaskStatus;
    }

    public String getDiDMComment() {
        return this.diDMComment;
    }

    public CheckList diDMComment(String diDMComment) {
        this.setDiDMComment(diDMComment);
        return this;
    }

    public void setDiDMComment(String diDMComment) {
        this.diDMComment = diDMComment;
    }

    public TaskStatus getDetecteursPonctuelsTaskStatus() {
        return this.detecteursPonctuelsTaskStatus;
    }

    public CheckList detecteursPonctuelsTaskStatus(TaskStatus detecteursPonctuelsTaskStatus) {
        this.setDetecteursPonctuelsTaskStatus(detecteursPonctuelsTaskStatus);
        return this;
    }

    public void setDetecteursPonctuelsTaskStatus(TaskStatus detecteursPonctuelsTaskStatus) {
        this.detecteursPonctuelsTaskStatus = detecteursPonctuelsTaskStatus;
    }

    public String getDetecteursPonctuelsComment() {
        return this.detecteursPonctuelsComment;
    }

    public CheckList detecteursPonctuelsComment(String detecteursPonctuelsComment) {
        this.setDetecteursPonctuelsComment(detecteursPonctuelsComment);
        return this;
    }

    public void setDetecteursPonctuelsComment(String detecteursPonctuelsComment) {
        this.detecteursPonctuelsComment = detecteursPonctuelsComment;
    }

    public TaskStatus getDeclencheurManuelsTaskStatus() {
        return this.declencheurManuelsTaskStatus;
    }

    public CheckList declencheurManuelsTaskStatus(TaskStatus declencheurManuelsTaskStatus) {
        this.setDeclencheurManuelsTaskStatus(declencheurManuelsTaskStatus);
        return this;
    }

    public void setDeclencheurManuelsTaskStatus(TaskStatus declencheurManuelsTaskStatus) {
        this.declencheurManuelsTaskStatus = declencheurManuelsTaskStatus;
    }

    public String getDeclencheurManuelsComment() {
        return this.declencheurManuelsComment;
    }

    public CheckList declencheurManuelsComment(String declencheurManuelsComment) {
        this.setDeclencheurManuelsComment(declencheurManuelsComment);
        return this;
    }

    public void setDeclencheurManuelsComment(String declencheurManuelsComment) {
        this.declencheurManuelsComment = declencheurManuelsComment;
    }

    public TaskStatus getTableMiseSecurityIncendieTaskStatus() {
        return this.tableMiseSecurityIncendieTaskStatus;
    }

    public CheckList tableMiseSecurityIncendieTaskStatus(TaskStatus tableMiseSecurityIncendieTaskStatus) {
        this.setTableMiseSecurityIncendieTaskStatus(tableMiseSecurityIncendieTaskStatus);
        return this;
    }

    public void setTableMiseSecurityIncendieTaskStatus(TaskStatus tableMiseSecurityIncendieTaskStatus) {
        this.tableMiseSecurityIncendieTaskStatus = tableMiseSecurityIncendieTaskStatus;
    }

    public String getTableMiseSecurityIncendieComment() {
        return this.tableMiseSecurityIncendieComment;
    }

    public CheckList tableMiseSecurityIncendieComment(String tableMiseSecurityIncendieComment) {
        this.setTableMiseSecurityIncendieComment(tableMiseSecurityIncendieComment);
        return this;
    }

    public void setTableMiseSecurityIncendieComment(String tableMiseSecurityIncendieComment) {
        this.tableMiseSecurityIncendieComment = tableMiseSecurityIncendieComment;
    }

    public TaskStatus getAlimentationSecoursTaskStatus() {
        return this.alimentationSecoursTaskStatus;
    }

    public CheckList alimentationSecoursTaskStatus(TaskStatus alimentationSecoursTaskStatus) {
        this.setAlimentationSecoursTaskStatus(alimentationSecoursTaskStatus);
        return this;
    }

    public void setAlimentationSecoursTaskStatus(TaskStatus alimentationSecoursTaskStatus) {
        this.alimentationSecoursTaskStatus = alimentationSecoursTaskStatus;
    }

    public String getAlimentationSecoursComment() {
        return this.alimentationSecoursComment;
    }

    public CheckList alimentationSecoursComment(String alimentationSecoursComment) {
        this.setAlimentationSecoursComment(alimentationSecoursComment);
        return this;
    }

    public void setAlimentationSecoursComment(String alimentationSecoursComment) {
        this.alimentationSecoursComment = alimentationSecoursComment;
    }

    public TaskStatus getEquipementAlarmeTaskStatus() {
        return this.equipementAlarmeTaskStatus;
    }

    public CheckList equipementAlarmeTaskStatus(TaskStatus equipementAlarmeTaskStatus) {
        this.setEquipementAlarmeTaskStatus(equipementAlarmeTaskStatus);
        return this;
    }

    public void setEquipementAlarmeTaskStatus(TaskStatus equipementAlarmeTaskStatus) {
        this.equipementAlarmeTaskStatus = equipementAlarmeTaskStatus;
    }

    public String getEquipementAlarmeComment() {
        return this.equipementAlarmeComment;
    }

    public CheckList equipementAlarmeComment(String equipementAlarmeComment) {
        this.setEquipementAlarmeComment(equipementAlarmeComment);
        return this;
    }

    public void setEquipementAlarmeComment(String equipementAlarmeComment) {
        this.equipementAlarmeComment = equipementAlarmeComment;
    }

    public TaskStatus getDasTaskStatus() {
        return this.dasTaskStatus;
    }

    public CheckList dasTaskStatus(TaskStatus dasTaskStatus) {
        this.setDasTaskStatus(dasTaskStatus);
        return this;
    }

    public void setDasTaskStatus(TaskStatus dasTaskStatus) {
        this.dasTaskStatus = dasTaskStatus;
    }

    public String getDasComment() {
        return this.dasComment;
    }

    public CheckList dasComment(String dasComment) {
        this.setDasComment(dasComment);
        return this;
    }

    public void setDasComment(String dasComment) {
        this.dasComment = dasComment;
    }

    public TaskStatus getArretTechniqueTaskStatus() {
        return this.arretTechniqueTaskStatus;
    }

    public CheckList arretTechniqueTaskStatus(TaskStatus arretTechniqueTaskStatus) {
        this.setArretTechniqueTaskStatus(arretTechniqueTaskStatus);
        return this;
    }

    public void setArretTechniqueTaskStatus(TaskStatus arretTechniqueTaskStatus) {
        this.arretTechniqueTaskStatus = arretTechniqueTaskStatus;
    }

    public String getArretTechniqueComment() {
        return this.arretTechniqueComment;
    }

    public CheckList arretTechniqueComment(String arretTechniqueComment) {
        this.setArretTechniqueComment(arretTechniqueComment);
        return this;
    }

    public void setArretTechniqueComment(String arretTechniqueComment) {
        this.arretTechniqueComment = arretTechniqueComment;
    }

    public TaskStatus getBaiePcsTaskStatus() {
        return this.baiePcsTaskStatus;
    }

    public CheckList baiePcsTaskStatus(TaskStatus baiePcsTaskStatus) {
        this.setBaiePcsTaskStatus(baiePcsTaskStatus);
        return this;
    }

    public void setBaiePcsTaskStatus(TaskStatus baiePcsTaskStatus) {
        this.baiePcsTaskStatus = baiePcsTaskStatus;
    }

    public String getBaiePCScomment() {
        return this.baiePCScomment;
    }

    public CheckList baiePCScomment(String baiePCScomment) {
        this.setBaiePCScomment(baiePCScomment);
        return this;
    }

    public void setBaiePCScomment(String baiePCScomment) {
        this.baiePCScomment = baiePCScomment;
    }

    public TaskStatus getSuperviseurTaskStatus() {
        return this.superviseurTaskStatus;
    }

    public CheckList superviseurTaskStatus(TaskStatus superviseurTaskStatus) {
        this.setSuperviseurTaskStatus(superviseurTaskStatus);
        return this;
    }

    public void setSuperviseurTaskStatus(TaskStatus superviseurTaskStatus) {
        this.superviseurTaskStatus = superviseurTaskStatus;
    }

    public String getSuperviseurComment() {
        return this.superviseurComment;
    }

    public CheckList superviseurComment(String superviseurComment) {
        this.setSuperviseurComment(superviseurComment);
        return this;
    }

    public void setSuperviseurComment(String superviseurComment) {
        this.superviseurComment = superviseurComment;
    }

    public Boolean getValidate() {
        return this.validate;
    }

    public CheckList validate(Boolean validate) {
        this.setValidate(validate);
        return this;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public CheckList createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckList)) {
            return false;
        }
        return getId() != null && getId().equals(((CheckList) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckList{" +
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
