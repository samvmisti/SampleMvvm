package com.epuzzle.epuzzlemvvm.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epuzzle.epuzzlemvvm.R;
import com.epuzzle.epuzzlemvvm.databinding.FragmentMainBinding;
import com.epuzzle.epuzzlemvvm.models.AppModel;
import com.epuzzle.epuzzlemvvm.ui.AppListAdapter;
import com.epuzzle.epuzzlemvvm.ui.base.BaseFragment;

import java.util.List;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainActivityViewModel> {

    private MainFragmentInteractor interactor;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return setBindView(inflater, container, R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (interactor != null) {
            viewModel.getAppModels().observe(this, this::fillAdapter);
        }
    }

    private void fillAdapter(List<AppModel> appModels) {
        AppListAdapter adapter = new AppListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.contentList.post(() -> {
            binding.contentList.setLayoutManager(layoutManager);
            binding.contentList.setAdapter(adapter);
            adapter.setItems(appModels);
        });
    }

    public MainFragment setInteractor(MainFragmentInteractor interactor) {
        this.interactor = interactor;
        return this;
    }

    public interface MainFragmentInteractor {
        void openNextFragment();
    }
}
