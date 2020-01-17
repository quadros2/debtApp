package com.example.mydebtapp1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.example.junk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebtsFragment extends Fragment {

    Button add;

    ScrollView addedDebt;

    DebtClassToPush debtClassToPush;

    public DebtsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_debts, container, false);

        addedDebt = rootView.findViewById(R.id.MyDebts);

        FirebaseDatabase.getInstance().getReference().child("User")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Debt")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            View debtChunk = getLayoutInflater().inflate(R.layout.chunk_your_debt, addedDebt, false);
                            debtClassToPush = snapshot.getValue(DebtClassToPush.class);
                            TextView recipient = debtChunk.findViewById(R.id.NameRecipient);
                            recipient.setText(debtClassToPush.getRecipient());
                            TextView amount = debtChunk.findViewById(R.id.AmountStated);
                            amount.setText(debtClassToPush.getAmount());
                            TextView dateCreated = debtChunk.findViewById(R.id.Datecreated);
                            dateCreated.setText(debtClassToPush.getDate());
                            TextView description = debtChunk.findViewById(R.id.Description);
                            description.setText(debtClassToPush.getDescription());
                            addedDebt.addView(debtChunk);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        add = rootView.findViewById(R.id.addDebts);
        add.setOnClickListener(V -> {
            Intent intent = new Intent(getContext(), WriteDebt.class);
            startActivity(intent);
        });

        return rootView;
    }

}
