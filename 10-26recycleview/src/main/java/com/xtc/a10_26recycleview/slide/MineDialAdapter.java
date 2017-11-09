package com.xtc.a10_26recycleview.slide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.xtc.a10_26recycleview.R;

import java.util.List;

public class MineDialAdapter extends RecyclerView.Adapter<MineDialAdapter.TitleHolder> implements View.OnLongClickListener {

    /**
     * 显示的为标题
     */
    private final int TITLE = 1;

    /**
     * 显示的为内容
     */
    private final int CONTENT = 2;

    /**
     * bottom
     */
    private final int BOTTOM = 3;
    /**
     * other
     */
    private final int OTHER = 4;

    private Context context;

    public MineDialAdapter(Context context) {
        this.context = context;

    }

    @Override
    public TitleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View titleInflate = LayoutInflater.from(context).inflate(R.layout.item_mine_dial_title, null);
        TitleHolder titleHolder = new TitleHolder(titleInflate);
        return titleHolder;

    }

    @Override
    public void onBindViewHolder(TitleHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void updateAdapterContent(Object object) {
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    class MineDialHolder extends RecyclerView.ViewHolder {

        public MineDialHolder(View itemView) {

            super(itemView);
        }
    }

    class TitleHolder extends MineDialHolder {
        public TextView tvTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_mine_title);
        }
    }

}
