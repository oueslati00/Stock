package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.CheckListAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.CheckList;
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
import com.mycompany.myapp.repository.CheckListRepository;
import com.mycompany.myapp.repository.search.CheckListSearchRepository;
import com.mycompany.myapp.service.dto.CheckListDTO;
import com.mycompany.myapp.service.mapper.CheckListMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
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
 * Integration tests for the {@link CheckListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CheckListResourceIT {

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICIEN_DEF = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICIEN_DEF = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TaskStatus DEFAULT_TABLE_DETECTION_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_TABLE_DETECTION_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_TABLE_DETECTION_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_DETECTION_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_DI_DM_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_DI_DM_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_DI_DM_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_DI_DM_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_DETECTEURS_PONCTUELS_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_DETECTEURS_PONCTUELS_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_DECLENCHEUR_MANUELS_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_DECLENCHEUR_MANUELS_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_TABLE_MISE_SECURITY_INCENDIE_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_MISE_SECURITY_INCENDIE_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_ALIMENTATION_SECOURS_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_ALIMENTATION_SECOURS_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_ALIMENTATION_SECOURS_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_EQUIPEMENT_ALARME_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_EQUIPEMENT_ALARME_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPEMENT_ALARME_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_DAS_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_DAS_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_DAS_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_DAS_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_ARRET_TECHNIQUE_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_ARRET_TECHNIQUE_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_ARRET_TECHNIQUE_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_ARRET_TECHNIQUE_COMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_BAIE_PCS_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_BAIE_PCS_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_BAIE_PC_SCOMMENT = "AAAAAAAAAA";
    private static final String UPDATED_BAIE_PC_SCOMMENT = "BBBBBBBBBB";

    private static final TaskStatus DEFAULT_SUPERVISEUR_TASK_STATUS = TaskStatus.INSPECTED;
    private static final TaskStatus UPDATED_SUPERVISEUR_TASK_STATUS = TaskStatus.REPAIRED;

    private static final String DEFAULT_SUPERVISEUR_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_SUPERVISEUR_COMMENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VALIDATE = false;
    private static final Boolean UPDATED_VALIDATE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/check-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/check-lists/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CheckListRepository checkListRepository;

    @Autowired
    private CheckListMapper checkListMapper;

    @Autowired
    private CheckListSearchRepository checkListSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCheckListMockMvc;

    private CheckList checkList;

    private CheckList insertedCheckList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckList createEntity(EntityManager em) {
        CheckList checkList = new CheckList()
            .client(DEFAULT_CLIENT)
            .contractNumber(DEFAULT_CONTRACT_NUMBER)
            .adress(DEFAULT_ADRESS)
            .technicienDef(DEFAULT_TECHNICIEN_DEF)
            .date(DEFAULT_DATE)
            .tableDetectionTaskStatus(DEFAULT_TABLE_DETECTION_TASK_STATUS)
            .tableDetectionComment(DEFAULT_TABLE_DETECTION_COMMENT)
            .diDMTaskStatus(DEFAULT_DI_DM_TASK_STATUS)
            .diDMComment(DEFAULT_DI_DM_COMMENT)
            .detecteursPonctuelsTaskStatus(DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS)
            .detecteursPonctuelsComment(DEFAULT_DETECTEURS_PONCTUELS_COMMENT)
            .declencheurManuelsTaskStatus(DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS)
            .declencheurManuelsComment(DEFAULT_DECLENCHEUR_MANUELS_COMMENT)
            .tableMiseSecurityIncendieTaskStatus(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS)
            .tableMiseSecurityIncendieComment(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_COMMENT)
            .alimentationSecoursTaskStatus(DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS)
            .alimentationSecoursComment(DEFAULT_ALIMENTATION_SECOURS_COMMENT)
            .equipementAlarmeTaskStatus(DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS)
            .equipementAlarmeComment(DEFAULT_EQUIPEMENT_ALARME_COMMENT)
            .dasTaskStatus(DEFAULT_DAS_TASK_STATUS)
            .dasComment(DEFAULT_DAS_COMMENT)
            .arretTechniqueTaskStatus(DEFAULT_ARRET_TECHNIQUE_TASK_STATUS)
            .arretTechniqueComment(DEFAULT_ARRET_TECHNIQUE_COMMENT)
            .baiePcsTaskStatus(DEFAULT_BAIE_PCS_TASK_STATUS)
            .baiePCScomment(DEFAULT_BAIE_PC_SCOMMENT)
            .superviseurTaskStatus(DEFAULT_SUPERVISEUR_TASK_STATUS)
            .superviseurComment(DEFAULT_SUPERVISEUR_COMMENT)
            .validate(DEFAULT_VALIDATE)
            .createdBy(DEFAULT_CREATED_BY);
        return checkList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckList createUpdatedEntity(EntityManager em) {
        CheckList checkList = new CheckList()
            .client(UPDATED_CLIENT)
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .adress(UPDATED_ADRESS)
            .technicienDef(UPDATED_TECHNICIEN_DEF)
            .date(UPDATED_DATE)
            .tableDetectionTaskStatus(UPDATED_TABLE_DETECTION_TASK_STATUS)
            .tableDetectionComment(UPDATED_TABLE_DETECTION_COMMENT)
            .diDMTaskStatus(UPDATED_DI_DM_TASK_STATUS)
            .diDMComment(UPDATED_DI_DM_COMMENT)
            .detecteursPonctuelsTaskStatus(UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS)
            .detecteursPonctuelsComment(UPDATED_DETECTEURS_PONCTUELS_COMMENT)
            .declencheurManuelsTaskStatus(UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS)
            .declencheurManuelsComment(UPDATED_DECLENCHEUR_MANUELS_COMMENT)
            .tableMiseSecurityIncendieTaskStatus(UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS)
            .tableMiseSecurityIncendieComment(UPDATED_TABLE_MISE_SECURITY_INCENDIE_COMMENT)
            .alimentationSecoursTaskStatus(UPDATED_ALIMENTATION_SECOURS_TASK_STATUS)
            .alimentationSecoursComment(UPDATED_ALIMENTATION_SECOURS_COMMENT)
            .equipementAlarmeTaskStatus(UPDATED_EQUIPEMENT_ALARME_TASK_STATUS)
            .equipementAlarmeComment(UPDATED_EQUIPEMENT_ALARME_COMMENT)
            .dasTaskStatus(UPDATED_DAS_TASK_STATUS)
            .dasComment(UPDATED_DAS_COMMENT)
            .arretTechniqueTaskStatus(UPDATED_ARRET_TECHNIQUE_TASK_STATUS)
            .arretTechniqueComment(UPDATED_ARRET_TECHNIQUE_COMMENT)
            .baiePcsTaskStatus(UPDATED_BAIE_PCS_TASK_STATUS)
            .baiePCScomment(UPDATED_BAIE_PC_SCOMMENT)
            .superviseurTaskStatus(UPDATED_SUPERVISEUR_TASK_STATUS)
            .superviseurComment(UPDATED_SUPERVISEUR_COMMENT)
            .validate(UPDATED_VALIDATE)
            .createdBy(UPDATED_CREATED_BY);
        return checkList;
    }

    @BeforeEach
    public void initTest() {
        checkList = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCheckList != null) {
            checkListRepository.delete(insertedCheckList);
            checkListSearchRepository.delete(insertedCheckList);
            insertedCheckList = null;
        }
    }

    @Test
    @Transactional
    void createCheckList() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);
        var returnedCheckListDTO = om.readValue(
            restCheckListMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(checkListDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CheckListDTO.class
        );

        // Validate the CheckList in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCheckList = checkListMapper.toEntity(returnedCheckListDTO);
        assertCheckListUpdatableFieldsEquals(returnedCheckList, getPersistedCheckList(returnedCheckList));

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedCheckList = returnedCheckList;
    }

    @Test
    @Transactional
    void createCheckListWithExistingId() throws Exception {
        // Create the CheckList with an existing ID
        checkList.setId(1L);
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckListMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(checkListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllCheckLists() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList
        restCheckListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkList.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].contractNumber").value(hasItem(DEFAULT_CONTRACT_NUMBER)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].technicienDef").value(hasItem(DEFAULT_TECHNICIEN_DEF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].tableDetectionTaskStatus").value(hasItem(DEFAULT_TABLE_DETECTION_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].tableDetectionComment").value(hasItem(DEFAULT_TABLE_DETECTION_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].diDMTaskStatus").value(hasItem(DEFAULT_DI_DM_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].diDMComment").value(hasItem(DEFAULT_DI_DM_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].detecteursPonctuelsTaskStatus").value(hasItem(DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].detecteursPonctuelsComment").value(hasItem(DEFAULT_DETECTEURS_PONCTUELS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].declencheurManuelsTaskStatus").value(hasItem(DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].declencheurManuelsComment").value(hasItem(DEFAULT_DECLENCHEUR_MANUELS_COMMENT.toString())))
            .andExpect(
                jsonPath("$.[*].tableMiseSecurityIncendieTaskStatus").value(
                    hasItem(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS.toString())
                )
            )
            .andExpect(
                jsonPath("$.[*].tableMiseSecurityIncendieComment").value(hasItem(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_COMMENT.toString()))
            )
            .andExpect(jsonPath("$.[*].alimentationSecoursTaskStatus").value(hasItem(DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].alimentationSecoursComment").value(hasItem(DEFAULT_ALIMENTATION_SECOURS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].equipementAlarmeTaskStatus").value(hasItem(DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].equipementAlarmeComment").value(hasItem(DEFAULT_EQUIPEMENT_ALARME_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dasTaskStatus").value(hasItem(DEFAULT_DAS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dasComment").value(hasItem(DEFAULT_DAS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].arretTechniqueTaskStatus").value(hasItem(DEFAULT_ARRET_TECHNIQUE_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].arretTechniqueComment").value(hasItem(DEFAULT_ARRET_TECHNIQUE_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].baiePcsTaskStatus").value(hasItem(DEFAULT_BAIE_PCS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].baiePCScomment").value(hasItem(DEFAULT_BAIE_PC_SCOMMENT.toString())))
            .andExpect(jsonPath("$.[*].superviseurTaskStatus").value(hasItem(DEFAULT_SUPERVISEUR_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].superviseurComment").value(hasItem(DEFAULT_SUPERVISEUR_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }

    @Test
    @Transactional
    void getCheckList() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get the checkList
        restCheckListMockMvc
            .perform(get(ENTITY_API_URL_ID, checkList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checkList.getId().intValue()))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.contractNumber").value(DEFAULT_CONTRACT_NUMBER))
            .andExpect(jsonPath("$.adress").value(DEFAULT_ADRESS))
            .andExpect(jsonPath("$.technicienDef").value(DEFAULT_TECHNICIEN_DEF))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.tableDetectionTaskStatus").value(DEFAULT_TABLE_DETECTION_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.tableDetectionComment").value(DEFAULT_TABLE_DETECTION_COMMENT.toString()))
            .andExpect(jsonPath("$.diDMTaskStatus").value(DEFAULT_DI_DM_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.diDMComment").value(DEFAULT_DI_DM_COMMENT.toString()))
            .andExpect(jsonPath("$.detecteursPonctuelsTaskStatus").value(DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.detecteursPonctuelsComment").value(DEFAULT_DETECTEURS_PONCTUELS_COMMENT.toString()))
            .andExpect(jsonPath("$.declencheurManuelsTaskStatus").value(DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.declencheurManuelsComment").value(DEFAULT_DECLENCHEUR_MANUELS_COMMENT.toString()))
            .andExpect(jsonPath("$.tableMiseSecurityIncendieTaskStatus").value(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.tableMiseSecurityIncendieComment").value(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_COMMENT.toString()))
            .andExpect(jsonPath("$.alimentationSecoursTaskStatus").value(DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.alimentationSecoursComment").value(DEFAULT_ALIMENTATION_SECOURS_COMMENT.toString()))
            .andExpect(jsonPath("$.equipementAlarmeTaskStatus").value(DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.equipementAlarmeComment").value(DEFAULT_EQUIPEMENT_ALARME_COMMENT.toString()))
            .andExpect(jsonPath("$.dasTaskStatus").value(DEFAULT_DAS_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.dasComment").value(DEFAULT_DAS_COMMENT.toString()))
            .andExpect(jsonPath("$.arretTechniqueTaskStatus").value(DEFAULT_ARRET_TECHNIQUE_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.arretTechniqueComment").value(DEFAULT_ARRET_TECHNIQUE_COMMENT.toString()))
            .andExpect(jsonPath("$.baiePcsTaskStatus").value(DEFAULT_BAIE_PCS_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.baiePCScomment").value(DEFAULT_BAIE_PC_SCOMMENT.toString()))
            .andExpect(jsonPath("$.superviseurTaskStatus").value(DEFAULT_SUPERVISEUR_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.superviseurComment").value(DEFAULT_SUPERVISEUR_COMMENT.toString()))
            .andExpect(jsonPath("$.validate").value(DEFAULT_VALIDATE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }

    @Test
    @Transactional
    void getCheckListsByIdFiltering() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        Long id = checkList.getId();

        defaultCheckListFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultCheckListFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultCheckListFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCheckListsByClientIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where client equals to
        defaultCheckListFiltering("client.equals=" + DEFAULT_CLIENT, "client.equals=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    void getAllCheckListsByClientIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where client in
        defaultCheckListFiltering("client.in=" + DEFAULT_CLIENT + "," + UPDATED_CLIENT, "client.in=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    void getAllCheckListsByClientIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where client is not null
        defaultCheckListFiltering("client.specified=true", "client.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByClientContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where client contains
        defaultCheckListFiltering("client.contains=" + DEFAULT_CLIENT, "client.contains=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    void getAllCheckListsByClientNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where client does not contain
        defaultCheckListFiltering("client.doesNotContain=" + UPDATED_CLIENT, "client.doesNotContain=" + DEFAULT_CLIENT);
    }

    @Test
    @Transactional
    void getAllCheckListsByContractNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where contractNumber equals to
        defaultCheckListFiltering("contractNumber.equals=" + DEFAULT_CONTRACT_NUMBER, "contractNumber.equals=" + UPDATED_CONTRACT_NUMBER);
    }

    @Test
    @Transactional
    void getAllCheckListsByContractNumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where contractNumber in
        defaultCheckListFiltering(
            "contractNumber.in=" + DEFAULT_CONTRACT_NUMBER + "," + UPDATED_CONTRACT_NUMBER,
            "contractNumber.in=" + UPDATED_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByContractNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where contractNumber is not null
        defaultCheckListFiltering("contractNumber.specified=true", "contractNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByContractNumberContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where contractNumber contains
        defaultCheckListFiltering(
            "contractNumber.contains=" + DEFAULT_CONTRACT_NUMBER,
            "contractNumber.contains=" + UPDATED_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByContractNumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where contractNumber does not contain
        defaultCheckListFiltering(
            "contractNumber.doesNotContain=" + UPDATED_CONTRACT_NUMBER,
            "contractNumber.doesNotContain=" + DEFAULT_CONTRACT_NUMBER
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByAdressIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where adress equals to
        defaultCheckListFiltering("adress.equals=" + DEFAULT_ADRESS, "adress.equals=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    void getAllCheckListsByAdressIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where adress in
        defaultCheckListFiltering("adress.in=" + DEFAULT_ADRESS + "," + UPDATED_ADRESS, "adress.in=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    void getAllCheckListsByAdressIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where adress is not null
        defaultCheckListFiltering("adress.specified=true", "adress.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByAdressContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where adress contains
        defaultCheckListFiltering("adress.contains=" + DEFAULT_ADRESS, "adress.contains=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    void getAllCheckListsByAdressNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where adress does not contain
        defaultCheckListFiltering("adress.doesNotContain=" + UPDATED_ADRESS, "adress.doesNotContain=" + DEFAULT_ADRESS);
    }

    @Test
    @Transactional
    void getAllCheckListsByTechnicienDefIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where technicienDef equals to
        defaultCheckListFiltering("technicienDef.equals=" + DEFAULT_TECHNICIEN_DEF, "technicienDef.equals=" + UPDATED_TECHNICIEN_DEF);
    }

    @Test
    @Transactional
    void getAllCheckListsByTechnicienDefIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where technicienDef in
        defaultCheckListFiltering(
            "technicienDef.in=" + DEFAULT_TECHNICIEN_DEF + "," + UPDATED_TECHNICIEN_DEF,
            "technicienDef.in=" + UPDATED_TECHNICIEN_DEF
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByTechnicienDefIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where technicienDef is not null
        defaultCheckListFiltering("technicienDef.specified=true", "technicienDef.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByTechnicienDefContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where technicienDef contains
        defaultCheckListFiltering("technicienDef.contains=" + DEFAULT_TECHNICIEN_DEF, "technicienDef.contains=" + UPDATED_TECHNICIEN_DEF);
    }

    @Test
    @Transactional
    void getAllCheckListsByTechnicienDefNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where technicienDef does not contain
        defaultCheckListFiltering(
            "technicienDef.doesNotContain=" + UPDATED_TECHNICIEN_DEF,
            "technicienDef.doesNotContain=" + DEFAULT_TECHNICIEN_DEF
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where date equals to
        defaultCheckListFiltering("date.equals=" + DEFAULT_DATE, "date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllCheckListsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where date in
        defaultCheckListFiltering("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE, "date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllCheckListsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where date is not null
        defaultCheckListFiltering("date.specified=true", "date.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByTableDetectionTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where tableDetectionTaskStatus equals to
        defaultCheckListFiltering(
            "tableDetectionTaskStatus.equals=" + DEFAULT_TABLE_DETECTION_TASK_STATUS,
            "tableDetectionTaskStatus.equals=" + UPDATED_TABLE_DETECTION_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByTableDetectionTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where tableDetectionTaskStatus in
        defaultCheckListFiltering(
            "tableDetectionTaskStatus.in=" + DEFAULT_TABLE_DETECTION_TASK_STATUS + "," + UPDATED_TABLE_DETECTION_TASK_STATUS,
            "tableDetectionTaskStatus.in=" + UPDATED_TABLE_DETECTION_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByTableDetectionTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where tableDetectionTaskStatus is not null
        defaultCheckListFiltering("tableDetectionTaskStatus.specified=true", "tableDetectionTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByDiDMTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where diDMTaskStatus equals to
        defaultCheckListFiltering(
            "diDMTaskStatus.equals=" + DEFAULT_DI_DM_TASK_STATUS,
            "diDMTaskStatus.equals=" + UPDATED_DI_DM_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDiDMTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where diDMTaskStatus in
        defaultCheckListFiltering(
            "diDMTaskStatus.in=" + DEFAULT_DI_DM_TASK_STATUS + "," + UPDATED_DI_DM_TASK_STATUS,
            "diDMTaskStatus.in=" + UPDATED_DI_DM_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDiDMTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where diDMTaskStatus is not null
        defaultCheckListFiltering("diDMTaskStatus.specified=true", "diDMTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByDetecteursPonctuelsTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where detecteursPonctuelsTaskStatus equals to
        defaultCheckListFiltering(
            "detecteursPonctuelsTaskStatus.equals=" + DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS,
            "detecteursPonctuelsTaskStatus.equals=" + UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDetecteursPonctuelsTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where detecteursPonctuelsTaskStatus in
        defaultCheckListFiltering(
            "detecteursPonctuelsTaskStatus.in=" + DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS + "," + UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS,
            "detecteursPonctuelsTaskStatus.in=" + UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDetecteursPonctuelsTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where detecteursPonctuelsTaskStatus is not null
        defaultCheckListFiltering("detecteursPonctuelsTaskStatus.specified=true", "detecteursPonctuelsTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByDeclencheurManuelsTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where declencheurManuelsTaskStatus equals to
        defaultCheckListFiltering(
            "declencheurManuelsTaskStatus.equals=" + DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS,
            "declencheurManuelsTaskStatus.equals=" + UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDeclencheurManuelsTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where declencheurManuelsTaskStatus in
        defaultCheckListFiltering(
            "declencheurManuelsTaskStatus.in=" + DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS + "," + UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS,
            "declencheurManuelsTaskStatus.in=" + UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDeclencheurManuelsTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where declencheurManuelsTaskStatus is not null
        defaultCheckListFiltering("declencheurManuelsTaskStatus.specified=true", "declencheurManuelsTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByTableMiseSecurityIncendieTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where tableMiseSecurityIncendieTaskStatus equals to
        defaultCheckListFiltering(
            "tableMiseSecurityIncendieTaskStatus.equals=" + DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS,
            "tableMiseSecurityIncendieTaskStatus.equals=" + UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByTableMiseSecurityIncendieTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where tableMiseSecurityIncendieTaskStatus in
        defaultCheckListFiltering(
            "tableMiseSecurityIncendieTaskStatus.in=" +
            DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS +
            "," +
            UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS,
            "tableMiseSecurityIncendieTaskStatus.in=" + UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByTableMiseSecurityIncendieTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where tableMiseSecurityIncendieTaskStatus is not null
        defaultCheckListFiltering(
            "tableMiseSecurityIncendieTaskStatus.specified=true",
            "tableMiseSecurityIncendieTaskStatus.specified=false"
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByAlimentationSecoursTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where alimentationSecoursTaskStatus equals to
        defaultCheckListFiltering(
            "alimentationSecoursTaskStatus.equals=" + DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS,
            "alimentationSecoursTaskStatus.equals=" + UPDATED_ALIMENTATION_SECOURS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByAlimentationSecoursTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where alimentationSecoursTaskStatus in
        defaultCheckListFiltering(
            "alimentationSecoursTaskStatus.in=" + DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS + "," + UPDATED_ALIMENTATION_SECOURS_TASK_STATUS,
            "alimentationSecoursTaskStatus.in=" + UPDATED_ALIMENTATION_SECOURS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByAlimentationSecoursTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where alimentationSecoursTaskStatus is not null
        defaultCheckListFiltering("alimentationSecoursTaskStatus.specified=true", "alimentationSecoursTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByEquipementAlarmeTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where equipementAlarmeTaskStatus equals to
        defaultCheckListFiltering(
            "equipementAlarmeTaskStatus.equals=" + DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS,
            "equipementAlarmeTaskStatus.equals=" + UPDATED_EQUIPEMENT_ALARME_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByEquipementAlarmeTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where equipementAlarmeTaskStatus in
        defaultCheckListFiltering(
            "equipementAlarmeTaskStatus.in=" + DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS + "," + UPDATED_EQUIPEMENT_ALARME_TASK_STATUS,
            "equipementAlarmeTaskStatus.in=" + UPDATED_EQUIPEMENT_ALARME_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByEquipementAlarmeTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where equipementAlarmeTaskStatus is not null
        defaultCheckListFiltering("equipementAlarmeTaskStatus.specified=true", "equipementAlarmeTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByDasTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where dasTaskStatus equals to
        defaultCheckListFiltering("dasTaskStatus.equals=" + DEFAULT_DAS_TASK_STATUS, "dasTaskStatus.equals=" + UPDATED_DAS_TASK_STATUS);
    }

    @Test
    @Transactional
    void getAllCheckListsByDasTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where dasTaskStatus in
        defaultCheckListFiltering(
            "dasTaskStatus.in=" + DEFAULT_DAS_TASK_STATUS + "," + UPDATED_DAS_TASK_STATUS,
            "dasTaskStatus.in=" + UPDATED_DAS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByDasTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where dasTaskStatus is not null
        defaultCheckListFiltering("dasTaskStatus.specified=true", "dasTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByArretTechniqueTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where arretTechniqueTaskStatus equals to
        defaultCheckListFiltering(
            "arretTechniqueTaskStatus.equals=" + DEFAULT_ARRET_TECHNIQUE_TASK_STATUS,
            "arretTechniqueTaskStatus.equals=" + UPDATED_ARRET_TECHNIQUE_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByArretTechniqueTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where arretTechniqueTaskStatus in
        defaultCheckListFiltering(
            "arretTechniqueTaskStatus.in=" + DEFAULT_ARRET_TECHNIQUE_TASK_STATUS + "," + UPDATED_ARRET_TECHNIQUE_TASK_STATUS,
            "arretTechniqueTaskStatus.in=" + UPDATED_ARRET_TECHNIQUE_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByArretTechniqueTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where arretTechniqueTaskStatus is not null
        defaultCheckListFiltering("arretTechniqueTaskStatus.specified=true", "arretTechniqueTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByBaiePcsTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where baiePcsTaskStatus equals to
        defaultCheckListFiltering(
            "baiePcsTaskStatus.equals=" + DEFAULT_BAIE_PCS_TASK_STATUS,
            "baiePcsTaskStatus.equals=" + UPDATED_BAIE_PCS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByBaiePcsTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where baiePcsTaskStatus in
        defaultCheckListFiltering(
            "baiePcsTaskStatus.in=" + DEFAULT_BAIE_PCS_TASK_STATUS + "," + UPDATED_BAIE_PCS_TASK_STATUS,
            "baiePcsTaskStatus.in=" + UPDATED_BAIE_PCS_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsByBaiePcsTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where baiePcsTaskStatus is not null
        defaultCheckListFiltering("baiePcsTaskStatus.specified=true", "baiePcsTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsBySuperviseurTaskStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where superviseurTaskStatus equals to
        defaultCheckListFiltering(
            "superviseurTaskStatus.equals=" + DEFAULT_SUPERVISEUR_TASK_STATUS,
            "superviseurTaskStatus.equals=" + UPDATED_SUPERVISEUR_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsBySuperviseurTaskStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where superviseurTaskStatus in
        defaultCheckListFiltering(
            "superviseurTaskStatus.in=" + DEFAULT_SUPERVISEUR_TASK_STATUS + "," + UPDATED_SUPERVISEUR_TASK_STATUS,
            "superviseurTaskStatus.in=" + UPDATED_SUPERVISEUR_TASK_STATUS
        );
    }

    @Test
    @Transactional
    void getAllCheckListsBySuperviseurTaskStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where superviseurTaskStatus is not null
        defaultCheckListFiltering("superviseurTaskStatus.specified=true", "superviseurTaskStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByValidateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where validate equals to
        defaultCheckListFiltering("validate.equals=" + DEFAULT_VALIDATE, "validate.equals=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllCheckListsByValidateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where validate in
        defaultCheckListFiltering("validate.in=" + DEFAULT_VALIDATE + "," + UPDATED_VALIDATE, "validate.in=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllCheckListsByValidateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where validate is not null
        defaultCheckListFiltering("validate.specified=true", "validate.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where createdBy equals to
        defaultCheckListFiltering("createdBy.equals=" + DEFAULT_CREATED_BY, "createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllCheckListsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where createdBy in
        defaultCheckListFiltering("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY, "createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllCheckListsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where createdBy is not null
        defaultCheckListFiltering("createdBy.specified=true", "createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllCheckListsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where createdBy contains
        defaultCheckListFiltering("createdBy.contains=" + DEFAULT_CREATED_BY, "createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllCheckListsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        // Get all the checkListList where createdBy does not contain
        defaultCheckListFiltering("createdBy.doesNotContain=" + UPDATED_CREATED_BY, "createdBy.doesNotContain=" + DEFAULT_CREATED_BY);
    }

    private void defaultCheckListFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCheckListShouldBeFound(shouldBeFound);
        defaultCheckListShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCheckListShouldBeFound(String filter) throws Exception {
        restCheckListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkList.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].contractNumber").value(hasItem(DEFAULT_CONTRACT_NUMBER)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].technicienDef").value(hasItem(DEFAULT_TECHNICIEN_DEF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].tableDetectionTaskStatus").value(hasItem(DEFAULT_TABLE_DETECTION_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].tableDetectionComment").value(hasItem(DEFAULT_TABLE_DETECTION_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].diDMTaskStatus").value(hasItem(DEFAULT_DI_DM_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].diDMComment").value(hasItem(DEFAULT_DI_DM_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].detecteursPonctuelsTaskStatus").value(hasItem(DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].detecteursPonctuelsComment").value(hasItem(DEFAULT_DETECTEURS_PONCTUELS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].declencheurManuelsTaskStatus").value(hasItem(DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].declencheurManuelsComment").value(hasItem(DEFAULT_DECLENCHEUR_MANUELS_COMMENT.toString())))
            .andExpect(
                jsonPath("$.[*].tableMiseSecurityIncendieTaskStatus").value(
                    hasItem(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS.toString())
                )
            )
            .andExpect(
                jsonPath("$.[*].tableMiseSecurityIncendieComment").value(hasItem(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_COMMENT.toString()))
            )
            .andExpect(jsonPath("$.[*].alimentationSecoursTaskStatus").value(hasItem(DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].alimentationSecoursComment").value(hasItem(DEFAULT_ALIMENTATION_SECOURS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].equipementAlarmeTaskStatus").value(hasItem(DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].equipementAlarmeComment").value(hasItem(DEFAULT_EQUIPEMENT_ALARME_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dasTaskStatus").value(hasItem(DEFAULT_DAS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dasComment").value(hasItem(DEFAULT_DAS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].arretTechniqueTaskStatus").value(hasItem(DEFAULT_ARRET_TECHNIQUE_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].arretTechniqueComment").value(hasItem(DEFAULT_ARRET_TECHNIQUE_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].baiePcsTaskStatus").value(hasItem(DEFAULT_BAIE_PCS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].baiePCScomment").value(hasItem(DEFAULT_BAIE_PC_SCOMMENT.toString())))
            .andExpect(jsonPath("$.[*].superviseurTaskStatus").value(hasItem(DEFAULT_SUPERVISEUR_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].superviseurComment").value(hasItem(DEFAULT_SUPERVISEUR_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));

        // Check, that the count call also returns 1
        restCheckListMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCheckListShouldNotBeFound(String filter) throws Exception {
        restCheckListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCheckListMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCheckList() throws Exception {
        // Get the checkList
        restCheckListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCheckList() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        checkListSearchRepository.save(checkList);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());

        // Update the checkList
        CheckList updatedCheckList = checkListRepository.findById(checkList.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCheckList are not directly saved in db
        em.detach(updatedCheckList);
        updatedCheckList
            .client(UPDATED_CLIENT)
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .adress(UPDATED_ADRESS)
            .technicienDef(UPDATED_TECHNICIEN_DEF)
            .date(UPDATED_DATE)
            .tableDetectionTaskStatus(UPDATED_TABLE_DETECTION_TASK_STATUS)
            .tableDetectionComment(UPDATED_TABLE_DETECTION_COMMENT)
            .diDMTaskStatus(UPDATED_DI_DM_TASK_STATUS)
            .diDMComment(UPDATED_DI_DM_COMMENT)
            .detecteursPonctuelsTaskStatus(UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS)
            .detecteursPonctuelsComment(UPDATED_DETECTEURS_PONCTUELS_COMMENT)
            .declencheurManuelsTaskStatus(UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS)
            .declencheurManuelsComment(UPDATED_DECLENCHEUR_MANUELS_COMMENT)
            .tableMiseSecurityIncendieTaskStatus(UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS)
            .tableMiseSecurityIncendieComment(UPDATED_TABLE_MISE_SECURITY_INCENDIE_COMMENT)
            .alimentationSecoursTaskStatus(UPDATED_ALIMENTATION_SECOURS_TASK_STATUS)
            .alimentationSecoursComment(UPDATED_ALIMENTATION_SECOURS_COMMENT)
            .equipementAlarmeTaskStatus(UPDATED_EQUIPEMENT_ALARME_TASK_STATUS)
            .equipementAlarmeComment(UPDATED_EQUIPEMENT_ALARME_COMMENT)
            .dasTaskStatus(UPDATED_DAS_TASK_STATUS)
            .dasComment(UPDATED_DAS_COMMENT)
            .arretTechniqueTaskStatus(UPDATED_ARRET_TECHNIQUE_TASK_STATUS)
            .arretTechniqueComment(UPDATED_ARRET_TECHNIQUE_COMMENT)
            .baiePcsTaskStatus(UPDATED_BAIE_PCS_TASK_STATUS)
            .baiePCScomment(UPDATED_BAIE_PC_SCOMMENT)
            .superviseurTaskStatus(UPDATED_SUPERVISEUR_TASK_STATUS)
            .superviseurComment(UPDATED_SUPERVISEUR_COMMENT)
            .validate(UPDATED_VALIDATE)
            .createdBy(UPDATED_CREATED_BY);
        CheckListDTO checkListDTO = checkListMapper.toDto(updatedCheckList);

        restCheckListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkListDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(checkListDTO))
            )
            .andExpect(status().isOk());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCheckListToMatchAllProperties(updatedCheckList);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<CheckList> checkListSearchList = Streamable.of(checkListSearchRepository.findAll()).toList();
                CheckList testCheckListSearch = checkListSearchList.get(searchDatabaseSizeAfter - 1);

                assertCheckListAllPropertiesEquals(testCheckListSearch, updatedCheckList);
            });
    }

    @Test
    @Transactional
    void putNonExistingCheckList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        checkList.setId(longCount.incrementAndGet());

        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkListDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(checkListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchCheckList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        checkList.setId(longCount.incrementAndGet());

        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(checkListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCheckList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        checkList.setId(longCount.incrementAndGet());

        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckListMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(checkListDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateCheckListWithPatch() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the checkList using partial update
        CheckList partialUpdatedCheckList = new CheckList();
        partialUpdatedCheckList.setId(checkList.getId());

        partialUpdatedCheckList
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .adress(UPDATED_ADRESS)
            .technicienDef(UPDATED_TECHNICIEN_DEF)
            .date(UPDATED_DATE)
            .tableDetectionTaskStatus(UPDATED_TABLE_DETECTION_TASK_STATUS)
            .tableDetectionComment(UPDATED_TABLE_DETECTION_COMMENT)
            .diDMTaskStatus(UPDATED_DI_DM_TASK_STATUS)
            .diDMComment(UPDATED_DI_DM_COMMENT)
            .detecteursPonctuelsTaskStatus(UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS)
            .declencheurManuelsTaskStatus(UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS)
            .declencheurManuelsComment(UPDATED_DECLENCHEUR_MANUELS_COMMENT)
            .alimentationSecoursTaskStatus(UPDATED_ALIMENTATION_SECOURS_TASK_STATUS)
            .equipementAlarmeComment(UPDATED_EQUIPEMENT_ALARME_COMMENT)
            .arretTechniqueTaskStatus(UPDATED_ARRET_TECHNIQUE_TASK_STATUS)
            .baiePcsTaskStatus(UPDATED_BAIE_PCS_TASK_STATUS)
            .baiePCScomment(UPDATED_BAIE_PC_SCOMMENT)
            .superviseurTaskStatus(UPDATED_SUPERVISEUR_TASK_STATUS)
            .validate(UPDATED_VALIDATE)
            .createdBy(UPDATED_CREATED_BY);

        restCheckListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCheckList))
            )
            .andExpect(status().isOk());

        // Validate the CheckList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCheckListUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCheckList, checkList),
            getPersistedCheckList(checkList)
        );
    }

    @Test
    @Transactional
    void fullUpdateCheckListWithPatch() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the checkList using partial update
        CheckList partialUpdatedCheckList = new CheckList();
        partialUpdatedCheckList.setId(checkList.getId());

        partialUpdatedCheckList
            .client(UPDATED_CLIENT)
            .contractNumber(UPDATED_CONTRACT_NUMBER)
            .adress(UPDATED_ADRESS)
            .technicienDef(UPDATED_TECHNICIEN_DEF)
            .date(UPDATED_DATE)
            .tableDetectionTaskStatus(UPDATED_TABLE_DETECTION_TASK_STATUS)
            .tableDetectionComment(UPDATED_TABLE_DETECTION_COMMENT)
            .diDMTaskStatus(UPDATED_DI_DM_TASK_STATUS)
            .diDMComment(UPDATED_DI_DM_COMMENT)
            .detecteursPonctuelsTaskStatus(UPDATED_DETECTEURS_PONCTUELS_TASK_STATUS)
            .detecteursPonctuelsComment(UPDATED_DETECTEURS_PONCTUELS_COMMENT)
            .declencheurManuelsTaskStatus(UPDATED_DECLENCHEUR_MANUELS_TASK_STATUS)
            .declencheurManuelsComment(UPDATED_DECLENCHEUR_MANUELS_COMMENT)
            .tableMiseSecurityIncendieTaskStatus(UPDATED_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS)
            .tableMiseSecurityIncendieComment(UPDATED_TABLE_MISE_SECURITY_INCENDIE_COMMENT)
            .alimentationSecoursTaskStatus(UPDATED_ALIMENTATION_SECOURS_TASK_STATUS)
            .alimentationSecoursComment(UPDATED_ALIMENTATION_SECOURS_COMMENT)
            .equipementAlarmeTaskStatus(UPDATED_EQUIPEMENT_ALARME_TASK_STATUS)
            .equipementAlarmeComment(UPDATED_EQUIPEMENT_ALARME_COMMENT)
            .dasTaskStatus(UPDATED_DAS_TASK_STATUS)
            .dasComment(UPDATED_DAS_COMMENT)
            .arretTechniqueTaskStatus(UPDATED_ARRET_TECHNIQUE_TASK_STATUS)
            .arretTechniqueComment(UPDATED_ARRET_TECHNIQUE_COMMENT)
            .baiePcsTaskStatus(UPDATED_BAIE_PCS_TASK_STATUS)
            .baiePCScomment(UPDATED_BAIE_PC_SCOMMENT)
            .superviseurTaskStatus(UPDATED_SUPERVISEUR_TASK_STATUS)
            .superviseurComment(UPDATED_SUPERVISEUR_COMMENT)
            .validate(UPDATED_VALIDATE)
            .createdBy(UPDATED_CREATED_BY);

        restCheckListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCheckList))
            )
            .andExpect(status().isOk());

        // Validate the CheckList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCheckListUpdatableFieldsEquals(partialUpdatedCheckList, getPersistedCheckList(partialUpdatedCheckList));
    }

    @Test
    @Transactional
    void patchNonExistingCheckList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        checkList.setId(longCount.incrementAndGet());

        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, checkListDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(checkListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCheckList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        checkList.setId(longCount.incrementAndGet());

        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(checkListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCheckList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        checkList.setId(longCount.incrementAndGet());

        // Create the CheckList
        CheckListDTO checkListDTO = checkListMapper.toDto(checkList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckListMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(checkListDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteCheckList() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);
        checkListRepository.save(checkList);
        checkListSearchRepository.save(checkList);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the checkList
        restCheckListMockMvc
            .perform(delete(ENTITY_API_URL_ID, checkList.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(checkListSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchCheckList() throws Exception {
        // Initialize the database
        insertedCheckList = checkListRepository.saveAndFlush(checkList);
        checkListSearchRepository.save(checkList);

        // Search the checkList
        restCheckListMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + checkList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkList.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].contractNumber").value(hasItem(DEFAULT_CONTRACT_NUMBER)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].technicienDef").value(hasItem(DEFAULT_TECHNICIEN_DEF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].tableDetectionTaskStatus").value(hasItem(DEFAULT_TABLE_DETECTION_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].tableDetectionComment").value(hasItem(DEFAULT_TABLE_DETECTION_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].diDMTaskStatus").value(hasItem(DEFAULT_DI_DM_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].diDMComment").value(hasItem(DEFAULT_DI_DM_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].detecteursPonctuelsTaskStatus").value(hasItem(DEFAULT_DETECTEURS_PONCTUELS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].detecteursPonctuelsComment").value(hasItem(DEFAULT_DETECTEURS_PONCTUELS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].declencheurManuelsTaskStatus").value(hasItem(DEFAULT_DECLENCHEUR_MANUELS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].declencheurManuelsComment").value(hasItem(DEFAULT_DECLENCHEUR_MANUELS_COMMENT.toString())))
            .andExpect(
                jsonPath("$.[*].tableMiseSecurityIncendieTaskStatus").value(
                    hasItem(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_TASK_STATUS.toString())
                )
            )
            .andExpect(
                jsonPath("$.[*].tableMiseSecurityIncendieComment").value(hasItem(DEFAULT_TABLE_MISE_SECURITY_INCENDIE_COMMENT.toString()))
            )
            .andExpect(jsonPath("$.[*].alimentationSecoursTaskStatus").value(hasItem(DEFAULT_ALIMENTATION_SECOURS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].alimentationSecoursComment").value(hasItem(DEFAULT_ALIMENTATION_SECOURS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].equipementAlarmeTaskStatus").value(hasItem(DEFAULT_EQUIPEMENT_ALARME_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].equipementAlarmeComment").value(hasItem(DEFAULT_EQUIPEMENT_ALARME_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dasTaskStatus").value(hasItem(DEFAULT_DAS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dasComment").value(hasItem(DEFAULT_DAS_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].arretTechniqueTaskStatus").value(hasItem(DEFAULT_ARRET_TECHNIQUE_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].arretTechniqueComment").value(hasItem(DEFAULT_ARRET_TECHNIQUE_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].baiePcsTaskStatus").value(hasItem(DEFAULT_BAIE_PCS_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].baiePCScomment").value(hasItem(DEFAULT_BAIE_PC_SCOMMENT.toString())))
            .andExpect(jsonPath("$.[*].superviseurTaskStatus").value(hasItem(DEFAULT_SUPERVISEUR_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].superviseurComment").value(hasItem(DEFAULT_SUPERVISEUR_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }

    protected long getRepositoryCount() {
        return checkListRepository.count();
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

    protected CheckList getPersistedCheckList(CheckList checkList) {
        return checkListRepository.findById(checkList.getId()).orElseThrow();
    }

    protected void assertPersistedCheckListToMatchAllProperties(CheckList expectedCheckList) {
        assertCheckListAllPropertiesEquals(expectedCheckList, getPersistedCheckList(expectedCheckList));
    }

    protected void assertPersistedCheckListToMatchUpdatableProperties(CheckList expectedCheckList) {
        assertCheckListAllUpdatablePropertiesEquals(expectedCheckList, getPersistedCheckList(expectedCheckList));
    }
}
