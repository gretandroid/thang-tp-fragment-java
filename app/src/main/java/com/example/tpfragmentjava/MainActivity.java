package com.example.tpfragmentjava;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tpfragmentjava.model.Model;

import java.util.Collections;

public class MainActivity extends LogableActivity implements Communication{

    public static final String KEY_CATEGORY_ID = "category_id";
    private int selected_index = -1;

    // declare a launcher to call an intent to start
    // an execution process ActivityResultContracts
    private ActivityResultLauncher<Intent> intentLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // launcher register
        intentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        onActivityResult(result);
                    }
                });

    }

    private void onActivityResult(ActivityResult result) {
        // Check whether result is OK
        if (result.getResultCode() == Activity.RESULT_OK) {
            // retrieve the intent return by second Activity
            Intent data = result.getData();

            // get category id in intent
            String category_id = data.getStringExtra(MainActivity.KEY_CATEGORY_ID);
            Model.Category category = Model.ITEM_MAP.get(category_id);
            int index = Model.ITEMS.indexOf(category);
        }

        // refresh fragment
//        CategoryFragment fragment = (CategoryFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//        fragment.getAdapter().notifyItemChanged(selected_index);
    }

    @Override
    public void send(View source, int selected_index) {
        this.selected_index = selected_index;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // create an intent to sent message to main activity
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(MainActivity.KEY_CATEGORY_ID, Model.ITEMS.get(selected_index).id);
            intentLauncher.launch(intent);
        }
        else {
            ArticleFragment fragment = (ArticleFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
            Model.Category category = Model.ITEMS.get(selected_index);
            fragment.getRecyclerView().setAdapter(new ArticleRecyclerViewAdapter(category != null ?category.getArticles(): Collections.emptyList()));
        }
    }

}