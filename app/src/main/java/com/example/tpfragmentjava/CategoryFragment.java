package com.example.tpfragmentjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpfragmentjava.model.Model;

/**
 * A fragment representing a list of Items.
 */
public class CategoryFragment extends Fragment {

    private Activity activity;
    RecyclerView recyclerView;

    // declare a launcher to call an intent to start
    // an execution process ActivityResultContracts
    private ActivityResultLauncher<Intent> intentLauncher;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        // launcher register
        intentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Check whether result is OK
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // retrieve the intent return by second Activity
                            Intent data = result.getData();

                            // get category id in intent
                            String category_id = data.getStringExtra(MainActivity.KEY_CATEGORY_ID);
                            Model.Category category = Model.ITEM_MAP.get(category_id);
                            int index = Model.ITEMS.indexOf(category);

                        }
                    }
                });
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new CategoryRecyclerViewAdapter(Model.ITEMS, activity, intentLauncher));

        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }
}