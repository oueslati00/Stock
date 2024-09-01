package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReportInterventionListTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReportInterventionList getReportInterventionListSample1() {
        return new ReportInterventionList()
            .id(1L)
            .site("site1")
            .codeAgence("codeAgence1")
            .affaireNumber("affaireNumber1")
            .contractNumber("contractNumber1")
            .installationAdress("installationAdress1")
            .interlocuteurIntervation("interlocuteurIntervation1")
            .tel("tel1")
            .adressFacturation("adressFacturation1")
            .interlocuteurFacturation("interlocuteurFacturation1")
            .conditionPayementComment("conditionPayementComment1")
            .bT("bT1")
            .anomalieSignalee("anomalieSignalee1")
            .autreInterventionsAPrevoir("autreInterventionsAPrevoir1")
            .clientApreciation("clientApreciation1")
            .technicienName("technicienName1")
            .codeTechnicien("codeTechnicien1")
            .validationClientName("validationClientName1")
            .validationNameFunction("validationNameFunction1")
            .createdBy("createdBy1");
    }

    public static ReportInterventionList getReportInterventionListSample2() {
        return new ReportInterventionList()
            .id(2L)
            .site("site2")
            .codeAgence("codeAgence2")
            .affaireNumber("affaireNumber2")
            .contractNumber("contractNumber2")
            .installationAdress("installationAdress2")
            .interlocuteurIntervation("interlocuteurIntervation2")
            .tel("tel2")
            .adressFacturation("adressFacturation2")
            .interlocuteurFacturation("interlocuteurFacturation2")
            .conditionPayementComment("conditionPayementComment2")
            .bT("bT2")
            .anomalieSignalee("anomalieSignalee2")
            .autreInterventionsAPrevoir("autreInterventionsAPrevoir2")
            .clientApreciation("clientApreciation2")
            .technicienName("technicienName2")
            .codeTechnicien("codeTechnicien2")
            .validationClientName("validationClientName2")
            .validationNameFunction("validationNameFunction2")
            .createdBy("createdBy2");
    }

    public static ReportInterventionList getReportInterventionListRandomSampleGenerator() {
        return new ReportInterventionList()
            .id(longCount.incrementAndGet())
            .site(UUID.randomUUID().toString())
            .codeAgence(UUID.randomUUID().toString())
            .affaireNumber(UUID.randomUUID().toString())
            .contractNumber(UUID.randomUUID().toString())
            .installationAdress(UUID.randomUUID().toString())
            .interlocuteurIntervation(UUID.randomUUID().toString())
            .tel(UUID.randomUUID().toString())
            .adressFacturation(UUID.randomUUID().toString())
            .interlocuteurFacturation(UUID.randomUUID().toString())
            .conditionPayementComment(UUID.randomUUID().toString())
            .bT(UUID.randomUUID().toString())
            .anomalieSignalee(UUID.randomUUID().toString())
            .autreInterventionsAPrevoir(UUID.randomUUID().toString())
            .clientApreciation(UUID.randomUUID().toString())
            .technicienName(UUID.randomUUID().toString())
            .codeTechnicien(UUID.randomUUID().toString())
            .validationClientName(UUID.randomUUID().toString())
            .validationNameFunction(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString());
    }
}
