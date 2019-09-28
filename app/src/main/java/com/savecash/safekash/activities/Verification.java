package com.savecash.safekash.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.savecash.safekash.utils.CountryData;
import com.savecash.safekash.R;

public class Verification extends AppCompatActivity {
    Spinner spinner;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        spinner=(Spinner)findViewById(R.id.spinner);
        editText=(EditText)findViewById(R.id.editTextMobile);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountryData.CountryName));
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=CountryData.CountryAreaCodes[spinner.getSelectedItemPosition()];
                String number=editText.getText().toString().trim();

                if(number.isEmpty()||number.length()<10)
                {
                    editText.setError("Enter a valid number");
                    editText.requestFocus();
                    return;
                }
                String phoneNumber="+"+code+number;
                Intent intent=new Intent(Verification.this,ConfirmCode.class);
                intent.putExtra("phoneNumber",phoneNumber);
                startActivity(intent);
            }
        });


    }
}
