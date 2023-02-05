package com.example.carrental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carrental.Car.CarsFragment;

public class AdminFragment extends Fragment implements View.OnClickListener {

    private Button btnLocations, btnCars, btnUsers;

    public AdminFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        btnLocations = view.findViewById(R.id.btnLocations);
        btnCars = view.findViewById(R.id.btnCars);
        btnUsers = view.findViewById(R.id.btnUsers);

        btnLocations.setOnClickListener(this);
        btnCars.setOnClickListener(this);
        btnUsers.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLocations:
                openLocationsFragment();
                break;
            case R.id.btnCars:
                openCarsFragment();
                break;
            case R.id.btnUsers:
                openUsersFragment();
                break;
        }
    }
    private void openLocationsFragment() {
        LocationsFragment locationsFragment = new LocationsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, locationsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void openCarsFragment() {
        // Replace with your CarsFragment class
        CarsFragment carsFragment = new CarsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, carsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openUsersFragment() {
        // Replace with your UsersFragment class
        UsersFragment usersFragment = new UsersFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, usersFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
