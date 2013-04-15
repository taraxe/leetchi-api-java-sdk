package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.User;
import com.leetchi.api.client.model.Wallet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class WalletTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("WalletTestRules.properties").getPath());
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
    public void createWalletTest() throws Exception {
        Wallet wallet = Leetchi.create(Wallet.newWallet()
                .name("test").description("stuff")
                .ownersIds(1L, 2L)
                .contributionLimitDate(1380186358L)
                .raisingGoalAmount(12000L));

        assertThat(wallet.getName()).isEqualTo("test");
    }

    @Test
    public void fetchWalletTest() throws Exception {
        Wallet wallet = Wallet.fetch(12345L);
        assertThat(wallet.getName()).isEqualTo("test");
    }

    @Test
    public void putWalletTest() throws Exception {
        Wallet wallet = Leetchi.put(Wallet.fetch(12345L));
        assertThat(wallet.getName()).isEqualTo("test");
    }

    @Test
    public void listUserWalletsTest() throws Exception {
        List<Wallet> wallets = Wallet.fromUser(12345L);
        assertThat(wallets).hasSize(2);

        wallets = User.fetch(12345L).wallets();
        assertThat(wallets).hasSize(2);
    }

    @Test
    public void listWalletUsersTest() throws Exception {
        List<User> users = Wallet.users(12345L);
        assertThat(users).hasSize(2);

        users = Wallet.fetch(12345L).users();
        assertThat(users).hasSize(2);
    }
}
