package com.old4g.tinnews.save;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.old4g.tinnews.R;
import com.old4g.tinnews.common.BaseViewModel;
import com.old4g.tinnews.common.TinFragmentManager;
import com.old4g.tinnews.common.Util;
import com.old4g.tinnews.retrofit.response.News;
import com.old4g.tinnews.save.detail.SavedNewsDetailedFragment;

public class SavedNewsViewModel extends BaseViewModel<SavedNewsViewModel.SavedNewsViewHolder> {

    private News news;
    private TinFragmentManager fragmentManager;
    private static int[] ICON_ARRAY =  new int[]{R.drawable.a_news_icon,R.drawable.g_news_icon,
    R.drawable.c_news_icon,R.drawable.y_news_icon,R.drawable.m_news_icon};

    public SavedNewsViewModel(int itemResourceId) {
        super(itemResourceId);
    }
    public SavedNewsViewModel(News news, TinFragmentManager tinFragmentManager) {
        super(R.layout.saved_news_item);
        this.news = news;
        this.fragmentManager = tinFragmentManager;
    }

    @Override
    public SavedNewsViewHolder createItemViewHolder(View view) {
        return new SavedNewsViewHolder(view);
    }

    @Override
    public void bindViewHolder(SavedNewsViewHolder holder) {
        if (!Util.isStringEmpty(news.author)) {
            holder.author.setText(news.author);
        }
        holder.description.setText(news.getDescription());
        holder.icon.setImageResource(getDrawable());
        holder.itemView.setOnClickListener(v -> {
            fragmentManager.doFragmentTransaction(SavedNewsDetailedFragment.newInstance(news));
        });
    }
    private @DrawableRes
    int getDrawable() {
        return ICON_ARRAY[(int)(Math.random() * 5)];
    }

    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView description;
        ImageView icon;

        public SavedNewsViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.image);
        }
    }


}
