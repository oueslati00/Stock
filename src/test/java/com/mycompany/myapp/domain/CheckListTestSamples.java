package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CheckListTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CheckList getCheckListSample1() {
        return new CheckList()
            .id(1L)
            .client("client1")
            .contractNumber("contractNumber1")
            .adress("adress1")
            .technicienDef("technicienDef1")
            .createdBy("createdBy1");
    }

    public static CheckList getCheckListSample2() {
        return new CheckList()
            .id(2L)
            .client("client2")
            .contractNumber("contractNumber2")
            .adress("adress2")
            .technicienDef("technicienDef2")
            .createdBy("createdBy2");
    }

    public static CheckList getCheckListRandomSampleGenerator() {
        return new CheckList()
            .id(longCount.incrementAndGet())
            .client(UUID.randomUUID().toString())
            .contractNumber(UUID.randomUUID().toString())
            .adress(UUID.randomUUID().toString())
            .technicienDef(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString());
    }
}
