package sensor.com.sensorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import movile.com.creditcardguide.CreditCardFragment;
import movile.com.creditcardguide.model.IssuerCode;
import movile.com.creditcardguide.model.PaymentMethod;
import movile.com.creditcardguide.model.PurchaseOption;

public class PayActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Log.d("DEBUG", "Pay activity created");
        CreditCardFragment inputCardFragment = (CreditCardFragment) getFragmentManager().findFragmentById(R.id.frgCreditCard);

        // YOU MUST set the pages order (Set a order that makes sense to the user)
        inputCardFragment.setPagesOrder(CreditCardFragment.Step.FLAG, CreditCardFragment.Step.NUMBER,
                CreditCardFragment.Step.EXPIRE_DATE, CreditCardFragment.Step.CVV, CreditCardFragment.Step.NAME);

        // YOU MUST provide a purchase options list
        inputCardFragment.setListPurchaseOptions(getStubList(), 230.0);
    }

    private List<PurchaseOption> getStubList() {
        List<PurchaseOption> list = new ArrayList<>();
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.MASTERCARD, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.VISACREDITO, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.AMEX, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.PAYPAL, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.DINERS, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.NUBANK, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.AURA, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.ELO, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.HIPERCARD, 6));
        list.add(new PurchaseOption(PaymentMethod.Type.CREDIT_CARD, IssuerCode.OTHER, 6));

        return list;
    }

}