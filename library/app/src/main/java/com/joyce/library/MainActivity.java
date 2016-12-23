package com.joyce.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ListView listResult;
    Toolbar toolbar;
    String[] time = new String[55];
    String[] add = new String[55];
    String[] result = new String[55];
    String[] purpose = new String[55];
    String[] service = new String[55];
    private static final String TAG = "OkHttpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        btn = (Button) findViewById(R.id.btn);
        listResult = (ListView) findViewById(R.id.list_result);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,result);
                listResult.setAdapter(arrayAdapter);
            }
        });
        listResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "地址: " + add[position], Toast.LENGTH_SHORT).show();
            }
        });
        listResult.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("LibName",listResult.getItemAtPosition(position).toString());
                intent.putExtra("LibAdd",add[position]);
                intent.putExtra("LibTime",time[position]);
                intent.putExtra("LibPurpose",purpose[position]);
                intent.putExtra("LibSer",service[position]);
                startActivity(intent);
                return true;
            }
        });
        OkHttpClient okHttpClient = new OkHttpClient();

        // step 2 create new request
        Request request = new Request.Builder()
                .url("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=0a1e7957-7699-452c-8020-bf11a5408fc8")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // http request fail
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // http request success

                //body().string() just call once
                final String json = response.body().string();
                Log.d(TAG, "onResponse: " + json);
                final ResultResponse resultResponse = new Gson().fromJson(json, ResultResponse.class);

                Log.d(TAG, "get " + resultResponse.getResult().getResults().get(0).getO_tlc_agency_name());
                for (int i = 0; i < 55; i++){
                    result[i] = resultResponse.getResult().getResults().get(i).getO_tlc_agency_name();
                    add[i]= resultResponse.getResult().getResults().get(i).getO_tlc_agency_address();
                    time[i]= resultResponse.getResult().getResults().get(i).getO_tlc_agency_opentime();
                    purpose[i]= resultResponse.getResult().getResults().get(i).getO_tlc_agency_purpose();
                    service[i]= resultResponse.getResult().getResults().get(i).getO_tlc_agency_service();
                }

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // ui thread
                    }
                });
            }
        });
    }

}

