package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.Transfer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class TransferTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("TransferTestRules.properties").getPath());
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
    public void createTranserTest() throws Exception {
        Transfer transfer = Leetchi.create(Transfer.newTransfer()
                .payerWalletId(12345L)
                .beneficiaryWalletId(12345L)
                .amount(1200L)
                .clientFeeAmount(1200L)
                .tag("optional")
                .beneficiaryId(12345L)
                .payerId(12345L));

        assertThat(transfer.getBeneficiaryWalletId()).isEqualTo(196902L);
    }

    @Test
    public void fetchTransferTest() throws Exception {
        Transfer transfer = Transfer.fetch(15L);

        assertThat(transfer.getBeneficiaryWalletId()).isEqualTo(196902L);
    }

}
