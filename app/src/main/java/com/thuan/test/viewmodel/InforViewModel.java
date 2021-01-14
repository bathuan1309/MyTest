package com.thuan.test.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.thuan.test.model.Infor;
import com.thuan.test.model.InforRepository;
import com.thuan.test.ui.InforAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class InforViewModel extends AndroidViewModel {
    private InforRepository inforRepository;
    private InforAdapter adapter;
    private List<Infor> mListInfor;
    public MutableLiveData<Infor> selected;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    public ObservableInt loadingMore;


    public void init() {
        adapter = new InforAdapter(this);
        selected = new MutableLiveData<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        loadingMore = new ObservableInt(View.GONE);
    }

    public InforViewModel(@NonNull Application application) {
        super(application);
        inforRepository = new InforRepository(application);
    }

    public MutableLiveData<List<Infor>> getCarObserver() {
        return inforRepository.getMutableLiveData();
    }


    public InforAdapter getAdapter() {
        return adapter;
    }

    public void setCarsInAdapter(List<Infor> infors) {
        this.adapter.setInfors(infors);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<Infor> getSelected() {
        return selected;
    }

    public void onItemClick(Infor i) {
        selected.setValue(i);
    }

   /* private void loadNextPage(List<Infor> infors, boolean isLoading, boolean isLastPage, int currentPage, int totalPage) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Infor> infor10 = new ArrayList<>();
                for(int i = 1; i <= 10; i++) {
                    mListInfor.addAll(infor10);
                }
                adapter.notifyDataSetChanged();

                if (currentPage == totalPage) {

                }
            }
        }, 2000);
    }
    List<Infor> infor10 = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
        infor10.addAll(infors);
    }
*/
}
