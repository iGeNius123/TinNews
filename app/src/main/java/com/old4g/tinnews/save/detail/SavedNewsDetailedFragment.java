package com.old4g.tinnews.save.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.old4g.tinnews.R;
import com.old4g.tinnews.common.BaseViewModel;
import com.old4g.tinnews.common.TinBasicFragment;
import com.old4g.tinnews.common.Util;
import com.old4g.tinnews.common.ViewModelAdapter;
import com.old4g.tinnews.retrofit.response.News;
import com.old4g.tinnews.save.SavedNewsFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedNewsDetailedFragment extends TinBasicFragment {


    private static final String NEWS = "news";
    private ViewModelAdapter viewModelAdapter;
    public static SavedNewsDetailedFragment newInstance(News news) {

        Bundle args = new Bundle();
        args.putParcelable(NEWS, news);
        SavedNewsDetailedFragment fragment = new SavedNewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
        View view = inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;

    }

    private void loadNews(News news){

        List<BaseViewModel> viewModels = new LinkedList<>();

        //load title
        if (!Util.isStringEmpty(news.title)) {
            viewModels.add(new TitleViewModel(news.title));
        }
        //load author and time
        if(!Util.isStringEmpty(news.author) ||!Util.isStringEmpty(news.time)){
            viewModels.add(new AuthorViewModel(news.author,news.time));
        }
        //load images
        if (!Util.isStringEmpty((news.image))) {
            viewModels.add(new ImageViewModel(news.image));
        }
        //load description
        if (!Util.isStringEmpty(news.description)) {
            viewModels.add(new DescriptionViewModel(news.description));
        }
        //read more
        if (!Util.isStringEmpty(news.url)) {
            viewModels.add(new ReadmoreViewModel(news.url, tinFragmentManager));
        }
        viewModelAdapter.addViewModels(viewModels);
    }
    //life cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {

        super.onResume();
        loadNews(getArguments().getParcelable(NEWS));
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
