package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.ReportInterventionListAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ReportInterventionList;
import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import com.mycompany.myapp.domain.enumeration.EvaluationStatus;
import com.mycompany.myapp.repository.ReportInterventionListRepository;
import com.mycompany.myapp.repository.search.ReportInterventionListSearchRepository;
import com.mycompany.myapp.service.dto.ReportInterventionListDTO;
import com.mycompany.myapp.service.mapper.ReportInterventionListMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ReportInterventionListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportInterventionListResourceIT {

    private static final String DEFAULT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_AGENCE = "BBBBBBBBBB";

    private static final String DEFAULT_AFFAIRE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_AFFAIRE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INSTALLATION_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_INSTALLATION_ADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INTERLOCUTEUR_INTERVATION = "AAAAAAAAAA";
    private static final String UPDATED_INTERLOCUTEUR_INTERVATION = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INSTALLATION_SOUS_CONTRACT = false;
    private static final Boolean UPDATED_INSTALLATION_SOUS_CONTRACT = true;

    private static final Boolean DEFAULT_INSTALLATION_SOUS_GARANTIE = false;
    private static final Boolean UPDATED_INSTALLATION_SOUS_GARANTIE = true;

    private static final String DEFAULT_ADRESS_FACTURATION = "AAAAAAAAAA";
    private static final String UPDATED_ADRESS_FACTURATION = "BBBBBBBBBB";

    private static final String DEFAULT_INTERLOCUTEUR_FACTURATION = "AAAAAAAAAA";
    private static final String UPDATED_INTERLOCUTEUR_FACTURATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE = false;
    private static final Boolean UPDATED_CONDITION_DE_PAYEMENT_CHEQUE = true;

    private static final Boolean DEFAULT_CONDITION_PAYEMENT_AUTRE = false;
    private static final Boolean UPDATED_CONDITION_PAYEMENT_AUTRE = true;

    private static final String DEFAULT_CONDITION_PAYEMENT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION_PAYEMENT_COMMENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MISE_EN_SERVICE_DEFINITVIE = false;
    private static final Boolean UPDATED_MISE_EN_SERVICE_DEFINITVIE = true;

    private static final Boolean DEFAULT_MISE_EN_SERVICE_PARTIELLE = false;
    private static final Boolean UPDATED_MISE_EN_SERVICE_PARTIELLE = true;

    private static final Boolean DEFAULT_MAINTENANCE_PREVENTIVE = false;
    private static final Boolean UPDATED_MAINTENANCE_PREVENTIVE = true;

    private static final Boolean DEFAULT_MAINTENANCE_CORRECTIVE = false;
    private static final Boolean UPDATED_MAINTENANCE_CORRECTIVE = true;

    private static final String DEFAULT_B_T = "AAAAAAAAAA";
    private static final String UPDATED_B_T = "BBBBBBBBBB";

    private static final String DEFAULT_ANOMALIE_SIGNALEE = "AAAAAAAAAA";
    private static final String UPDATED_ANOMALIE_SIGNALEE = "BBBBBBBBBB";

    private static final Instant DEFAULT_INTERVENTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INTERVENTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INTERVENTION_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INTERVENTION_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REMISE_SERVICE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REMISE_SERVICE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NATURE_INTERVENTION = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_INTERVENTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CAUSE_EXTERIEUR_INSTALLATION = false;
    private static final Boolean UPDATED_CAUSE_EXTERIEUR_INSTALLATION = true;

    private static final Boolean DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION = false;
    private static final Boolean UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION = true;

    private static final String DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_APRECIATION = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_APRECIATION = "BBBBBBBBBB";

    private static final EvaluationStatus DEFAULT_RESPECT_DELAIS = EvaluationStatus.TRES_SATISFAISANT;
    private static final EvaluationStatus UPDATED_RESPECT_DELAIS = EvaluationStatus.SATISFAISANT;

    private static final EvaluationStatus DEFAULT_QUALITE_INTERVENTION = EvaluationStatus.TRES_SATISFAISANT;
    private static final EvaluationStatus UPDATED_QUALITE_INTERVENTION = EvaluationStatus.SATISFAISANT;

    private static final EvaluationStatus DEFAULT_QUALITE_DEVOIR_CONSEIL = EvaluationStatus.TRES_SATISFAISANT;
    private static final EvaluationStatus UPDATED_QUALITE_DEVOIR_CONSEIL = EvaluationStatus.SATISFAISANT;

    private static final Boolean DEFAULT_PRESTATIONS_ACHEVEES = false;
    private static final Boolean UPDATED_PRESTATIONS_ACHEVEES = true;

    private static final Boolean DEFAULT_DEVIS_COMPLENTAIRE = false;
    private static final Boolean UPDATED_DEVIS_COMPLENTAIRE = true;

    private static final String DEFAULT_TECHNICIEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICIEN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_TECHNICIEN = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TECHNICIEN = "BBBBBBBBBB";

    private static final String DEFAULT_VALIDATION_CLIENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VALIDATION_CLIENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALIDATION_NAME_FUNCTION = "AAAAAAAAAA";
    private static final String UPDATED_VALIDATION_NAME_FUNCTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_VALIDATION_CLIENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALIDATION_CLIENT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_VALIDATION_CLIENT_DATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_BON_POUR_COMMAND = false;
    private static final Boolean UPDATED_BON_POUR_COMMAND = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VALIDATION = false;
    private static final Boolean UPDATED_VALIDATION = true;

    private static final String ENTITY_API_URL = "/api/report-intervention-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/report-intervention-lists/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReportInterventionListRepository reportInterventionListRepository;

    @Autowired
    private ReportInterventionListMapper reportInterventionListMapper;

    @Autowired
    private ReportInterventionListSearchRepository reportInterventionListSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportInterventionListMockMvc;

    private ReportInterventionList reportInterventionList;

    private ReportInterventionList insertedReportInterventionList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportInterventionList createEntity(EntityManager em) {
        ReportInterventionList reportInterventionList = new ReportInterventionList()
            .site(DEFAULT_SITE)
            .codeAgence(DEFAULT_CODE_AGENCE)
            .affaireNumber(DEFAULT_AFFAIRE_NUMBER)
            .contractNumber(DEFAULT_CONTRACT_NUMBER)
            .installationAdress(DEFAULT_INSTALLATION_ADRESS)
            .interlocuteurIntervation(DEFAULT_INTERLOCUTEUR_INTERVATION)
            .tel(DEFAULT_TEL)
            .installationSousContract(DEFAULT_INSTALLATION_SOUS_CONTRACT)
            .installationSousGarantie(DEFAULT_INSTALLATION_SOUS_GARANTIE)
            .adressFacturation(DEFAULT_ADRESS_FACTURATION)
            .interlocuteurFacturation(DEFAULT_INTERLOCUTEUR_FACTURATION)
            .conditionDePayementCheque(DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE)
            .conditionPayementAutre(DEFAULT_CONDITION_PAYEMENT_AUTRE)
            .conditionPayementComment(DEFAULT_CONDITION_PAYEMENT_COMMENT)
            .miseEnServiceDefinitvie(DEFAULT_MISE_EN_SERVICE_DEFINITVIE)
            .miseEnServicePartielle(DEFAULT_MISE_EN_SERVICE_PARTIELLE)
            .maintenancePreventive(DEFAULT_MAINTENANCE_PREVENTIVE)
            .maintenanceCorrective(DEFAULT_MAINTENANCE_CORRECTIVE)
            .bT(DEFAULT_B_T)
            .anomalieSignalee(DEFAULT_ANOMALIE_SIGNALEE)
            .interventionDate(DEFAULT_INTERVENTION_DATE)
            .interventionStartDate(DEFAULT_INTERVENTION_START_DATE)
            .remiseServiceDate(DEFAULT_REMISE_SERVICE_DATE)
            .endDate(DEFAULT_END_DATE)
            .natureIntervention(DEFAULT_NATURE_INTERVENTION)
            .causeExterieurInstallation(DEFAULT_CAUSE_EXTERIEUR_INSTALLATION)
            .installationFonctionnelleApresInervention(DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION)
            .autreInterventionsAPrevoir(DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR)
            .clientApreciation(DEFAULT_CLIENT_APRECIATION)
            .respectDelais(DEFAULT_RESPECT_DELAIS)
            .qualiteIntervention(DEFAULT_QUALITE_INTERVENTION)
            .qualiteDevoirConseil(DEFAULT_QUALITE_DEVOIR_CONSEIL)
            .prestationsAchevees(DEFAULT_PRESTATIONS_ACHEVEES)
            .devisComplentaire(DEFAULT_DEVIS_COMPLENTAIRE)
            .technicienName(DEFAULT_TECHNICIEN_NAME)
            .codeTechnicien(DEFAULT_CODE_TECHNICIEN)
            .validationClientName(DEFAULT_VALIDATION_CLIENT_NAME)
            .validationNameFunction(DEFAULT_VALIDATION_NAME_FUNCTION)
            .validationClientDate(DEFAULT_VALIDATION_CLIENT_DATE)
            .bonPourCommand(DEFAULT_BON_POUR_COMMAND)
            .createdBy(DEFAULT_CREATED_BY)
            .validation(DEFAULT_VALIDATION);
        return reportInterventionList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportInterventionList createUpdatedEntity(EntityManager em) {
        ReportInterventionList reportInterventionList = new ReportInterventionList()
            .site(UPDATED_SITE)
            .codeAgence(UPDATED_CODE_AGENCE)
            .affaireNumber(UPDATED_AFFAIRE_NUMBER)
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .installationAdress(UPDATED_INSTALLATION_ADRESS)
            .interlocuteurIntervation(UPDATED_INTERLOCUTEUR_INTERVATION)
            .tel(UPDATED_TEL)
            .installationSousContract(UPDATED_INSTALLATION_SOUS_CONTRACT)
            .installationSousGarantie(UPDATED_INSTALLATION_SOUS_GARANTIE)
            .adressFacturation(UPDATED_ADRESS_FACTURATION)
            .interlocuteurFacturation(UPDATED_INTERLOCUTEUR_FACTURATION)
            .conditionDePayementCheque(UPDATED_CONDITION_DE_PAYEMENT_CHEQUE)
            .conditionPayementAutre(UPDATED_CONDITION_PAYEMENT_AUTRE)
            .conditionPayementComment(UPDATED_CONDITION_PAYEMENT_COMMENT)
            .miseEnServiceDefinitvie(UPDATED_MISE_EN_SERVICE_DEFINITVIE)
            .miseEnServicePartielle(UPDATED_MISE_EN_SERVICE_PARTIELLE)
            .maintenancePreventive(UPDATED_MAINTENANCE_PREVENTIVE)
            .maintenanceCorrective(UPDATED_MAINTENANCE_CORRECTIVE)
            .bT(UPDATED_B_T)
            .anomalieSignalee(UPDATED_ANOMALIE_SIGNALEE)
            .interventionDate(UPDATED_INTERVENTION_DATE)
            .interventionStartDate(UPDATED_INTERVENTION_START_DATE)
            .remiseServiceDate(UPDATED_REMISE_SERVICE_DATE)
            .endDate(UPDATED_END_DATE)
            .natureIntervention(UPDATED_NATURE_INTERVENTION)
            .causeExterieurInstallation(UPDATED_CAUSE_EXTERIEUR_INSTALLATION)
            .installationFonctionnelleApresInervention(UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION)
            .autreInterventionsAPrevoir(UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR)
            .clientApreciation(UPDATED_CLIENT_APRECIATION)
            .respectDelais(UPDATED_RESPECT_DELAIS)
            .qualiteIntervention(UPDATED_QUALITE_INTERVENTION)
            .qualiteDevoirConseil(UPDATED_QUALITE_DEVOIR_CONSEIL)
            .prestationsAchevees(UPDATED_PRESTATIONS_ACHEVEES)
            .devisComplentaire(UPDATED_DEVIS_COMPLENTAIRE)
            .technicienName(UPDATED_TECHNICIEN_NAME)
            .codeTechnicien(UPDATED_CODE_TECHNICIEN)
            .validationClientName(UPDATED_VALIDATION_CLIENT_NAME)
            .validationNameFunction(UPDATED_VALIDATION_NAME_FUNCTION)
            .validationClientDate(UPDATED_VALIDATION_CLIENT_DATE)
            .bonPourCommand(UPDATED_BON_POUR_COMMAND)
            .createdBy(UPDATED_CREATED_BY)
            .validation(UPDATED_VALIDATION);
        return reportInterventionList;
    }

    @BeforeEach
    public void initTest() {
        reportInterventionList = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedReportInterventionList != null) {
            reportInterventionListRepository.delete(insertedReportInterventionList);
            reportInterventionListSearchRepository.delete(insertedReportInterventionList);
            insertedReportInterventionList = null;
        }
    }

    @Test
    @Transactional
    void createReportInterventionList() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);
        var returnedReportInterventionListDTO = om.readValue(
            restReportInterventionListMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reportInterventionListDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ReportInterventionListDTO.class
        );

        // Validate the ReportInterventionList in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedReportInterventionList = reportInterventionListMapper.toEntity(returnedReportInterventionListDTO);
        assertReportInterventionListUpdatableFieldsEquals(
            returnedReportInterventionList,
            getPersistedReportInterventionList(returnedReportInterventionList)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedReportInterventionList = returnedReportInterventionList;
    }

    @Test
    @Transactional
    void createReportInterventionListWithExistingId() throws Exception {
        // Create the ReportInterventionList with an existing ID
        reportInterventionList.setId(1L);
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportInterventionListMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reportInterventionListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllReportInterventionLists() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList
        restReportInterventionListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportInterventionList.getId().intValue())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE)))
            .andExpect(jsonPath("$.[*].codeAgence").value(hasItem(DEFAULT_CODE_AGENCE)))
            .andExpect(jsonPath("$.[*].affaireNumber").value(hasItem(DEFAULT_AFFAIRE_NUMBER)))
            .andExpect(jsonPath("$.[*].contractNumber").value(hasItem(DEFAULT_CONTRACT_NUMBER)))
            .andExpect(jsonPath("$.[*].installationAdress").value(hasItem(DEFAULT_INSTALLATION_ADRESS)))
            .andExpect(jsonPath("$.[*].interlocuteurIntervation").value(hasItem(DEFAULT_INTERLOCUTEUR_INTERVATION)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].installationSousContract").value(hasItem(DEFAULT_INSTALLATION_SOUS_CONTRACT.booleanValue())))
            .andExpect(jsonPath("$.[*].installationSousGarantie").value(hasItem(DEFAULT_INSTALLATION_SOUS_GARANTIE.booleanValue())))
            .andExpect(jsonPath("$.[*].adressFacturation").value(hasItem(DEFAULT_ADRESS_FACTURATION)))
            .andExpect(jsonPath("$.[*].interlocuteurFacturation").value(hasItem(DEFAULT_INTERLOCUTEUR_FACTURATION)))
            .andExpect(jsonPath("$.[*].conditionDePayementCheque").value(hasItem(DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].conditionPayementAutre").value(hasItem(DEFAULT_CONDITION_PAYEMENT_AUTRE.booleanValue())))
            .andExpect(jsonPath("$.[*].conditionPayementComment").value(hasItem(DEFAULT_CONDITION_PAYEMENT_COMMENT)))
            .andExpect(jsonPath("$.[*].miseEnServiceDefinitvie").value(hasItem(DEFAULT_MISE_EN_SERVICE_DEFINITVIE.booleanValue())))
            .andExpect(jsonPath("$.[*].miseEnServicePartielle").value(hasItem(DEFAULT_MISE_EN_SERVICE_PARTIELLE.booleanValue())))
            .andExpect(jsonPath("$.[*].maintenancePreventive").value(hasItem(DEFAULT_MAINTENANCE_PREVENTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].maintenanceCorrective").value(hasItem(DEFAULT_MAINTENANCE_CORRECTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].bT").value(hasItem(DEFAULT_B_T)))
            .andExpect(jsonPath("$.[*].anomalieSignalee").value(hasItem(DEFAULT_ANOMALIE_SIGNALEE)))
            .andExpect(jsonPath("$.[*].interventionDate").value(hasItem(DEFAULT_INTERVENTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].interventionStartDate").value(hasItem(DEFAULT_INTERVENTION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].remiseServiceDate").value(hasItem(DEFAULT_REMISE_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].natureIntervention").value(hasItem(DEFAULT_NATURE_INTERVENTION.toString())))
            .andExpect(jsonPath("$.[*].causeExterieurInstallation").value(hasItem(DEFAULT_CAUSE_EXTERIEUR_INSTALLATION.booleanValue())))
            .andExpect(
                jsonPath("$.[*].installationFonctionnelleApresInervention").value(
                    hasItem(DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION.booleanValue())
                )
            )
            .andExpect(jsonPath("$.[*].autreInterventionsAPrevoir").value(hasItem(DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR)))
            .andExpect(jsonPath("$.[*].clientApreciation").value(hasItem(DEFAULT_CLIENT_APRECIATION)))
            .andExpect(jsonPath("$.[*].respectDelais").value(hasItem(DEFAULT_RESPECT_DELAIS.toString())))
            .andExpect(jsonPath("$.[*].qualiteIntervention").value(hasItem(DEFAULT_QUALITE_INTERVENTION.toString())))
            .andExpect(jsonPath("$.[*].qualiteDevoirConseil").value(hasItem(DEFAULT_QUALITE_DEVOIR_CONSEIL.toString())))
            .andExpect(jsonPath("$.[*].prestationsAchevees").value(hasItem(DEFAULT_PRESTATIONS_ACHEVEES.booleanValue())))
            .andExpect(jsonPath("$.[*].devisComplentaire").value(hasItem(DEFAULT_DEVIS_COMPLENTAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].technicienName").value(hasItem(DEFAULT_TECHNICIEN_NAME)))
            .andExpect(jsonPath("$.[*].codeTechnicien").value(hasItem(DEFAULT_CODE_TECHNICIEN)))
            .andExpect(jsonPath("$.[*].validationClientName").value(hasItem(DEFAULT_VALIDATION_CLIENT_NAME)))
            .andExpect(jsonPath("$.[*].validationNameFunction").value(hasItem(DEFAULT_VALIDATION_NAME_FUNCTION)))
            .andExpect(jsonPath("$.[*].validationClientDate").value(hasItem(DEFAULT_VALIDATION_CLIENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].bonPourCommand").value(hasItem(DEFAULT_BON_POUR_COMMAND.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].validation").value(hasItem(DEFAULT_VALIDATION.booleanValue())));
    }

    @Test
    @Transactional
    void getReportInterventionList() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get the reportInterventionList
        restReportInterventionListMockMvc
            .perform(get(ENTITY_API_URL_ID, reportInterventionList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportInterventionList.getId().intValue()))
            .andExpect(jsonPath("$.site").value(DEFAULT_SITE))
            .andExpect(jsonPath("$.codeAgence").value(DEFAULT_CODE_AGENCE))
            .andExpect(jsonPath("$.affaireNumber").value(DEFAULT_AFFAIRE_NUMBER))
            .andExpect(jsonPath("$.contractNumber").value(DEFAULT_CONTRACT_NUMBER))
            .andExpect(jsonPath("$.installationAdress").value(DEFAULT_INSTALLATION_ADRESS))
            .andExpect(jsonPath("$.interlocuteurIntervation").value(DEFAULT_INTERLOCUTEUR_INTERVATION))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.installationSousContract").value(DEFAULT_INSTALLATION_SOUS_CONTRACT.booleanValue()))
            .andExpect(jsonPath("$.installationSousGarantie").value(DEFAULT_INSTALLATION_SOUS_GARANTIE.booleanValue()))
            .andExpect(jsonPath("$.adressFacturation").value(DEFAULT_ADRESS_FACTURATION))
            .andExpect(jsonPath("$.interlocuteurFacturation").value(DEFAULT_INTERLOCUTEUR_FACTURATION))
            .andExpect(jsonPath("$.conditionDePayementCheque").value(DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE.booleanValue()))
            .andExpect(jsonPath("$.conditionPayementAutre").value(DEFAULT_CONDITION_PAYEMENT_AUTRE.booleanValue()))
            .andExpect(jsonPath("$.conditionPayementComment").value(DEFAULT_CONDITION_PAYEMENT_COMMENT))
            .andExpect(jsonPath("$.miseEnServiceDefinitvie").value(DEFAULT_MISE_EN_SERVICE_DEFINITVIE.booleanValue()))
            .andExpect(jsonPath("$.miseEnServicePartielle").value(DEFAULT_MISE_EN_SERVICE_PARTIELLE.booleanValue()))
            .andExpect(jsonPath("$.maintenancePreventive").value(DEFAULT_MAINTENANCE_PREVENTIVE.booleanValue()))
            .andExpect(jsonPath("$.maintenanceCorrective").value(DEFAULT_MAINTENANCE_CORRECTIVE.booleanValue()))
            .andExpect(jsonPath("$.bT").value(DEFAULT_B_T))
            .andExpect(jsonPath("$.anomalieSignalee").value(DEFAULT_ANOMALIE_SIGNALEE))
            .andExpect(jsonPath("$.interventionDate").value(DEFAULT_INTERVENTION_DATE.toString()))
            .andExpect(jsonPath("$.interventionStartDate").value(DEFAULT_INTERVENTION_START_DATE.toString()))
            .andExpect(jsonPath("$.remiseServiceDate").value(DEFAULT_REMISE_SERVICE_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.natureIntervention").value(DEFAULT_NATURE_INTERVENTION.toString()))
            .andExpect(jsonPath("$.causeExterieurInstallation").value(DEFAULT_CAUSE_EXTERIEUR_INSTALLATION.booleanValue()))
            .andExpect(
                jsonPath("$.installationFonctionnelleApresInervention").value(
                    DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION.booleanValue()
                )
            )
            .andExpect(jsonPath("$.autreInterventionsAPrevoir").value(DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR))
            .andExpect(jsonPath("$.clientApreciation").value(DEFAULT_CLIENT_APRECIATION))
            .andExpect(jsonPath("$.respectDelais").value(DEFAULT_RESPECT_DELAIS.toString()))
            .andExpect(jsonPath("$.qualiteIntervention").value(DEFAULT_QUALITE_INTERVENTION.toString()))
            .andExpect(jsonPath("$.qualiteDevoirConseil").value(DEFAULT_QUALITE_DEVOIR_CONSEIL.toString()))
            .andExpect(jsonPath("$.prestationsAchevees").value(DEFAULT_PRESTATIONS_ACHEVEES.booleanValue()))
            .andExpect(jsonPath("$.devisComplentaire").value(DEFAULT_DEVIS_COMPLENTAIRE.booleanValue()))
            .andExpect(jsonPath("$.technicienName").value(DEFAULT_TECHNICIEN_NAME))
            .andExpect(jsonPath("$.codeTechnicien").value(DEFAULT_CODE_TECHNICIEN))
            .andExpect(jsonPath("$.validationClientName").value(DEFAULT_VALIDATION_CLIENT_NAME))
            .andExpect(jsonPath("$.validationNameFunction").value(DEFAULT_VALIDATION_NAME_FUNCTION))
            .andExpect(jsonPath("$.validationClientDate").value(DEFAULT_VALIDATION_CLIENT_DATE.toString()))
            .andExpect(jsonPath("$.bonPourCommand").value(DEFAULT_BON_POUR_COMMAND.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.validation").value(DEFAULT_VALIDATION.booleanValue()));
    }

    @Test
    @Transactional
    void getReportInterventionListsByIdFiltering() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        Long id = reportInterventionList.getId();

        defaultReportInterventionListFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultReportInterventionListFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultReportInterventionListFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBySiteIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where site equals to
        defaultReportInterventionListFiltering("site.equals=" + DEFAULT_SITE, "site.equals=" + UPDATED_SITE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBySiteIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where site in
        defaultReportInterventionListFiltering("site.in=" + DEFAULT_SITE + "," + UPDATED_SITE, "site.in=" + UPDATED_SITE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBySiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where site is not null
        defaultReportInterventionListFiltering("site.specified=true", "site.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBySiteContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where site contains
        defaultReportInterventionListFiltering("site.contains=" + DEFAULT_SITE, "site.contains=" + UPDATED_SITE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBySiteNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where site does not contain
        defaultReportInterventionListFiltering("site.doesNotContain=" + UPDATED_SITE, "site.doesNotContain=" + DEFAULT_SITE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeAgenceIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeAgence equals to
        defaultReportInterventionListFiltering("codeAgence.equals=" + DEFAULT_CODE_AGENCE, "codeAgence.equals=" + UPDATED_CODE_AGENCE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeAgenceIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeAgence in
        defaultReportInterventionListFiltering(
            "codeAgence.in=" + DEFAULT_CODE_AGENCE + "," + UPDATED_CODE_AGENCE,
            "codeAgence.in=" + UPDATED_CODE_AGENCE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeAgenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeAgence is not null
        defaultReportInterventionListFiltering("codeAgence.specified=true", "codeAgence.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeAgenceContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeAgence contains
        defaultReportInterventionListFiltering("codeAgence.contains=" + DEFAULT_CODE_AGENCE, "codeAgence.contains=" + UPDATED_CODE_AGENCE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeAgenceNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeAgence does not contain
        defaultReportInterventionListFiltering(
            "codeAgence.doesNotContain=" + UPDATED_CODE_AGENCE,
            "codeAgence.doesNotContain=" + DEFAULT_CODE_AGENCE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAffaireNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where affaireNumber equals to
        defaultReportInterventionListFiltering(
            "affaireNumber.equals=" + DEFAULT_AFFAIRE_NUMBER,
            "affaireNumber.equals=" + UPDATED_AFFAIRE_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAffaireNumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where affaireNumber in
        defaultReportInterventionListFiltering(
            "affaireNumber.in=" + DEFAULT_AFFAIRE_NUMBER + "," + UPDATED_AFFAIRE_NUMBER,
            "affaireNumber.in=" + UPDATED_AFFAIRE_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAffaireNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where affaireNumber is not null
        defaultReportInterventionListFiltering("affaireNumber.specified=true", "affaireNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAffaireNumberContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where affaireNumber contains
        defaultReportInterventionListFiltering(
            "affaireNumber.contains=" + DEFAULT_AFFAIRE_NUMBER,
            "affaireNumber.contains=" + UPDATED_AFFAIRE_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAffaireNumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where affaireNumber does not contain
        defaultReportInterventionListFiltering(
            "affaireNumber.doesNotContain=" + UPDATED_AFFAIRE_NUMBER,
            "affaireNumber.doesNotContain=" + DEFAULT_AFFAIRE_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByContractNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where contractNumber equals to
        defaultReportInterventionListFiltering(
            "contractNumber.equals=" + DEFAULT_CONTRACT_NUMBER,
            "contractNumber.equals=" + UPDATED_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByContractNumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where contractNumber in
        defaultReportInterventionListFiltering(
            "contractNumber.in=" + DEFAULT_CONTRACT_NUMBER + "," + UPDATED_CONTRACT_NUMBER,
            "contractNumber.in=" + UPDATED_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByContractNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where contractNumber is not null
        defaultReportInterventionListFiltering("contractNumber.specified=true", "contractNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByContractNumberContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where contractNumber contains
        defaultReportInterventionListFiltering(
            "contractNumber.contains=" + DEFAULT_CONTRACT_NUMBER,
            "contractNumber.contains=" + UPDATED_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByContractNumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where contractNumber does not contain
        defaultReportInterventionListFiltering(
            "contractNumber.doesNotContain=" + UPDATED_CONTRACT_NUMBER,
            "contractNumber.doesNotContain=" + DEFAULT_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationAdressIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationAdress equals to
        defaultReportInterventionListFiltering(
            "installationAdress.equals=" + DEFAULT_INSTALLATION_ADRESS,
            "installationAdress.equals=" + UPDATED_INSTALLATION_ADRESS
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationAdressIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationAdress in
        defaultReportInterventionListFiltering(
            "installationAdress.in=" + DEFAULT_INSTALLATION_ADRESS + "," + UPDATED_INSTALLATION_ADRESS,
            "installationAdress.in=" + UPDATED_INSTALLATION_ADRESS
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationAdressIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationAdress is not null
        defaultReportInterventionListFiltering("installationAdress.specified=true", "installationAdress.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationAdressContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationAdress contains
        defaultReportInterventionListFiltering(
            "installationAdress.contains=" + DEFAULT_INSTALLATION_ADRESS,
            "installationAdress.contains=" + UPDATED_INSTALLATION_ADRESS
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationAdressNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationAdress does not contain
        defaultReportInterventionListFiltering(
            "installationAdress.doesNotContain=" + UPDATED_INSTALLATION_ADRESS,
            "installationAdress.doesNotContain=" + DEFAULT_INSTALLATION_ADRESS
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurIntervationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurIntervation equals to
        defaultReportInterventionListFiltering(
            "interlocuteurIntervation.equals=" + DEFAULT_INTERLOCUTEUR_INTERVATION,
            "interlocuteurIntervation.equals=" + UPDATED_INTERLOCUTEUR_INTERVATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurIntervationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurIntervation in
        defaultReportInterventionListFiltering(
            "interlocuteurIntervation.in=" + DEFAULT_INTERLOCUTEUR_INTERVATION + "," + UPDATED_INTERLOCUTEUR_INTERVATION,
            "interlocuteurIntervation.in=" + UPDATED_INTERLOCUTEUR_INTERVATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurIntervationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurIntervation is not null
        defaultReportInterventionListFiltering("interlocuteurIntervation.specified=true", "interlocuteurIntervation.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurIntervationContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurIntervation contains
        defaultReportInterventionListFiltering(
            "interlocuteurIntervation.contains=" + DEFAULT_INTERLOCUTEUR_INTERVATION,
            "interlocuteurIntervation.contains=" + UPDATED_INTERLOCUTEUR_INTERVATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurIntervationNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurIntervation does not contain
        defaultReportInterventionListFiltering(
            "interlocuteurIntervation.doesNotContain=" + UPDATED_INTERLOCUTEUR_INTERVATION,
            "interlocuteurIntervation.doesNotContain=" + DEFAULT_INTERLOCUTEUR_INTERVATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where tel equals to
        defaultReportInterventionListFiltering("tel.equals=" + DEFAULT_TEL, "tel.equals=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where tel in
        defaultReportInterventionListFiltering("tel.in=" + DEFAULT_TEL + "," + UPDATED_TEL, "tel.in=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where tel is not null
        defaultReportInterventionListFiltering("tel.specified=true", "tel.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTelContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where tel contains
        defaultReportInterventionListFiltering("tel.contains=" + DEFAULT_TEL, "tel.contains=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTelNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where tel does not contain
        defaultReportInterventionListFiltering("tel.doesNotContain=" + UPDATED_TEL, "tel.doesNotContain=" + DEFAULT_TEL);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationSousContractIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationSousContract equals to
        defaultReportInterventionListFiltering(
            "installationSousContract.equals=" + DEFAULT_INSTALLATION_SOUS_CONTRACT,
            "installationSousContract.equals=" + UPDATED_INSTALLATION_SOUS_CONTRACT
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationSousContractIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationSousContract in
        defaultReportInterventionListFiltering(
            "installationSousContract.in=" + DEFAULT_INSTALLATION_SOUS_CONTRACT + "," + UPDATED_INSTALLATION_SOUS_CONTRACT,
            "installationSousContract.in=" + UPDATED_INSTALLATION_SOUS_CONTRACT
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationSousContractIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationSousContract is not null
        defaultReportInterventionListFiltering("installationSousContract.specified=true", "installationSousContract.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationSousGarantieIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationSousGarantie equals to
        defaultReportInterventionListFiltering(
            "installationSousGarantie.equals=" + DEFAULT_INSTALLATION_SOUS_GARANTIE,
            "installationSousGarantie.equals=" + UPDATED_INSTALLATION_SOUS_GARANTIE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationSousGarantieIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationSousGarantie in
        defaultReportInterventionListFiltering(
            "installationSousGarantie.in=" + DEFAULT_INSTALLATION_SOUS_GARANTIE + "," + UPDATED_INSTALLATION_SOUS_GARANTIE,
            "installationSousGarantie.in=" + UPDATED_INSTALLATION_SOUS_GARANTIE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationSousGarantieIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationSousGarantie is not null
        defaultReportInterventionListFiltering("installationSousGarantie.specified=true", "installationSousGarantie.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAdressFacturationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where adressFacturation equals to
        defaultReportInterventionListFiltering(
            "adressFacturation.equals=" + DEFAULT_ADRESS_FACTURATION,
            "adressFacturation.equals=" + UPDATED_ADRESS_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAdressFacturationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where adressFacturation in
        defaultReportInterventionListFiltering(
            "adressFacturation.in=" + DEFAULT_ADRESS_FACTURATION + "," + UPDATED_ADRESS_FACTURATION,
            "adressFacturation.in=" + UPDATED_ADRESS_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAdressFacturationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where adressFacturation is not null
        defaultReportInterventionListFiltering("adressFacturation.specified=true", "adressFacturation.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAdressFacturationContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where adressFacturation contains
        defaultReportInterventionListFiltering(
            "adressFacturation.contains=" + DEFAULT_ADRESS_FACTURATION,
            "adressFacturation.contains=" + UPDATED_ADRESS_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAdressFacturationNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where adressFacturation does not contain
        defaultReportInterventionListFiltering(
            "adressFacturation.doesNotContain=" + UPDATED_ADRESS_FACTURATION,
            "adressFacturation.doesNotContain=" + DEFAULT_ADRESS_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurFacturationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurFacturation equals to
        defaultReportInterventionListFiltering(
            "interlocuteurFacturation.equals=" + DEFAULT_INTERLOCUTEUR_FACTURATION,
            "interlocuteurFacturation.equals=" + UPDATED_INTERLOCUTEUR_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurFacturationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurFacturation in
        defaultReportInterventionListFiltering(
            "interlocuteurFacturation.in=" + DEFAULT_INTERLOCUTEUR_FACTURATION + "," + UPDATED_INTERLOCUTEUR_FACTURATION,
            "interlocuteurFacturation.in=" + UPDATED_INTERLOCUTEUR_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurFacturationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurFacturation is not null
        defaultReportInterventionListFiltering("interlocuteurFacturation.specified=true", "interlocuteurFacturation.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurFacturationContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurFacturation contains
        defaultReportInterventionListFiltering(
            "interlocuteurFacturation.contains=" + DEFAULT_INTERLOCUTEUR_FACTURATION,
            "interlocuteurFacturation.contains=" + UPDATED_INTERLOCUTEUR_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterlocuteurFacturationNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interlocuteurFacturation does not contain
        defaultReportInterventionListFiltering(
            "interlocuteurFacturation.doesNotContain=" + UPDATED_INTERLOCUTEUR_FACTURATION,
            "interlocuteurFacturation.doesNotContain=" + DEFAULT_INTERLOCUTEUR_FACTURATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionDePayementChequeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionDePayementCheque equals to
        defaultReportInterventionListFiltering(
            "conditionDePayementCheque.equals=" + DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE,
            "conditionDePayementCheque.equals=" + UPDATED_CONDITION_DE_PAYEMENT_CHEQUE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionDePayementChequeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionDePayementCheque in
        defaultReportInterventionListFiltering(
            "conditionDePayementCheque.in=" + DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE + "," + UPDATED_CONDITION_DE_PAYEMENT_CHEQUE,
            "conditionDePayementCheque.in=" + UPDATED_CONDITION_DE_PAYEMENT_CHEQUE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionDePayementChequeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionDePayementCheque is not null
        defaultReportInterventionListFiltering("conditionDePayementCheque.specified=true", "conditionDePayementCheque.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementAutreIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementAutre equals to
        defaultReportInterventionListFiltering(
            "conditionPayementAutre.equals=" + DEFAULT_CONDITION_PAYEMENT_AUTRE,
            "conditionPayementAutre.equals=" + UPDATED_CONDITION_PAYEMENT_AUTRE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementAutreIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementAutre in
        defaultReportInterventionListFiltering(
            "conditionPayementAutre.in=" + DEFAULT_CONDITION_PAYEMENT_AUTRE + "," + UPDATED_CONDITION_PAYEMENT_AUTRE,
            "conditionPayementAutre.in=" + UPDATED_CONDITION_PAYEMENT_AUTRE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementAutreIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementAutre is not null
        defaultReportInterventionListFiltering("conditionPayementAutre.specified=true", "conditionPayementAutre.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementComment equals to
        defaultReportInterventionListFiltering(
            "conditionPayementComment.equals=" + DEFAULT_CONDITION_PAYEMENT_COMMENT,
            "conditionPayementComment.equals=" + UPDATED_CONDITION_PAYEMENT_COMMENT
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementCommentIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementComment in
        defaultReportInterventionListFiltering(
            "conditionPayementComment.in=" + DEFAULT_CONDITION_PAYEMENT_COMMENT + "," + UPDATED_CONDITION_PAYEMENT_COMMENT,
            "conditionPayementComment.in=" + UPDATED_CONDITION_PAYEMENT_COMMENT
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementComment is not null
        defaultReportInterventionListFiltering("conditionPayementComment.specified=true", "conditionPayementComment.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementCommentContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementComment contains
        defaultReportInterventionListFiltering(
            "conditionPayementComment.contains=" + DEFAULT_CONDITION_PAYEMENT_COMMENT,
            "conditionPayementComment.contains=" + UPDATED_CONDITION_PAYEMENT_COMMENT
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByConditionPayementCommentNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where conditionPayementComment does not contain
        defaultReportInterventionListFiltering(
            "conditionPayementComment.doesNotContain=" + UPDATED_CONDITION_PAYEMENT_COMMENT,
            "conditionPayementComment.doesNotContain=" + DEFAULT_CONDITION_PAYEMENT_COMMENT
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMiseEnServiceDefinitvieIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where miseEnServiceDefinitvie equals to
        defaultReportInterventionListFiltering(
            "miseEnServiceDefinitvie.equals=" + DEFAULT_MISE_EN_SERVICE_DEFINITVIE,
            "miseEnServiceDefinitvie.equals=" + UPDATED_MISE_EN_SERVICE_DEFINITVIE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMiseEnServiceDefinitvieIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where miseEnServiceDefinitvie in
        defaultReportInterventionListFiltering(
            "miseEnServiceDefinitvie.in=" + DEFAULT_MISE_EN_SERVICE_DEFINITVIE + "," + UPDATED_MISE_EN_SERVICE_DEFINITVIE,
            "miseEnServiceDefinitvie.in=" + UPDATED_MISE_EN_SERVICE_DEFINITVIE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMiseEnServiceDefinitvieIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where miseEnServiceDefinitvie is not null
        defaultReportInterventionListFiltering("miseEnServiceDefinitvie.specified=true", "miseEnServiceDefinitvie.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMiseEnServicePartielleIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where miseEnServicePartielle equals to
        defaultReportInterventionListFiltering(
            "miseEnServicePartielle.equals=" + DEFAULT_MISE_EN_SERVICE_PARTIELLE,
            "miseEnServicePartielle.equals=" + UPDATED_MISE_EN_SERVICE_PARTIELLE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMiseEnServicePartielleIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where miseEnServicePartielle in
        defaultReportInterventionListFiltering(
            "miseEnServicePartielle.in=" + DEFAULT_MISE_EN_SERVICE_PARTIELLE + "," + UPDATED_MISE_EN_SERVICE_PARTIELLE,
            "miseEnServicePartielle.in=" + UPDATED_MISE_EN_SERVICE_PARTIELLE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMiseEnServicePartielleIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where miseEnServicePartielle is not null
        defaultReportInterventionListFiltering("miseEnServicePartielle.specified=true", "miseEnServicePartielle.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMaintenancePreventiveIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where maintenancePreventive equals to
        defaultReportInterventionListFiltering(
            "maintenancePreventive.equals=" + DEFAULT_MAINTENANCE_PREVENTIVE,
            "maintenancePreventive.equals=" + UPDATED_MAINTENANCE_PREVENTIVE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMaintenancePreventiveIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where maintenancePreventive in
        defaultReportInterventionListFiltering(
            "maintenancePreventive.in=" + DEFAULT_MAINTENANCE_PREVENTIVE + "," + UPDATED_MAINTENANCE_PREVENTIVE,
            "maintenancePreventive.in=" + UPDATED_MAINTENANCE_PREVENTIVE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMaintenancePreventiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where maintenancePreventive is not null
        defaultReportInterventionListFiltering("maintenancePreventive.specified=true", "maintenancePreventive.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMaintenanceCorrectiveIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where maintenanceCorrective equals to
        defaultReportInterventionListFiltering(
            "maintenanceCorrective.equals=" + DEFAULT_MAINTENANCE_CORRECTIVE,
            "maintenanceCorrective.equals=" + UPDATED_MAINTENANCE_CORRECTIVE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMaintenanceCorrectiveIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where maintenanceCorrective in
        defaultReportInterventionListFiltering(
            "maintenanceCorrective.in=" + DEFAULT_MAINTENANCE_CORRECTIVE + "," + UPDATED_MAINTENANCE_CORRECTIVE,
            "maintenanceCorrective.in=" + UPDATED_MAINTENANCE_CORRECTIVE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByMaintenanceCorrectiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where maintenanceCorrective is not null
        defaultReportInterventionListFiltering("maintenanceCorrective.specified=true", "maintenanceCorrective.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBybTIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bT equals to
        defaultReportInterventionListFiltering("bT.equals=" + DEFAULT_B_T, "bT.equals=" + UPDATED_B_T);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBybTIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bT in
        defaultReportInterventionListFiltering("bT.in=" + DEFAULT_B_T + "," + UPDATED_B_T, "bT.in=" + UPDATED_B_T);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBybTIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bT is not null
        defaultReportInterventionListFiltering("bT.specified=true", "bT.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBybTContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bT contains
        defaultReportInterventionListFiltering("bT.contains=" + DEFAULT_B_T, "bT.contains=" + UPDATED_B_T);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsBybTNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bT does not contain
        defaultReportInterventionListFiltering("bT.doesNotContain=" + UPDATED_B_T, "bT.doesNotContain=" + DEFAULT_B_T);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAnomalieSignaleeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where anomalieSignalee equals to
        defaultReportInterventionListFiltering(
            "anomalieSignalee.equals=" + DEFAULT_ANOMALIE_SIGNALEE,
            "anomalieSignalee.equals=" + UPDATED_ANOMALIE_SIGNALEE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAnomalieSignaleeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where anomalieSignalee in
        defaultReportInterventionListFiltering(
            "anomalieSignalee.in=" + DEFAULT_ANOMALIE_SIGNALEE + "," + UPDATED_ANOMALIE_SIGNALEE,
            "anomalieSignalee.in=" + UPDATED_ANOMALIE_SIGNALEE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAnomalieSignaleeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where anomalieSignalee is not null
        defaultReportInterventionListFiltering("anomalieSignalee.specified=true", "anomalieSignalee.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAnomalieSignaleeContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where anomalieSignalee contains
        defaultReportInterventionListFiltering(
            "anomalieSignalee.contains=" + DEFAULT_ANOMALIE_SIGNALEE,
            "anomalieSignalee.contains=" + UPDATED_ANOMALIE_SIGNALEE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAnomalieSignaleeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where anomalieSignalee does not contain
        defaultReportInterventionListFiltering(
            "anomalieSignalee.doesNotContain=" + UPDATED_ANOMALIE_SIGNALEE,
            "anomalieSignalee.doesNotContain=" + DEFAULT_ANOMALIE_SIGNALEE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterventionDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interventionDate equals to
        defaultReportInterventionListFiltering(
            "interventionDate.equals=" + DEFAULT_INTERVENTION_DATE,
            "interventionDate.equals=" + UPDATED_INTERVENTION_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterventionDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interventionDate in
        defaultReportInterventionListFiltering(
            "interventionDate.in=" + DEFAULT_INTERVENTION_DATE + "," + UPDATED_INTERVENTION_DATE,
            "interventionDate.in=" + UPDATED_INTERVENTION_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterventionDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interventionDate is not null
        defaultReportInterventionListFiltering("interventionDate.specified=true", "interventionDate.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterventionStartDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interventionStartDate equals to
        defaultReportInterventionListFiltering(
            "interventionStartDate.equals=" + DEFAULT_INTERVENTION_START_DATE,
            "interventionStartDate.equals=" + UPDATED_INTERVENTION_START_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterventionStartDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interventionStartDate in
        defaultReportInterventionListFiltering(
            "interventionStartDate.in=" + DEFAULT_INTERVENTION_START_DATE + "," + UPDATED_INTERVENTION_START_DATE,
            "interventionStartDate.in=" + UPDATED_INTERVENTION_START_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInterventionStartDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where interventionStartDate is not null
        defaultReportInterventionListFiltering("interventionStartDate.specified=true", "interventionStartDate.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByRemiseServiceDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where remiseServiceDate equals to
        defaultReportInterventionListFiltering(
            "remiseServiceDate.equals=" + DEFAULT_REMISE_SERVICE_DATE,
            "remiseServiceDate.equals=" + UPDATED_REMISE_SERVICE_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByRemiseServiceDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where remiseServiceDate in
        defaultReportInterventionListFiltering(
            "remiseServiceDate.in=" + DEFAULT_REMISE_SERVICE_DATE + "," + UPDATED_REMISE_SERVICE_DATE,
            "remiseServiceDate.in=" + UPDATED_REMISE_SERVICE_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByRemiseServiceDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where remiseServiceDate is not null
        defaultReportInterventionListFiltering("remiseServiceDate.specified=true", "remiseServiceDate.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where endDate equals to
        defaultReportInterventionListFiltering("endDate.equals=" + DEFAULT_END_DATE, "endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where endDate in
        defaultReportInterventionListFiltering("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE, "endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where endDate is not null
        defaultReportInterventionListFiltering("endDate.specified=true", "endDate.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCauseExterieurInstallationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where causeExterieurInstallation equals to
        defaultReportInterventionListFiltering(
            "causeExterieurInstallation.equals=" + DEFAULT_CAUSE_EXTERIEUR_INSTALLATION,
            "causeExterieurInstallation.equals=" + UPDATED_CAUSE_EXTERIEUR_INSTALLATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCauseExterieurInstallationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where causeExterieurInstallation in
        defaultReportInterventionListFiltering(
            "causeExterieurInstallation.in=" + DEFAULT_CAUSE_EXTERIEUR_INSTALLATION + "," + UPDATED_CAUSE_EXTERIEUR_INSTALLATION,
            "causeExterieurInstallation.in=" + UPDATED_CAUSE_EXTERIEUR_INSTALLATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCauseExterieurInstallationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where causeExterieurInstallation is not null
        defaultReportInterventionListFiltering("causeExterieurInstallation.specified=true", "causeExterieurInstallation.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationFonctionnelleApresInerventionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationFonctionnelleApresInervention equals to
        defaultReportInterventionListFiltering(
            "installationFonctionnelleApresInervention.equals=" + DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION,
            "installationFonctionnelleApresInervention.equals=" + UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationFonctionnelleApresInerventionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationFonctionnelleApresInervention in
        defaultReportInterventionListFiltering(
            "installationFonctionnelleApresInervention.in=" +
            DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION +
            "," +
            UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION,
            "installationFonctionnelleApresInervention.in=" + UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByInstallationFonctionnelleApresInerventionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where installationFonctionnelleApresInervention is not null
        defaultReportInterventionListFiltering(
            "installationFonctionnelleApresInervention.specified=true",
            "installationFonctionnelleApresInervention.specified=false"
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAutreInterventionsAPrevoirIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where autreInterventionsAPrevoir equals to
        defaultReportInterventionListFiltering(
            "autreInterventionsAPrevoir.equals=" + DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR,
            "autreInterventionsAPrevoir.equals=" + UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAutreInterventionsAPrevoirIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where autreInterventionsAPrevoir in
        defaultReportInterventionListFiltering(
            "autreInterventionsAPrevoir.in=" + DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR + "," + UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR,
            "autreInterventionsAPrevoir.in=" + UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAutreInterventionsAPrevoirIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where autreInterventionsAPrevoir is not null
        defaultReportInterventionListFiltering("autreInterventionsAPrevoir.specified=true", "autreInterventionsAPrevoir.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAutreInterventionsAPrevoirContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where autreInterventionsAPrevoir contains
        defaultReportInterventionListFiltering(
            "autreInterventionsAPrevoir.contains=" + DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR,
            "autreInterventionsAPrevoir.contains=" + UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByAutreInterventionsAPrevoirNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where autreInterventionsAPrevoir does not contain
        defaultReportInterventionListFiltering(
            "autreInterventionsAPrevoir.doesNotContain=" + UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR,
            "autreInterventionsAPrevoir.doesNotContain=" + DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByClientApreciationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where clientApreciation equals to
        defaultReportInterventionListFiltering(
            "clientApreciation.equals=" + DEFAULT_CLIENT_APRECIATION,
            "clientApreciation.equals=" + UPDATED_CLIENT_APRECIATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByClientApreciationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where clientApreciation in
        defaultReportInterventionListFiltering(
            "clientApreciation.in=" + DEFAULT_CLIENT_APRECIATION + "," + UPDATED_CLIENT_APRECIATION,
            "clientApreciation.in=" + UPDATED_CLIENT_APRECIATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByClientApreciationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where clientApreciation is not null
        defaultReportInterventionListFiltering("clientApreciation.specified=true", "clientApreciation.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByClientApreciationContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where clientApreciation contains
        defaultReportInterventionListFiltering(
            "clientApreciation.contains=" + DEFAULT_CLIENT_APRECIATION,
            "clientApreciation.contains=" + UPDATED_CLIENT_APRECIATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByClientApreciationNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where clientApreciation does not contain
        defaultReportInterventionListFiltering(
            "clientApreciation.doesNotContain=" + UPDATED_CLIENT_APRECIATION,
            "clientApreciation.doesNotContain=" + DEFAULT_CLIENT_APRECIATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByRespectDelaisIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where respectDelais equals to
        defaultReportInterventionListFiltering(
            "respectDelais.equals=" + DEFAULT_RESPECT_DELAIS,
            "respectDelais.equals=" + UPDATED_RESPECT_DELAIS
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByRespectDelaisIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where respectDelais in
        defaultReportInterventionListFiltering(
            "respectDelais.in=" + DEFAULT_RESPECT_DELAIS + "," + UPDATED_RESPECT_DELAIS,
            "respectDelais.in=" + UPDATED_RESPECT_DELAIS
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByRespectDelaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where respectDelais is not null
        defaultReportInterventionListFiltering("respectDelais.specified=true", "respectDelais.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByQualiteInterventionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where qualiteIntervention equals to
        defaultReportInterventionListFiltering(
            "qualiteIntervention.equals=" + DEFAULT_QUALITE_INTERVENTION,
            "qualiteIntervention.equals=" + UPDATED_QUALITE_INTERVENTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByQualiteInterventionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where qualiteIntervention in
        defaultReportInterventionListFiltering(
            "qualiteIntervention.in=" + DEFAULT_QUALITE_INTERVENTION + "," + UPDATED_QUALITE_INTERVENTION,
            "qualiteIntervention.in=" + UPDATED_QUALITE_INTERVENTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByQualiteInterventionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where qualiteIntervention is not null
        defaultReportInterventionListFiltering("qualiteIntervention.specified=true", "qualiteIntervention.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByQualiteDevoirConseilIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where qualiteDevoirConseil equals to
        defaultReportInterventionListFiltering(
            "qualiteDevoirConseil.equals=" + DEFAULT_QUALITE_DEVOIR_CONSEIL,
            "qualiteDevoirConseil.equals=" + UPDATED_QUALITE_DEVOIR_CONSEIL
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByQualiteDevoirConseilIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where qualiteDevoirConseil in
        defaultReportInterventionListFiltering(
            "qualiteDevoirConseil.in=" + DEFAULT_QUALITE_DEVOIR_CONSEIL + "," + UPDATED_QUALITE_DEVOIR_CONSEIL,
            "qualiteDevoirConseil.in=" + UPDATED_QUALITE_DEVOIR_CONSEIL
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByQualiteDevoirConseilIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where qualiteDevoirConseil is not null
        defaultReportInterventionListFiltering("qualiteDevoirConseil.specified=true", "qualiteDevoirConseil.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByPrestationsAcheveesIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where prestationsAchevees equals to
        defaultReportInterventionListFiltering(
            "prestationsAchevees.equals=" + DEFAULT_PRESTATIONS_ACHEVEES,
            "prestationsAchevees.equals=" + UPDATED_PRESTATIONS_ACHEVEES
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByPrestationsAcheveesIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where prestationsAchevees in
        defaultReportInterventionListFiltering(
            "prestationsAchevees.in=" + DEFAULT_PRESTATIONS_ACHEVEES + "," + UPDATED_PRESTATIONS_ACHEVEES,
            "prestationsAchevees.in=" + UPDATED_PRESTATIONS_ACHEVEES
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByPrestationsAcheveesIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where prestationsAchevees is not null
        defaultReportInterventionListFiltering("prestationsAchevees.specified=true", "prestationsAchevees.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByDevisComplentaireIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where devisComplentaire equals to
        defaultReportInterventionListFiltering(
            "devisComplentaire.equals=" + DEFAULT_DEVIS_COMPLENTAIRE,
            "devisComplentaire.equals=" + UPDATED_DEVIS_COMPLENTAIRE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByDevisComplentaireIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where devisComplentaire in
        defaultReportInterventionListFiltering(
            "devisComplentaire.in=" + DEFAULT_DEVIS_COMPLENTAIRE + "," + UPDATED_DEVIS_COMPLENTAIRE,
            "devisComplentaire.in=" + UPDATED_DEVIS_COMPLENTAIRE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByDevisComplentaireIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where devisComplentaire is not null
        defaultReportInterventionListFiltering("devisComplentaire.specified=true", "devisComplentaire.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTechnicienNameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where technicienName equals to
        defaultReportInterventionListFiltering(
            "technicienName.equals=" + DEFAULT_TECHNICIEN_NAME,
            "technicienName.equals=" + UPDATED_TECHNICIEN_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTechnicienNameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where technicienName in
        defaultReportInterventionListFiltering(
            "technicienName.in=" + DEFAULT_TECHNICIEN_NAME + "," + UPDATED_TECHNICIEN_NAME,
            "technicienName.in=" + UPDATED_TECHNICIEN_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTechnicienNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where technicienName is not null
        defaultReportInterventionListFiltering("technicienName.specified=true", "technicienName.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTechnicienNameContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where technicienName contains
        defaultReportInterventionListFiltering(
            "technicienName.contains=" + DEFAULT_TECHNICIEN_NAME,
            "technicienName.contains=" + UPDATED_TECHNICIEN_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByTechnicienNameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where technicienName does not contain
        defaultReportInterventionListFiltering(
            "technicienName.doesNotContain=" + UPDATED_TECHNICIEN_NAME,
            "technicienName.doesNotContain=" + DEFAULT_TECHNICIEN_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeTechnicienIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeTechnicien equals to
        defaultReportInterventionListFiltering(
            "codeTechnicien.equals=" + DEFAULT_CODE_TECHNICIEN,
            "codeTechnicien.equals=" + UPDATED_CODE_TECHNICIEN
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeTechnicienIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeTechnicien in
        defaultReportInterventionListFiltering(
            "codeTechnicien.in=" + DEFAULT_CODE_TECHNICIEN + "," + UPDATED_CODE_TECHNICIEN,
            "codeTechnicien.in=" + UPDATED_CODE_TECHNICIEN
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeTechnicienIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeTechnicien is not null
        defaultReportInterventionListFiltering("codeTechnicien.specified=true", "codeTechnicien.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeTechnicienContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeTechnicien contains
        defaultReportInterventionListFiltering(
            "codeTechnicien.contains=" + DEFAULT_CODE_TECHNICIEN,
            "codeTechnicien.contains=" + UPDATED_CODE_TECHNICIEN
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCodeTechnicienNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where codeTechnicien does not contain
        defaultReportInterventionListFiltering(
            "codeTechnicien.doesNotContain=" + UPDATED_CODE_TECHNICIEN,
            "codeTechnicien.doesNotContain=" + DEFAULT_CODE_TECHNICIEN
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientNameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientName equals to
        defaultReportInterventionListFiltering(
            "validationClientName.equals=" + DEFAULT_VALIDATION_CLIENT_NAME,
            "validationClientName.equals=" + UPDATED_VALIDATION_CLIENT_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientNameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientName in
        defaultReportInterventionListFiltering(
            "validationClientName.in=" + DEFAULT_VALIDATION_CLIENT_NAME + "," + UPDATED_VALIDATION_CLIENT_NAME,
            "validationClientName.in=" + UPDATED_VALIDATION_CLIENT_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientName is not null
        defaultReportInterventionListFiltering("validationClientName.specified=true", "validationClientName.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientNameContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientName contains
        defaultReportInterventionListFiltering(
            "validationClientName.contains=" + DEFAULT_VALIDATION_CLIENT_NAME,
            "validationClientName.contains=" + UPDATED_VALIDATION_CLIENT_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientNameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientName does not contain
        defaultReportInterventionListFiltering(
            "validationClientName.doesNotContain=" + UPDATED_VALIDATION_CLIENT_NAME,
            "validationClientName.doesNotContain=" + DEFAULT_VALIDATION_CLIENT_NAME
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationNameFunctionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationNameFunction equals to
        defaultReportInterventionListFiltering(
            "validationNameFunction.equals=" + DEFAULT_VALIDATION_NAME_FUNCTION,
            "validationNameFunction.equals=" + UPDATED_VALIDATION_NAME_FUNCTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationNameFunctionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationNameFunction in
        defaultReportInterventionListFiltering(
            "validationNameFunction.in=" + DEFAULT_VALIDATION_NAME_FUNCTION + "," + UPDATED_VALIDATION_NAME_FUNCTION,
            "validationNameFunction.in=" + UPDATED_VALIDATION_NAME_FUNCTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationNameFunctionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationNameFunction is not null
        defaultReportInterventionListFiltering("validationNameFunction.specified=true", "validationNameFunction.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationNameFunctionContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationNameFunction contains
        defaultReportInterventionListFiltering(
            "validationNameFunction.contains=" + DEFAULT_VALIDATION_NAME_FUNCTION,
            "validationNameFunction.contains=" + UPDATED_VALIDATION_NAME_FUNCTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationNameFunctionNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationNameFunction does not contain
        defaultReportInterventionListFiltering(
            "validationNameFunction.doesNotContain=" + UPDATED_VALIDATION_NAME_FUNCTION,
            "validationNameFunction.doesNotContain=" + DEFAULT_VALIDATION_NAME_FUNCTION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate equals to
        defaultReportInterventionListFiltering(
            "validationClientDate.equals=" + DEFAULT_VALIDATION_CLIENT_DATE,
            "validationClientDate.equals=" + UPDATED_VALIDATION_CLIENT_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate in
        defaultReportInterventionListFiltering(
            "validationClientDate.in=" + DEFAULT_VALIDATION_CLIENT_DATE + "," + UPDATED_VALIDATION_CLIENT_DATE,
            "validationClientDate.in=" + UPDATED_VALIDATION_CLIENT_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate is not null
        defaultReportInterventionListFiltering("validationClientDate.specified=true", "validationClientDate.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate is greater than or equal to
        defaultReportInterventionListFiltering(
            "validationClientDate.greaterThanOrEqual=" + DEFAULT_VALIDATION_CLIENT_DATE,
            "validationClientDate.greaterThanOrEqual=" + UPDATED_VALIDATION_CLIENT_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate is less than or equal to
        defaultReportInterventionListFiltering(
            "validationClientDate.lessThanOrEqual=" + DEFAULT_VALIDATION_CLIENT_DATE,
            "validationClientDate.lessThanOrEqual=" + SMALLER_VALIDATION_CLIENT_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate is less than
        defaultReportInterventionListFiltering(
            "validationClientDate.lessThan=" + UPDATED_VALIDATION_CLIENT_DATE,
            "validationClientDate.lessThan=" + DEFAULT_VALIDATION_CLIENT_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationClientDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validationClientDate is greater than
        defaultReportInterventionListFiltering(
            "validationClientDate.greaterThan=" + SMALLER_VALIDATION_CLIENT_DATE,
            "validationClientDate.greaterThan=" + DEFAULT_VALIDATION_CLIENT_DATE
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByBonPourCommandIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bonPourCommand equals to
        defaultReportInterventionListFiltering(
            "bonPourCommand.equals=" + DEFAULT_BON_POUR_COMMAND,
            "bonPourCommand.equals=" + UPDATED_BON_POUR_COMMAND
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByBonPourCommandIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bonPourCommand in
        defaultReportInterventionListFiltering(
            "bonPourCommand.in=" + DEFAULT_BON_POUR_COMMAND + "," + UPDATED_BON_POUR_COMMAND,
            "bonPourCommand.in=" + UPDATED_BON_POUR_COMMAND
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByBonPourCommandIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where bonPourCommand is not null
        defaultReportInterventionListFiltering("bonPourCommand.specified=true", "bonPourCommand.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where createdBy equals to
        defaultReportInterventionListFiltering("createdBy.equals=" + DEFAULT_CREATED_BY, "createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where createdBy in
        defaultReportInterventionListFiltering(
            "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY,
            "createdBy.in=" + UPDATED_CREATED_BY
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where createdBy is not null
        defaultReportInterventionListFiltering("createdBy.specified=true", "createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where createdBy contains
        defaultReportInterventionListFiltering("createdBy.contains=" + DEFAULT_CREATED_BY, "createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where createdBy does not contain
        defaultReportInterventionListFiltering(
            "createdBy.doesNotContain=" + UPDATED_CREATED_BY,
            "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validation equals to
        defaultReportInterventionListFiltering("validation.equals=" + DEFAULT_VALIDATION, "validation.equals=" + UPDATED_VALIDATION);
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validation in
        defaultReportInterventionListFiltering(
            "validation.in=" + DEFAULT_VALIDATION + "," + UPDATED_VALIDATION,
            "validation.in=" + UPDATED_VALIDATION
        );
    }

    @Test
    @Transactional
    void getAllReportInterventionListsByValidationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        // Get all the reportInterventionListList where validation is not null
        defaultReportInterventionListFiltering("validation.specified=true", "validation.specified=false");
    }

    private void defaultReportInterventionListFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultReportInterventionListShouldBeFound(shouldBeFound);
        defaultReportInterventionListShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultReportInterventionListShouldBeFound(String filter) throws Exception {
        restReportInterventionListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportInterventionList.getId().intValue())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE)))
            .andExpect(jsonPath("$.[*].codeAgence").value(hasItem(DEFAULT_CODE_AGENCE)))
            .andExpect(jsonPath("$.[*].affaireNumber").value(hasItem(DEFAULT_AFFAIRE_NUMBER)))
            .andExpect(jsonPath("$.[*].contractNumber").value(hasItem(DEFAULT_CONTRACT_NUMBER)))
            .andExpect(jsonPath("$.[*].installationAdress").value(hasItem(DEFAULT_INSTALLATION_ADRESS)))
            .andExpect(jsonPath("$.[*].interlocuteurIntervation").value(hasItem(DEFAULT_INTERLOCUTEUR_INTERVATION)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].installationSousContract").value(hasItem(DEFAULT_INSTALLATION_SOUS_CONTRACT.booleanValue())))
            .andExpect(jsonPath("$.[*].installationSousGarantie").value(hasItem(DEFAULT_INSTALLATION_SOUS_GARANTIE.booleanValue())))
            .andExpect(jsonPath("$.[*].adressFacturation").value(hasItem(DEFAULT_ADRESS_FACTURATION)))
            .andExpect(jsonPath("$.[*].interlocuteurFacturation").value(hasItem(DEFAULT_INTERLOCUTEUR_FACTURATION)))
            .andExpect(jsonPath("$.[*].conditionDePayementCheque").value(hasItem(DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].conditionPayementAutre").value(hasItem(DEFAULT_CONDITION_PAYEMENT_AUTRE.booleanValue())))
            .andExpect(jsonPath("$.[*].conditionPayementComment").value(hasItem(DEFAULT_CONDITION_PAYEMENT_COMMENT)))
            .andExpect(jsonPath("$.[*].miseEnServiceDefinitvie").value(hasItem(DEFAULT_MISE_EN_SERVICE_DEFINITVIE.booleanValue())))
            .andExpect(jsonPath("$.[*].miseEnServicePartielle").value(hasItem(DEFAULT_MISE_EN_SERVICE_PARTIELLE.booleanValue())))
            .andExpect(jsonPath("$.[*].maintenancePreventive").value(hasItem(DEFAULT_MAINTENANCE_PREVENTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].maintenanceCorrective").value(hasItem(DEFAULT_MAINTENANCE_CORRECTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].bT").value(hasItem(DEFAULT_B_T)))
            .andExpect(jsonPath("$.[*].anomalieSignalee").value(hasItem(DEFAULT_ANOMALIE_SIGNALEE)))
            .andExpect(jsonPath("$.[*].interventionDate").value(hasItem(DEFAULT_INTERVENTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].interventionStartDate").value(hasItem(DEFAULT_INTERVENTION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].remiseServiceDate").value(hasItem(DEFAULT_REMISE_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].natureIntervention").value(hasItem(DEFAULT_NATURE_INTERVENTION.toString())))
            .andExpect(jsonPath("$.[*].causeExterieurInstallation").value(hasItem(DEFAULT_CAUSE_EXTERIEUR_INSTALLATION.booleanValue())))
            .andExpect(
                jsonPath("$.[*].installationFonctionnelleApresInervention").value(
                    hasItem(DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION.booleanValue())
                )
            )
            .andExpect(jsonPath("$.[*].autreInterventionsAPrevoir").value(hasItem(DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR)))
            .andExpect(jsonPath("$.[*].clientApreciation").value(hasItem(DEFAULT_CLIENT_APRECIATION)))
            .andExpect(jsonPath("$.[*].respectDelais").value(hasItem(DEFAULT_RESPECT_DELAIS.toString())))
            .andExpect(jsonPath("$.[*].qualiteIntervention").value(hasItem(DEFAULT_QUALITE_INTERVENTION.toString())))
            .andExpect(jsonPath("$.[*].qualiteDevoirConseil").value(hasItem(DEFAULT_QUALITE_DEVOIR_CONSEIL.toString())))
            .andExpect(jsonPath("$.[*].prestationsAchevees").value(hasItem(DEFAULT_PRESTATIONS_ACHEVEES.booleanValue())))
            .andExpect(jsonPath("$.[*].devisComplentaire").value(hasItem(DEFAULT_DEVIS_COMPLENTAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].technicienName").value(hasItem(DEFAULT_TECHNICIEN_NAME)))
            .andExpect(jsonPath("$.[*].codeTechnicien").value(hasItem(DEFAULT_CODE_TECHNICIEN)))
            .andExpect(jsonPath("$.[*].validationClientName").value(hasItem(DEFAULT_VALIDATION_CLIENT_NAME)))
            .andExpect(jsonPath("$.[*].validationNameFunction").value(hasItem(DEFAULT_VALIDATION_NAME_FUNCTION)))
            .andExpect(jsonPath("$.[*].validationClientDate").value(hasItem(DEFAULT_VALIDATION_CLIENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].bonPourCommand").value(hasItem(DEFAULT_BON_POUR_COMMAND.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].validation").value(hasItem(DEFAULT_VALIDATION.booleanValue())));

        // Check, that the count call also returns 1
        restReportInterventionListMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultReportInterventionListShouldNotBeFound(String filter) throws Exception {
        restReportInterventionListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restReportInterventionListMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingReportInterventionList() throws Exception {
        // Get the reportInterventionList
        restReportInterventionListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportInterventionList() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        reportInterventionListSearchRepository.save(reportInterventionList);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());

        // Update the reportInterventionList
        ReportInterventionList updatedReportInterventionList = reportInterventionListRepository
            .findById(reportInterventionList.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedReportInterventionList are not directly saved in db
        em.detach(updatedReportInterventionList);
        updatedReportInterventionList
            .site(UPDATED_SITE)
            .codeAgence(UPDATED_CODE_AGENCE)
            .affaireNumber(UPDATED_AFFAIRE_NUMBER)
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .installationAdress(UPDATED_INSTALLATION_ADRESS)
            .interlocuteurIntervation(UPDATED_INTERLOCUTEUR_INTERVATION)
            .tel(UPDATED_TEL)
            .installationSousContract(UPDATED_INSTALLATION_SOUS_CONTRACT)
            .installationSousGarantie(UPDATED_INSTALLATION_SOUS_GARANTIE)
            .adressFacturation(UPDATED_ADRESS_FACTURATION)
            .interlocuteurFacturation(UPDATED_INTERLOCUTEUR_FACTURATION)
            .conditionDePayementCheque(UPDATED_CONDITION_DE_PAYEMENT_CHEQUE)
            .conditionPayementAutre(UPDATED_CONDITION_PAYEMENT_AUTRE)
            .conditionPayementComment(UPDATED_CONDITION_PAYEMENT_COMMENT)
            .miseEnServiceDefinitvie(UPDATED_MISE_EN_SERVICE_DEFINITVIE)
            .miseEnServicePartielle(UPDATED_MISE_EN_SERVICE_PARTIELLE)
            .maintenancePreventive(UPDATED_MAINTENANCE_PREVENTIVE)
            .maintenanceCorrective(UPDATED_MAINTENANCE_CORRECTIVE)
            .bT(UPDATED_B_T)
            .anomalieSignalee(UPDATED_ANOMALIE_SIGNALEE)
            .interventionDate(UPDATED_INTERVENTION_DATE)
            .interventionStartDate(UPDATED_INTERVENTION_START_DATE)
            .remiseServiceDate(UPDATED_REMISE_SERVICE_DATE)
            .endDate(UPDATED_END_DATE)
            .natureIntervention(UPDATED_NATURE_INTERVENTION)
            .causeExterieurInstallation(UPDATED_CAUSE_EXTERIEUR_INSTALLATION)
            .installationFonctionnelleApresInervention(UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION)
            .autreInterventionsAPrevoir(UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR)
            .clientApreciation(UPDATED_CLIENT_APRECIATION)
            .respectDelais(UPDATED_RESPECT_DELAIS)
            .qualiteIntervention(UPDATED_QUALITE_INTERVENTION)
            .qualiteDevoirConseil(UPDATED_QUALITE_DEVOIR_CONSEIL)
            .prestationsAchevees(UPDATED_PRESTATIONS_ACHEVEES)
            .devisComplentaire(UPDATED_DEVIS_COMPLENTAIRE)
            .technicienName(UPDATED_TECHNICIEN_NAME)
            .codeTechnicien(UPDATED_CODE_TECHNICIEN)
            .validationClientName(UPDATED_VALIDATION_CLIENT_NAME)
            .validationNameFunction(UPDATED_VALIDATION_NAME_FUNCTION)
            .validationClientDate(UPDATED_VALIDATION_CLIENT_DATE)
            .bonPourCommand(UPDATED_BON_POUR_COMMAND)
            .createdBy(UPDATED_CREATED_BY)
            .validation(UPDATED_VALIDATION);
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(updatedReportInterventionList);

        restReportInterventionListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportInterventionListDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reportInterventionListDTO))
            )
            .andExpect(status().isOk());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedReportInterventionListToMatchAllProperties(updatedReportInterventionList);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<ReportInterventionList> reportInterventionListSearchList = Streamable.of(
                    reportInterventionListSearchRepository.findAll()
                ).toList();
                ReportInterventionList testReportInterventionListSearch = reportInterventionListSearchList.get(searchDatabaseSizeAfter - 1);

                assertReportInterventionListAllPropertiesEquals(testReportInterventionListSearch, updatedReportInterventionList);
            });
    }

    @Test
    @Transactional
    void putNonExistingReportInterventionList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        reportInterventionList.setId(longCount.incrementAndGet());

        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportInterventionListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportInterventionListDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reportInterventionListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportInterventionList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        reportInterventionList.setId(longCount.incrementAndGet());

        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInterventionListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reportInterventionListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportInterventionList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        reportInterventionList.setId(longCount.incrementAndGet());

        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInterventionListMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reportInterventionListDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateReportInterventionListWithPatch() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reportInterventionList using partial update
        ReportInterventionList partialUpdatedReportInterventionList = new ReportInterventionList();
        partialUpdatedReportInterventionList.setId(reportInterventionList.getId());

        partialUpdatedReportInterventionList
            .codeAgence(UPDATED_CODE_AGENCE)
            .affaireNumber(UPDATED_AFFAIRE_NUMBER)
            .interlocuteurIntervation(UPDATED_INTERLOCUTEUR_INTERVATION)
            .installationSousContract(UPDATED_INSTALLATION_SOUS_CONTRACT)
            .adressFacturation(UPDATED_ADRESS_FACTURATION)
            .interlocuteurFacturation(UPDATED_INTERLOCUTEUR_FACTURATION)
            .conditionDePayementCheque(UPDATED_CONDITION_DE_PAYEMENT_CHEQUE)
            .conditionPayementAutre(UPDATED_CONDITION_PAYEMENT_AUTRE)
            .conditionPayementComment(UPDATED_CONDITION_PAYEMENT_COMMENT)
            .miseEnServicePartielle(UPDATED_MISE_EN_SERVICE_PARTIELLE)
            .bT(UPDATED_B_T)
            .anomalieSignalee(UPDATED_ANOMALIE_SIGNALEE)
            .interventionDate(UPDATED_INTERVENTION_DATE)
            .remiseServiceDate(UPDATED_REMISE_SERVICE_DATE)
            .endDate(UPDATED_END_DATE)
            .natureIntervention(UPDATED_NATURE_INTERVENTION)
            .installationFonctionnelleApresInervention(UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION)
            .autreInterventionsAPrevoir(UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR)
            .clientApreciation(UPDATED_CLIENT_APRECIATION)
            .respectDelais(UPDATED_RESPECT_DELAIS)
            .qualiteDevoirConseil(UPDATED_QUALITE_DEVOIR_CONSEIL)
            .validationNameFunction(UPDATED_VALIDATION_NAME_FUNCTION)
            .bonPourCommand(UPDATED_BON_POUR_COMMAND)
            .validation(UPDATED_VALIDATION);

        restReportInterventionListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportInterventionList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReportInterventionList))
            )
            .andExpect(status().isOk());

        // Validate the ReportInterventionList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReportInterventionListUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedReportInterventionList, reportInterventionList),
            getPersistedReportInterventionList(reportInterventionList)
        );
    }

    @Test
    @Transactional
    void fullUpdateReportInterventionListWithPatch() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reportInterventionList using partial update
        ReportInterventionList partialUpdatedReportInterventionList = new ReportInterventionList();
        partialUpdatedReportInterventionList.setId(reportInterventionList.getId());

        partialUpdatedReportInterventionList
            .site(UPDATED_SITE)
            .codeAgence(UPDATED_CODE_AGENCE)
            .affaireNumber(UPDATED_AFFAIRE_NUMBER)
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .installationAdress(UPDATED_INSTALLATION_ADRESS)
            .interlocuteurIntervation(UPDATED_INTERLOCUTEUR_INTERVATION)
            .tel(UPDATED_TEL)
            .installationSousContract(UPDATED_INSTALLATION_SOUS_CONTRACT)
            .installationSousGarantie(UPDATED_INSTALLATION_SOUS_GARANTIE)
            .adressFacturation(UPDATED_ADRESS_FACTURATION)
            .interlocuteurFacturation(UPDATED_INTERLOCUTEUR_FACTURATION)
            .conditionDePayementCheque(UPDATED_CONDITION_DE_PAYEMENT_CHEQUE)
            .conditionPayementAutre(UPDATED_CONDITION_PAYEMENT_AUTRE)
            .conditionPayementComment(UPDATED_CONDITION_PAYEMENT_COMMENT)
            .miseEnServiceDefinitvie(UPDATED_MISE_EN_SERVICE_DEFINITVIE)
            .miseEnServicePartielle(UPDATED_MISE_EN_SERVICE_PARTIELLE)
            .maintenancePreventive(UPDATED_MAINTENANCE_PREVENTIVE)
            .maintenanceCorrective(UPDATED_MAINTENANCE_CORRECTIVE)
            .bT(UPDATED_B_T)
            .anomalieSignalee(UPDATED_ANOMALIE_SIGNALEE)
            .interventionDate(UPDATED_INTERVENTION_DATE)
            .interventionStartDate(UPDATED_INTERVENTION_START_DATE)
            .remiseServiceDate(UPDATED_REMISE_SERVICE_DATE)
            .endDate(UPDATED_END_DATE)
            .natureIntervention(UPDATED_NATURE_INTERVENTION)
            .causeExterieurInstallation(UPDATED_CAUSE_EXTERIEUR_INSTALLATION)
            .installationFonctionnelleApresInervention(UPDATED_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION)
            .autreInterventionsAPrevoir(UPDATED_AUTRE_INTERVENTIONS_A_PREVOIR)
            .clientApreciation(UPDATED_CLIENT_APRECIATION)
            .respectDelais(UPDATED_RESPECT_DELAIS)
            .qualiteIntervention(UPDATED_QUALITE_INTERVENTION)
            .qualiteDevoirConseil(UPDATED_QUALITE_DEVOIR_CONSEIL)
            .prestationsAchevees(UPDATED_PRESTATIONS_ACHEVEES)
            .devisComplentaire(UPDATED_DEVIS_COMPLENTAIRE)
            .technicienName(UPDATED_TECHNICIEN_NAME)
            .codeTechnicien(UPDATED_CODE_TECHNICIEN)
            .validationClientName(UPDATED_VALIDATION_CLIENT_NAME)
            .validationNameFunction(UPDATED_VALIDATION_NAME_FUNCTION)
            .validationClientDate(UPDATED_VALIDATION_CLIENT_DATE)
            .bonPourCommand(UPDATED_BON_POUR_COMMAND)
            .createdBy(UPDATED_CREATED_BY)
            .validation(UPDATED_VALIDATION);

        restReportInterventionListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportInterventionList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReportInterventionList))
            )
            .andExpect(status().isOk());

        // Validate the ReportInterventionList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReportInterventionListUpdatableFieldsEquals(
            partialUpdatedReportInterventionList,
            getPersistedReportInterventionList(partialUpdatedReportInterventionList)
        );
    }

    @Test
    @Transactional
    void patchNonExistingReportInterventionList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        reportInterventionList.setId(longCount.incrementAndGet());

        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportInterventionListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportInterventionListDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reportInterventionListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportInterventionList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        reportInterventionList.setId(longCount.incrementAndGet());

        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInterventionListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reportInterventionListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportInterventionList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        reportInterventionList.setId(longCount.incrementAndGet());

        // Create the ReportInterventionList
        ReportInterventionListDTO reportInterventionListDTO = reportInterventionListMapper.toDto(reportInterventionList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInterventionListMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(reportInterventionListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportInterventionList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteReportInterventionList() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);
        reportInterventionListRepository.save(reportInterventionList);
        reportInterventionListSearchRepository.save(reportInterventionList);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the reportInterventionList
        restReportInterventionListMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportInterventionList.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(reportInterventionListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchReportInterventionList() throws Exception {
        // Initialize the database
        insertedReportInterventionList = reportInterventionListRepository.saveAndFlush(reportInterventionList);
        reportInterventionListSearchRepository.save(reportInterventionList);

        // Search the reportInterventionList
        restReportInterventionListMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + reportInterventionList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportInterventionList.getId().intValue())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE)))
            .andExpect(jsonPath("$.[*].codeAgence").value(hasItem(DEFAULT_CODE_AGENCE)))
            .andExpect(jsonPath("$.[*].affaireNumber").value(hasItem(DEFAULT_AFFAIRE_NUMBER)))
            .andExpect(jsonPath("$.[*].contractNumber").value(hasItem(DEFAULT_CONTRACT_NUMBER)))
            .andExpect(jsonPath("$.[*].installationAdress").value(hasItem(DEFAULT_INSTALLATION_ADRESS)))
            .andExpect(jsonPath("$.[*].interlocuteurIntervation").value(hasItem(DEFAULT_INTERLOCUTEUR_INTERVATION)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].installationSousContract").value(hasItem(DEFAULT_INSTALLATION_SOUS_CONTRACT.booleanValue())))
            .andExpect(jsonPath("$.[*].installationSousGarantie").value(hasItem(DEFAULT_INSTALLATION_SOUS_GARANTIE.booleanValue())))
            .andExpect(jsonPath("$.[*].adressFacturation").value(hasItem(DEFAULT_ADRESS_FACTURATION)))
            .andExpect(jsonPath("$.[*].interlocuteurFacturation").value(hasItem(DEFAULT_INTERLOCUTEUR_FACTURATION)))
            .andExpect(jsonPath("$.[*].conditionDePayementCheque").value(hasItem(DEFAULT_CONDITION_DE_PAYEMENT_CHEQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].conditionPayementAutre").value(hasItem(DEFAULT_CONDITION_PAYEMENT_AUTRE.booleanValue())))
            .andExpect(jsonPath("$.[*].conditionPayementComment").value(hasItem(DEFAULT_CONDITION_PAYEMENT_COMMENT)))
            .andExpect(jsonPath("$.[*].miseEnServiceDefinitvie").value(hasItem(DEFAULT_MISE_EN_SERVICE_DEFINITVIE.booleanValue())))
            .andExpect(jsonPath("$.[*].miseEnServicePartielle").value(hasItem(DEFAULT_MISE_EN_SERVICE_PARTIELLE.booleanValue())))
            .andExpect(jsonPath("$.[*].maintenancePreventive").value(hasItem(DEFAULT_MAINTENANCE_PREVENTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].maintenanceCorrective").value(hasItem(DEFAULT_MAINTENANCE_CORRECTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].bT").value(hasItem(DEFAULT_B_T)))
            .andExpect(jsonPath("$.[*].anomalieSignalee").value(hasItem(DEFAULT_ANOMALIE_SIGNALEE)))
            .andExpect(jsonPath("$.[*].interventionDate").value(hasItem(DEFAULT_INTERVENTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].interventionStartDate").value(hasItem(DEFAULT_INTERVENTION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].remiseServiceDate").value(hasItem(DEFAULT_REMISE_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].natureIntervention").value(hasItem(DEFAULT_NATURE_INTERVENTION.toString())))
            .andExpect(jsonPath("$.[*].causeExterieurInstallation").value(hasItem(DEFAULT_CAUSE_EXTERIEUR_INSTALLATION.booleanValue())))
            .andExpect(
                jsonPath("$.[*].installationFonctionnelleApresInervention").value(
                    hasItem(DEFAULT_INSTALLATION_FONCTIONNELLE_APRES_INERVENTION.booleanValue())
                )
            )
            .andExpect(jsonPath("$.[*].autreInterventionsAPrevoir").value(hasItem(DEFAULT_AUTRE_INTERVENTIONS_A_PREVOIR)))
            .andExpect(jsonPath("$.[*].clientApreciation").value(hasItem(DEFAULT_CLIENT_APRECIATION)))
            .andExpect(jsonPath("$.[*].respectDelais").value(hasItem(DEFAULT_RESPECT_DELAIS.toString())))
            .andExpect(jsonPath("$.[*].qualiteIntervention").value(hasItem(DEFAULT_QUALITE_INTERVENTION.toString())))
            .andExpect(jsonPath("$.[*].qualiteDevoirConseil").value(hasItem(DEFAULT_QUALITE_DEVOIR_CONSEIL.toString())))
            .andExpect(jsonPath("$.[*].prestationsAchevees").value(hasItem(DEFAULT_PRESTATIONS_ACHEVEES.booleanValue())))
            .andExpect(jsonPath("$.[*].devisComplentaire").value(hasItem(DEFAULT_DEVIS_COMPLENTAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].technicienName").value(hasItem(DEFAULT_TECHNICIEN_NAME)))
            .andExpect(jsonPath("$.[*].codeTechnicien").value(hasItem(DEFAULT_CODE_TECHNICIEN)))
            .andExpect(jsonPath("$.[*].validationClientName").value(hasItem(DEFAULT_VALIDATION_CLIENT_NAME)))
            .andExpect(jsonPath("$.[*].validationNameFunction").value(hasItem(DEFAULT_VALIDATION_NAME_FUNCTION)))
            .andExpect(jsonPath("$.[*].validationClientDate").value(hasItem(DEFAULT_VALIDATION_CLIENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].bonPourCommand").value(hasItem(DEFAULT_BON_POUR_COMMAND.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].validation").value(hasItem(DEFAULT_VALIDATION.booleanValue())));
    }

    protected long getRepositoryCount() {
        return reportInterventionListRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected ReportInterventionList getPersistedReportInterventionList(ReportInterventionList reportInterventionList) {
        return reportInterventionListRepository.findById(reportInterventionList.getId()).orElseThrow();
    }

    protected void assertPersistedReportInterventionListToMatchAllProperties(ReportInterventionList expectedReportInterventionList) {
        assertReportInterventionListAllPropertiesEquals(
            expectedReportInterventionList,
            getPersistedReportInterventionList(expectedReportInterventionList)
        );
    }

    protected void assertPersistedReportInterventionListToMatchUpdatableProperties(ReportInterventionList expectedReportInterventionList) {
        assertReportInterventionListAllUpdatablePropertiesEquals(
            expectedReportInterventionList,
            getPersistedReportInterventionList(expectedReportInterventionList)
        );
    }
}
