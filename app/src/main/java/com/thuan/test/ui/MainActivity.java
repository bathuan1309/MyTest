package com.thuan.test.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.thuan.test.R;
import com.thuan.test.databinding.MainActivityBinding;
import com.thuan.test.listener.PaginationScrollListener;
import com.thuan.test.model.Infor;
import com.thuan.test.viewmodel.InforViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    InforViewModel viewModel;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 1;
    MainActivityBinding binding;
    InforAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupBindings(savedInstanceState);

        adapter = new InforAdapter(viewModel);

    }

    private void setupBindings(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(InforViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setModel(viewModel);


        setupListUpdate();
    }

    private void setSearchView() {

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (containsName(InforAdapter.inforSearch, s.toUpperCase())) {
                    adapter.getFilter().filter(s);
                } else {
                    Toast.makeText(getBaseContext(),
                            "Not found",
                            Toast.LENGTH_LONG)
                            .show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }


    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.getCarObserver().observe(this, new Observer<List<Infor>>() {
            @Override
            public void onChanged(List<Infor> cars) {
                viewModel.loading.set(View.GONE);
                if (cars.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setCarsInAdapter(cars);
                }
            }
        });

        setupListClick();
    }

    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<Infor>() {
            @Override
            public void onChanged(Infor i) {
                if (i != null) {
                    Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                    intent.putExtra("title", i.getTitle());
                    intent.putExtra("body", i.getBody());
                    startActivity(intent);
                }
            }
        });
    }
    boolean containsName(List<Infor> list, String name){
        for (Infor i :
                list) {
            if(i.getTitle().toUpperCase().contains(name))
                return true;
        }
        return false;
    }

}