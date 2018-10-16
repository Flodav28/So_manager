package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
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
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnexion = (Button) findViewById(R.id.connection_button);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtn(v);
            }
        });

    }

    public void onClickBtn(View view){
        q="LOGON";
        user="aubinseb";
        pass="Lsm5hs51s9ks";
        List<String> values=new ArrayList<String>();
        values.add(q);
        values.add(user);
        values.add(pass);
        FetchDataLogon fetchDataLogon = new FetchDataLogon(this.getApplicationContext(),q,values);
        fetchDataLogon.execute();
        //getData(data);
        Log.d("LogActivity","ouias");
        if(fetchDataLogon.getValue().get(0).equals("OK")){
            Intent intent = new Intent(LogActivity.this, ProjectsActivity.class);
            startActivity(intent);
        } else {
            System.out.println("ça marche pas");
        }
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

