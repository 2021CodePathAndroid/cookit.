package com.example.cookit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppListAdapter extends RecyclerView.Adapter<ShoppListAdapter.ViewHolder>{

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

        List<String> items;
        OnLongClickListener longClickListener;
        OnClickListener clickListener;

        public ShoppListAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
            this.items = items;
            this.longClickListener = longClickListener;
            this.clickListener = clickListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View tobuyView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(tobuyView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //Grab the item at the position
            String item = items.get(position);
            //Bind the item into the specified view holder
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvItem = itemView.findViewById(android.R.id.text1);
            }

            //Update the view inside of the view holder with this data
            public void bind(String item) {
                tvItem.setText(item);
                tvItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClicked(getAdapterPosition());
                    }
                });
                tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //Notify the listener which position was long pressed
                        longClickListener.onItemLongClicked(getAdapterPosition());
                        return true;
                    }
                });
            }
        }
}
