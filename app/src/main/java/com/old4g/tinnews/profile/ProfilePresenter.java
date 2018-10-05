package com.old4g.tinnews.profile;

import android.view.View;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private ProfileContract.Model model;
    private static int setCount=0;
    ProfilePresenter() {
        this.model = new ProfileModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(ProfileContract.View view) {
        this.view = view;
        if(setCount==0) {
            this.view.setView();
            setCount=1;
        }


    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onCacheCleared() {
        if (view != null) {
            view.onCacheCleared();
        }
    }

    @Override
    public View.OnClickListener getCacheClearListener() {
        return view -> {
            model.deleteAllNewsCache();
        };
    }

    @Override
    public View.OnClickListener getOnCountryChangeListener(String country) {
        return view -> {
            model.setDefaultCountry(country);
        };
    }
}
