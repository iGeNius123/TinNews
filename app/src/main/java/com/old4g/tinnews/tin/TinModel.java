package com.old4g.tinnews.tin;

import android.annotation.SuppressLint;

import com.old4g.tinnews.TinApplication;
import com.old4g.tinnews.retrofit.NewsRequestApi;
import com.old4g.tinnews.retrofit.RetrofitClient;
import com.old4g.tinnews.retrofit.response.News;
import com.old4g.tinnews.database.NewsDao.AppDatabase;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {
    //keep reference of Tincontract.Presenter
    private TinContract.Presenter presenter;

    //retroit client
    private final NewsRequestApi newsRequestApi;

    //db reference
    private final AppDatabase db;
    public TinModel(){
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);

        db = TinApplication.getDataBase();
    }
    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        //assign presenter
        this.presenter=presenter;
    }

    @Override
    public void fetchData(String country) {
        newsRequestApi.getNewsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    //pass fetched data to model
                    presenter.showNewsCard(baseResponse.articles);
                });
    }
    //save FavoriteNews
    @SuppressLint("CheckResult")
    @Override
    public void saveFavoriteNews(News news) {
        Completable.fromAction(() -> db.newsDao().insertNews(news)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{

        }, error -> {
            presenter.onError();
        });
    }

}
