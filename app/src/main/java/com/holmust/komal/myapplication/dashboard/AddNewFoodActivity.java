package com.holmust.komal.myapplication.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.holmust.komal.myapplication.R;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.FoodieDb;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Komal on 9/30/2015.
 */
public class AddNewFoodActivity extends Activity implements View.OnClickListener {

    private static final String LOG_TAG = "Calorimeter_AddNew";
    EditText ed_fooname, ed_cal, ed_totalfat, ed_sat_fat, ed_poly_fat, ed_mono_fat, ed_trans_fat, ed_sod, ed_pota, ed_carbs,
    ed_fiber, ed_sugar, ed_choles, ed_protein, ed_serves, ed_portion;

    String food_type = "";

    Button btn_addfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        food_type = getIntent().getStringExtra(LandingPageActivity.FOOD_TYPE_STR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewfood);
        ed_cal = (EditText)findViewById(R.id.tv_nf_cal_value);
        ed_totalfat =  (EditText)findViewById(R.id.tv_nf_fat_value);
        ed_sat_fat = (EditText)findViewById(R.id.tv_nf_fat_sat_value);
        ed_poly_fat= (EditText)findViewById(R.id.tv_nf_fat_poly_value);
        ed_mono_fat = (EditText)findViewById(R.id.tv_nf_fat_mono_value);
        ed_trans_fat = (EditText)findViewById(R.id.tv_nf_fat_trans_value);
        ed_sod = (EditText)findViewById(R.id.tv_nf_sod_value);
        ed_pota = (EditText)findViewById(R.id.tv_nf_pot_value);
        ed_carbs = (EditText)findViewById(R.id.tv_nf_carb_value);
        ed_fiber = (EditText)findViewById(R.id.tv_nf_fib_value);
        ed_sugar = (EditText)findViewById(R.id.tv_nf_sug_value);
        ed_choles = (EditText)findViewById(R.id.tv_nf_chol_value);
        ed_protein = (EditText)findViewById(R.id.tv_nf_pro_value);
        ed_serves = (EditText)findViewById(R.id.tv_nf_serves_value);
        ed_portion = (EditText)findViewById(R.id.ed_nf_portion_val);
        ed_fooname = (EditText)findViewById(R.id.ed_nf_foodname);
        btn_addfood =(Button)findViewById(R.id.btn_add);

        btn_addfood.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        FoodieDb userRealm = realm.createObject(FoodieDb.class);
        //FoodieDb userRealm = DbUtil.CreateDb(this);
        String foodName = ed_fooname.getText().toString();
        Date date = new Date();//TBD
        String mealType = food_type;
        String servPort = ed_portion.toString();
        String servSize = (ed_serves.getText().toString());
        userRealm.setPrim_key(foodName + "-" + mealType + "-" + date + "-" + servPort);
        userRealm.setFood_name(foodName);
        userRealm.setMeal_type(mealType);
        userRealm.setDate(date);
        userRealm.setServing_portion(servPort);
        userRealm.setServing_size(Float.parseFloat(servSize));
        userRealm.setTotal_fat(Float.parseFloat(ed_totalfat.getText().toString()));
        userRealm.setSat_fat(Float.parseFloat(ed_sat_fat.getText().toString()));
        userRealm.setCalories(Float.parseFloat(ed_cal.getText().toString()));
        userRealm.setMono_fat(Float.parseFloat(ed_mono_fat.getText().toString()));
        userRealm.setSodium(Float.parseFloat(ed_sod.getText().toString()));
        userRealm.setPotassium(Float.parseFloat(ed_pota.getText().toString()));
        userRealm.setDietary_fiber(Float.parseFloat(ed_fiber.getText().toString()));
        userRealm.setCholestrol(Float.parseFloat(ed_choles.getText().toString()));
        userRealm.setTrans_fat(Float.parseFloat(ed_trans_fat.getText().toString()));
        userRealm.setProtein(Float.parseFloat(ed_protein.getText().toString()));
        userRealm.setTotal_carbs(Float.parseFloat(ed_carbs.getText().toString()));

        realm.commitTransaction();
        realm.close();

        /*realm = Realm.getInstance(this);
        realm.beginTransaction();
        RealmResults<FoodieDb> hallos = realm.where(FoodieDb.class).findAll();
        for (FoodieDb object : hallos) {
            Log.i(LOG_TAG, object.getPrim_key());
        }*/
        finish();

    }
}
