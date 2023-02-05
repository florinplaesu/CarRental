package com.example.carrental.Car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private ArrayList<Car> cars;
    private OnClickListener onClickListener;

    CarAdapter(ArrayList<Car> cars) { this.cars = cars; }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.car_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.model.setText(cars.get(position).getModel());
        viewHolder.mark.setText(cars.get(position).getMark());
        viewHolder.price.setText(String.valueOf(cars.get(position).getPrice()));
        viewHolder.city.setText(cars.get(position).getCity());
        //viewHolder.icon.setImageResource(cars.get(position).getIconId());
    }

    public int getItemCount() {
        return cars.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mark, model, price, city;
        //ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            mark = itemView.findViewById(R.id.text_mark);
            model = itemView.findViewById(R.id.text_model);
            price = itemView.findViewById(R.id.text_price);
            city = itemView.findViewById(R.id.text_city);
            //icon = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(cars.get(getBindingAdapterPosition()));
            });
        }
    }
    public interface OnClickListener {
        void onClick(Car car);
    }

}
