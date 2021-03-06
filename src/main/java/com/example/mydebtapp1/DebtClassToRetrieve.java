package com.example.mydebtapp1;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DebtClassToRetrieve extends ArrayAdapter<DebtClassToPush> {

    private Activity appContext;
    private List<DebtClassToPush> debtList;

    public DebtClassToRetrieve(Activity appContext, List<DebtClassToPush> debtList) {
        super(appContext, R.layout.chunk_your_debt, debtList);
        this.appContext = appContext;
        this.debtList = debtList;
    }

    //This controls the code to the inflated debts please continue here
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = appContext.getLayoutInflater();
        View listItems = inflater.inflate(R.layout.chunk_your_debt, null, true);

        TextView recipient = listItems.findViewById(R.id.NameRecipient);
        TextView amount = listItems.findViewById(R.id.AmountStated);
        TextView date = listItems.findViewById(R.id.Datecreated);
        TextView description = listItems.findViewById(R.id.Description);
        Button delete = listItems.findViewById(R.id.delete);

        DebtClassToPush debtClassPulled = debtList.get(position);

        recipient.setText("Name: " + debtClassPulled.getRecipient());
        amount.setText(debtClassPulled.getCurrency() + debtClassPulled.getAmount());
        date.setText("Date Created: " + debtClassPulled.getDate());
        description.setText("Description: " + debtClassPulled.getDescription());
        delete.setOnClickListener(V -> {
            System.out.println("Y");
            System.out.println(position);
        });

        return listItems;
    }
}
