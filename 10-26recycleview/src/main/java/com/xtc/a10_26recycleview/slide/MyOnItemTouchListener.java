package com.xtc.a10_26recycleview.slide;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by wuqi on 2017/11/7.
 */

public abstract class MyOnItemTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;


    public MyOnItemTouchListener(RecyclerView view) {
        this.recyclerView = view;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    /**
     *
     * @param rv
     * @param e
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(RecyclerView.ViewHolder holder, int position);

    public void onLongPress(RecyclerView.ViewHolder holder, int position) {
    }

    class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                int position = recyclerView.getChildAdapterPosition(child);
                onItemClick(recyclerView.getChildViewHolder(child), position);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                //int position = recyclerView.indexOfChild(child);
                int position = recyclerView.getChildAdapterPosition(child);
                MyOnItemTouchListener.this.onLongPress(recyclerView.getChildViewHolder(child), position);
            }
        }
    }
}

