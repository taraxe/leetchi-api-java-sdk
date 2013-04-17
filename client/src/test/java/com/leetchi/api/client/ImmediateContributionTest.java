package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.ImmediateContribution;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ImmediateContributionTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("ImmediateContributionTestRules.properties").getPath());
        server.startAndWait();
    }

    @AfterClass
    public static void afterClass() {
        server.stopAndWait();
    }

    @Before
    public void before() throws Exception {
        super.before();
        Leetchi.config()
                .privateKey(pemFile)
                .partnerId("MyPartnerId")
                .baseUrl(LEETCHI_STUB_URL)
                .password(PASSWORD);
    }

    @Test
    public void createContributionTest() throws Exception {
        ImmediateContribution contribution = Leetchi.create(ImmediateContribution.newImmediateContribution()
                .userId(12345L)
                .walletId(12345L)
                .amount(1200L)
                .tag("optional")
                .paymentCardId(12345L));

        assertThat(contribution.getAnswerMessage()).isEqualTo("Transaction approved");
    }

    @Test
    public void fetchContributionTest() throws Exception {
        ImmediateContribution contribution = ImmediateContribution.fetch(15L);

        assertThat(contribution.getAnswerMessage()).isEqualTo("Transaction approved");


    }

}
