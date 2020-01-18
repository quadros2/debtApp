package com.example.mydebtapp1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import com.example.junk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionsFragment extends Fragment {

    Button add;

    ListView addedDebt;

    DatabaseReference databaseReference;

    List<DebtClassToPush> debts;

    public CollectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_collections, container, false);

        addedDebt = rootView.findViewById(R.id.listViewTheirDebt);

        databaseReference = FirebaseDatabase.getInstance().getReference("theirDebt_" +
                FirebaseAuth.getInstance().getCurrentUser().getUid());

        onStart();

        debts = new ArrayList<>();

        add = rootView.findViewById(R.id.addTheirDebt);
        add.setOnClickListener(V -> {
            Intent intent = new Intent(getContext(), WriteDebt.class);
            startActivity(intent);
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                debts.clear();
                for (DataSnapshot debtSnapshot : dataSnapshot.getChildren()) {
                    DebtClassToPush debtClassToPush = debtSnapshot.getValue(DebtClassToPush.class);
                    debts.add(debtClassToPush);
                }
                DebtClassToRetrieve adapter = new DebtClassToRetrieve(getActivity(), debts);
                addedDebt.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
