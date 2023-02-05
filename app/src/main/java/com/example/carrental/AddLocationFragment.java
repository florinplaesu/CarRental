package com.example.carrental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.carrental.Model.Location;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddLocationFragment extends Fragment {

    private EditText etCity;
    private EditText etPostcode;
    private Button btnAddLocation;

    public AddLocationFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_location, container, false);

        etCity = view.findViewById(R.id.etCity);
        etPostcode = view.findViewById(R.id.etPostcode);
        btnAddLocation = view.findViewById(R.id.btnAddLocation);

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = etCity.getText().toString().trim();
                int postcode = 0;
                try {
                    postcode = Integer.parseInt(etPostcode.getText().toString().trim());
                } catch (NumberFormatException e) {
                    // do nothing
                }
                if (!city.isEmpty() && postcode != 0) {
                    Location location = new Location(city, postcode);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("locations");
                    String key = databaseReference.push().getKey();
                    databaseReference.child(key).setValue(location);
                    etCity.setText("");
                    etPostcode.setText("");
                }
            }
        });

        return view;
    }
}