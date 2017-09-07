package com.example.a8_31actionbartest;

import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("wq","onCreate");
        setTitle("测试actionBar");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useActionBar();

        String date = "2015-12-7T16:00:00.000Z";
        date = date.replace("Z", " GMT");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        try {
            Date d = format.parse(date );
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            String format1 = sdf.format(d);
            Log.i("wq","format1 = "+format1);
            long time = d.getTime();
            Log.i("wq","time = "+time);

            Date date1 = new Date();
            Log.i("wq","time = "+date1.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //可以正常编译运行，只是报下划线
        setHh(123);
    }

    private void useActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //隐藏actionbar
        //actionBar.hide();
        //显示左边返回的箭头
        actionBar.setDisplayHomeAsUpEnabled(true);
        //显示程序图标
        //actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setHomeButtonEnabled(true);


    }

    /**
     * 当Activity启动的时候，系统会调用Activity的onCreateOptionsMenu()方法来取出所有的Action按钮
     *
     * @param menu menu资源
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("wq","onCreateOptionsMenu");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.item_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 点击acionBar的所有按钮时，会调用此方法
     * @param item menu和其它按钮等
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("wq","onOptionsItemSelected = "+item.getItemId());
        switch (item.getItemId()) {
            case R.id.action_compose:
                Toast.makeText(this, "测试1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this, "测试2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "测试3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 点击actionBar左返回箭头回调
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
        return super.onSupportNavigateUp();
    }

    /**
     * DrawableRes 注解 会报红下划线异常，但依然可以正常编译
     * @param iconRes
     */
    public void setHh(@DrawableRes int iconRes){
    }
}
