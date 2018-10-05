package com.old4g.tinnews.save;

import com.old4g.tinnews.mvp.MvpContract;
import com.old4g.tinnews.retrofit.response.News;

import java.util.List;

public interface SavedNewsContract {

    interface View extends MvpContract.View<Presenter> {
        //load saved news
        void loadSavedNews(List<News> newsList);
    }

    interface Presenter extends  MvpContract.Presenter<View, Model> {
        //load saved news
        void loadSavedNews(List<News> newsList);
    }

    interface Model extends MvpContract.Model<Presenter> {
        //fetch data
        void fetchData();
    }
}

