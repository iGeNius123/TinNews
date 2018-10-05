package com.old4g.tinnews.tin;

import com.old4g.tinnews.profile.CountryEvent;
import com.old4g.tinnews.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {

    //reference of view
    private TinContract.View view;

    //link model with presenter
    private TinContract.Model model;

    public TinPresenter(){
        this.model = new TinModel();
        //link
        this.model.setPresenter(this);
    }
    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CountryEvent countryEvent) {
        if (this.view != null) {
            this.model.fetchData("us");
        }
    }


    @Override
    public void onViewAttached(TinContract.View view) {
        //hold reference of view
        this.view=view;

        //fetch data
        this.model.fetchData("us");
    }

    @Override
    public void onViewDetached() {
        //clear
        this.view=null;
    }

    @Override
    public void showNewsCard(List<News> newsList) {
        if (this.view != null) {
            view.showNewsCard(newsList);
        }

    }

    @Override
    public void saveFavoriteNews(News news) {
        model.saveFavoriteNews(news);
    }

    @Override
    public void onError() {
        if(view!=null){
            view.onError();
        }
    }
}
