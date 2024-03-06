package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPayment() {
        // Given
        Order order = new Order("orderId", OrderStatus.PENDING, "buyer");
        String method = "voucherCode";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        // When
        when(paymentRepository.addPayment(anyString(), anyString(), anyMap())).thenReturn(new Payment("paymentId", method, paymentData));
        Payment payment = paymentService.addPayment(order, method, paymentData);

        // Then
        assertNotNull(payment);
        assertEquals(order.getId(), payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
        verify(paymentRepository, times(1)).addPayment(anyString(), anyString(), anyMap());
    }

    @Test
    void testSetPaymentStatusSuccess() {
        // Given
        Payment payment = new Payment("paymentId", "method", new HashMap<>());
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        Order order = new Order("orderId", OrderStatus.PENDING, "buyer");

        // When
        when(paymentRepository.getPayment(anyString())).thenReturn(payment);
        Payment updatedPayment = paymentService.setPaymentStatus(payment.getId(), PaymentStatus.SUCCESS);

        // Then
        assertNotNull(updatedPayment);
        assertEquals(payment.getId(), updatedPayment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());
        assertEquals(OrderStatus.SUCCESS, order.getStatus());
        verify(paymentRepository, times(1)).getPayment(anyString());
    }

    @Test
    void testSetPaymentStatusRejected() {
        // Given
        Payment payment = new Payment("paymentId", "method", new HashMap<>());
        Order order = new Order("orderId", OrderStatus.PENDING, "buyer");

        // When
        when(paymentRepository.getPayment(anyString())).thenReturn(payment);
        Payment updatedPayment = paymentService.setPaymentStatus(payment.getId(), PaymentStatus.REJECTED);

        // Then
        assertNotNull(updatedPayment);
        assertEquals(payment.getId(), updatedPayment.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), updatedPayment.getStatus());
        assertEquals(OrderStatus.FAILED, order.getStatus());
        verify(paymentRepository, times(1)).getPayment(anyString());
    }

    @Test
    void testGetPayment() {
        // Given
        Payment payment = new Payment("paymentId", "method", new HashMap<>());

        // When
        when(paymentRepository.getPayment(anyString())).thenReturn(payment);
        Payment retrievedPayment = paymentService.getPayment(payment.getId());

        // Then
        assertNotNull(retrievedPayment);
        assertEquals(payment.getId(), retrievedPayment.getId());
        verify(paymentRepository, times(1)).getPayment(anyString());
    }

    @Test
    void testGetAllPayments() {
        // Given
        Payment payment1 = new Payment("paymentId1", "method", new HashMap<>());
        Payment payment2 = new Payment("paymentId2", "method", new HashMap<>());

        // When
        when(paymentRepository.getAllPayments()).thenReturn(new Payment[]{payment1, payment2});
        Iterable<Payment> payments = paymentService.getAllPayments();

        // Then
        assertNotNull(payments);
        assertTrue(payments.iterator().hasNext());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}
