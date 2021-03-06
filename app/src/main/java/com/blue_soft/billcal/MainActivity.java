package com.blue_soft.billcal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EditText> nList = new ArrayList<>();
    List<EditText> tList = new ArrayList<>();
    List<EditText> qList = new ArrayList<>();
    List<EditText> dList = new ArrayList<>();
    List<TextView> tvList = new ArrayList<>();

    TextView totalBeforeDiscountTV;
    TextView totalAfterDiscountTV;
    TextView totalDiscountTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initialize();

        addListeners();
    }


    private void initialize() {

        totalBeforeDiscountTV = findViewById(R.id.totalBeforeDiscount);
        totalAfterDiscountTV = findViewById(R.id.totalAfterDiscount);
        totalDiscountTV = findViewById(R.id.totalDiscount);

        nList.add(findViewById(R.id.n1));
        nList.add(findViewById(R.id.n2));
        nList.add(findViewById(R.id.n3));
        nList.add(findViewById(R.id.n4));
        nList.add(findViewById(R.id.n5));
        nList.add(findViewById(R.id.n6));
        nList.add(findViewById(R.id.n7));
        nList.add(findViewById(R.id.n8));
        nList.add(findViewById(R.id.n9));
        nList.add(findViewById(R.id.n10));

        tList.add(findViewById(R.id.t1));
        tList.add(findViewById(R.id.t2));
        tList.add(findViewById(R.id.t3));
        tList.add(findViewById(R.id.t4));
        tList.add(findViewById(R.id.t5));
        tList.add(findViewById(R.id.t6));
        tList.add(findViewById(R.id.t7));
        tList.add(findViewById(R.id.t8));
        tList.add(findViewById(R.id.t9));
        tList.add(findViewById(R.id.t10));

        qList.add(findViewById(R.id.q1));
        qList.add(findViewById(R.id.q2));
        qList.add(findViewById(R.id.q3));
        qList.add(findViewById(R.id.q4));
        qList.add(findViewById(R.id.q5));
        qList.add(findViewById(R.id.q6));
        qList.add(findViewById(R.id.q7));
        qList.add(findViewById(R.id.q8));
        qList.add(findViewById(R.id.q9));
        qList.add(findViewById(R.id.q10));

        dList.add(findViewById(R.id.d1));
        dList.add(findViewById(R.id.d2));
        dList.add(findViewById(R.id.d3));
        dList.add(findViewById(R.id.d4));
        dList.add(findViewById(R.id.d5));
        dList.add(findViewById(R.id.d6));
        dList.add(findViewById(R.id.d7));
        dList.add(findViewById(R.id.d8));
        dList.add(findViewById(R.id.d9));
        dList.add(findViewById(R.id.d10));

        tvList.add(findViewById(R.id.v1));
        tvList.add(findViewById(R.id.v2));
        tvList.add(findViewById(R.id.v3));
        tvList.add(findViewById(R.id.v4));
        tvList.add(findViewById(R.id.v5));
        tvList.add(findViewById(R.id.v6));
        tvList.add(findViewById(R.id.v7));
        tvList.add(findViewById(R.id.v8));
        tvList.add(findViewById(R.id.v9));
        tvList.add(findViewById(R.id.v10));

        for (int i = 0; i <= 9; i++) {

            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

            nList.get(i).setText(sharedPreferences.getString("n" + i, ""));
            tList.get(i).setText(sharedPreferences.getString("t" + i, ""));
            dList.get(i).setText(sharedPreferences.getString("d" + i, ""));

        }

    }


    public void addListeners() {

        qList.get(0).addTextChangedListener(new PriceMethods(0));
        qList.get(1).addTextChangedListener(new PriceMethods(1));
        qList.get(2).addTextChangedListener(new PriceMethods(2));
        qList.get(3).addTextChangedListener(new PriceMethods(3));
        qList.get(4).addTextChangedListener(new PriceMethods(4));
        qList.get(5).addTextChangedListener(new PriceMethods(5));
        qList.get(6).addTextChangedListener(new PriceMethods(6));
        qList.get(7).addTextChangedListener(new PriceMethods(7));
        qList.get(8).addTextChangedListener(new PriceMethods(8));
        qList.get(9).addTextChangedListener(new PriceMethods(9));

        dList.get(0).addTextChangedListener(new PriceMethods(0));
        dList.get(1).addTextChangedListener(new PriceMethods(1));
        dList.get(2).addTextChangedListener(new PriceMethods(2));
        dList.get(3).addTextChangedListener(new PriceMethods(3));
        dList.get(4).addTextChangedListener(new PriceMethods(4));
        dList.get(5).addTextChangedListener(new PriceMethods(5));
        dList.get(6).addTextChangedListener(new PriceMethods(6));
        dList.get(7).addTextChangedListener(new PriceMethods(7));
        dList.get(8).addTextChangedListener(new PriceMethods(8));
        dList.get(9).addTextChangedListener(new PriceMethods(9));

    }

    public float getBillTotalBeforeDiscount() {

        float totalBeforeDiscount = 0;
        String qString, tString;
        float qFloat = 0, tFloat = 0;

        for (int i = 0; i < tList.size(); i++) {

            qString = qList.get(i).getText().toString();
            tString = tList.get(i).getText().toString();

            tFloat = checkFloatValues(tString);
            qFloat = checkFloatValues(qString);

            totalBeforeDiscount += qFloat * tFloat;
        }

        return totalBeforeDiscount;
    }


    public float getBillTotalAfterDiscount() {

        float totalAfterDiscount = 0;

        for (TextView totalTextViewTemp : tvList) {

            String totalString = totalTextViewTemp.getText().toString();

            totalAfterDiscount += checkFloatValues(totalString);
        }

        return totalAfterDiscount;
    }


    public void newBill(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i <= 9; i++) {

            qList.get(i).setText("");

            editor.putString("n" + i, nList.get(i).getText().toString());
            editor.putString("t" + i, tList.get(i).getText().toString());
            editor.putString("d" + i, dList.get(i).getText().toString());
        }

        editor.commit();
    }

    public String replaceArabicNumbers(String original) {

        return original.replaceAll("١", "1")
                .replaceAll("٢", "2")
                .replaceAll("٣", "3")
                .replaceAll("٤", "4")
                .replaceAll("٥", "5")
                .replaceAll("٦", "6")
                .replaceAll("٧", "7")
                .replaceAll("٨", "8")
                .replaceAll("٩", "9")
                .replaceAll(",", "");
    }

    public class PriceMethods implements TextWatcher {

        int index;
        float totalBeforeDiscount;
        float totalAfterDiscount;

        public PriceMethods(int index) {

            this.index = index;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String totalTemp = tList.get(this.index).getText().toString();
            String quantityTemp = qList.get(this.index).getText().toString();
            String discountTemp = dList.get(this.index).getText().toString();

            Float quantityVal = checkFloatValues(quantityTemp);
            Float totalVal = checkFloatValues(totalTemp);
            Float discountVal = checkFloatValues(discountTemp);

            float totalFloatValue = totalVal * quantityVal - (quantityVal * discountVal);

            if (totalFloatValue == (int) totalFloatValue)
                tvList.get(this.index).setText(String.valueOf(totalFloatValue));
            else {
                tvList.get(this.index).setText(replaceArabicNumbers(String.valueOf(totalFloatValue)));
            }

            totalBeforeDiscount = getBillTotalBeforeDiscount();
            totalAfterDiscount = getBillTotalAfterDiscount();

            totalBeforeDiscountTV.setText(totalBeforeDiscount + "");
            totalAfterDiscountTV.setText(totalAfterDiscount + "");

            totalDiscountTV.setText(totalBeforeDiscount - totalAfterDiscount + "");
        }

        @Override
        public void afterTextChanged(Editable s) {


        }


    }

    private float checkFloatValues(String floatAsString) {

        return floatAsString.equals("") ? 0 : Float.parseFloat(floatAsString);
    }
}