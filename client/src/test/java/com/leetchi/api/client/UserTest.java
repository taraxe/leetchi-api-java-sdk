package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UserTest extends LeetchiTest {

    private static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("UserTestRules.properties").getPath());
        server.startAndWait();
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
    public void createUserTest() throws Exception {
        User user = Leetchi.createUser(User.newUser()
                .firstName("Mark")
                .lastName("Zuckeberg")
                .email("mark@leetchi.com")
                .nationality("FR")
                .personType("NATURAL_PERSON")
                .tag("Custom info from app"));

        assertThat(user.getEmail()).isEqualTo("mark@leetchi.com");
    }

    @Test
    public void fetchUserTest() throws Exception {
        User user = Leetchi.fetchUser(15L);

        assertThat(user.getEmail()).isEqualTo("mark@leetchi.com");
    }

    @Test
    public void patchUserTest() throws Exception {
        User user = Leetchi.patchUser(Leetchi.fetchUser(15L));

        assertThat(user.getEmail()).isEqualTo("mark@leetchi.com");
    }

    @Test
    public void putUserTest() throws Exception {
        User user = Leetchi.putUser(Leetchi.fetchUser(15L));

        assertThat(user.getEmail()).isEqualTo("mark@leetchi.com");
    }
}
