package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ReportInterventionList} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ReportInterventionListResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /report-intervention-lists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportInterventionListCriteria implements Serializable, Criteria {

    /**
     * Class for filtering EvaluationStatus
     */
    public static class EvaluationStatusFilter extends Filter<EvaluationStatus> {

        public EvaluationStatusFilter() {}

        public EvaluationStatusFilter(EvaluationStatusFilter filter) {
            super(filter);
        }

        @Override
        public EvaluationStatusFilter copy() {
            return new EvaluationStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter site;

    private StringFilter codeAgence;

    private StringFilter affaireNumber;

    private StringFilter contractNumber;

    private StringFilter installationAdress;

    private StringFilter interlocuteurIntervation;

    private StringFilter tel;

    private BooleanFilter installationSousContract;

    private BooleanFilter installationSousGarantie;

    private StringFilter adressFacturation;

    private StringFilter interlocuteurFacturation;

    private BooleanFilter conditionDePayementCheque;

    private BooleanFilter conditionPayementAutre;

    private StringFilter conditionPayementComment;

    private BooleanFilter miseEnServiceDefinitvie;

    private BooleanFilter miseEnServicePartielle;

    private BooleanFilter maintenancePreventive;

    private BooleanFilter maintenanceCorrective;

    private StringFilter bT;

    private StringFilter anomalieSignalee;

    private InstantFilter interventionDate;

    private InstantFilter interventionStartDate;

    private InstantFilter remiseServiceDate;

    private InstantFilter endDate;

    private BooleanFilter causeExterieurInstallation;

    private BooleanFilter installationFonctionnelleApresInervention;

    private StringFilter autreInterventionsAPrevoir;

    private StringFilter clientApreciation;

    private EvaluationStatusFilter respectDelais;

    private EvaluationStatusFilter qualiteIntervention;

    private EvaluationStatusFilter qualiteDevoirConseil;

    private BooleanFilter prestationsAchevees;

    private BooleanFilter devisComplentaire;

    private StringFilter technicienName;

    private StringFilter codeTechnicien;

    private StringFilter validationClientName;

    private StringFilter validationNameFunction;

    private LocalDateFilter validationClientDate;

    private BooleanFilter bonPourCommand;

    private StringFilter createdBy;

    private BooleanFilter validation;

    private Boolean distinct;

    public ReportInterventionListCriteria() {}

    public ReportInterventionListCriteria(ReportInterventionListCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.site = other.optionalSite().map(StringFilter::copy).orElse(null);
        this.codeAgence = other.optionalCodeAgence().map(StringFilter::copy).orElse(null);
        this.affaireNumber = other.optionalAffaireNumber().map(StringFilter::copy).orElse(null);
        this.contractNumber = other.optionalContractNumber().map(StringFilter::copy).orElse(null);
        this.installationAdress = other.optionalInstallationAdress().map(StringFilter::copy).orElse(null);
        this.interlocuteurIntervation = other.optionalInterlocuteurIntervation().map(StringFilter::copy).orElse(null);
        this.tel = other.optionalTel().map(StringFilter::copy).orElse(null);
        this.installationSousContract = other.optionalInstallationSousContract().map(BooleanFilter::copy).orElse(null);
        this.installationSousGarantie = other.optionalInstallationSousGarantie().map(BooleanFilter::copy).orElse(null);
        this.adressFacturation = other.optionalAdressFacturation().map(StringFilter::copy).orElse(null);
        this.interlocuteurFacturation = other.optionalInterlocuteurFacturation().map(StringFilter::copy).orElse(null);
        this.conditionDePayementCheque = other.optionalConditionDePayementCheque().map(BooleanFilter::copy).orElse(null);
        this.conditionPayementAutre = other.optionalConditionPayementAutre().map(BooleanFilter::copy).orElse(null);
        this.conditionPayementComment = other.optionalConditionPayementComment().map(StringFilter::copy).orElse(null);
        this.miseEnServiceDefinitvie = other.optionalMiseEnServiceDefinitvie().map(BooleanFilter::copy).orElse(null);
        this.miseEnServicePartielle = other.optionalMiseEnServicePartielle().map(BooleanFilter::copy).orElse(null);
        this.maintenancePreventive = other.optionalMaintenancePreventive().map(BooleanFilter::copy).orElse(null);
        this.maintenanceCorrective = other.optionalMaintenanceCorrective().map(BooleanFilter::copy).orElse(null);
        this.bT = other.optionalbT().map(StringFilter::copy).orElse(null);
        this.anomalieSignalee = other.optionalAnomalieSignalee().map(StringFilter::copy).orElse(null);
        this.interventionDate = other.optionalInterventionDate().map(InstantFilter::copy).orElse(null);
        this.interventionStartDate = other.optionalInterventionStartDate().map(InstantFilter::copy).orElse(null);
        this.remiseServiceDate = other.optionalRemiseServiceDate().map(InstantFilter::copy).orElse(null);
        this.endDate = other.optionalEndDate().map(InstantFilter::copy).orElse(null);
        this.causeExterieurInstallation = other.optionalCauseExterieurInstallation().map(BooleanFilter::copy).orElse(null);
        this.installationFonctionnelleApresInervention = other
            .optionalInstallationFonctionnelleApresInervention()
            .map(BooleanFilter::copy)
            .orElse(null);
        this.autreInterventionsAPrevoir = other.optionalAutreInterventionsAPrevoir().map(StringFilter::copy).orElse(null);
        this.clientApreciation = other.optionalClientApreciation().map(StringFilter::copy).orElse(null);
        this.respectDelais = other.optionalRespectDelais().map(EvaluationStatusFilter::copy).orElse(null);
        this.qualiteIntervention = other.optionalQualiteIntervention().map(EvaluationStatusFilter::copy).orElse(null);
        this.qualiteDevoirConseil = other.optionalQualiteDevoirConseil().map(EvaluationStatusFilter::copy).orElse(null);
        this.prestationsAchevees = other.optionalPrestationsAchevees().map(BooleanFilter::copy).orElse(null);
        this.devisComplentaire = other.optionalDevisComplentaire().map(BooleanFilter::copy).orElse(null);
        this.technicienName = other.optionalTechnicienName().map(StringFilter::copy).orElse(null);
        this.codeTechnicien = other.optionalCodeTechnicien().map(StringFilter::copy).orElse(null);
        this.validationClientName = other.optionalValidationClientName().map(StringFilter::copy).orElse(null);
        this.validationNameFunction = other.optionalValidationNameFunction().map(StringFilter::copy).orElse(null);
        this.validationClientDate = other.optionalValidationClientDate().map(LocalDateFilter::copy).orElse(null);
        this.bonPourCommand = other.optionalBonPourCommand().map(BooleanFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.validation = other.optionalValidation().map(BooleanFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public ReportInterventionListCriteria copy() {
        return new ReportInterventionListCriteria(this);
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

    public StringFilter getSite() {
        return site;
    }

    public Optional<StringFilter> optionalSite() {
        return Optional.ofNullable(site);
    }

    public StringFilter site() {
        if (site == null) {
            setSite(new StringFilter());
        }
        return site;
    }

    public void setSite(StringFilter site) {
        this.site = site;
    }

    public StringFilter getCodeAgence() {
        return codeAgence;
    }

    public Optional<StringFilter> optionalCodeAgence() {
        return Optional.ofNullable(codeAgence);
    }

    public StringFilter codeAgence() {
        if (codeAgence == null) {
            setCodeAgence(new StringFilter());
        }
        return codeAgence;
    }

    public void setCodeAgence(StringFilter codeAgence) {
        this.codeAgence = codeAgence;
    }

    public StringFilter getAffaireNumber() {
        return affaireNumber;
    }

    public Optional<StringFilter> optionalAffaireNumber() {
        return Optional.ofNullable(affaireNumber);
    }

    public StringFilter affaireNumber() {
        if (affaireNumber == null) {
            setAffaireNumber(new StringFilter());
        }
        return affaireNumber;
    }

    public void setAffaireNumber(StringFilter affaireNumber) {
        this.affaireNumber = affaireNumber;
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

    public StringFilter getInstallationAdress() {
        return installationAdress;
    }

    public Optional<StringFilter> optionalInstallationAdress() {
        return Optional.ofNullable(installationAdress);
    }

    public StringFilter installationAdress() {
        if (installationAdress == null) {
            setInstallationAdress(new StringFilter());
        }
        return installationAdress;
    }

    public void setInstallationAdress(StringFilter installationAdress) {
        this.installationAdress = installationAdress;
    }

    public StringFilter getInterlocuteurIntervation() {
        return interlocuteurIntervation;
    }

    public Optional<StringFilter> optionalInterlocuteurIntervation() {
        return Optional.ofNullable(interlocuteurIntervation);
    }

    public StringFilter interlocuteurIntervation() {
        if (interlocuteurIntervation == null) {
            setInterlocuteurIntervation(new StringFilter());
        }
        return interlocuteurIntervation;
    }

    public void setInterlocuteurIntervation(StringFilter interlocuteurIntervation) {
        this.interlocuteurIntervation = interlocuteurIntervation;
    }

    public StringFilter getTel() {
        return tel;
    }

    public Optional<StringFilter> optionalTel() {
        return Optional.ofNullable(tel);
    }

    public StringFilter tel() {
        if (tel == null) {
            setTel(new StringFilter());
        }
        return tel;
    }

    public void setTel(StringFilter tel) {
        this.tel = tel;
    }

    public BooleanFilter getInstallationSousContract() {
        return installationSousContract;
    }

    public Optional<BooleanFilter> optionalInstallationSousContract() {
        return Optional.ofNullable(installationSousContract);
    }

    public BooleanFilter installationSousContract() {
        if (installationSousContract == null) {
            setInstallationSousContract(new BooleanFilter());
        }
        return installationSousContract;
    }

    public void setInstallationSousContract(BooleanFilter installationSousContract) {
        this.installationSousContract = installationSousContract;
    }

    public BooleanFilter getInstallationSousGarantie() {
        return installationSousGarantie;
    }

    public Optional<BooleanFilter> optionalInstallationSousGarantie() {
        return Optional.ofNullable(installationSousGarantie);
    }

    public BooleanFilter installationSousGarantie() {
        if (installationSousGarantie == null) {
            setInstallationSousGarantie(new BooleanFilter());
        }
        return installationSousGarantie;
    }

    public void setInstallationSousGarantie(BooleanFilter installationSousGarantie) {
        this.installationSousGarantie = installationSousGarantie;
    }

    public StringFilter getAdressFacturation() {
        return adressFacturation;
    }

    public Optional<StringFilter> optionalAdressFacturation() {
        return Optional.ofNullable(adressFacturation);
    }

    public StringFilter adressFacturation() {
        if (adressFacturation == null) {
            setAdressFacturation(new StringFilter());
        }
        return adressFacturation;
    }

    public void setAdressFacturation(StringFilter adressFacturation) {
        this.adressFacturation = adressFacturation;
    }

    public StringFilter getInterlocuteurFacturation() {
        return interlocuteurFacturation;
    }

    public Optional<StringFilter> optionalInterlocuteurFacturation() {
        return Optional.ofNullable(interlocuteurFacturation);
    }

    public StringFilter interlocuteurFacturation() {
        if (interlocuteurFacturation == null) {
            setInterlocuteurFacturation(new StringFilter());
        }
        return interlocuteurFacturation;
    }

    public void setInterlocuteurFacturation(StringFilter interlocuteurFacturation) {
        this.interlocuteurFacturation = interlocuteurFacturation;
    }

    public BooleanFilter getConditionDePayementCheque() {
        return conditionDePayementCheque;
    }

    public Optional<BooleanFilter> optionalConditionDePayementCheque() {
        return Optional.ofNullable(conditionDePayementCheque);
    }

    public BooleanFilter conditionDePayementCheque() {
        if (conditionDePayementCheque == null) {
            setConditionDePayementCheque(new BooleanFilter());
        }
        return conditionDePayementCheque;
    }

    public void setConditionDePayementCheque(BooleanFilter conditionDePayementCheque) {
        this.conditionDePayementCheque = conditionDePayementCheque;
    }

    public BooleanFilter getConditionPayementAutre() {
        return conditionPayementAutre;
    }

    public Optional<BooleanFilter> optionalConditionPayementAutre() {
        return Optional.ofNullable(conditionPayementAutre);
    }

    public BooleanFilter conditionPayementAutre() {
        if (conditionPayementAutre == null) {
            setConditionPayementAutre(new BooleanFilter());
        }
        return conditionPayementAutre;
    }

    public void setConditionPayementAutre(BooleanFilter conditionPayementAutre) {
        this.conditionPayementAutre = conditionPayementAutre;
    }

    public StringFilter getConditionPayementComment() {
        return conditionPayementComment;
    }

    public Optional<StringFilter> optionalConditionPayementComment() {
        return Optional.ofNullable(conditionPayementComment);
    }

    public StringFilter conditionPayementComment() {
        if (conditionPayementComment == null) {
            setConditionPayementComment(new StringFilter());
        }
        return conditionPayementComment;
    }

    public void setConditionPayementComment(StringFilter conditionPayementComment) {
        this.conditionPayementComment = conditionPayementComment;
    }

    public BooleanFilter getMiseEnServiceDefinitvie() {
        return miseEnServiceDefinitvie;
    }

    public Optional<BooleanFilter> optionalMiseEnServiceDefinitvie() {
        return Optional.ofNullable(miseEnServiceDefinitvie);
    }

    public BooleanFilter miseEnServiceDefinitvie() {
        if (miseEnServiceDefinitvie == null) {
            setMiseEnServiceDefinitvie(new BooleanFilter());
        }
        return miseEnServiceDefinitvie;
    }

    public void setMiseEnServiceDefinitvie(BooleanFilter miseEnServiceDefinitvie) {
        this.miseEnServiceDefinitvie = miseEnServiceDefinitvie;
    }

    public BooleanFilter getMiseEnServicePartielle() {
        return miseEnServicePartielle;
    }

    public Optional<BooleanFilter> optionalMiseEnServicePartielle() {
        return Optional.ofNullable(miseEnServicePartielle);
    }

    public BooleanFilter miseEnServicePartielle() {
        if (miseEnServicePartielle == null) {
            setMiseEnServicePartielle(new BooleanFilter());
        }
        return miseEnServicePartielle;
    }

    public void setMiseEnServicePartielle(BooleanFilter miseEnServicePartielle) {
        this.miseEnServicePartielle = miseEnServicePartielle;
    }

    public BooleanFilter getMaintenancePreventive() {
        return maintenancePreventive;
    }

    public Optional<BooleanFilter> optionalMaintenancePreventive() {
        return Optional.ofNullable(maintenancePreventive);
    }

    public BooleanFilter maintenancePreventive() {
        if (maintenancePreventive == null) {
            setMaintenancePreventive(new BooleanFilter());
        }
        return maintenancePreventive;
    }

    public void setMaintenancePreventive(BooleanFilter maintenancePreventive) {
        this.maintenancePreventive = maintenancePreventive;
    }

    public BooleanFilter getMaintenanceCorrective() {
        return maintenanceCorrective;
    }

    public Optional<BooleanFilter> optionalMaintenanceCorrective() {
        return Optional.ofNullable(maintenanceCorrective);
    }

    public BooleanFilter maintenanceCorrective() {
        if (maintenanceCorrective == null) {
            setMaintenanceCorrective(new BooleanFilter());
        }
        return maintenanceCorrective;
    }

    public void setMaintenanceCorrective(BooleanFilter maintenanceCorrective) {
        this.maintenanceCorrective = maintenanceCorrective;
    }

    public StringFilter getbT() {
        return bT;
    }

    public Optional<StringFilter> optionalbT() {
        return Optional.ofNullable(bT);
    }

    public StringFilter bT() {
        if (bT == null) {
            setbT(new StringFilter());
        }
        return bT;
    }

    public void setbT(StringFilter bT) {
        this.bT = bT;
    }

    public StringFilter getAnomalieSignalee() {
        return anomalieSignalee;
    }

    public Optional<StringFilter> optionalAnomalieSignalee() {
        return Optional.ofNullable(anomalieSignalee);
    }

    public StringFilter anomalieSignalee() {
        if (anomalieSignalee == null) {
            setAnomalieSignalee(new StringFilter());
        }
        return anomalieSignalee;
    }

    public void setAnomalieSignalee(StringFilter anomalieSignalee) {
        this.anomalieSignalee = anomalieSignalee;
    }

    public InstantFilter getInterventionDate() {
        return interventionDate;
    }

    public Optional<InstantFilter> optionalInterventionDate() {
        return Optional.ofNullable(interventionDate);
    }

    public InstantFilter interventionDate() {
        if (interventionDate == null) {
            setInterventionDate(new InstantFilter());
        }
        return interventionDate;
    }

    public void setInterventionDate(InstantFilter interventionDate) {
        this.interventionDate = interventionDate;
    }

    public InstantFilter getInterventionStartDate() {
        return interventionStartDate;
    }

    public Optional<InstantFilter> optionalInterventionStartDate() {
        return Optional.ofNullable(interventionStartDate);
    }

    public InstantFilter interventionStartDate() {
        if (interventionStartDate == null) {
            setInterventionStartDate(new InstantFilter());
        }
        return interventionStartDate;
    }

    public void setInterventionStartDate(InstantFilter interventionStartDate) {
        this.interventionStartDate = interventionStartDate;
    }

    public InstantFilter getRemiseServiceDate() {
        return remiseServiceDate;
    }

    public Optional<InstantFilter> optionalRemiseServiceDate() {
        return Optional.ofNullable(remiseServiceDate);
    }

    public InstantFilter remiseServiceDate() {
        if (remiseServiceDate == null) {
            setRemiseServiceDate(new InstantFilter());
        }
        return remiseServiceDate;
    }

    public void setRemiseServiceDate(InstantFilter remiseServiceDate) {
        this.remiseServiceDate = remiseServiceDate;
    }

    public InstantFilter getEndDate() {
        return endDate;
    }

    public Optional<InstantFilter> optionalEndDate() {
        return Optional.ofNullable(endDate);
    }

    public InstantFilter endDate() {
        if (endDate == null) {
            setEndDate(new InstantFilter());
        }
        return endDate;
    }

    public void setEndDate(InstantFilter endDate) {
        this.endDate = endDate;
    }

    public BooleanFilter getCauseExterieurInstallation() {
        return causeExterieurInstallation;
    }

    public Optional<BooleanFilter> optionalCauseExterieurInstallation() {
        return Optional.ofNullable(causeExterieurInstallation);
    }

    public BooleanFilter causeExterieurInstallation() {
        if (causeExterieurInstallation == null) {
            setCauseExterieurInstallation(new BooleanFilter());
        }
        return causeExterieurInstallation;
    }

    public void setCauseExterieurInstallation(BooleanFilter causeExterieurInstallation) {
        this.causeExterieurInstallation = causeExterieurInstallation;
    }

    public BooleanFilter getInstallationFonctionnelleApresInervention() {
        return installationFonctionnelleApresInervention;
    }

    public Optional<BooleanFilter> optionalInstallationFonctionnelleApresInervention() {
        return Optional.ofNullable(installationFonctionnelleApresInervention);
    }

    public BooleanFilter installationFonctionnelleApresInervention() {
        if (installationFonctionnelleApresInervention == null) {
            setInstallationFonctionnelleApresInervention(new BooleanFilter());
        }
        return installationFonctionnelleApresInervention;
    }

    public void setInstallationFonctionnelleApresInervention(BooleanFilter installationFonctionnelleApresInervention) {
        this.installationFonctionnelleApresInervention = installationFonctionnelleApresInervention;
    }

    public StringFilter getAutreInterventionsAPrevoir() {
        return autreInterventionsAPrevoir;
    }

    public Optional<StringFilter> optionalAutreInterventionsAPrevoir() {
        return Optional.ofNullable(autreInterventionsAPrevoir);
    }

    public StringFilter autreInterventionsAPrevoir() {
        if (autreInterventionsAPrevoir == null) {
            setAutreInterventionsAPrevoir(new StringFilter());
        }
        return autreInterventionsAPrevoir;
    }

    public void setAutreInterventionsAPrevoir(StringFilter autreInterventionsAPrevoir) {
        this.autreInterventionsAPrevoir = autreInterventionsAPrevoir;
    }

    public StringFilter getClientApreciation() {
        return clientApreciation;
    }

    public Optional<StringFilter> optionalClientApreciation() {
        return Optional.ofNullable(clientApreciation);
    }

    public StringFilter clientApreciation() {
        if (clientApreciation == null) {
            setClientApreciation(new StringFilter());
        }
        return clientApreciation;
    }

    public void setClientApreciation(StringFilter clientApreciation) {
        this.clientApreciation = clientApreciation;
    }

    public EvaluationStatusFilter getRespectDelais() {
        return respectDelais;
    }

    public Optional<EvaluationStatusFilter> optionalRespectDelais() {
        return Optional.ofNullable(respectDelais);
    }

    public EvaluationStatusFilter respectDelais() {
        if (respectDelais == null) {
            setRespectDelais(new EvaluationStatusFilter());
        }
        return respectDelais;
    }

    public void setRespectDelais(EvaluationStatusFilter respectDelais) {
        this.respectDelais = respectDelais;
    }

    public EvaluationStatusFilter getQualiteIntervention() {
        return qualiteIntervention;
    }

    public Optional<EvaluationStatusFilter> optionalQualiteIntervention() {
        return Optional.ofNullable(qualiteIntervention);
    }

    public EvaluationStatusFilter qualiteIntervention() {
        if (qualiteIntervention == null) {
            setQualiteIntervention(new EvaluationStatusFilter());
        }
        return qualiteIntervention;
    }

    public void setQualiteIntervention(EvaluationStatusFilter qualiteIntervention) {
        this.qualiteIntervention = qualiteIntervention;
    }

    public EvaluationStatusFilter getQualiteDevoirConseil() {
        return qualiteDevoirConseil;
    }

    public Optional<EvaluationStatusFilter> optionalQualiteDevoirConseil() {
        return Optional.ofNullable(qualiteDevoirConseil);
    }

    public EvaluationStatusFilter qualiteDevoirConseil() {
        if (qualiteDevoirConseil == null) {
            setQualiteDevoirConseil(new EvaluationStatusFilter());
        }
        return qualiteDevoirConseil;
    }

    public void setQualiteDevoirConseil(EvaluationStatusFilter qualiteDevoirConseil) {
        this.qualiteDevoirConseil = qualiteDevoirConseil;
    }

    public BooleanFilter getPrestationsAchevees() {
        return prestationsAchevees;
    }

    public Optional<BooleanFilter> optionalPrestationsAchevees() {
        return Optional.ofNullable(prestationsAchevees);
    }

    public BooleanFilter prestationsAchevees() {
        if (prestationsAchevees == null) {
            setPrestationsAchevees(new BooleanFilter());
        }
        return prestationsAchevees;
    }

    public void setPrestationsAchevees(BooleanFilter prestationsAchevees) {
        this.prestationsAchevees = prestationsAchevees;
    }

    public BooleanFilter getDevisComplentaire() {
        return devisComplentaire;
    }

    public Optional<BooleanFilter> optionalDevisComplentaire() {
        return Optional.ofNullable(devisComplentaire);
    }

    public BooleanFilter devisComplentaire() {
        if (devisComplentaire == null) {
            setDevisComplentaire(new BooleanFilter());
        }
        return devisComplentaire;
    }

    public void setDevisComplentaire(BooleanFilter devisComplentaire) {
        this.devisComplentaire = devisComplentaire;
    }

    public StringFilter getTechnicienName() {
        return technicienName;
    }

    public Optional<StringFilter> optionalTechnicienName() {
        return Optional.ofNullable(technicienName);
    }

    public StringFilter technicienName() {
        if (technicienName == null) {
            setTechnicienName(new StringFilter());
        }
        return technicienName;
    }

    public void setTechnicienName(StringFilter technicienName) {
        this.technicienName = technicienName;
    }

    public StringFilter getCodeTechnicien() {
        return codeTechnicien;
    }

    public Optional<StringFilter> optionalCodeTechnicien() {
        return Optional.ofNullable(codeTechnicien);
    }

    public StringFilter codeTechnicien() {
        if (codeTechnicien == null) {
            setCodeTechnicien(new StringFilter());
        }
        return codeTechnicien;
    }

    public void setCodeTechnicien(StringFilter codeTechnicien) {
        this.codeTechnicien = codeTechnicien;
    }

    public StringFilter getValidationClientName() {
        return validationClientName;
    }

    public Optional<StringFilter> optionalValidationClientName() {
        return Optional.ofNullable(validationClientName);
    }

    public StringFilter validationClientName() {
        if (validationClientName == null) {
            setValidationClientName(new StringFilter());
        }
        return validationClientName;
    }

    public void setValidationClientName(StringFilter validationClientName) {
        this.validationClientName = validationClientName;
    }

    public StringFilter getValidationNameFunction() {
        return validationNameFunction;
    }

    public Optional<StringFilter> optionalValidationNameFunction() {
        return Optional.ofNullable(validationNameFunction);
    }

    public StringFilter validationNameFunction() {
        if (validationNameFunction == null) {
            setValidationNameFunction(new StringFilter());
        }
        return validationNameFunction;
    }

    public void setValidationNameFunction(StringFilter validationNameFunction) {
        this.validationNameFunction = validationNameFunction;
    }

    public LocalDateFilter getValidationClientDate() {
        return validationClientDate;
    }

    public Optional<LocalDateFilter> optionalValidationClientDate() {
        return Optional.ofNullable(validationClientDate);
    }

    public LocalDateFilter validationClientDate() {
        if (validationClientDate == null) {
            setValidationClientDate(new LocalDateFilter());
        }
        return validationClientDate;
    }

    public void setValidationClientDate(LocalDateFilter validationClientDate) {
        this.validationClientDate = validationClientDate;
    }

    public BooleanFilter getBonPourCommand() {
        return bonPourCommand;
    }

    public Optional<BooleanFilter> optionalBonPourCommand() {
        return Optional.ofNullable(bonPourCommand);
    }

    public BooleanFilter bonPourCommand() {
        if (bonPourCommand == null) {
            setBonPourCommand(new BooleanFilter());
        }
        return bonPourCommand;
    }

    public void setBonPourCommand(BooleanFilter bonPourCommand) {
        this.bonPourCommand = bonPourCommand;
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

    public BooleanFilter getValidation() {
        return validation;
    }

    public Optional<BooleanFilter> optionalValidation() {
        return Optional.ofNullable(validation);
    }

    public BooleanFilter validation() {
        if (validation == null) {
            setValidation(new BooleanFilter());
        }
        return validation;
    }

    public void setValidation(BooleanFilter validation) {
        this.validation = validation;
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
        final ReportInterventionListCriteria that = (ReportInterventionListCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(site, that.site) &&
            Objects.equals(codeAgence, that.codeAgence) &&
            Objects.equals(affaireNumber, that.affaireNumber) &&
            Objects.equals(contractNumber, that.contractNumber) &&
            Objects.equals(installationAdress, that.installationAdress) &&
            Objects.equals(interlocuteurIntervation, that.interlocuteurIntervation) &&
            Objects.equals(tel, that.tel) &&
            Objects.equals(installationSousContract, that.installationSousContract) &&
            Objects.equals(installationSousGarantie, that.installationSousGarantie) &&
            Objects.equals(adressFacturation, that.adressFacturation) &&
            Objects.equals(interlocuteurFacturation, that.interlocuteurFacturation) &&
            Objects.equals(conditionDePayementCheque, that.conditionDePayementCheque) &&
            Objects.equals(conditionPayementAutre, that.conditionPayementAutre) &&
            Objects.equals(conditionPayementComment, that.conditionPayementComment) &&
            Objects.equals(miseEnServiceDefinitvie, that.miseEnServiceDefinitvie) &&
            Objects.equals(miseEnServicePartielle, that.miseEnServicePartielle) &&
            Objects.equals(maintenancePreventive, that.maintenancePreventive) &&
            Objects.equals(maintenanceCorrective, that.maintenanceCorrective) &&
            Objects.equals(bT, that.bT) &&
            Objects.equals(anomalieSignalee, that.anomalieSignalee) &&
            Objects.equals(interventionDate, that.interventionDate) &&
            Objects.equals(interventionStartDate, that.interventionStartDate) &&
            Objects.equals(remiseServiceDate, that.remiseServiceDate) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(causeExterieurInstallation, that.causeExterieurInstallation) &&
            Objects.equals(installationFonctionnelleApresInervention, that.installationFonctionnelleApresInervention) &&
            Objects.equals(autreInterventionsAPrevoir, that.autreInterventionsAPrevoir) &&
            Objects.equals(clientApreciation, that.clientApreciation) &&
            Objects.equals(respectDelais, that.respectDelais) &&
            Objects.equals(qualiteIntervention, that.qualiteIntervention) &&
            Objects.equals(qualiteDevoirConseil, that.qualiteDevoirConseil) &&
            Objects.equals(prestationsAchevees, that.prestationsAchevees) &&
            Objects.equals(devisComplentaire, that.devisComplentaire) &&
            Objects.equals(technicienName, that.technicienName) &&
            Objects.equals(codeTechnicien, that.codeTechnicien) &&
            Objects.equals(validationClientName, that.validationClientName) &&
            Objects.equals(validationNameFunction, that.validationNameFunction) &&
            Objects.equals(validationClientDate, that.validationClientDate) &&
            Objects.equals(bonPourCommand, that.bonPourCommand) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(validation, that.validation) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            site,
            codeAgence,
            affaireNumber,
            contractNumber,
            installationAdress,
            interlocuteurIntervation,
            tel,
            installationSousContract,
            installationSousGarantie,
            adressFacturation,
            interlocuteurFacturation,
            conditionDePayementCheque,
            conditionPayementAutre,
            conditionPayementComment,
            miseEnServiceDefinitvie,
            miseEnServicePartielle,
            maintenancePreventive,
            maintenanceCorrective,
            bT,
            anomalieSignalee,
            interventionDate,
            interventionStartDate,
            remiseServiceDate,
            endDate,
            causeExterieurInstallation,
            installationFonctionnelleApresInervention,
            autreInterventionsAPrevoir,
            clientApreciation,
            respectDelais,
            qualiteIntervention,
            qualiteDevoirConseil,
            prestationsAchevees,
            devisComplentaire,
            technicienName,
            codeTechnicien,
            validationClientName,
            validationNameFunction,
            validationClientDate,
            bonPourCommand,
            createdBy,
            validation,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportInterventionListCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalSite().map(f -> "site=" + f + ", ").orElse("") +
            optionalCodeAgence().map(f -> "codeAgence=" + f + ", ").orElse("") +
            optionalAffaireNumber().map(f -> "affaireNumber=" + f + ", ").orElse("") +
            optionalContractNumber().map(f -> "contractNumber=" + f + ", ").orElse("") +
            optionalInstallationAdress().map(f -> "installationAdress=" + f + ", ").orElse("") +
            optionalInterlocuteurIntervation().map(f -> "interlocuteurIntervation=" + f + ", ").orElse("") +
            optionalTel().map(f -> "tel=" + f + ", ").orElse("") +
            optionalInstallationSousContract().map(f -> "installationSousContract=" + f + ", ").orElse("") +
            optionalInstallationSousGarantie().map(f -> "installationSousGarantie=" + f + ", ").orElse("") +
            optionalAdressFacturation().map(f -> "adressFacturation=" + f + ", ").orElse("") +
            optionalInterlocuteurFacturation().map(f -> "interlocuteurFacturation=" + f + ", ").orElse("") +
            optionalConditionDePayementCheque().map(f -> "conditionDePayementCheque=" + f + ", ").orElse("") +
            optionalConditionPayementAutre().map(f -> "conditionPayementAutre=" + f + ", ").orElse("") +
            optionalConditionPayementComment().map(f -> "conditionPayementComment=" + f + ", ").orElse("") +
            optionalMiseEnServiceDefinitvie().map(f -> "miseEnServiceDefinitvie=" + f + ", ").orElse("") +
            optionalMiseEnServicePartielle().map(f -> "miseEnServicePartielle=" + f + ", ").orElse("") +
            optionalMaintenancePreventive().map(f -> "maintenancePreventive=" + f + ", ").orElse("") +
            optionalMaintenanceCorrective().map(f -> "maintenanceCorrective=" + f + ", ").orElse("") +
            optionalbT().map(f -> "bT=" + f + ", ").orElse("") +
            optionalAnomalieSignalee().map(f -> "anomalieSignalee=" + f + ", ").orElse("") +
            optionalInterventionDate().map(f -> "interventionDate=" + f + ", ").orElse("") +
            optionalInterventionStartDate().map(f -> "interventionStartDate=" + f + ", ").orElse("") +
            optionalRemiseServiceDate().map(f -> "remiseServiceDate=" + f + ", ").orElse("") +
            optionalEndDate().map(f -> "endDate=" + f + ", ").orElse("") +
            optionalCauseExterieurInstallation().map(f -> "causeExterieurInstallation=" + f + ", ").orElse("") +
            optionalInstallationFonctionnelleApresInervention().map(f -> "installationFonctionnelleApresInervention=" + f + ", ").orElse("") +
            optionalAutreInterventionsAPrevoir().map(f -> "autreInterventionsAPrevoir=" + f + ", ").orElse("") +
            optionalClientApreciation().map(f -> "clientApreciation=" + f + ", ").orElse("") +
            optionalRespectDelais().map(f -> "respectDelais=" + f + ", ").orElse("") +
            optionalQualiteIntervention().map(f -> "qualiteIntervention=" + f + ", ").orElse("") +
            optionalQualiteDevoirConseil().map(f -> "qualiteDevoirConseil=" + f + ", ").orElse("") +
            optionalPrestationsAchevees().map(f -> "prestationsAchevees=" + f + ", ").orElse("") +
            optionalDevisComplentaire().map(f -> "devisComplentaire=" + f + ", ").orElse("") +
            optionalTechnicienName().map(f -> "technicienName=" + f + ", ").orElse("") +
            optionalCodeTechnicien().map(f -> "codeTechnicien=" + f + ", ").orElse("") +
            optionalValidationClientName().map(f -> "validationClientName=" + f + ", ").orElse("") +
            optionalValidationNameFunction().map(f -> "validationNameFunction=" + f + ", ").orElse("") +
            optionalValidationClientDate().map(f -> "validationClientDate=" + f + ", ").orElse("") +
            optionalBonPourCommand().map(f -> "bonPourCommand=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalValidation().map(f -> "validation=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
