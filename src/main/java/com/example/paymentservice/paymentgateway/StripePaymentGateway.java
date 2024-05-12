package com.example.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class StripePaymentGateway implements PaymentGateway{
    @Value("${stripe.key.secret}")
    private String stripeKey;
    @Override
    public String generatePaymentLink(String orderId, Long amount,String phoneNumber, String email) throws StripeException {

        Stripe.apiKey = stripeKey;

//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
//                                        .setQuantity(1L)
//                                        .build()
//                        )
//                        .build();
//
//        PaymentLink paymentLink = PaymentLink.create(params);

        Map<String, Object> productData= new HashMap<>();
        productData.put("name","iPhone");

        Map<String,Object> priceData = new HashMap<>();
        priceData.put("unit_amount",amount);
        priceData.put("currency","inr");
        priceData.put("product_data",productData);

        Price price = Price.create(priceData);

        Map<String,Object> lineItem1 = new HashMap<>();
        lineItem1.put("price",price.getId());
        lineItem1.put("quantity",1);


        //Callback URL
        Map<String,Object> redirecturl = new HashMap<>();
        redirecturl.put("url","https://www.google.com/");  // define url where you want to redirect after payment

        Map<String,Object> afterPayment = new HashMap<>();
        afterPayment.put("type","redirect");  // define type  as redirect
        afterPayment.put("redirect",redirecturl);

        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lineItem1);


        Map<String, Object> params = new HashMap<>();
        params.put("line_items", lineItems);
        params.put("after_completion", afterPayment);


        PaymentLink paymentLink = PaymentLink.create(params);



        return paymentLink.getUrl();

        //To interact with Razorpay or Stripe , what do we need? We need contract or API to interact with them.
        //go to maven repository and search for Razorpay Java client or Stripe Java client.
    }
}
