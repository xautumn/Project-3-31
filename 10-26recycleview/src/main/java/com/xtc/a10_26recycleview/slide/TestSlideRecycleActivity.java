package com.xtc.a10_26recycleview.slide;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.xtc.a10_26recycleview.R;

import static android.view.View.VISIBLE;

public class TestSlideRecycleActivity extends AppCompatActivity {

    private MineDialAdapter mineDialAdapter;
    private RecyclerView recyclerView;
    ItemTouchHelper itemTouchHelper;

    //限制ImageView长度所能增加的最大值
    private double ICON_MAX_SIZE = 50;
    //ImageView的初始长宽
    private int fixedWidth = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_slide_recycle);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        mineDialAdapter = new MineDialAdapter(this);
        recyclerView.setAdapter(mineDialAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider));

        //addOnItemTouchListener 监听触摸反应，具体要详细判断是长按还是点击

        recyclerView.addOnItemTouchListener(new MyOnItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(TestSlideRecycleActivity.this, "click = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongPress(RecyclerView.ViewHolder holder, int position) {
                super.onLongPress(holder, position);
                Toast.makeText(TestSlideRecycleActivity.this, "长按 = " + position, Toast.LENGTH_SHORT).show();

            }
        });

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            //设置移动方式方向
            //开始滑就调用
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Log.i("wq", "getMovementFlags");
                final int dragFlags;
                final int swipeFlags;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    Log.i("wq", "GridLayoutManager");
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                } else {
                    Log.i("wq", "other");
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    //start支持从右往左滑动 end支持从左往右滑动
                    swipeFlags = ItemTouchHelper.LEFT ;
                }
                //上下为拖动（drag）  左右为滑动（swipe）
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            //上下 item 移动位置变化调用
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Log.i("wq", "onMove");
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                //data.add(toPosition, data.remove(fromPosition));
                mineDialAdapter.notifyItemMoved(fromPosition, toPosition);
                return false;
            }

            //左右侧滑成功
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("wq", "onSwiped");
                int position = viewHolder.getAdapterPosition();
                //adapter.notifyItemRemoved(position);
                //data.remove(position);
            }

            /**是否可以拖拽移动位置
             * @return true:可以  false ：不支持
             * */
            @Override
            public boolean isLongPressDragEnabled() {
                Log.i("wq", "isLongPressDragEnabled");
                return false;
            }

            //当长按的时候调用
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                Log.i("wq", "onSelectedChanged = ");
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                //android.permission.VIBRATE  允许程序振动
                Vibrator vibrator = (Vibrator) TestSlideRecycleActivity.this.getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(70);
                super.onSelectedChanged(viewHolder, actionState);
            }

            //当松开，动画执行完毕调用
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Log.i("wq", "clearView = ");
                super.clearView(recyclerView, viewHolder);
                //viewHolder.itemView.setBackgroundResource(0);
            }

            /**
             *
             * @return true 滑动删除时，会回调onSwiped
             */
            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();
            }


            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                //仅对侧滑状态下的效果做出改变
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    Log.i("wq", "swipe dx = " + dX + "--dy = " + dY);
                    //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
                    int slideLimitation = getSlideLimitation(viewHolder);
                    Log.i("wq", "slideLimitation = " + slideLimitation);
                    //左滑的最大距离
                    if (Math.abs(dX) <= 500) {
                        Log.i("wq", "scrollTo");
                        //viewHolder.itemView.scrollTo(-(int) dX, 0);
                        viewHolder.itemView.setTranslationX((int) dX);
                    } else {
                        //
                    }

                 /*   //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
                    else if (Math.abs(dX) <= recyclerView.getWidth() / 2){
                        double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
                        double factor = ICON_MAX_SIZE / distance;
                        double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
                        if (diff >= ICON_MAX_SIZE)
                            diff = ICON_MAX_SIZE;
                        //((MineDialAdapter.TitleHolder)viewHolder).tvTitle.setText("");   //把文字去掉
                        //((MineDialAdapter.TitleHolder) viewHolder).iv.setVisibility(VISIBLE);  //显示眼睛
                        //FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((MineDialAdapter.TitleHolder) viewHolder).iv.getLayoutParams();
                        //params.width = (int) (fixWidth + diff);
                        //params.height = (int) (fixWidth + diff);
                        //((MineDialAdapter.TitleHolder) viewHolder).iv.setLayoutParams(params);
                    }*/
                } else {
                    //拖拽状态下不做改变，需要调用父类的方法
                    super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
                }
            }
        });
        //
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * 获取删除方块的宽度
     */
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder){
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(1).getLayoutParams().width;
    }
}
