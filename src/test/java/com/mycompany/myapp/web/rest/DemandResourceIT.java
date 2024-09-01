package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.DemandAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Demand;
import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.repository.DemandRepository;
import com.mycompany.myapp.repository.search.DemandSearchRepository;
import com.mycompany.myapp.service.DemandService;
import com.mycompany.myapp.service.dto.DemandDTO;
import com.mycompany.myapp.service.mapper.DemandMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DemandResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DemandResourceIT {

    private static final Integer DEFAULT_Q_T = 1;
    private static final Integer UPDATED_Q_T = 2;
    private static final Integer SMALLER_Q_T = 1 - 1;

    private static final String DEFAULT_DEMAND_BY = "AAAAAAAAAA";
    private static final String UPDATED_DEMAND_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DEMAND_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DEMAND_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.APPROVED;
    private static final Status UPDATED_STATUS = Status.REJECTED;

    private static final Boolean DEFAULT_VALIDATE = false;
    private static final Boolean UPDATED_VALIDATE = true;

    private static final String ENTITY_API_URL = "/api/demands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/demands/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DemandRepository demandRepository;

    @Mock
    private DemandRepository demandRepositoryMock;

    @Autowired
    private DemandMapper demandMapper;

    @Mock
    private DemandService demandServiceMock;

    @Autowired
    private DemandSearchRepository demandSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandMockMvc;

    private Demand demand;

    private Demand insertedDemand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demand createEntity(EntityManager em) {
        Demand demand = new Demand()
            .qT(DEFAULT_Q_T)
            .demandBy(DEFAULT_DEMAND_BY)
            .demandDate(DEFAULT_DEMAND_DATE)
            .status(DEFAULT_STATUS)
            .validate(DEFAULT_VALIDATE);
        return demand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demand createUpdatedEntity(EntityManager em) {
        Demand demand = new Demand()
            .qT(UPDATED_Q_T)
            .demandBy(UPDATED_DEMAND_BY)
            .demandDate(UPDATED_DEMAND_DATE)
            .status(UPDATED_STATUS)
            .validate(UPDATED_VALIDATE);
        return demand;
    }

    @BeforeEach
    public void initTest() {
        demand = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDemand != null) {
            demandRepository.delete(insertedDemand);
            demandSearchRepository.delete(insertedDemand);
            insertedDemand = null;
        }
    }

    @Test
    @Transactional
    void createDemand() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);
        var returnedDemandDTO = om.readValue(
            restDemandMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DemandDTO.class
        );

        // Validate the Demand in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDemand = demandMapper.toEntity(returnedDemandDTO);
        assertDemandUpdatableFieldsEquals(returnedDemand, getPersistedDemand(returnedDemand));

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedDemand = returnedDemand;
    }

    @Test
    @Transactional
    void createDemandWithExistingId() throws Exception {
        // Create the Demand with an existing ID
        demand.setId(1L);
        DemandDTO demandDTO = demandMapper.toDto(demand);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllDemands() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList
        restDemandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demand.getId().intValue())))
            .andExpect(jsonPath("$.[*].qT").value(hasItem(DEFAULT_Q_T)))
            .andExpect(jsonPath("$.[*].demandBy").value(hasItem(DEFAULT_DEMAND_BY)))
            .andExpect(jsonPath("$.[*].demandDate").value(hasItem(DEFAULT_DEMAND_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE.booleanValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDemandsWithEagerRelationshipsIsEnabled() throws Exception {
        when(demandServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDemandMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(demandServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDemandsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(demandServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDemandMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(demandRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getDemand() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get the demand
        restDemandMockMvc
            .perform(get(ENTITY_API_URL_ID, demand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demand.getId().intValue()))
            .andExpect(jsonPath("$.qT").value(DEFAULT_Q_T))
            .andExpect(jsonPath("$.demandBy").value(DEFAULT_DEMAND_BY))
            .andExpect(jsonPath("$.demandDate").value(DEFAULT_DEMAND_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.validate").value(DEFAULT_VALIDATE.booleanValue()));
    }

    @Test
    @Transactional
    void getDemandsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        Long id = demand.getId();

        defaultDemandFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDemandFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDemandFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT equals to
        defaultDemandFiltering("qT.equals=" + DEFAULT_Q_T, "qT.equals=" + UPDATED_Q_T);
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT in
        defaultDemandFiltering("qT.in=" + DEFAULT_Q_T + "," + UPDATED_Q_T, "qT.in=" + UPDATED_Q_T);
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT is not null
        defaultDemandFiltering("qT.specified=true", "qT.specified=false");
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT is greater than or equal to
        defaultDemandFiltering("qT.greaterThanOrEqual=" + DEFAULT_Q_T, "qT.greaterThanOrEqual=" + UPDATED_Q_T);
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT is less than or equal to
        defaultDemandFiltering("qT.lessThanOrEqual=" + DEFAULT_Q_T, "qT.lessThanOrEqual=" + SMALLER_Q_T);
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT is less than
        defaultDemandFiltering("qT.lessThan=" + UPDATED_Q_T, "qT.lessThan=" + DEFAULT_Q_T);
    }

    @Test
    @Transactional
    void getAllDemandsByqTIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where qT is greater than
        defaultDemandFiltering("qT.greaterThan=" + SMALLER_Q_T, "qT.greaterThan=" + DEFAULT_Q_T);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandByIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandBy equals to
        defaultDemandFiltering("demandBy.equals=" + DEFAULT_DEMAND_BY, "demandBy.equals=" + UPDATED_DEMAND_BY);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandByIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandBy in
        defaultDemandFiltering("demandBy.in=" + DEFAULT_DEMAND_BY + "," + UPDATED_DEMAND_BY, "demandBy.in=" + UPDATED_DEMAND_BY);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandByIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandBy is not null
        defaultDemandFiltering("demandBy.specified=true", "demandBy.specified=false");
    }

    @Test
    @Transactional
    void getAllDemandsByDemandByContainsSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandBy contains
        defaultDemandFiltering("demandBy.contains=" + DEFAULT_DEMAND_BY, "demandBy.contains=" + UPDATED_DEMAND_BY);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandByNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandBy does not contain
        defaultDemandFiltering("demandBy.doesNotContain=" + UPDATED_DEMAND_BY, "demandBy.doesNotContain=" + DEFAULT_DEMAND_BY);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandDateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandDate equals to
        defaultDemandFiltering("demandDate.equals=" + DEFAULT_DEMAND_DATE, "demandDate.equals=" + UPDATED_DEMAND_DATE);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandDateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandDate in
        defaultDemandFiltering("demandDate.in=" + DEFAULT_DEMAND_DATE + "," + UPDATED_DEMAND_DATE, "demandDate.in=" + UPDATED_DEMAND_DATE);
    }

    @Test
    @Transactional
    void getAllDemandsByDemandDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where demandDate is not null
        defaultDemandFiltering("demandDate.specified=true", "demandDate.specified=false");
    }

    @Test
    @Transactional
    void getAllDemandsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where status equals to
        defaultDemandFiltering("status.equals=" + DEFAULT_STATUS, "status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDemandsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where status in
        defaultDemandFiltering("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS, "status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDemandsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where status is not null
        defaultDemandFiltering("status.specified=true", "status.specified=false");
    }

    @Test
    @Transactional
    void getAllDemandsByValidateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where validate equals to
        defaultDemandFiltering("validate.equals=" + DEFAULT_VALIDATE, "validate.equals=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllDemandsByValidateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where validate in
        defaultDemandFiltering("validate.in=" + DEFAULT_VALIDATE + "," + UPDATED_VALIDATE, "validate.in=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllDemandsByValidateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        // Get all the demandList where validate is not null
        defaultDemandFiltering("validate.specified=true", "validate.specified=false");
    }

    @Test
    @Transactional
    void getAllDemandsByNameIsEqualToSomething() throws Exception {
        Product name;
        if (TestUtil.findAll(em, Product.class).isEmpty()) {
            demandRepository.saveAndFlush(demand);
            name = ProductResourceIT.createEntity(em);
        } else {
            name = TestUtil.findAll(em, Product.class).get(0);
        }
        em.persist(name);
        em.flush();
        demand.setName(name);
        demandRepository.saveAndFlush(demand);
        Long nameId = name.getId();
        // Get all the demandList where name equals to nameId
        defaultDemandShouldBeFound("nameId.equals=" + nameId);

        // Get all the demandList where name equals to (nameId + 1)
        defaultDemandShouldNotBeFound("nameId.equals=" + (nameId + 1));
    }

    private void defaultDemandFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDemandShouldBeFound(shouldBeFound);
        defaultDemandShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDemandShouldBeFound(String filter) throws Exception {
        restDemandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demand.getId().intValue())))
            .andExpect(jsonPath("$.[*].qT").value(hasItem(DEFAULT_Q_T)))
            .andExpect(jsonPath("$.[*].demandBy").value(hasItem(DEFAULT_DEMAND_BY)))
            .andExpect(jsonPath("$.[*].demandDate").value(hasItem(DEFAULT_DEMAND_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE.booleanValue())));

        // Check, that the count call also returns 1
        restDemandMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDemandShouldNotBeFound(String filter) throws Exception {
        restDemandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDemandMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDemand() throws Exception {
        // Get the demand
        restDemandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDemand() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandSearchRepository.save(demand);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());

        // Update the demand
        Demand updatedDemand = demandRepository.findById(demand.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDemand are not directly saved in db
        em.detach(updatedDemand);
        updatedDemand
            .qT(UPDATED_Q_T)
            .demandBy(UPDATED_DEMAND_BY)
            .demandDate(UPDATED_DEMAND_DATE)
            .status(UPDATED_STATUS)
            .validate(UPDATED_VALIDATE);
        DemandDTO demandDTO = demandMapper.toDto(updatedDemand);

        restDemandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandDTO))
            )
            .andExpect(status().isOk());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDemandToMatchAllProperties(updatedDemand);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Demand> demandSearchList = Streamable.of(demandSearchRepository.findAll()).toList();
                Demand testDemandSearch = demandSearchList.get(searchDatabaseSizeAfter - 1);

                assertDemandAllPropertiesEquals(testDemandSearch, updatedDemand);
            });
    }

    @Test
    @Transactional
    void putNonExistingDemand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        demand.setId(longCount.incrementAndGet());

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        demand.setId(longCount.incrementAndGet());

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(demandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        demand.setId(longCount.incrementAndGet());

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateDemandWithPatch() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the demand using partial update
        Demand partialUpdatedDemand = new Demand();
        partialUpdatedDemand.setId(demand.getId());

        partialUpdatedDemand.qT(UPDATED_Q_T).demandBy(UPDATED_DEMAND_BY).demandDate(UPDATED_DEMAND_DATE).validate(UPDATED_VALIDATE);

        restDemandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemand.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDemand))
            )
            .andExpect(status().isOk());

        // Validate the Demand in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDemandUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDemand, demand), getPersistedDemand(demand));
    }

    @Test
    @Transactional
    void fullUpdateDemandWithPatch() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the demand using partial update
        Demand partialUpdatedDemand = new Demand();
        partialUpdatedDemand.setId(demand.getId());

        partialUpdatedDemand
            .qT(UPDATED_Q_T)
            .demandBy(UPDATED_DEMAND_BY)
            .demandDate(UPDATED_DEMAND_DATE)
            .status(UPDATED_STATUS)
            .validate(UPDATED_VALIDATE);

        restDemandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemand.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDemand))
            )
            .andExpect(status().isOk());

        // Validate the Demand in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDemandUpdatableFieldsEquals(partialUpdatedDemand, getPersistedDemand(partialUpdatedDemand));
    }

    @Test
    @Transactional
    void patchNonExistingDemand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        demand.setId(longCount.incrementAndGet());

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(demandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        demand.setId(longCount.incrementAndGet());

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(demandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        demand.setId(longCount.incrementAndGet());

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(demandDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteDemand() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);
        demandRepository.save(demand);
        demandSearchRepository.save(demand);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the demand
        restDemandMockMvc
            .perform(delete(ENTITY_API_URL_ID, demand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(demandSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchDemand() throws Exception {
        // Initialize the database
        insertedDemand = demandRepository.saveAndFlush(demand);
        demandSearchRepository.save(demand);

        // Search the demand
        restDemandMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + demand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demand.getId().intValue())))
            .andExpect(jsonPath("$.[*].qT").value(hasItem(DEFAULT_Q_T)))
            .andExpect(jsonPath("$.[*].demandBy").value(hasItem(DEFAULT_DEMAND_BY)))
            .andExpect(jsonPath("$.[*].demandDate").value(hasItem(DEFAULT_DEMAND_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE.booleanValue())));
    }

    protected long getRepositoryCount() {
        return demandRepository.count();
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

    protected Demand getPersistedDemand(Demand demand) {
        return demandRepository.findById(demand.getId()).orElseThrow();
    }

    protected void assertPersistedDemandToMatchAllProperties(Demand expectedDemand) {
        assertDemandAllPropertiesEquals(expectedDemand, getPersistedDemand(expectedDemand));
    }

    protected void assertPersistedDemandToMatchUpdatableProperties(Demand expectedDemand) {
        assertDemandAllUpdatablePropertiesEquals(expectedDemand, getPersistedDemand(expectedDemand));
    }
}
