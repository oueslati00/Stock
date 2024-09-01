package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A ReportInterventionList.
 */
@Entity
@Table(name = "report_intervention_list")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "reportinterventionlist")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportInterventionList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "site")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String site;

    @Column(name = "code_agence")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codeAgence;

    @Column(name = "affaire_number")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String affaireNumber;

    @Column(name = "contract_number")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String contractNumber;

    @Column(name = "installation_adress")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String installationAdress;

    @Column(name = "interlocuteur_intervation")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String interlocuteurIntervation;

    @Column(name = "tel")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String tel;

    @Column(name = "installation_sous_contract")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean installationSousContract;

    @Column(name = "installation_sous_garantie")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean installationSousGarantie;

    @Column(name = "adress_facturation")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String adressFacturation;

    @Column(name = "interlocuteur_facturation")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String interlocuteurFacturation;

    @Column(name = "condition_de_payement_cheque")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean conditionDePayementCheque;

    @Column(name = "condition_payement_autre")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean conditionPayementAutre;

    @Column(name = "condition_payement_comment")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String conditionPayementComment;

    @Column(name = "mise_en_service_definitvie")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean miseEnServiceDefinitvie;

    @Column(name = "mise_en_service_partielle")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean miseEnServicePartielle;

    @Column(name = "maintenance_preventive")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean maintenancePreventive;

    @Column(name = "maintenance_corrective")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean maintenanceCorrective;

    @Column(name = "b_t")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String bT;

    @Column(name = "anomalie_signalee")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String anomalieSignalee;

    @Column(name = "intervention_date")
    private Instant interventionDate;

    @Column(name = "intervention_start_date")
    private Instant interventionStartDate;

    @Column(name = "remise_service_date")
    private Instant remiseServiceDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Lob
    @Column(name = "nature_intervention")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String natureIntervention;

    @Column(name = "cause_exterieur_installation")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean causeExterieurInstallation;

    @Column(name = "installation_fonctionnelle_apres_inervention")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean installationFonctionnelleApresInervention;

    @Column(name = "autre_interventions_a_prevoir")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String autreInterventionsAPrevoir;

    @Column(name = "client_apreciation")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String clientApreciation;

    @Enumerated(EnumType.STRING)
    @Column(name = "respect_delais")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private EvaluationStatus respectDelais;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualite_intervention")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private EvaluationStatus qualiteIntervention;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualite_devoir_conseil")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private EvaluationStatus qualiteDevoirConseil;

    @Column(name = "prestations_achevees")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean prestationsAchevees;

    @Column(name = "devis_complentaire")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean devisComplentaire;

    @Column(name = "technicien_name")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String technicienName;

    @Column(name = "code_technicien")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String codeTechnicien;

    @Column(name = "validation_client_name")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String validationClientName;

    @Column(name = "validation_name_function")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String validationNameFunction;

    @Column(name = "validation_client_date")
    private LocalDate validationClientDate;

    @Column(name = "bon_pour_command")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean bonPourCommand;

    @Column(name = "created_by")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String createdBy;

    @Column(name = "validation")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Boolean)
    private Boolean validation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportInterventionList id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite() {
        return this.site;
    }

    public ReportInterventionList site(String site) {
        this.setSite(site);
        return this;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCodeAgence() {
        return this.codeAgence;
    }

    public ReportInterventionList codeAgence(String codeAgence) {
        this.setCodeAgence(codeAgence);
        return this;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public String getAffaireNumber() {
        return this.affaireNumber;
    }

    public ReportInterventionList affaireNumber(String affaireNumber) {
        this.setAffaireNumber(affaireNumber);
        return this;
    }

    public void setAffaireNumber(String affaireNumber) {
        this.affaireNumber = affaireNumber;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public ReportInterventionList contractNumber(String contractNumber) {
        this.setContractNumber(contractNumber);
        return this;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getInstallationAdress() {
        return this.installationAdress;
    }

    public ReportInterventionList installationAdress(String installationAdress) {
        this.setInstallationAdress(installationAdress);
        return this;
    }

    public void setInstallationAdress(String installationAdress) {
        this.installationAdress = installationAdress;
    }

    public String getInterlocuteurIntervation() {
        return this.interlocuteurIntervation;
    }

    public ReportInterventionList interlocuteurIntervation(String interlocuteurIntervation) {
        this.setInterlocuteurIntervation(interlocuteurIntervation);
        return this;
    }

    public void setInterlocuteurIntervation(String interlocuteurIntervation) {
        this.interlocuteurIntervation = interlocuteurIntervation;
    }

    public String getTel() {
        return this.tel;
    }

    public ReportInterventionList tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getInstallationSousContract() {
        return this.installationSousContract;
    }

    public ReportInterventionList installationSousContract(Boolean installationSousContract) {
        this.setInstallationSousContract(installationSousContract);
        return this;
    }

    public void setInstallationSousContract(Boolean installationSousContract) {
        this.installationSousContract = installationSousContract;
    }

    public Boolean getInstallationSousGarantie() {
        return this.installationSousGarantie;
    }

    public ReportInterventionList installationSousGarantie(Boolean installationSousGarantie) {
        this.setInstallationSousGarantie(installationSousGarantie);
        return this;
    }

    public void setInstallationSousGarantie(Boolean installationSousGarantie) {
        this.installationSousGarantie = installationSousGarantie;
    }

    public String getAdressFacturation() {
        return this.adressFacturation;
    }

    public ReportInterventionList adressFacturation(String adressFacturation) {
        this.setAdressFacturation(adressFacturation);
        return this;
    }

    public void setAdressFacturation(String adressFacturation) {
        this.adressFacturation = adressFacturation;
    }

    public String getInterlocuteurFacturation() {
        return this.interlocuteurFacturation;
    }

    public ReportInterventionList interlocuteurFacturation(String interlocuteurFacturation) {
        this.setInterlocuteurFacturation(interlocuteurFacturation);
        return this;
    }

    public void setInterlocuteurFacturation(String interlocuteurFacturation) {
        this.interlocuteurFacturation = interlocuteurFacturation;
    }

    public Boolean getConditionDePayementCheque() {
        return this.conditionDePayementCheque;
    }

    public ReportInterventionList conditionDePayementCheque(Boolean conditionDePayementCheque) {
        this.setConditionDePayementCheque(conditionDePayementCheque);
        return this;
    }

    public void setConditionDePayementCheque(Boolean conditionDePayementCheque) {
        this.conditionDePayementCheque = conditionDePayementCheque;
    }

    public Boolean getConditionPayementAutre() {
        return this.conditionPayementAutre;
    }

    public ReportInterventionList conditionPayementAutre(Boolean conditionPayementAutre) {
        this.setConditionPayementAutre(conditionPayementAutre);
        return this;
    }

    public void setConditionPayementAutre(Boolean conditionPayementAutre) {
        this.conditionPayementAutre = conditionPayementAutre;
    }

    public String getConditionPayementComment() {
        return this.conditionPayementComment;
    }

    public ReportInterventionList conditionPayementComment(String conditionPayementComment) {
        this.setConditionPayementComment(conditionPayementComment);
        return this;
    }

    public void setConditionPayementComment(String conditionPayementComment) {
        this.conditionPayementComment = conditionPayementComment;
    }

    public Boolean getMiseEnServiceDefinitvie() {
        return this.miseEnServiceDefinitvie;
    }

    public ReportInterventionList miseEnServiceDefinitvie(Boolean miseEnServiceDefinitvie) {
        this.setMiseEnServiceDefinitvie(miseEnServiceDefinitvie);
        return this;
    }

    public void setMiseEnServiceDefinitvie(Boolean miseEnServiceDefinitvie) {
        this.miseEnServiceDefinitvie = miseEnServiceDefinitvie;
    }

    public Boolean getMiseEnServicePartielle() {
        return this.miseEnServicePartielle;
    }

    public ReportInterventionList miseEnServicePartielle(Boolean miseEnServicePartielle) {
        this.setMiseEnServicePartielle(miseEnServicePartielle);
        return this;
    }

    public void setMiseEnServicePartielle(Boolean miseEnServicePartielle) {
        this.miseEnServicePartielle = miseEnServicePartielle;
    }

    public Boolean getMaintenancePreventive() {
        return this.maintenancePreventive;
    }

    public ReportInterventionList maintenancePreventive(Boolean maintenancePreventive) {
        this.setMaintenancePreventive(maintenancePreventive);
        return this;
    }

    public void setMaintenancePreventive(Boolean maintenancePreventive) {
        this.maintenancePreventive = maintenancePreventive;
    }

    public Boolean getMaintenanceCorrective() {
        return this.maintenanceCorrective;
    }

    public ReportInterventionList maintenanceCorrective(Boolean maintenanceCorrective) {
        this.setMaintenanceCorrective(maintenanceCorrective);
        return this;
    }

    public void setMaintenanceCorrective(Boolean maintenanceCorrective) {
        this.maintenanceCorrective = maintenanceCorrective;
    }

    public String getbT() {
        return this.bT;
    }

    public ReportInterventionList bT(String bT) {
        this.setbT(bT);
        return this;
    }

    public void setbT(String bT) {
        this.bT = bT;
    }

    public String getAnomalieSignalee() {
        return this.anomalieSignalee;
    }

    public ReportInterventionList anomalieSignalee(String anomalieSignalee) {
        this.setAnomalieSignalee(anomalieSignalee);
        return this;
    }

    public void setAnomalieSignalee(String anomalieSignalee) {
        this.anomalieSignalee = anomalieSignalee;
    }

    public Instant getInterventionDate() {
        return this.interventionDate;
    }

    public ReportInterventionList interventionDate(Instant interventionDate) {
        this.setInterventionDate(interventionDate);
        return this;
    }

    public void setInterventionDate(Instant interventionDate) {
        this.interventionDate = interventionDate;
    }

    public Instant getInterventionStartDate() {
        return this.interventionStartDate;
    }

    public ReportInterventionList interventionStartDate(Instant interventionStartDate) {
        this.setInterventionStartDate(interventionStartDate);
        return this;
    }

    public void setInterventionStartDate(Instant interventionStartDate) {
        this.interventionStartDate = interventionStartDate;
    }

    public Instant getRemiseServiceDate() {
        return this.remiseServiceDate;
    }

    public ReportInterventionList remiseServiceDate(Instant remiseServiceDate) {
        this.setRemiseServiceDate(remiseServiceDate);
        return this;
    }

    public void setRemiseServiceDate(Instant remiseServiceDate) {
        this.remiseServiceDate = remiseServiceDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public ReportInterventionList endDate(Instant endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getNatureIntervention() {
        return this.natureIntervention;
    }

    public ReportInterventionList natureIntervention(String natureIntervention) {
        this.setNatureIntervention(natureIntervention);
        return this;
    }

    public void setNatureIntervention(String natureIntervention) {
        this.natureIntervention = natureIntervention;
    }

    public Boolean getCauseExterieurInstallation() {
        return this.causeExterieurInstallation;
    }

    public ReportInterventionList causeExterieurInstallation(Boolean causeExterieurInstallation) {
        this.setCauseExterieurInstallation(causeExterieurInstallation);
        return this;
    }

    public void setCauseExterieurInstallation(Boolean causeExterieurInstallation) {
        this.causeExterieurInstallation = causeExterieurInstallation;
    }

    public Boolean getInstallationFonctionnelleApresInervention() {
        return this.installationFonctionnelleApresInervention;
    }

    public ReportInterventionList installationFonctionnelleApresInervention(Boolean installationFonctionnelleApresInervention) {
        this.setInstallationFonctionnelleApresInervention(installationFonctionnelleApresInervention);
        return this;
    }

    public void setInstallationFonctionnelleApresInervention(Boolean installationFonctionnelleApresInervention) {
        this.installationFonctionnelleApresInervention = installationFonctionnelleApresInervention;
    }

    public String getAutreInterventionsAPrevoir() {
        return this.autreInterventionsAPrevoir;
    }

    public ReportInterventionList autreInterventionsAPrevoir(String autreInterventionsAPrevoir) {
        this.setAutreInterventionsAPrevoir(autreInterventionsAPrevoir);
        return this;
    }

    public void setAutreInterventionsAPrevoir(String autreInterventionsAPrevoir) {
        this.autreInterventionsAPrevoir = autreInterventionsAPrevoir;
    }

    public String getClientApreciation() {
        return this.clientApreciation;
    }

    public ReportInterventionList clientApreciation(String clientApreciation) {
        this.setClientApreciation(clientApreciation);
        return this;
    }

    public void setClientApreciation(String clientApreciation) {
        this.clientApreciation = clientApreciation;
    }

    public EvaluationStatus getRespectDelais() {
        return this.respectDelais;
    }

    public ReportInterventionList respectDelais(EvaluationStatus respectDelais) {
        this.setRespectDelais(respectDelais);
        return this;
    }

    public void setRespectDelais(EvaluationStatus respectDelais) {
        this.respectDelais = respectDelais;
    }

    public EvaluationStatus getQualiteIntervention() {
        return this.qualiteIntervention;
    }

    public ReportInterventionList qualiteIntervention(EvaluationStatus qualiteIntervention) {
        this.setQualiteIntervention(qualiteIntervention);
        return this;
    }

    public void setQualiteIntervention(EvaluationStatus qualiteIntervention) {
        this.qualiteIntervention = qualiteIntervention;
    }

    public EvaluationStatus getQualiteDevoirConseil() {
        return this.qualiteDevoirConseil;
    }

    public ReportInterventionList qualiteDevoirConseil(EvaluationStatus qualiteDevoirConseil) {
        this.setQualiteDevoirConseil(qualiteDevoirConseil);
        return this;
    }

    public void setQualiteDevoirConseil(EvaluationStatus qualiteDevoirConseil) {
        this.qualiteDevoirConseil = qualiteDevoirConseil;
    }

    public Boolean getPrestationsAchevees() {
        return this.prestationsAchevees;
    }

    public ReportInterventionList prestationsAchevees(Boolean prestationsAchevees) {
        this.setPrestationsAchevees(prestationsAchevees);
        return this;
    }

    public void setPrestationsAchevees(Boolean prestationsAchevees) {
        this.prestationsAchevees = prestationsAchevees;
    }

    public Boolean getDevisComplentaire() {
        return this.devisComplentaire;
    }

    public ReportInterventionList devisComplentaire(Boolean devisComplentaire) {
        this.setDevisComplentaire(devisComplentaire);
        return this;
    }

    public void setDevisComplentaire(Boolean devisComplentaire) {
        this.devisComplentaire = devisComplentaire;
    }

    public String getTechnicienName() {
        return this.technicienName;
    }

    public ReportInterventionList technicienName(String technicienName) {
        this.setTechnicienName(technicienName);
        return this;
    }

    public void setTechnicienName(String technicienName) {
        this.technicienName = technicienName;
    }

    public String getCodeTechnicien() {
        return this.codeTechnicien;
    }

    public ReportInterventionList codeTechnicien(String codeTechnicien) {
        this.setCodeTechnicien(codeTechnicien);
        return this;
    }

    public void setCodeTechnicien(String codeTechnicien) {
        this.codeTechnicien = codeTechnicien;
    }

    public String getValidationClientName() {
        return this.validationClientName;
    }

    public ReportInterventionList validationClientName(String validationClientName) {
        this.setValidationClientName(validationClientName);
        return this;
    }

    public void setValidationClientName(String validationClientName) {
        this.validationClientName = validationClientName;
    }

    public String getValidationNameFunction() {
        return this.validationNameFunction;
    }

    public ReportInterventionList validationNameFunction(String validationNameFunction) {
        this.setValidationNameFunction(validationNameFunction);
        return this;
    }

    public void setValidationNameFunction(String validationNameFunction) {
        this.validationNameFunction = validationNameFunction;
    }

    public LocalDate getValidationClientDate() {
        return this.validationClientDate;
    }

    public ReportInterventionList validationClientDate(LocalDate validationClientDate) {
        this.setValidationClientDate(validationClientDate);
        return this;
    }

    public void setValidationClientDate(LocalDate validationClientDate) {
        this.validationClientDate = validationClientDate;
    }

    public Boolean getBonPourCommand() {
        return this.bonPourCommand;
    }

    public ReportInterventionList bonPourCommand(Boolean bonPourCommand) {
        this.setBonPourCommand(bonPourCommand);
        return this;
    }

    public void setBonPourCommand(Boolean bonPourCommand) {
        this.bonPourCommand = bonPourCommand;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ReportInterventionList createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getValidation() {
        return this.validation;
    }

    public ReportInterventionList validation(Boolean validation) {
        this.setValidation(validation);
        return this;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportInterventionList)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportInterventionList) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportInterventionList{" +
            "id=" + getId() +
            ", site='" + getSite() + "'" +
            ", codeAgence='" + getCodeAgence() + "'" +
            ", affaireNumber='" + getAffaireNumber() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", installationAdress='" + getInstallationAdress() + "'" +
            ", interlocuteurIntervation='" + getInterlocuteurIntervation() + "'" +
            ", tel='" + getTel() + "'" +
            ", installationSousContract='" + getInstallationSousContract() + "'" +
            ", installationSousGarantie='" + getInstallationSousGarantie() + "'" +
            ", adressFacturation='" + getAdressFacturation() + "'" +
            ", interlocuteurFacturation='" + getInterlocuteurFacturation() + "'" +
            ", conditionDePayementCheque='" + getConditionDePayementCheque() + "'" +
            ", conditionPayementAutre='" + getConditionPayementAutre() + "'" +
            ", conditionPayementComment='" + getConditionPayementComment() + "'" +
            ", miseEnServiceDefinitvie='" + getMiseEnServiceDefinitvie() + "'" +
            ", miseEnServicePartielle='" + getMiseEnServicePartielle() + "'" +
            ", maintenancePreventive='" + getMaintenancePreventive() + "'" +
            ", maintenanceCorrective='" + getMaintenanceCorrective() + "'" +
            ", bT='" + getbT() + "'" +
            ", anomalieSignalee='" + getAnomalieSignalee() + "'" +
            ", interventionDate='" + getInterventionDate() + "'" +
            ", interventionStartDate='" + getInterventionStartDate() + "'" +
            ", remiseServiceDate='" + getRemiseServiceDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", natureIntervention='" + getNatureIntervention() + "'" +
            ", causeExterieurInstallation='" + getCauseExterieurInstallation() + "'" +
            ", installationFonctionnelleApresInervention='" + getInstallationFonctionnelleApresInervention() + "'" +
            ", autreInterventionsAPrevoir='" + getAutreInterventionsAPrevoir() + "'" +
            ", clientApreciation='" + getClientApreciation() + "'" +
            ", respectDelais='" + getRespectDelais() + "'" +
            ", qualiteIntervention='" + getQualiteIntervention() + "'" +
            ", qualiteDevoirConseil='" + getQualiteDevoirConseil() + "'" +
            ", prestationsAchevees='" + getPrestationsAchevees() + "'" +
            ", devisComplentaire='" + getDevisComplentaire() + "'" +
            ", technicienName='" + getTechnicienName() + "'" +
            ", codeTechnicien='" + getCodeTechnicien() + "'" +
            ", validationClientName='" + getValidationClientName() + "'" +
            ", validationNameFunction='" + getValidationNameFunction() + "'" +
            ", validationClientDate='" + getValidationClientDate() + "'" +
            ", bonPourCommand='" + getBonPourCommand() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", validation='" + getValidation() + "'" +
            "}";
    }
}
