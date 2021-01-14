package com.thuan.test.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.thuan.test.R;
import com.thuan.test.databinding.ItemActivityBinding;
import com.thuan.test.databinding.MainActivityBinding;
import com.thuan.test.databinding.SplashActivityBinding;
import com.thuan.test.viewmodel.InforViewModel;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemActivityBinding binding = ItemActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String strTitle = getIntent().getStringExtra("title");
        String strBody = getIntent().getStringExtra("body");

        binding.txtTitle.setText(strTitle);
        binding.txtBody.setText(strBody);
    }
}

