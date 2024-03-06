package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    Order order;

    @Test
    void testCreatePaymentByVoucherCodeCorrect() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "ESHOP01020304KAZ"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertPaymentSuccess(payment);
    }

    @Test
    void testCreatePaymentByVoucherCodeWrongLength() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "ESHOP01020304"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByVoucherCodeDoesNotStartWithEshop() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "WRONG01020304KAZ"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByVoucherCodeWrongAmountOfDigits() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "ESHOP0102030KAZ"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByVoucherCodeLowered() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "ESHOP01020304KAZ".toLowerCase()
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByVoucherNoVoucher() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI",
                "referenceCode", "2e785519-4fb0-40ce-b5ea-a2a22712a8e6"
        );
        assertThrows(IllegalArgumentException.class,
                () -> createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData));
    }

    @Test
    void testCreatePaymentByBankTransferCorrect() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI",
                "referenceCode", "2e785519-4fb0-40ce-b5ea-a2a22712a8e6"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData);
        assertPaymentSuccess(payment);
    }

    @Test
    void testCreatePaymentByBankTransferEmptyBankName() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI",
                "referenceCode", "2e785519-4fb0-40ce-b5ea-a2a22712a8e6"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByBankTransferNullBankName() {
        Map<String, String> paymentData = Map.of(
                "bankName", null,
                "referenceCode", "2e785519-4fb0-40ce-b5ea-a2a22712a8e6"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByBankTransferEmptyReferenceCode() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI",
                "referenceCode", ""
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByBankTransferNullReferenceCode() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI",
                "referenceCode", null
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData);
        assertPaymentRejected(payment);
    }

    @Test
    void testCreatePaymentByBankTransferNoBank() {
        Map<String, String> paymentData = Map.of(
                "referenceCode", "2e785519-4fb0-40ce-b5ea-a2a22712a8e6"
        );
        assertThrows(IllegalArgumentException.class,
                () -> createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData));
    }

    @Test
    void testCreatePaymentByBankTransferNoReferenceCode() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI"
        );
        assertThrows(IllegalArgumentException.class,
                () -> createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bankTransfer", paymentData));
    }

    @Test
    void testCreatePaymentInvalidMethodName() {
        Map<String, String> paymentData = Map.of(
                "bankName", "MANDIRI",
                "referenceCode", "2e785519-4fb0-40ce-b5ea-a2a22712a8e6"
        );
        assertThrows(IllegalArgumentException.class,
                () -> createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "bank", paymentData));
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "ESHOP01020304KAZ".toLowerCase()
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertEquals("REJECTED", payment.getStatus());
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Map<String, String> paymentData = Map.of(
                "voucherCode", "ESHOP01020304KAZ"
        );
        Payment payment = createPayment("8c4a1012-40d5-4135-a719-0be0c2793c58", "voucherCode", paymentData);
        assertThrows(IllegalArgumentException.class,
                () -> payment.setStatus("MEOW"));
    }

    private Payment createPayment(String id, String method, Map<String, String> paymentData) {
        return new Payment(id, method, paymentData);
    }

    private void assertPaymentSuccess(Payment payment) {
        assertEquals("SUCCESS", payment.getStatus());
    }

    private void assertPaymentRejected(Payment payment) {
        assertEquals("REJECTED", payment.getStatus());
    }
}
