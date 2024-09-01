package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DemandTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Demand getDemandSample1() {
        return new Demand().id(1L).qT(1).demandBy("demandBy1");
    }

    public static Demand getDemandSample2() {
        return new Demand().id(2L).qT(2).demandBy("demandBy2");
    }

    public static Demand getDemandRandomSampleGenerator() {
        return new Demand().id(longCount.incrementAndGet()).qT(intCount.incrementAndGet()).demandBy(UUID.randomUUID().toString());
    }
}
