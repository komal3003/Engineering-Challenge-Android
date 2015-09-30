package com.holmust.komal.myapplication.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.holmust.komal.myapplication.R;
import com.holmust.komal.myapplication.dashboard.JavaBeans.FoodValue;
import com.holmust.komal.myapplication.dashboard.JavaBeans.ImportantNutrients;
import com.holmust.komal.myapplication.dashboard.JavaBeans.Nutrient;
import com.holmust.komal.myapplication.dashboard.JavaBeans.Portion;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.DbUtil;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.FoodieDb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Komal on 9/29/2015.
 */
public class ShowFoodNutrientsActivity extends Activity implements AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener,
        View.OnClickListener {

    TextView tv_fooname, tv_cal, tv_totalfat, tv_sat_fat, tv_poly_fat, tv_mono_fat, tv_trans_fat, tv_sod, tv_pota, tv_carbs,
            tv_fiber, tv_sugar, tv_choles, tv_protein;
    EditText ed_servings;
    Button btn_add;
    double servings = 1.0;
    String food_type = "";

    Spinner sp_serving;
    final String LOG_TAG = "Calorimeter-ShowFoodNut";
    HashMap<String, ImportantNutrients> mapPortionWithNutrient = new HashMap<>();
    ArrayList<String> spin_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        FoodValue food_details = (FoodValue) getIntent().getSerializableExtra("FoodDetails");
        food_type = getIntent().getStringExtra(LandingPageActivity.FOOD_TYPE_STR);
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Food name is :" + food_details.getName());
        setContentView(R.layout.diplayfoodnutrients);

        //Map views with classes
        sp_serving = (Spinner)findViewById(R.id.dd_servings);
        tv_fooname = (TextView)findViewById(R.id.tv_foodname);
        ed_servings = (EditText)findViewById(R.id.tv_serves_value);
        tv_cal =(TextView)findViewById(R.id.tv_cal_value);
        tv_totalfat =(TextView)findViewById(R.id.tv_fat_value);
        tv_sat_fat =(TextView)findViewById(R.id.tv_fat_sat_value);
        tv_poly_fat =(TextView)findViewById(R.id.tv_fat_poly_value);
        tv_mono_fat=(TextView)findViewById(R.id.tv_fat_mono_value);
        tv_trans_fat=(TextView)findViewById(R.id.tv_fat_trans_value);
        tv_sod=(TextView)findViewById(R.id.tv_sod_value);
        tv_pota =(TextView)findViewById(R.id.tv_pot_value);
        tv_carbs = (TextView)findViewById(R.id.tv_carb_value);
        tv_fiber= (TextView)findViewById(R.id.tv_fib_value);
        tv_sugar = (TextView)findViewById(R.id.tv_sug_value);
        tv_choles= (TextView)findViewById(R.id.tv_chol_value);
        tv_protein = (TextView)findViewById(R.id.tv_pro_value);
        btn_add = (Button)findViewById(R.id.btn_addfood);
        sp_serving.setPrompt("Select Serving Size");

        //Add listeners to UI components
        sp_serving.setOnItemSelectedListener(this);
        ed_servings.setOnEditorActionListener(this);
        btn_add.setOnClickListener(this);
        populateSpinnerView(food_details);
    }

    private ArrayList<String> getServingSizes(ArrayList<Portion> portions)
    {
        ArrayList<String> servings = new ArrayList<>();

        for(int i=0;i<portions.size();i++)
        {
            if(!servings.contains(portions.get(i).getName()))
            servings.add(portions.get(i).getName());
            mapPortionWithNutrient.put(portions.get(i).getName(), portions.get(i).getNutrients().getImportant());

        }
        Log.d(LOG_TAG, "Servings are :" + servings);
       // Log.d(LOG_TAG,"Map date are :"+Float.toString(mapPortionWithNutrient.get(servings.get(0)).getTotal_carbs().getValue()));
        return servings;
    }

    private void populateSpinnerView(FoodValue details) {

        tv_fooname.setText(details.getName().toString());
        spin_data = getServingSizes(details.getPortions());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item,spin_data);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_serving.setAdapter(adapter);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ImportantNutrients imp_nut = mapPortionWithNutrient.get(sp_serving.getSelectedItem());

        if(imp_nut.getCalories()!=null)tv_cal.setText(Double.toString(imp_nut.getCalories().getValue()*servings));
        if(imp_nut.getTotal_fats()!=null)tv_totalfat.setText(Double.toString(imp_nut.getTotal_fats().getValue()*servings));
        if(imp_nut.getSaturated()!=null)tv_sat_fat.setText(Double.toString(imp_nut.getSaturated().getValue()*servings));
        if(imp_nut.getPolyunsaturated()!=null)tv_poly_fat.setText(Double.toString(imp_nut.getPolyunsaturated().getValue()*servings));
        if(imp_nut.getMonounsaturated()!=null)tv_mono_fat.setText(Double.toString(imp_nut.getMonounsaturated().getValue()*servings));
        if(imp_nut.getTrans()!=null)tv_trans_fat.setText(Double.toString(imp_nut.getTrans().getValue()*servings));
        if(imp_nut.getSodium()!=null)tv_sod.setText(Double.toString(imp_nut.getSodium().getValue()*servings));
        if(imp_nut.getPotassium()!=null)tv_pota.setText(Double.toString(imp_nut.getPotassium().getValue()*servings));
        if(imp_nut.getTotal_carbs()!=null)tv_carbs.setText(Double.toString(imp_nut.getTotal_carbs().getValue()*servings));
        if (imp_nut.getDietary_fibre()!=null)tv_fiber.setText(Double.toString(imp_nut.getDietary_fibre().getValue()*servings));
        if(imp_nut.getSugar()!=null)tv_sugar.setText(Double.toString(imp_nut.getSugar().getValue()*servings));
        if(imp_nut.getCholestrol()!=null)tv_choles.setText(Double.toString(imp_nut.getCholestrol().getValue()*servings));
        if(imp_nut.getProtein()!=null)tv_protein.setText(Double.toString(imp_nut.getProtein().getValue()*servings));


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        ImportantNutrients imp_nut = mapPortionWithNutrient.get(spin_data.get(0));

        tv_cal.setText(Double.toString(imp_nut.getCalories().getValue()));
        tv_totalfat.setText(Double.toString(imp_nut.getTotal_fats().getValue()));
        tv_sat_fat.setText(Double.toString(imp_nut.getSaturated().getValue()));
        tv_poly_fat.setText(Double.toString(imp_nut.getPolyunsaturated().getValue()));
        tv_mono_fat.setText(Double.toString(imp_nut.getMonounsaturated().getValue()));
        tv_trans_fat.setText(Double.toString(imp_nut.getTrans().getValue()));
        tv_sod.setText(Double.toString(imp_nut.getSodium().getValue()));
        tv_pota.setText(Double.toString(imp_nut.getPotassium().getValue()));
        tv_carbs.setText(Double.toString(imp_nut.getTotal_carbs().getValue()));
        tv_fiber.setText(Double.toString(imp_nut.getDietary_fibre().getValue()));
        tv_sugar.setText(Double.toString(imp_nut.getSugar().getValue()));
        tv_choles.setText(Double.toString(imp_nut.getCholestrol().getValue()));
        tv_protein.setText(Double.toString(imp_nut.getProtein().getValue()));


    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        ImportantNutrients imp_nut = mapPortionWithNutrient.get(sp_serving.getSelectedItem());
        servings = Double.parseDouble(v.getEditableText().toString());
        if(imp_nut.getCalories()!=null)tv_cal.setText(Double.toString(imp_nut.getCalories().getValue()*servings));
        if(imp_nut.getTotal_fats()!=null)tv_totalfat.setText(Double.toString(imp_nut.getTotal_fats().getValue()*servings));
        if(imp_nut.getSaturated()!=null)tv_sat_fat.setText(Double.toString(imp_nut.getSaturated().getValue()*servings));
        if(imp_nut.getPolyunsaturated()!=null)tv_poly_fat.setText(Double.toString(imp_nut.getPolyunsaturated().getValue()*servings));
        if(imp_nut.getMonounsaturated()!=null)tv_mono_fat.setText(Double.toString(imp_nut.getMonounsaturated().getValue()*servings));
        if(imp_nut.getTrans()!=null)tv_trans_fat.setText(Double.toString(imp_nut.getTrans().getValue()*servings));
        if(imp_nut.getSodium()!=null)tv_sod.setText(Double.toString(imp_nut.getSodium().getValue()*servings));
        if(imp_nut.getPotassium()!=null)tv_pota.setText(Double.toString(imp_nut.getPotassium().getValue()*servings));
        if(imp_nut.getTotal_carbs()!=null)tv_carbs.setText(Double.toString(imp_nut.getTotal_carbs().getValue()*servings));
        if (imp_nut.getDietary_fibre()!=null)tv_fiber.setText(Double.toString(imp_nut.getDietary_fibre().getValue()*servings));
        if(imp_nut.getSugar()!=null)tv_sugar.setText(Double.toString(imp_nut.getSugar().getValue()*servings));
        if(imp_nut.getCholestrol()!=null)tv_choles.setText(Double.toString(imp_nut.getCholestrol().getValue()*servings));
        if(imp_nut.getProtein()!=null)tv_protein.setText(Double.toString(imp_nut.getProtein().getValue()*servings));

        return false;
    }


    @Override
    public void onClick(View v) {
        if(v.equals(btn_add)) {
            Log.i(LOG_TAG, "Button clicked");
            Realm realm = Realm.getInstance(this);
            Long start_write = new Date().getTime();
            realm.beginTransaction();
            FoodieDb userRealm = realm.createObject(FoodieDb.class);
            //FoodieDb userRealm = DbUtil.CreateDb(this);
            String foodName = tv_fooname.getText().toString();
            Date date = new Date();//TBD
            String mealType = food_type;
            String servPort = sp_serving.getSelectedItem().toString();
            String servSize = (ed_servings.getText().toString());
            userRealm.setPrim_key(foodName + "-" + mealType + "-" + date + "-" + servPort);
            userRealm.setFood_name(foodName);
            userRealm.setMeal_type(mealType);
            userRealm.setDate(date);
            userRealm.setServing_portion(servPort);
            userRealm.setServing_size(Float.parseFloat(servSize));
            userRealm.setTotal_fat(Float.parseFloat(tv_totalfat.getText().toString()));
            userRealm.setSat_fat(Float.parseFloat(tv_sat_fat.getText().toString()));
            userRealm.setCalories(Float.parseFloat(tv_cal.getText().toString()));
            userRealm.setMono_fat(Float.parseFloat(tv_mono_fat.getText().toString()));
            userRealm.setSodium(Float.parseFloat(tv_sod.getText().toString()));
            userRealm.setPotassium(Float.parseFloat(tv_pota.getText().toString()));
            userRealm.setDietary_fiber(Float.parseFloat(tv_fiber.getText().toString()));
            userRealm.setCholestrol(Float.parseFloat(tv_choles.getText().toString()));
            userRealm.setTrans_fat(Float.parseFloat(tv_trans_fat.getText().toString()));
            userRealm.setProtein(Float.parseFloat(tv_protein.getText().toString()));
            userRealm.setTotal_carbs(Float.parseFloat(tv_carbs.getText().toString()));

            realm.commitTransaction();
            Long end_write = new Date().getTime();
            Long write_dur = end_write - start_write;
            Log.d(LOG_TAG, "Time taken to write data : :" + write_dur + "ms");
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

}
