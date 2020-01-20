package com.example.mydebtapp1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.junk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteDebt extends AppCompatActivity {

    EditText name;
    EditText amount;
    EditText description;

    RadioButton IOU;
    RadioButton YOM;

    Spinner currency;

    Button addButton;

    private DatabaseReference mDatabase;

    DebtClassToPush debtClassToPush;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_debt);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.amount);
        description = findViewById(R.id.description);

        IOU = findViewById(R.id.IOU);
        YOM = findViewById(R.id.YOM);

        addButton = findViewById(R.id.addButtonOnrWriteDebt);


        debtClassToPush = new DebtClassToPush();

        currency = findViewById(R.id.currency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.currency_symbols, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        currency.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String setRecipient = name.getText().toString().trim();
            if (name.length() == 0) {
                Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_LONG).show();
                return;
            }
            String setAmount = amount.getText().toString().trim();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String setDate = dtf.format(now);
            String setCurrency = currency.getSelectedItem().toString().trim();

            if (!(IOU.isChecked()) && !(YOM.isChecked())) {
                Toast.makeText(getApplicationContext(), "Please select I owe them or They owe me",
                        Toast.LENGTH_LONG).show();
                return;
            } else if (IOU.isChecked()) {
                mDatabase = FirebaseDatabase.getInstance().getReference()
                        .child("myDebt_" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            } else {
                mDatabase = FirebaseDatabase.getInstance().getReference()
                        .child("theirDebt_" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            }
            debtClassToPush.setAmount(setAmount);
            debtClassToPush.setDate(setDate);
            debtClassToPush.setRecipient(setRecipient);
            debtClassToPush.setCurrency(setCurrency);
            if (!(TextUtils.isEmpty(description.getText()))) {
                debtClassToPush.setDescription(description.getText().toString().trim());
            } else {
                debtClassToPush.setDescription("Description Unavailable");
            }
            mDatabase.push().setValue(debtClassToPush);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}
