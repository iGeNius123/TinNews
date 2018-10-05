package com.old4g.tinnews.profile;

import com.old4g.tinnews.TinApplication;
import com.old4g.tinnews.database.NewsDao;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileModel implements ProfileContract.Model {
    private ProfileContract.Presenter presenter;
    private NewsDao.AppDatabase db = TinApplication.getDataBase();
    @Override
    public void deleteAllNewsCache() {
        Completable.fromAction(() -> db.newsDao().deleteAllNews()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            presenter.onCacheCleared();
        }, error -> {

        });
    }

    @Override
    public void setDefaultCountry(String country) {
        EventBus.getDefault().post(new CountryEvent(country));

    }



    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.presenter=presenter;
    }


}
