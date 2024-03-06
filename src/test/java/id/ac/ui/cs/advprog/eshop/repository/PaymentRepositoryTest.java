package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentRepositoryTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSavePayment() {
        Payment payment = new Payment("1", "voucherCode", null);
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.addPayment(payment);
        assertEquals(payment, savedPayment);

        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetPaymentStatus() {
        Payment payment = new Payment("1", "voucherCode", null);
        payment.setStatus("SUCCESS");
        when(paymentRepository.findById("1")).thenReturn(Optional.of(payment));

        Payment updatedPayment = paymentService.setStatus("1", "SUCCESS");
        assertEquals("SUCCESS", updatedPayment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment("1", "voucherCode", null);
        when(paymentRepository.findById("1")).thenReturn(Optional.of(payment));

        Optional<Payment> optionalPayment = paymentService.getPayment("1");
        assertEquals(payment, optionalPayment.orElse(null));
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("1", "voucherCode", null));
        payments.add(new Payment("2", "bankTransfer", null));
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> allPayments = paymentService.getAllPayments();
        assertEquals(2, allPayments.size());
        assertEquals(payments.get(0), allPayments.get(0));
        assertEquals(payments.get(1), allPayments.get(1));
    }
}
