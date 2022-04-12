package com.example.tpfragmentjava;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tpfragmentjava.model.Model.Category;
import com.example.tpfragmentjava.databinding.FragmentCategoryBinding;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private int selected_index = -1;
    private final List<Category> categories;
    private final Fragment fragment;

    public CategoryRecyclerViewAdapter(List<Category> items, Fragment fragment) {
        categories = items;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = categories.get(position);
        holder.mIdView.setText(categories.get(position).id);
        holder.mNameView.setText(categories.get(position).name);
        holder.layout.setOnClickListener((view) -> {
                selected_index = position;
                notifyDataSetChanged();
                onClick(view, position);
            }
        );
        // set back ground color of selected item here
        if (position == selected_index) {
            holder.layout.setBackgroundColor(Color.parseColor("#567845"));
        }
        else {
            holder.layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
//        holder.mNameView.setOnClickListener((view) -> onClick(view, position));
//        holder.mIdView.setOnClickListener((view) -> onClick(view, position));
    }

    private void onClick(View view, int position) {
        selected_index = position;
        if (fragment instanceof Communication) {
            ((Communication) fragment).send(view, position);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mNameView;
        public final ViewGroup layout;
        public Category mItem;

        public ViewHolder(FragmentCategoryBinding binding) {
            super(binding.getRoot());
            layout = binding.getRoot();
            mIdView = binding.categoryNumber;
            mNameView = binding.categoryName;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}