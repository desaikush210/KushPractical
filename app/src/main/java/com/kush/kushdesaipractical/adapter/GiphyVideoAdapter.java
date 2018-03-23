package com.kush.kushdesaipractical.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kush.kushdesaipractical.R;
import com.kush.kushdesaipractical.activity.VideoActivity;
import com.kush.kushdesaipractical.database.AppDatabase;
import com.kush.kushdesaipractical.database.Downvote;
import com.kush.kushdesaipractical.database.Upvote;
import com.kush.kushdesaipractical.model.GiphyvideoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 3/23/2018.
 */

public class GiphyVideoAdapter  extends RecyclerView.Adapter<GiphyVideoAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<GiphyvideoModel> giphyvideoArrayList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    AppDatabase db;

    public GiphyVideoAdapter(Context context) {
        mContext = context;
        db = AppDatabase.getInstance(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void setData(ArrayList<GiphyvideoModel> videoArrayList) {
        giphyvideoArrayList = videoArrayList;
        notifyDataSetChanged();
    }
    public void addAll(ArrayList<GiphyvideoModel> gitHubUserList) {
        int pos = giphyvideoArrayList.size();
        giphyvideoArrayList.addAll(gitHubUserList);
        notifyItemRangeInserted(pos, gitHubUserList.size());
    }
    public void add(GiphyvideoModel gitHubUser) {
        int pos = giphyvideoArrayList.size();
        giphyvideoArrayList.add(gitHubUser);
        notifyItemInserted(pos);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GiphyvideoModel giphyvideoModel = giphyvideoArrayList.get(position);
        if (!TextUtils.isEmpty(giphyvideoModel.getOriginal().getUrl())) {

            Picasso.with(mContext)
                    .load(giphyvideoModel.getOriginal().getUrl())
                    .resize(200, 200)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.giphyimage);
        }

        Log.d("up", db.userVote().getNumberofUpvoteForurl(giphyvideoModel.getOriginal().getUrl())+"");

        holder.upvote.setText(""+db.userVote().getNumberofUpvoteForurl(giphyvideoModel.getOriginal().getUrl()));
        holder.downvote.setText(""+db.userVote().getNumberofDownvoteForurl(giphyvideoModel.getOriginal().getUrl()));

        holder.giphyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra("videourl",giphyvideoModel.getOriginal().getMp4());
                mContext.startActivity(intent);
//                Toast.makeText(mContext, giphyvideoModel.getOriginal().getUrl(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int noOfRows = db.userVote().getNumberRowofUpvote();

                Upvote upvote = new Upvote(++noOfRows,giphyvideoModel.getOriginal().getUrl());
                db.userVote().insertUpvote(upvote);

                notifyDataSetChanged();

            }
        });

        holder.downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int noOfRows = db.userVote().getNumberRowofDownvote();

                Downvote downvote = new Downvote(++noOfRows,giphyvideoModel.getOriginal().getUrl());
                db.userVote().insertDownvote(downvote);

                notifyDataSetChanged();


            }
        });
    }
    @Override
    public int getItemCount() {
        return giphyvideoArrayList.size();
    }
//    @Override
//    public void onViewDetachedFromWindow(ViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        holder.cleanup();
//    }
    public interface OnItemClickListener {
        void onItemClick(View v, GiphyvideoModel gitHubUser, int position);
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.giphyimage) ImageView giphyimage;
        @BindView(R.id.upvote) TextView upvote;
        @BindView(R.id.downvote) TextView downvote;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, giphyvideoArrayList.get(getAdapterPosition()), getAdapterPosition());
            }
        }
        public void cleanup() {
            Picasso.with(giphyimage.getContext())
                    .cancelRequest(giphyimage);
            giphyimage.setImageDrawable(null);
        }
    }
}
