package com.old4g.tinnews.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.old4g.tinnews.R;
import com.old4g.tinnews.common.ViewModelAdapter;
import com.old4g.tinnews.mvp.MvpFragment;
import com.old4g.tinnews.save.detail.TitleViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinProfileFragment extends MvpFragment<ProfileContract.Presenter> implements ProfileContract.View {

    private ViewModelAdapter viewModelAdapter;



    public static TinProfileFragment newInstance() {
        TinProfileFragment fragment = new TinProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tin_profile, container, false);
        View view = inflater.inflate(R.layout.fragment_tin_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public ProfileContract.Presenter getPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public void setView() {
        viewModelAdapter.addViewModel(new TitleViewModel(getString(R.string.setting), R.layout.setting_title_layout));
        //clear cache
        viewModelAdapter.addViewModel(new RowTextViewModel(getString(R.string.clear_cache),
                presenter.getCacheClearListener()));


        viewModelAdapter.addViewModel(new TitleViewModel(getString(R.string.change_source),
                R.layout.setting_title_layout));

        //change country
        viewModelAdapter.addViewModel(new RowTextViewModel(getString(R.string.us),
                presenter.getOnCountryChangeListener(getString(R.string.us))));
        viewModelAdapter.addViewModel(new RowTextViewModel(getString(R.string.de),
                presenter.getOnCountryChangeListener(getString(R.string.de))));
    }

    @Override
    public void onCacheCleared() {
        Toast.makeText(getContext(), "News Cache has been cleared", Toast.LENGTH_SHORT).show();

    }

}
