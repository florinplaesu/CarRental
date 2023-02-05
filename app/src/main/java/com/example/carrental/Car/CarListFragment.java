package com.example.carrental.Car;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.Car.CarAdapter;
import com.example.carrental.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CarListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    //private DatabaseReference databaseReference;
    //private ArrayList<Car> cars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_list, container, false);

        recyclerView = view.findViewById(R.id.rv);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Car> cars= new ArrayList<>();

        cars.add(new Car("ford","focus",35,"Vejle"));
        cars.add(new Car("opel","corsa",30,"Bucuresti"));
        cars.add(new Car("dacia","sandero",30,"Horsens"));
        cars.add(new Car("dacia","1310",25,"Vejle"));
        cars.add(new Car("ford","mustang",25,"Vejle"));
        cars.add(new Car("opel","astra",25,"Horsens"));
        cars.add(new Car("VW","golf",30,"Bucuresti"));
        cars.add(new Car("ford","focus",25,"Vejle"));
        cars.add(new Car("ford","focus",25,"Horsens"));
        cars.add(new Car("ford","focus",25,"Bucuresti"));
        cars.add(new Car("ford","focus",25,"Vejle"));
        cars.add(new Car("ford","focus",25,"Bucuresti"));
        cars.add(new Car("ford","focus",25,"Horsens"));
        cars.add(new Car("ford","focus",25,"Bucuresti"));

        /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("cars");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Car car = ds.getValue(Car.class);
                    cars.add(car);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage());
            }
        };
        databaseReference.addValueEventListener(valueEventListener);*/
        carAdapter= new CarAdapter(cars);

        carAdapter.setOnClickListener(car -> {
            Toast.makeText(getContext(),car.getModel(), Toast.LENGTH_LONG).show();
        });

        recyclerView.setAdapter(carAdapter);


        /*databaseReference = FirebaseDatabase.getInstance().getReference().child("cars");
        cars = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cars.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Car car = itemSnapshot.getValue(Car.class);
                    cars.add(car);
                }
                carAdapter = new CarAdapter(new ArrayList<>(cars));
                recyclerView.setAdapter(carAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }
/*

    @Override
    public void onStart() {
        super.onStart();


    }
*/

}
