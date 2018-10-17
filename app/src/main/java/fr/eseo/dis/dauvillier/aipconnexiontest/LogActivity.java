package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {

    //   Button  button = findViewById(R.id.button);
    //    TextView  textView = findViewById(R.id.textView);
    private static final String apiName = "LOGON";

    private Button btnConnexion;
    private String user;
    private String password;

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
        EditText userValue = (EditText) findViewById(R.id.username);
        user = userValue.getText().toString();
        EditText passwordValue = (EditText) findViewById(R.id.password);
        password = passwordValue.getText().toString();

        List<String> values=new ArrayList<String>();
        values.add(apiName);
        values.add(user);
        values.add(password);

        FetchDataLogon fetchDataLogon = new FetchDataLogon(this.getApplicationContext(), apiName, values);
        fetchDataLogon.execute();
        //getData(data);
        Log.d("LogActivity","ouias");

        String ok = "OK";

        if(ok == "OK"){
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

