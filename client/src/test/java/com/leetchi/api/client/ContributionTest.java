package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.Contribution;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ContributionTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("ContributionTestRules.properties").getPath());
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
        Contribution contribution = Leetchi.create(Contribution.newContribution()
                .userId(12345L)
                .walletId(12345L)
                .amount(1200L)
                .returnURL("http://return/url")
                .tag("optional")
                .clientFeeAmount(100L)
                .templateURL("http://template/url")
                .registerMeanOfPayment(false)
                .paymentCardId(12345L)
                .paymentMethodType("cb_visa_mastercard")
                .type("Payline"));

        assertThat(contribution.getPaymentMethodType()).isEqualTo("cb_visa_mastercard");
    }

    @Test
    public void fetchContributionTest() throws Exception {
        Contribution contribution = Contribution.fetch(15L);

        assertThat(contribution.getPaymentMethodType()).isEqualTo("cb_visa_mastercard");

    }

}
