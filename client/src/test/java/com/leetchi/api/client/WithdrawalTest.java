package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.Withdrawal;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class WithdrawalTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("WithdrawalTestRules.properties").getPath());
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
    public void createWithdrawalTest() throws Exception {
        Withdrawal withdrawal = Leetchi.create(Withdrawal.newWithdrawal()
                .userId(12345L)
                .walletId(12345L)
                .amount(1200L)
                .tag("optional")
                .beneficiaryId(12345L));

        assertThat(withdrawal.getBankAccountIBAN()).isEqualTo("FR3020041010124530725S03383");
    }

    @Test
    public void fetchWithdrawalTest() throws Exception {
        Withdrawal withdrawal = Withdrawal.fetch(15L);

        assertThat(withdrawal.getBankAccountIBAN()).isEqualTo("FR3020041010124530725S03383");
    }

}
