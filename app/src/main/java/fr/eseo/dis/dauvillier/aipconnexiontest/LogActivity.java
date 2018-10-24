package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LogActivity extends MasterActivity {

    //   Button  button = findViewById(R.id.button);
    //    TextView  textView = findViewById(R.id.textView);
    private static final String apiName = "LOGON";
    public static final String PREFS_NAME = "MyPrefsFile";

    private Button btnConnexion;
    private String q;
    private String user;
    private String password;
    private Boolean verification;
    private ResponseObject responseObject;
    public List<String> list;

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
        values.add("aubinseb");
        values.add("Lsm5hs51s9ks");



        FetchDataLogon fetchDataLogon = new FetchDataLogon(this, apiName, values);
        fetchDataLogon.execute();
    }

    @Override
    public void getResponse(List response){
        if(response.get(0).equals("OK")){
            Intent intent = new Intent(LogActivity.this, ProjectsActivity.class);
            intent.putExtra("login","aubinseb");
            intent.putExtra("token",(String)response.get(1));

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("name","sffzez");
            editor.putString("surname","sdsdfef");
            editor.putString("token",(String)response.get(1));

            startActivity(intent);
        }else{
            System.out.println("BOLOSSSS");
        }
    }
}

