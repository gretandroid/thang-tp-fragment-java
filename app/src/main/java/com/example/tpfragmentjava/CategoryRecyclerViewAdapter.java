package com.example.tpfragmentjava;

import androidx.activity.result.ActivityResultLauncher;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tpfragmentjava.model.Model.Category;
import com.example.tpfragmentjava.databinding.FragmentCategoryBinding;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private int selected_index;
    private final List<Category> mValues;
    private ActivityResultLauncher<Intent> intentLauncher;
    private Activity activity;

    public CategoryRecyclerViewAdapter(List<Category> items, Activity activity,
                                       ActivityResultLauncher<Intent> intentLauncher) {
        mValues = items;
        this.activity = activity;
        this.intentLauncher = intentLauncher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mNameView.setText(mValues.get(position).name);
        // set back ground color of selected item here

        holder.mNameView.setOnClickListener((view) -> onClick(view, position));
        holder.mIdView.setOnClickListener((view) -> onClick(view, position));
    }

    private void onClick(View view, int position) {
        selected_index = position;
        // create an intent to sent message to main activity
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.putExtra(MainActivity.KEY_CATEGORY_ID, mValues.get(position).id);

        intentLauncher.launch(intent);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mNameView;
        public Category mItem;

        public ViewHolder(FragmentCategoryBinding binding) {
            super(binding.getRoot());
            mIdView = binding.categoryNumber;
            mNameView = binding.categoryName;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}