package com.fraint.eco;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class Payment extends AppCompatActivity {
    private static final String TAG =".Payment";
    private static final String PAYKEY="AURzboinxtHe0D-9MNDubFaO_AfzxCNfFaVWwn9AVchwiT4ZiK5DFU0VKriaAJXVSWAFFfu3IKVLefkO";
    private static final int REQUEST_CODE_PAY=1;
    private static final int REQUEST_PAY_FUTURE=2;
    private static final String CONFIG_ENV= PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private static PayPalConfiguration config;
    PayPalPayment compra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button orden = (Button)findViewById(R.id.order);
        orden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPago();
            }
        });
        configpaypal();
    }

    private void configpaypal()
    {
        config= new PayPalConfiguration().environment(CONFIG_ENV).clientId(PAYKEY).merchantName("Paypal Login");
    }
    private void GenerarPago()
    {
        Intent intent=new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        compra=new PayPalPayment(new BigDecimal(String.valueOf("0.5")), "USD", "Payment", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent pay= new Intent(this, PaymentActivity.class);
        pay.putExtra(PaymentActivity.EXTRA_PAYMENT, compra);
        pay.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startActivityForResult(pay, REQUEST_CODE_PAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_PAY)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                PaymentConfirmation confirmation= data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation!=null)
                {
                    try{
                        System.out.println(confirmation.toJSONObject().toString(4));
                        System.out.println(confirmation.getPayment().toJSONObject().toString(4));
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }else if(resultCode==Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, "PAGO CANCELADO", Toast.LENGTH_SHORT).show();
            }else if(resultCode==PaymentActivity.RESULT_EXTRAS_INVALID)
            {
                Toast.makeText(this, "ERROR AL PROCESAR EL PAGO", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode==REQUEST_PAY_FUTURE)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                PayPalAuthorization authorization=data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if(authorization!=null)
                {
                    try
                    {
                        Log.i("FuturePaymentExample", authorization.toJSONObject().toString(4));
                        String AuthCode=authorization.getAuthorizationCode();
                        Log.d("Future Example Payment", AuthCode);
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        }
    }
}
