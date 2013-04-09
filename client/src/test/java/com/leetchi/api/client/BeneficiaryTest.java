package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.Beneficiary;
import com.leetchi.api.client.model.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BeneficiaryTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("BeneficiaryTestRules.properties").getPath());
        server.startAndWait();
    }

    @AfterClass
    public static void afterClass(){
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
    public void createBeneficiaryTest() throws Exception {
        Beneficiary beneficiary = Leetchi.create(Beneficiary.newBeneficiary()
                .userId(15L)
                .tag("Custom info from app"))
                .bankAccountOwnerName("Zuckeberg")
                .bankAccountOwnerAddress("1 bis Cité Paradis, 75010 Paris")
                .bankAccountIBAN("FR3020041010124530725S03383")
                .bankAccountBIC("CRLYFRPP");

        assertThat(beneficiary.getId()).isEqualTo(185534L);
    }

    @Test
    public void fetchBeneficiaryTest() throws Exception {
        Beneficiary beneficiary = Beneficiary.fetch(185534L);

        assertThat(beneficiary.getId()).isEqualTo(185534L);
    }

}
