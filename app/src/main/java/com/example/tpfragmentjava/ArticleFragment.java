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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpfragmentjava.model.Model;

import java.util.Collections;

/**
 * A fragment representing a list of Items.
 */
public class ArticleFragment extends Fragment {

    private String category_id;
    private CategoryIdProvider categoryIdProvider;
    private RecyclerView recyclerView;

    public ArticleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        // retrieve category id from parent activity
        String category_id = categoryIdProvider!= null? categoryIdProvider.getId() : "-1" ;
        Model.Category category = Model.ITEM_MAP.get(category_id);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new ArticleRecyclerViewAdapter(category != null ?category.getArticles(): Collections.emptyList()));
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CategoryIdProvider) {
            categoryIdProvider = (CategoryIdProvider) context;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Logable.APP + this.getClass().getSimpleName(), "onPause");
//        Intent intent = new Intent();
//        intent.putExtra(MainActivity.KEY_CATEGORY_ID, category_id);
//        activity.setResult(Activity.RESULT_OK, intent);
//        activity.finish();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}