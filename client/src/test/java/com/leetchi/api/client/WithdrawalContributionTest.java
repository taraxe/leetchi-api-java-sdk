package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.ImmediateContribution;
import com.leetchi.api.client.model.WithdrawalContribution;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class WithdrawalContributionTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("WithdrawalContributionTestRules.properties").getPath());
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
        WithdrawalContribution contribution = Leetchi.create(WithdrawalContribution.newWithdrawalContribution()
                .userId(12345L)
                .walletId(12345L)
                .tag("optional")
                .amountDeclared(1400L));

        assertThat(contribution.getGeneratedReference()).isEqualTo("C200698_U200697_example_Wire");
    }

    @Test
    public void fetchContributionTest() throws Exception {
        WithdrawalContribution contribution = WithdrawalContribution.fetch(15L);

        assertThat(contribution.getGeneratedReference()).isEqualTo("C200698_U200697_example_Wire");
    }

}
