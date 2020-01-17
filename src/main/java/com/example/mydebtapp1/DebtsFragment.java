package com.example.mydebtapp1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

//import com.example.junk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebtsFragment extends Fragment {

    Button add;

    LinearLayout addedDebt;

    public DebtsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_debts, container, false);

        addedDebt = rootView.findViewById(R.id.MyDebts);
        View debtChunk = getLayoutInflater().inflate(R.layout.chunk_your_debt, addedDebt, false);


        add = rootView.findViewById(R.id.addDebts);
        add.setOnClickListener(V -> {
            Intent intent = new Intent(getContext(), WriteDebt.class);
            startActivity(intent);
        });

        return rootView;
    }

}
