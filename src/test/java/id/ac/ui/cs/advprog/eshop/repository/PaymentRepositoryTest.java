package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testAddPayment() {
        // Given
        String orderId = "orderId";
        String method = "voucherCode";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        // When
        Payment payment = paymentRepository.addPayment(orderId, method, paymentData);

        // Then
        assertNotNull(payment);
        assertEquals(orderId, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testGetPayment() {
        // Given
        String orderId = "orderId";
        String method = "voucherCode";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment addedPayment = paymentRepository.addPayment(orderId, method, paymentData);

        // When
        Payment retrievedPayment = paymentRepository.getPayment(addedPayment.getId());

        // Then
        assertNotNull(retrievedPayment);
        assertEquals(addedPayment, retrievedPayment);
    }

    @Test
    void testGetAllPayments() {
        // Given
        String orderId1 = "orderId1";
        String method1 = "voucherCode";
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment1 = paymentRepository.addPayment(orderId1, method1, paymentData1);

        String orderId2 = "orderId2";
        String method2 = "bankTransfer";
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", "1234567890");

        Payment payment2 = paymentRepository.addPayment(orderId2, method2, paymentData2);

        // When
        Iterable<Payment> allPayments = paymentRepository.getAllPayments();

        // Then
        assertNotNull(allPayments);
        assertTrue(allPayments.iterator().hasNext());
        assertTrue(containsPayment(allPayments, payment1));
        assertTrue(containsPayment(allPayments, payment2));
    }

    private boolean containsPayment(Iterable<Payment> payments, Payment payment) {
        for (Payment p : payments) {
            if (p.getId().equals(payment.getId())) {
                return true;
            }
        }
        return false;
    }
}
