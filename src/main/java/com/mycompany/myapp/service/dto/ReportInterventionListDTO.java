package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import jakarta.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ReportInterventionList} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportInterventionListDTO implements Serializable {

    private Long id;

    private String site;

    private String codeAgence;

    private String affaireNumber;

    private String contractNumber;

    private String installationAdress;

    private String interlocuteurIntervation;

    private String tel;

    private Boolean installationSousContract;

    private Boolean installationSousGarantie;

    private String adressFacturation;

    private String interlocuteurFacturation;

    private Boolean conditionDePayementCheque;

    private Boolean conditionPayementAutre;

    private String conditionPayementComment;

    private Boolean miseEnServiceDefinitvie;

    private Boolean miseEnServicePartielle;

    private Boolean maintenancePreventive;

    private Boolean maintenanceCorrective;

    private String bT;

    private String anomalieSignalee;

    private Instant interventionDate;

    private Instant interventionStartDate;

    private Instant remiseServiceDate;

    private Instant endDate;

    @Lob
    private String natureIntervention;

    private Boolean causeExterieurInstallation;

    private Boolean installationFonctionnelleApresInervention;

    private String autreInterventionsAPrevoir;

    private String clientApreciation;

    private EvaluationStatus respectDelais;

    private EvaluationStatus qualiteIntervention;

    private EvaluationStatus qualiteDevoirConseil;

    private Boolean prestationsAchevees;

    private Boolean devisComplentaire;

    private String technicienName;

    private String codeTechnicien;

    private String validationClientName;

    private String validationNameFunction;

    private LocalDate validationClientDate;

    private Boolean bonPourCommand;

    private String createdBy;

    private Boolean validation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public String getAffaireNumber() {
        return affaireNumber;
    }

    public void setAffaireNumber(String affaireNumber) {
        this.affaireNumber = affaireNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getInstallationAdress() {
        return installationAdress;
    }

    public void setInstallationAdress(String installationAdress) {
        this.installationAdress = installationAdress;
    }

    public String getInterlocuteurIntervation() {
        return interlocuteurIntervation;
    }

    public void setInterlocuteurIntervation(String interlocuteurIntervation) {
        this.interlocuteurIntervation = interlocuteurIntervation;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getInstallationSousContract() {
        return installationSousContract;
    }

    public void setInstallationSousContract(Boolean installationSousContract) {
        this.installationSousContract = installationSousContract;
    }

    public Boolean getInstallationSousGarantie() {
        return installationSousGarantie;
    }

    public void setInstallationSousGarantie(Boolean installationSousGarantie) {
        this.installationSousGarantie = installationSousGarantie;
    }

    public String getAdressFacturation() {
        return adressFacturation;
    }

    public void setAdressFacturation(String adressFacturation) {
        this.adressFacturation = adressFacturation;
    }

    public String getInterlocuteurFacturation() {
        return interlocuteurFacturation;
    }

    public void setInterlocuteurFacturation(String interlocuteurFacturation) {
        this.interlocuteurFacturation = interlocuteurFacturation;
    }

    public Boolean getConditionDePayementCheque() {
        return conditionDePayementCheque;
    }

    public void setConditionDePayementCheque(Boolean conditionDePayementCheque) {
        this.conditionDePayementCheque = conditionDePayementCheque;
    }

    public Boolean getConditionPayementAutre() {
        return conditionPayementAutre;
    }

    public void setConditionPayementAutre(Boolean conditionPayementAutre) {
        this.conditionPayementAutre = conditionPayementAutre;
    }

    public String getConditionPayementComment() {
        return conditionPayementComment;
    }

    public void setConditionPayementComment(String conditionPayementComment) {
        this.conditionPayementComment = conditionPayementComment;
    }

    public Boolean getMiseEnServiceDefinitvie() {
        return miseEnServiceDefinitvie;
    }

    public void setMiseEnServiceDefinitvie(Boolean miseEnServiceDefinitvie) {
        this.miseEnServiceDefinitvie = miseEnServiceDefinitvie;
    }

    public Boolean getMiseEnServicePartielle() {
        return miseEnServicePartielle;
    }

    public void setMiseEnServicePartielle(Boolean miseEnServicePartielle) {
        this.miseEnServicePartielle = miseEnServicePartielle;
    }

    public Boolean getMaintenancePreventive() {
        return maintenancePreventive;
    }

    public void setMaintenancePreventive(Boolean maintenancePreventive) {
        this.maintenancePreventive = maintenancePreventive;
    }

    public Boolean getMaintenanceCorrective() {
        return maintenanceCorrective;
    }

    public void setMaintenanceCorrective(Boolean maintenanceCorrective) {
        this.maintenanceCorrective = maintenanceCorrective;
    }

    public String getbT() {
        return bT;
    }

    public void setbT(String bT) {
        this.bT = bT;
    }

    public String getAnomalieSignalee() {
        return anomalieSignalee;
    }

    public void setAnomalieSignalee(String anomalieSignalee) {
        this.anomalieSignalee = anomalieSignalee;
    }

    public Instant getInterventionDate() {
        return interventionDate;
    }

    public void setInterventionDate(Instant interventionDate) {
        this.interventionDate = interventionDate;
    }

    public Instant getInterventionStartDate() {
        return interventionStartDate;
    }

    public void setInterventionStartDate(Instant interventionStartDate) {
        this.interventionStartDate = interventionStartDate;
    }

    public Instant getRemiseServiceDate() {
        return remiseServiceDate;
    }

    public void setRemiseServiceDate(Instant remiseServiceDate) {
        this.remiseServiceDate = remiseServiceDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getNatureIntervention() {
        return natureIntervention;
    }

    public void setNatureIntervention(String natureIntervention) {
        this.natureIntervention = natureIntervention;
    }

    public Boolean getCauseExterieurInstallation() {
        return causeExterieurInstallation;
    }

    public void setCauseExterieurInstallation(Boolean causeExterieurInstallation) {
        this.causeExterieurInstallation = causeExterieurInstallation;
    }

    public Boolean getInstallationFonctionnelleApresInervention() {
        return installationFonctionnelleApresInervention;
    }

    public void setInstallationFonctionnelleApresInervention(Boolean installationFonctionnelleApresInervention) {
        this.installationFonctionnelleApresInervention = installationFonctionnelleApresInervention;
    }

    public String getAutreInterventionsAPrevoir() {
        return autreInterventionsAPrevoir;
    }

    public void setAutreInterventionsAPrevoir(String autreInterventionsAPrevoir) {
        this.autreInterventionsAPrevoir = autreInterventionsAPrevoir;
    }

    public String getClientApreciation() {
        return clientApreciation;
    }

    public void setClientApreciation(String clientApreciation) {
        this.clientApreciation = clientApreciation;
    }

    public EvaluationStatus getRespectDelais() {
        return respectDelais;
    }

    public void setRespectDelais(EvaluationStatus respectDelais) {
        this.respectDelais = respectDelais;
    }

    public EvaluationStatus getQualiteIntervention() {
        return qualiteIntervention;
    }

    public void setQualiteIntervention(EvaluationStatus qualiteIntervention) {
        this.qualiteIntervention = qualiteIntervention;
    }

    public EvaluationStatus getQualiteDevoirConseil() {
        return qualiteDevoirConseil;
    }

    public void setQualiteDevoirConseil(EvaluationStatus qualiteDevoirConseil) {
        this.qualiteDevoirConseil = qualiteDevoirConseil;
    }

    public Boolean getPrestationsAchevees() {
        return prestationsAchevees;
    }

    public void setPrestationsAchevees(Boolean prestationsAchevees) {
        this.prestationsAchevees = prestationsAchevees;
    }

    public Boolean getDevisComplentaire() {
        return devisComplentaire;
    }

    public void setDevisComplentaire(Boolean devisComplentaire) {
        this.devisComplentaire = devisComplentaire;
    }

    public String getTechnicienName() {
        return technicienName;
    }

    public void setTechnicienName(String technicienName) {
        this.technicienName = technicienName;
    }

    public String getCodeTechnicien() {
        return codeTechnicien;
    }

    public void setCodeTechnicien(String codeTechnicien) {
        this.codeTechnicien = codeTechnicien;
    }

    public String getValidationClientName() {
        return validationClientName;
    }

    public void setValidationClientName(String validationClientName) {
        this.validationClientName = validationClientName;
    }

    public String getValidationNameFunction() {
        return validationNameFunction;
    }

    public void setValidationNameFunction(String validationNameFunction) {
        this.validationNameFunction = validationNameFunction;
    }

    public LocalDate getValidationClientDate() {
        return validationClientDate;
    }

    public void setValidationClientDate(LocalDate validationClientDate) {
        this.validationClientDate = validationClientDate;
    }

    public Boolean getBonPourCommand() {
        return bonPourCommand;
    }

    public void setBonPourCommand(Boolean bonPourCommand) {
        this.bonPourCommand = bonPourCommand;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportInterventionListDTO)) {
            return false;
        }

        ReportInterventionListDTO reportInterventionListDTO = (ReportInterventionListDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reportInterventionListDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportInterventionListDTO{" +
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
