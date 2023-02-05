package com.example.carrental;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.carrental.Car.CarListFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class SearchFragment extends Fragment {

    private TextView textView;
    private Spinner spinner;
    private Button fromDate, untilDate;
    private TextView fromDateTextView;
    private TextView untilDateTextView;
    private Button search;

    public SearchFragment() {}


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);

        // the buttons
        fromDate=view.findViewById(R.id.btn_from_date);
        untilDate=view.findViewById(R.id.btn_until_date);
        search= view.findViewById(R.id.searchCars);
        //the textViews
        fromDateTextView= view.findViewById(R.id.from_date);
        untilDateTextView=view.findViewById(R.id.until_date);

        spinner = view.findViewById(R.id.spinner);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new CarListFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view, true);
            }
        });
        untilDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view, false);
            }
        });

        ArrayList<String> cities = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("locations");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, String> location = (HashMap<String, String>) snapshot.getValue();
                    String city = location.get("city");
                    cities.add(city);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cities);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
        return view;
    }

    public void showDatePickerDialog(View v, final boolean isFromDate) {
        DialogFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, month);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if (isFromDate) {
                    fromDateTextView.setText(getFormattedDate(selectedDate));
                } else {
                    untilDateTextView.setText(getFormattedDate(selectedDate));
                }
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

    private String getFormattedDate(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(date.getTime());
    }
}