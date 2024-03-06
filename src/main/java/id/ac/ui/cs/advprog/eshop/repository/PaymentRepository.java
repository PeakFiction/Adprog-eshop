package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {

    private Map<String, Payment> payments;

    public PaymentRepository() {
        this.payments = new HashMap<>();
    }

    public Payment addPayment(String orderId, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(orderId, method, paymentData);
        payment.setStatus("SUCCESS");
        payments.put(payment.getId(), payment);
        return payment;
    }

    public Payment getPayment(String paymentId) {
        return payments.get(paymentId);
    }

    public Iterable<Payment> getAllPayments() {
        return payments.values();
    }
}
