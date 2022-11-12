package com.prasoon.gestfanz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prasoon.gestfanz.adapters.PagerAdapter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.container)
    ViewPager2 container;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.changeState)
    ImageView changeState;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.state)
    TextView state;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.speed)
    TextView speed;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fanName)
    TextView fanName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editName)
    ImageView editName;
    private Boolean isOn;
    private DatabaseReference database;
    private AlertDialog editDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance().getReference();

        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter timetablePagerAdapter = new PagerAdapter(fragmentManager, getLifecycle());
        container.setAdapter(timetablePagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                container.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        container.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        // getting speed of fan
        database.child("fan_speed").child("fanSpeed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int fbSpeed = snapshot.getValue(Integer.class);
                    speed.setText(String.valueOf(fbSpeed));
                    if (fbSpeed == 0) {
                        isOn = false;
                        HashMap<String, Boolean> info = new HashMap<>();
                        info.put("isOn", isOn);
                        database.child("fan_state").setValue(info).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Fan Turned OFF", Toast.LENGTH_SHORT).show();
                                state.setText("OFF");
                                changeState.setImageResource(R.drawable.ic_power_off);
                            }
                        });
                    } else {
                        isOn = true;
                        HashMap<String, Boolean> info = new HashMap<>();
                        info.put("isOn", isOn);
                        database.child("fan_state").setValue(info).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                state.setText("ON");
                                changeState.setImageResource(R.drawable.ic_power_on);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // for checking state of fan
        database.child("fan_state").child("isOn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Boolean status = snapshot.getValue(Boolean.class);
                    if (status != null) {
                        isOn = status;
                        if (status.equals(true)) {
                            changeState.setImageResource(R.drawable.ic_power_on);
                            state.setText("ON");
                        } else {
                            changeState.setImageResource(R.drawable.ic_power_off);
                            state.setText("OFF");
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // for checking name of fan
        database.child("fan_name").child("fanName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.getValue(String.class);
                    if (name != null) {
                        fanName.setText(name);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // showing edit dialog
        editName.setOnClickListener(view -> {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.edit_dialog, null);
            editDialog = new AlertDialog.Builder(MainActivity.this)
                    .setView(dialogView)
                    .setCancelable(true)
                    .create();
            editDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            editDialog.show();
            EditText name = editDialog.findViewById(R.id.name);
            Button submit = editDialog.findViewById(R.id.submit);
            submit.setOnClickListener(view1 -> {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(this, "Name cannot be empty!!", Toast.LENGTH_SHORT).show();
                } else if(name.getText().toString().length()>10){
                    Toast.makeText(this, "Please enter less than 10 characters.", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> info = new HashMap<>();
                    info.put("fanName", name.getText().toString());
                    database.child("fan_name").setValue(info).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Name Changed Successfully!!", Toast.LENGTH_SHORT).show();
                            editDialog.dismiss();
                        }
                    });
                }
            });
        });

    }
}