package com.prasoon.gestfanz.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prasoon.gestfanz.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeedFragment extends Fragment {
    private DatabaseReference database;
    private Boolean isOn;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.fan) LottieAnimationView fan;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.loading) LottieAnimationView loading;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.zero) Button zero;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.one) Button one;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.two) Button two;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.three) Button three;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.four) Button four;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.five) Button five;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_speed, container, false);
        ButterKnife.bind(this, root);
        database = FirebaseDatabase.getInstance().getReference();

        List<Button> allButtons = new ArrayList<>();
        allButtons.add(zero);
        allButtons.add(one);
        allButtons.add(two);
        allButtons.add(three);
        allButtons.add(four);
        allButtons.add(five);

        // for checking state of fan
        database.child("fan_state").child("isOn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Boolean status = snapshot.getValue(Boolean.class);
                    if(status!=null){
                        isOn = status;
                        if(status.equals(true)){
                            fan.playAnimation();
                        } else {
                            fan.pauseAnimation();
                        }
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // getting speed of fan

        database.child("fan_speed").child("fanSpeed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int speed = snapshot.getValue(Integer.class);
                    allButtons.get(speed).setBackgroundResource(R.drawable.selected_speed_bg);
                    fan.setSpeed(speed);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        zero.setOnClickListener(view -> {
            changeFanSpeed(0);
        });
        one.setOnClickListener(view -> {
            changeFanSpeed(1);
        });

        two.setOnClickListener(view -> {
            changeFanSpeed(2);
        });

        three.setOnClickListener(view -> {
            changeFanSpeed(3);
        });

        four.setOnClickListener(view -> {
            changeFanSpeed(4);
        });

        five.setOnClickListener(view -> {
            changeFanSpeed(5);
        });
        return root;
    }

    public void changeFanSpeed(int speed){
        loading.playAnimation();
        HashMap<String, Integer> info = new HashMap<>();
        info.put("fanSpeed", speed);
        database.child("fan_speed").setValue(info).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(getContext(), "Speed Changed : "+speed, Toast.LENGTH_SHORT).show();
                loading.pauseAnimation();
            }
        });
    }
}