package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentRepository paymentRepositoryMock;

    @BeforeEach
    void setUp() {
        paymentRepositoryMock = mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepositoryMock);
    }

    @Test
    void testAddPaymentWithValidVoucherCode() {
        // Given
        Order order = new Order("orderId", null, null, "PENDING");
        String method = "voucherCode";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        // When
        Payment payment = paymentService.addPayment(order, method, paymentData);

        // Then
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        verify(paymentRepositoryMock, times(1)).addPayment(anyString(), eq(method), eq(paymentData));
    }

    @Test
    void testAddPaymentWithInvalidVoucherCode() {
        // Given
        Order order = new Order("orderId", null, null, "PENDING");
        String method = "voucherCode";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID_CODE");

        // When
        Payment payment = paymentService.addPayment(order, method, paymentData);

        // Then
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(paymentRepositoryMock, times(1)).addPayment(anyString(), eq(method), eq(paymentData));
    }

    @Test
    void testSetStatusToSuccess() {
        // Given
        Payment payment = new Payment("paymentId", "method", new HashMap<>());
        String status = "SUCCESS";

        // When
        paymentService.setStatus(payment, status);

        // Then
        assertEquals(status, payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        // Given
        Payment payment = new Payment("paymentId", "method", new HashMap<>());
        String status = "REJECTED";

        // When
        paymentService.setStatus(payment, status);

        // Then
        assertEquals(status, payment.getStatus());
    }

    @Test
    void testSetStatusToSuccessAndOrderStatus() {
        // Given
        Order order = new Order("orderId", null, null, "PENDING");
        Payment payment = new Payment("paymentId", "method", new HashMap<>());
        payment.setOrder(order);
        String status = "SUCCESS";

        // When
        paymentService.setStatus(payment, status);

        // Then
        assertEquals(status, payment.getStatus());
        assertEquals(status, order.getStatus());
    }

    @Test
    void testSetStatusToRejectedAndOrderStatus() {
        // Given
        Order order = new Order("orderId", null, null, "PENDING");
        Payment payment = new Payment("paymentId", "method", new HashMap<>());
        payment.setOrder(order);
        String status = "REJECTED";

        // When
        paymentService.setStatus(payment, status);

        // Then
        assertEquals(status, payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testGetPayment() {
        // Given
        String paymentId = "paymentId";
        Payment expectedPayment = new Payment("paymentId", "method", new HashMap<>());
        when(paymentRepositoryMock.getPayment(paymentId)).thenReturn(expectedPayment);

        // When
        Payment actualPayment = paymentService.getPayment(paymentId);

        // Then
        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    void testGetAllPayments() {
        // Given
        Iterable<Payment> expectedPayments = mock(Iterable.class);
        when(paymentRepositoryMock.getAllPayments()).thenReturn(expectedPayments);

        // When
        Iterable<Payment> actualPayments = paymentService.getAllPayments();

        // Then
        assertEquals(expectedPayments, actualPayments);
    }
}
