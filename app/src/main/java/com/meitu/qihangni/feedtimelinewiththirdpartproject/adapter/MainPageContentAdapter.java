package com.meitu.qihangni.feedtimelinewiththirdpartproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.R;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;

import java.util.List;

/**
 * @author nqh 2018/7/16
 */
public class MainPageContentAdapter extends RecyclerView.Adapter<MainPageContentAdapter.ViewHolder> {
    private List<PageContentBean> mPageContentList;
    private final Context mContext;

    public MainPageContentAdapter(List<PageContentBean> pageContentList, Context context) {
        this.mPageContentList = pageContentList;
        this.mContext = context;
    }


    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_mainpagerecyclerview, null));
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
//        if (payloads.isEmpty()){
//            onBindViewHolder(holder,position);
//        }else {
//
//        }
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mPageContentList.get(position).getRecommend_cover_pic() != null) {
            Glide.with(mContext).load(mPageContentList.get(position).getRecommend_cover_pic()).into(holder.mImageCover);
        }
        holder.mTvContent.setText(mPageContentList.get(position).getMediaBean().getQq_share_caption());
        holder.mTvUsername.setText(mPageContentList.get(position).getMediaBean().getUser().getScreen_name());
    }

    @Override
    public int getItemCount() {
        return mPageContentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageCover;
        TextView mTvUsername;
        TextView mTvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageCover = itemView.findViewById(R.id.imgv_cover);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mTvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}
