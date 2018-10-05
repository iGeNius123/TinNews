package com.old4g.tinnews.save;

import android.annotation.SuppressLint;

import com.old4g.tinnews.TinApplication;
import com.old4g.tinnews.database.NewsDao.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SavedNewsModel implements SavedNewsContract.Model {
    private SavedNewsContract.Presenter presenter;
    private AppDatabase db;

    public SavedNewsModel(){
        db = TinApplication.getDataBase();
    }
    @Override
    public void setPresenter(SavedNewsContract.Presenter presenter) {
        this.presenter=presenter;
    }

    //fetch data
    @SuppressLint("CheckResult")
    @Override
    public void fetchData() {
        db.newsDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::loadSavedNews, error -> {
                    System.out.println("error");
                }, () -> {
                    System.out.println("complete");
                });
    }

}
