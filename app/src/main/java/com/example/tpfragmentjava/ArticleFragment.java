package com.example.tpfragmentjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
public class ArticleFragment extends Fragment {

    private String category_id;
    private Activity activity;

    public ArticleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        // retrieve intent from caller activity
        Intent intent = activity.getIntent();
        category_id = intent.getStringExtra(MainActivity.KEY_CATEGORY_ID);
        Model.Category category = Model.ITEM_MAP.get(category_id);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new ArticleRecyclerViewAdapter(category.getArticles()));
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onStop() {
        super.onStop();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_CATEGORY_ID, category_id);
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }
}