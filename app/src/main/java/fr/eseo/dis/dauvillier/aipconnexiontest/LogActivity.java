package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {

    //   Button  button = findViewById(R.id.button);
    //    TextView  textView = findViewById(R.id.textView);
    private Button btnConnexion;
    private String q;
    private String user;
    private  String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnexion = (Button) findViewById(R.id.button);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtn(v);
            }
        });

    }
    public void onClickBtn(View view){
        q="LIPRJ";
        user="aubinseb";
        pass="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1Mzk2NzM4NzAsImp0aSI6Im1lM1wvSTAxZjFKRDdpYUlJYWdLbHhrSUJ6MmFGRmFsZHVcL0p3XC81UFVaNzA9IiwianNzIjoiMTkyLjE2OC40LjI0OCIsIm5iZiI6MTUzOTY3Mzg3MCwiZXhwIjoxNTM5Njc0MTcwLCJkYXRhIjp7ImlkIjoxLCJrZXkiOiJydVgwb3JXaExTUVN4c1VwQWtCOEZRPT0ifX0.LRhYzDachguFwB_-1Havl_tBk7bHWJ44J64qr8Azh_IzRuYalKMjLeGAFMCqGlhwZmg72fqIYW8Wdluv4CunPQ";
        List<String> values=new ArrayList<String>();
        values.add(q);
        values.add(user);
        values.add(pass);
        FetchDataLogon fetchDataLogon = new FetchDataLogon(this.getApplicationContext(),q,values);
        fetchDataLogon.execute();
       // getData(data);
        Log.d("LogActivity","ouias");

    }
    public static void getData(String data){
        Log.d("LogActivity","data:"+data);
        JSONObject jsonObj;
        try {
            System.out.println(data);
            Log.d("LogActivity","ouias2");
            jsonObj = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

