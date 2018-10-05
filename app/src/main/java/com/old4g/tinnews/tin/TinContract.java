package com.old4g.tinnews.tin;

import com.old4g.tinnews.mvp.MvpContract;
import com.old4g.tinnews.retrofit.response.News;

import java.util.List;

public interface TinContract {

    interface View extends MvpContract.View<Presenter> {

        //show news card
        void showNewsCard(List<News> newsList);

        void onError();
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {

        //show news card
        void showNewsCard(List<News> newsList);

        //save favorite news
        void saveFavoriteNews(News news);

        void onError();
    }

    interface Model extends MvpContract.Model<Presenter> {

        //fetch data
        void fetchData(String country);

        //save favorite news
        void saveFavoriteNews(News news);
    }

}
