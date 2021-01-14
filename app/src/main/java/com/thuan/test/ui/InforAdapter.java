package com.thuan.test.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.thuan.test.BR;
import com.thuan.test.R;
import com.thuan.test.model.Infor;
import com.thuan.test.viewmodel.InforViewModel;

import java.util.ArrayList;
import java.util.List;

public class InforAdapter extends RecyclerView.Adapter<InforAdapter.InforViewHolder> implements Filterable {
    private List<Infor> infors;
    public static List<Infor> inforSearch;
    private InforViewModel mViewModel;

    public InforAdapter(InforViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    public void setInfors(List<Infor> inforList) {
        this.infors = inforList;
        inforSearch = inforList;
    }

    @NonNull
    @Override
    public InforAdapter.InforViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.infor_item, parent, false);
        return new InforViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InforAdapter.InforViewHolder holder, int position) {
        Infor infor = infors.get(position);
        holder.bind(mViewModel, infor);
    }

    @Override
    public int getItemCount() {
        return infors == null ? 0 : infors.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    results.count = inforSearch.size();
                    results.values = inforSearch;
                } else{
                    String searchStr = constraint.toString().toUpperCase();
                    List<Infor> resultsData = new ArrayList<>();
                    for (Infor i :
                            inforSearch) {
                        if(i.getTitle().toUpperCase().contains(searchStr))
                            resultsData.add(i);
                    }
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                infors = (List<Infor>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class InforViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        public InforViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(InforViewModel viewModel, Infor infor) {
            binding.setVariable(BR.infor, infor);
            binding.setVariable(BR.model, viewModel);
            binding.executePendingBindings();
        }
    }

}
