package com.fraint.eco;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.PayUTokens;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.CreditCard;
import com.payu.sdk.model.CreditCardToken;
import com.payu.sdk.model.Currency;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.PaymentCountry;
import com.payu.sdk.model.TransactionResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class interfaz_pago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_pago);



        Button confirmar=findViewById(R.id.relizar_orden);
        confirmar.setOnClickListener(view -> {
            try {
                pago();
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (InvalidParametersException e) {
                e.printStackTrace();
            } catch (PayUException e) {
                e.printStackTrace();
            }
        });

    }

    private void Paytoken()
    {
        Map<String, String> parameters=new HashMap<String, String>();

        EditText nombreuser=findViewById(R.id.Nombre_Usuario);
        //Nombre Del usuario de la tarjeta
        parameters.put(PayU.PARAMETERS.PAYER_NAME, nombreuser.getText().toString());
        //identificador del pagador

        //cedula del pagador
        EditText cedula=findViewById(R.id.CC);
        parameters.put(PayU.PARAMETERS.PAYER_DNI,cedula.getText().toString());

        //numero de tarjeta
        EditText NoCard=findViewById(R.id.NoTarjeta);
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER,NoCard.getText().toString());

        //Fecha de vencimiento
        EditText mes=findViewById(R.id.TarjetaMes);
        EditText anio=findViewById(R.id.anio_Tarjeta);

        String EXPIRE_DATE=""+anio.getText().toString()+"/"+mes.getText().toString()+"";
        parameters.put(PayU.PARAMETERS.EXPIRATION_DATE, EXPIRE_DATE);

       /* List<CreditCardToken> response= PayUTokens.find(parameters);
        Iterator<CreditCardToken> tokens_iterator=response.iterator();

        while (tokens_iterator.hasNext())
        {
            CreditCardToken token=(CreditCardToken);
            tokens_iterator.next();

            token.getTokenId();
            token.getMaskedNumber();
            token.getIdentificationNumber();
            token.getPaymentMethod();
        }*/
    }

    private void pago() throws ConnectionException, InvalidParametersException, PayUException {
        PayU.apiKey="963UQIXYXOH2Fb3goD62njcHrW";
        PayU.apiLogin="ZvPLwao5taOZLfB";
        PayU.isTest=true;
        PayU.language= Language.es;
        PayU.paymentsUrl = "https://api.payulatam.com/payments-api/”";
        PayU.reportsUrl = "https://api.payulatam.com/reports-api/";

        String reference = "paymentNoTest";
        String value= "100";

        Map<String, String> parameters = new HashMap<String, String>();

//Ingrese aquí el identificador de la cuenta.
        parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "512322");
//Ingrese aquí el código de referencia.
        parameters.put(PayU.PARAMETERS.REFERENCE_CODE, ""+reference);
//Ingrese aquí la descripción.
        parameters.put(PayU.PARAMETERS.DESCRIPTION, "payment ECOROGI");
//Ingrese aquí el idima de la orden.
        parameters.put(PayU.PARAMETERS.LANGUAGE, "Language.es");

// -- Valores --
//Ingrese aquí el valor de la transacción.
        parameters.put(PayU.PARAMETERS.VALUE, ""+value);


//Ingrese aquí el valor del IVA (Impuesto al Valor Agregado solo valido para Colombia) de la transacción,
//si se envía el IVA nulo el sistema aplicará el 19% automáticamente. Puede contener dos dígitos decimales.
//Ej: 19000.00. En caso de no tener IVA debe enviarse en 0.
      /*  parameters.put(PayU.PARAMETERS.TAX_VALUE, "3193");
//Ingrese aquí el valor base sobre el cual se calcula el IVA (solo valido para Colombia).
//En caso de que no tenga IVA debe enviarse en 0.
        parameters.put(PayU.PARAMETERS.TAX_RETURN_BASE, "16806");*/
//Ingrese aquí la moneda.
        parameters.put(PayU.PARAMETERS.CURRENCY, ""+Currency.COP.name());

// -- Comprador --
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
//Ingrese aquí el id del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_ID, "1");

//Ingrese aquí el nombre del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_NAME, account.getFamilyName());

//Ingrese aquí el email del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_EMAIL, account.getEmail());

//Ingrese aquí el teléfono de contacto del comprador.
        EditText inftel=findViewById(R.id.inf_number);
        parameters.put(PayU.PARAMETERS.BUYER_CONTACT_PHONE, inftel.getText().toString());

//Ingrese aquí el documento de contacto del comprador.
        EditText cedula=findViewById(R.id.CC);
        parameters.put(PayU.PARAMETERS.BUYER_DNI,cedula.getText().toString());

//Ingrese aquí la dirección del comprador.
        TextView infdir=findViewById(R.id.inf_Direccion);
        parameters.put(PayU.PARAMETERS.BUYER_STREET, infdir.getText().toString());
        parameters.put(PayU.PARAMETERS.BUYER_CITY, "Bogota");
        parameters.put(PayU.PARAMETERS.BUYER_STATE, "Cundinamarca");
        parameters.put(PayU.PARAMETERS.BUYER_COUNTRY, "CO");
        //parameters.put(PayU.PARAMETERS.BUYER_POSTAL_CODE, "000000");
        parameters.put(PayU.PARAMETERS.BUYER_PHONE, inftel.getText().toString());

// -- Pagador --
//Ingrese aquí el id del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_ID, "1");

//Ingrese aquí el nombre del pagador.
        EditText nombreuser=findViewById(R.id.Nombre_Usuario);
        parameters.put(PayU.PARAMETERS.PAYER_NAME, "APPROVED");

//Ingrese aquí el email del pagador.

        parameters.put(PayU.PARAMETERS.PAYER_EMAIL, account.getEmail());

//Ingrese aquí el teléfono de contacto del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_CONTACT_PHONE, "7563126");

//Ingrese aquí el documento de contacto del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_DNI,cedula.getText().toString());


//Ingrese aquí la dirección del pagador.
        parameters.put(PayU.PARAMETERS.BUYER_STREET, infdir.getText().toString());
        parameters.put(PayU.PARAMETERS.BUYER_CITY, "Bogota");
        parameters.put(PayU.PARAMETERS.BUYER_STATE, "Cundinamarca");
        parameters.put(PayU.PARAMETERS.BUYER_COUNTRY, "CO");
        //parameters.put(PayU.PARAMETERS.BUYER_POSTAL_CODE, "000000");
        parameters.put(PayU.PARAMETERS.BUYER_PHONE, inftel.getText().toString());

// -- Datos de la tarjeta de crédito --
//Ingrese aquí el número de la tarjeta de crédito
        EditText NoCard=findViewById(R.id.NoTarjeta);
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER,NoCard.getText().toString());

//Ingrese aquí la fecha de vencimiento de la tarjeta de crédito
        EditText mes=findViewById(R.id.TarjetaMes);
        EditText anio=findViewById(R.id.anio_Tarjeta);

        String EXPIRE_DATE=""+anio.getText().toString()+"/"+mes.getText().toString()+"";
        parameters.put(PayU.PARAMETERS.EXPIRATION_DATE, EXPIRE_DATE);

//Ingrese aquí el código de seguridad de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_SECURITY_CODE, "321");

//Ingrese aquí el nombre de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "MasterCard");


//Ingrese aquí el número de cuotas.
        parameters.put(PayU.PARAMETERS.INSTALLMENTS_NUMBER, "1");
//Ingrese aquí el nombre del pais.
        parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.CO.name());

//Session id del device.
        parameters.put(PayU.PARAMETERS.DEVICE_SESSION_ID, "vghs6tvkcle931686k1900o6e1");

//IP del pagadador
        parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");

//Cookie de la sesión actual.
        parameters.put(PayU.PARAMETERS.COOKIE, "pt1t38347bs6jc9ruv2ecpv7o2");

//Cookie de la sesión actual.
//        parameters.put(PayU.PARAMETERS.USER_AGENT, "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");

//Solicitud de autorización y captura
        TransactionResponse response = PayUPayments.doAuthorizationAndCapture(parameters);

//Respuesta
        if(response != null){
            response.getOrderId();
            response.getTransactionId();
            response.getState();
            if(response.getState().toString().equalsIgnoreCase("PENDING")){
                response.getPendingReason();
            }
            response.getPaymentNetworkResponseCode();
            response.getPaymentNetworkResponseErrorMessage();
            response.getTrazabilityCode();
            response.getResponseCode();
            response.getResponseMessage();
        }
    }
}
