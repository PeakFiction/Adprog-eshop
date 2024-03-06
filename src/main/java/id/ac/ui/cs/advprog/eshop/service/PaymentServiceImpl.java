// PaymentServiceImpl.java
package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        payment.setOrder(order); // Set the order for the payment
        if (method.equals("voucherCode")) {
            validateVoucherCode(payment);
        } else if (method.equals("cashOnDelivery")) {
            validateCashOnDelivery(payment);
        } else if (method.equals("bankTransfer")) {
            validateBankTransfer(payment);
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }
        paymentRepository.addPayment(payment.getId(), method, paymentData);
        return payment;
    }

    private void validateVoucherCode(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP") || !voucherCode.matches("[0-9]+")) {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }

    private void validateCashOnDelivery(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }

    private void validateBankTransfer(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }

    @Override
    public void setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (status.equals("SUCCESS")) {
            Order order = payment.getOrder();
            order.setStatus(status);
        } else if (status.equals("REJECTED")) {
            Order order = payment.getOrder();
            order.setStatus("FAILED");
        }
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public Iterable<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}