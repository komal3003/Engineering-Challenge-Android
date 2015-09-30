package com.holmust.komal.myapplication.dashboard;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.holmust.komal.myapplication.R;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.DbUtil;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.FoodieDb;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Komal on 9/30/2015.
 *
 * This activity shows fodd detail for today's consumption
 */
public class ShowTodaysFood extends Activity {

    private static final String LOG_TAG = "Calorimeter-ShowToday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaysconsumptiontable);

        initLayout(DbUtil.getDataSize(this));
    }

    //dynamically add rows to tablelayout
    private void initLayout(int foodsize) {

        TableLayout stk = (TableLayout) findViewById(R.id.food_table);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" MEAL ");
        tv0.setTextColor(Color.BLUE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Food Name ");
        tv1.setTextColor(Color.BLUE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Calories ");
        tv2.setTextColor(Color.BLUE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Portion ");
        tv3.setTextColor(Color.BLUE);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" Delete");
        tv4.setTextColor(Color.BLUE);
        tbrow0.addView(tv4);
        stk.addView(tbrow0);

        //Get all food details from db and populate into table
        RealmResults<FoodieDb> foods = DbUtil.getAllfood(this);
        int id = 0;
        for (final FoodieDb object : foods) {
            final TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(object.getMeal_type());
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(object.getFood_name());
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            t2v.setWidth(50);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(object.getCalories() + "");
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText(object.getServing_portion());
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            t4v.setWidth(50);
            tbrow.addView(t4v);
            Button btn_del1 = new Button(this);
            btn_del1.setId(id);
            id++;
            btn_del1.setText(" Delete");
            btn_del1.setTextColor(Color.BLACK);
            btn_del1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG,"Button clicked "+object.getFood_name()+v.getId());
                    DbUtil.deleteFood(ShowTodaysFood.this, object);
                    tbrow.removeAllViews();
                }
            });
            tbrow.addView(btn_del1);
            stk.addView(tbrow);
        }
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
