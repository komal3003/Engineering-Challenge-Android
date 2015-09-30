package com.holmust.komal.myapplication.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.holmust.komal.myapplication.R;
import com.holmust.komal.myapplication.dashboard.JavaBeans.FoodValue;
import com.holmust.komal.myapplication.dashboard.JavaBeans.Foods;
import com.holmust.komal.myapplication.dashboard.RealmDatabase.FoodieDb;
import com.holmust.komal.myapplication.dashboard.RetrofitInterfaces.ApiService;


import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Komal on 9/25/2015.
 *
 * This activity searches food items from server and displays it to the
 * user in a list with maximum 10 items
 */
public class CalculateFoodValueActivity extends Activity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private static String LOG_TAG = "Calorimeter";
    SearchView search;
    ListView listView;
    TextView tv_search_header;
    Button btn_newfood;
    String input_food = "";
    final private static String ROOT = "https://test.holmusk.com/food";
    Foods foods = new Foods();
    String food_type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        food_type = getIntent().getStringExtra(LandingPageActivity.FOOD_TYPE_STR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);

        //Map views with layout components
        search = (SearchView)findViewById(R.id.searchView);
        listView = (ListView)findViewById(R.id.food_list);
        tv_search_header = (TextView)findViewById(R.id.tv_search_header);
        btn_newfood = (Button)findViewById(R.id.btn_add_newfood);

        //Set listeners to UI components
        search.setOnQueryTextListener(this);
        listView.setOnItemClickListener(this);
        btn_newfood.setOnClickListener(this);


    }

    //On click of search icon, query is passed to async task
    @Override
    public boolean onQueryTextSubmit(String query) {
        LoadDataIntoSearchTask loadTask = new LoadDataIntoSearchTask();
        loadTask.execute(query);
        //listView.setAdapter(adapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(LOG_TAG, newText);
        LoadDataIntoSearchTask loadTask = new LoadDataIntoSearchTask();

        //Check if search query is empty
        if (TextUtils.isEmpty(newText)) {
            Log.i(LOG_TAG, "onQueryTextChange Empty String");
            listView.clearTextFilter();

        }
        else{
            loadTask.execute(newText);
        }
        return false;
    }


    /*
    *On click of food item from the list, ShowFoodNutrientsActivity is started.
    * The details of the selected food list item is passed  as intent to next activity,
    * as an object of FoodValue class
    */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent showfooddetails = new Intent(this, ShowFoodNutrientsActivity.class);
        FoodValue foodValue = new FoodValue();
        foodValue = foods.getFoods().get(position+1);
        Log.d(LOG_TAG,"Food name passed is: "+foodValue.getName());
        showfooddetails.putExtra("FoodDetails", foodValue);
        showfooddetails.putExtra(LandingPageActivity.FOOD_TYPE_STR,food_type);
        startActivity(showfooddetails);
        finish();

    }

    /*
    * The user can click on add new button to enter the details of a new food.
    * food_type i.e. lunch, breakfast or dinner is passed to next Activity as intent
    */
    @Override
    public void onClick(View v) {
        Intent addNewfood = new Intent(this, AddNewFoodActivity.class);
        addNewfood.putExtra(LandingPageActivity.FOOD_TYPE_STR,food_type);
        startActivity(addNewfood);
    }


    /* This async task searches food for the query entered in teh search view.
     * Retrofit Get network call is made to fetch data from server
     */
    private class LoadDataIntoSearchTask extends AsyncTask<String, Void, Foods>
    {
        //String url = new String();
        RestAdapter restAdapter;

        @Override
        protected Foods doInBackground(String... params) {
            Log.i(LOG_TAG,params[0]);
            ApiService methods = restAdapter.create(ApiService.class);
            methods.getFoodValue(params[0],new Callback<List<FoodValue>>() {
                @Override
                public void success(final List<FoodValue> foodValues, Response response) {

                   // Log.d(LOG_TAG, "Success:" + foodValues.get(0).getPortions().get(0).getNutrients().getImportant().getTotal_fats().getValue());

                    foods.setFoods(foodValues);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ArrayList<String> foodname = new ArrayList<>();
                            if(foodname != null) {
                                for (int i = 0; i < foods.getFoods().size(); i++) {
                                    if (!foodname.contains(foods.getFoods().get(i).getName()))
                                        foodname.add(foods.getFoods().get(i).getName());
                                    if (foodname.size() >= 10)
                                        break;
                                }
                                //list view is set with adapter and data is populated

                                final ArrayAdapter adapter = new ArrayAdapter(CalculateFoodValueActivity.this,
                                        android.R.layout.simple_list_item_1, foodname);
                                listView.setAdapter(adapter);
                            }
                            else{
                                listView.setVisibility(View.GONE);
                                tv_search_header.setText("No matching results");
                            }
                        }
                    });

                    foods.setFoods(foodValues);

                }

                @Override
                public void failure(RetrofitError error) {

                    Log.i(LOG_TAG, "Failure:" + error.toString());
                }
            });
            return foods;
        }

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT)
                    .build();
        }

        @Override
        protected void onPostExecute(Foods foods) {
            /*Log.d(LOG_TAG,""+foods.getFoods());
            if(foods.getFoods()!=null) {
                for (int i = 0; i < foods.getFoods().size(); i++) {
                    foodname.add(i, foods.getFoods().get(i).getFood_name());

                }
            }*/
            //populateJavaBeans(foods);
           // Log.d("Post execute"," is:"+foods.getFoods().size());

        }



    }







}
