package com.example.carrental.Car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.carrental.Car.Car;
import com.example.carrental.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCarFragment extends Fragment {

    private EditText etMark;
    private EditText etModel;
    private EditText etPrice;
    private EditText etCity;
    private Button btnAddCar;

    public AddCarFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_car, container, false);

        etMark = view.findViewById(R.id.etMark);
        etModel = view.findViewById(R.id.etModel);
        etPrice = view.findViewById(R.id.etPrice);
        etCity = view.findViewById(R.id.etCity);
        btnAddCar = view.findViewById(R.id.btnAddCar);

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mark = etMark.getText().toString().trim();
                String model = etModel.getText().toString().trim();
                int price = 0;
                try {
                    price = Integer.parseInt(etPrice.getText().toString().trim());
                } catch (NumberFormatException e) {
                    // do nothing
                }
                String city = etCity.getText().toString().trim();

                if (!mark.isEmpty() && !model.isEmpty() && price != 0 && !city.isEmpty() ) {
                    Car car = new Car(mark, model, price,city);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("cars");
                    String key = databaseReference.push().getKey();
                    databaseReference.child(key).setValue(car);
                    etMark.setText("");
                    etModel.setText("");
                    etPrice.setText("");
                    etCity.setText("");
                }
            }
        });

        return view;
    }


}
