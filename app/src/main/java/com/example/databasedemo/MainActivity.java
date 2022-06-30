package com.example.databasedemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    public static ArrayList<UserModel> userModelArrayList;
    private DBAdapter dbAdapter;
    private RecyclerView infoRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoRecycler = findViewById(R.id.recycler);
        userModelArrayList = new ArrayList<>();

        dbHelper = new DBHelper(MainActivity.this);
        dbAdapter = new DBAdapter(userModelArrayList, MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        infoRecycler.setLayoutManager(linearLayoutManager);
        infoRecycler.setAdapter(dbAdapter);
        getData();
    }

    private void getData() {
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading");
        pd.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.publicapis.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DBAPIInterface mainInterface = retrofit.create(DBAPIInterface.class);
        Call<String> stringCall = mainInterface.STRING_CALL();

        stringCall.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        pd.dismiss();
                        JSONObject jsonObject = new JSONObject(response.body());
                        parseArray(jsonObject);
                         dbHelper.getAllInfo();

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void parseArray(JSONObject jsonObject) {
        userModelArrayList.clear();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("entries");
            for (int i = 0; i < jsonArray.length(); i++) {
                UserModel userModel = new UserModel();
                userModel.setAPI(jsonArray.getJSONObject(i).getString("API"));
                userModel.setDescription(jsonArray.getJSONObject(i).getString("Description"));
                userModel.setAuth(jsonArray.getJSONObject(i).getString("Auth"));
                userModelArrayList.add(userModel);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dbAdapter.notifyDataSetChanged();

    }
}