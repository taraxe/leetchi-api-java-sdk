package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.PaymentCard;
import com.leetchi.api.client.model.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class PaymentCardTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("PaymentCardTestRules.properties").getPath());
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
    public void createPaymentCardTest() throws Exception {
        PaymentCard card = Leetchi.create(PaymentCard.newPaymentCards()
                .ownerId(12345L).tag("stuff")
                .returnUrl("http://return/url")
                .templateURL("http://template/url"));

        assertThat(card.getCardNumber()).isEqualTo("497010XXXXXX0154");
    }

    @Test
    public void fetchPaymentCardTest() throws Exception {
        PaymentCard card = PaymentCard.fetch(12345L);
        assertThat(card.getCardNumber()).isEqualTo("497010XXXXXX0154");

    }

    @Test
    public void putPaymentCardsTest() throws Exception {
        PaymentCard card = Leetchi.put(PaymentCard.fetch(12345L));
        assertThat(card.getCardNumber()).isEqualTo("497010XXXXXX0154");

    }

    @Test
    public void deletePaymentCardTest() throws Exception {
        Leetchi.delete(PaymentCard.path(PaymentCard.PATH, 12345L));
    }

    @Test
    public void listUserPaymentCardsTest() throws Exception {
        List<PaymentCard> cards = PaymentCard.fromUser(12345L);
        assertThat(cards).hasSize(2);

        cards = User.fetch(12345L).paymentCards();
        assertThat(cards).hasSize(2);
    }
}
