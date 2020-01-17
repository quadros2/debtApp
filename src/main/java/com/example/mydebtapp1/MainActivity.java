package com.example.mydebtapp1;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.junk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomToolBar;
    FrameLayout frameLayout;

    Fragment AccountFragment;
    Fragment CollectionsFragment;
    Fragment DebtFragment;
    Fragment WriteDebt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomToolBar = findViewById(R.id.toolBar);
        frameLayout = findViewById(R.id.activityFrame);

        AccountFragment = new AccountFragement();
        CollectionsFragment = new CollectionsFragment();
        DebtFragment = new DebtsFragment();

        bottomToolBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.debts) {
                    initializeFragment(DebtFragment);
                    return true;
                }
                if (menuItem.getItemId() == R.id.collections) {
                    initializeFragment(CollectionsFragment);
                    return true;
                }
                if (menuItem.getItemId() == R.id.myAccount) {
                    initializeFragment(AccountFragment);
                    return true;
                }

                return false;
            }
        });
    }

    public void initializeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activityFrame, fragment);
        fragmentTransaction.commit();
    }
}
