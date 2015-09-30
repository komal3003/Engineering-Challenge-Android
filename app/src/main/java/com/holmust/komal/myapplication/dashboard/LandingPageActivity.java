package com.holmust.komal.myapplication.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.holmust.komal.myapplication.R;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.DbUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Komal on 9/30/2015.
 */
public class LandingPageActivity extends Activity implements View.OnClickListener {

    TextView tv_appname, tv_nodta;
    Button btn_addLunch, btn_addBrk, btn_addDinner, btn_addMisc;
    LinearLayout lv_mainpage;
    static final String FOOD_TYPE_STR = "FOOD TYPE";
    String foodType;
    enum food_type{
        BREAKFAST,
        LUNCH,
        SNACKS,
        DINNER

    }

    //Piechart using MPAndroid Chart
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);
        tv_appname = (TextView)findViewById(R.id.tv_appname);
        btn_addBrk = (Button)findViewById(R.id.btn_addbrk);
        btn_addLunch = (Button)findViewById(R.id.btn_addLunch);
        btn_addDinner = (Button)findViewById(R.id.btn_dinner);
        btn_addMisc = (Button)findViewById(R.id.btnSnacks);
        lv_mainpage = (LinearLayout)findViewById(R.id.lv_landing);
        pieChart =  (PieChart)findViewById(R.id.piechart);

        tv_appname.startAnimation(AnimationUtils.loadAnimation(LandingPageActivity.this, android.R.anim.slide_in_left));
        tv_nodta = (TextView)findViewById(R.id.tv_no_pie);
        btn_addLunch.setOnClickListener(this);
        btn_addBrk.setOnClickListener(this);
        btn_addDinner.setOnClickListener(this);
        btn_addMisc.setOnClickListener(this);
        if(DbUtil.getDataSize(this)!=0){
            pieChart.setVisibility(View.VISIBLE);
            configurePieChart();

        }
        else{
            tv_nodta.setVisibility(View.VISIBLE);
        }
        //DbUtil.getCaloriesForToday(this);

    }

    @Override
    public void onClick(View v) {

        if(v.equals(btn_addBrk))
        {
            foodType = String.valueOf(food_type.BREAKFAST);
        }
        else if(v.equals(btn_addLunch))
        {
            foodType = String.valueOf(food_type.LUNCH);
        }
        else if(v.equals(btn_addDinner))
        {
            foodType = String.valueOf(food_type.DINNER);
        }
        else{
            foodType = String.valueOf(food_type.SNACKS);
        }

        Intent searchFoodIntent = new Intent(this, CalculateFoodValueActivity.class);
        searchFoodIntent.putExtra(FOOD_TYPE_STR, foodType);
        startActivity(searchFoodIntent);


    }

    private void configurePieChart()
    {
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Calories distribution for today");
        pieChart.setDescriptionTextSize(15f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(20);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        addData();

        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);
        legend.setYOffset(0f);


    }

    private void addData()
    {

        ArrayList<Entry> yEntry = new ArrayList<>();//DbUtil.getCaloriesForToday(this);
        ArrayList<String> xEntry = new ArrayList<>();
        String xVal [] = {String.valueOf(food_type.BREAKFAST),String.valueOf(food_type.LUNCH),String.valueOf(food_type.SNACKS),String.valueOf(food_type.DINNER)};
        float yVal [] = DbUtil.getCaloriesForToday(this);
        //float yArr [] = new float[yVal.size()];
        ArrayList<Integer> colors = new ArrayList<>();

        //forming xEntry set
        for(int i=0; i<xVal.length; i++)
            xEntry.add(xVal[i]);

        //forming yEntry set

        for(int i=0; i<yVal.length;i++)
            yEntry.add(new Entry(yVal[i],i));

        PieDataSet dataset = new PieDataSet(yEntry ,"Calories");
        dataset.setSliceSpace(4);
        dataset.setSelectionShift(5);

        for(int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        dataset.setColors(colors);

        PieData pieData = new PieData(xEntry,dataset);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.BLUE);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }
}
