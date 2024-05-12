package com.example.paymentservice.services;

import com.example.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(String orderId, Long amount, String phoneNumber, String email) throws RazorpayException, StripeException {
//        Order order = orderService.getOrderDetails(orderId);
//        int amount = order.getAmount();
//        String productName = order.getProductName();

        //the above code is commented because here we are not actually fetching the order details
        // we can call the order service to get the order details by writing the code here


//        Long amount = 10000L; //100.00

        //Genereate the payment link -> Payment Gateway will do that job

        return paymentGateway.generatePaymentLink(orderId, amount, phoneNumber, email);
    }
}
