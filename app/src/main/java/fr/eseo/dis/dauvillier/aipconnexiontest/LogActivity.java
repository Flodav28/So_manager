package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

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
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

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

        MainActivity.FetchDataLogon fetchDataLogon = new MainActivity.FetchDataLogon(this, apiName, values);
        fetchDataLogon.execute();
    }

    @Override
    public void getResponse(List response){
        if(response.get(0).equals("OK")){/*
            Intent intent = new Intent(LogActivity.this, ProjectsActivity.class);
            intent.putExtra("login","aubinseb");
            intent.putExtra("token",(String)response.get(1));

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("name","sffzez");
            editor.putString("surname","sdsdfef");
            editor.putString("token",(String)response.get(1));*/
            List<String> values1=new ArrayList<String>();

            values1.add("JYINF");
            values1.add("aubinseb");
            token=(String)response.get(1);
            values1.add("1");
            values1.add((String)response.get(1));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            MainActivity.FetchDataLogon fetchDataLogon = new MainActivity.FetchDataLogon(this,"JYINF",values1);
            fetchDataLogon.execute();

        }else{
            System.out.println("BOLOSSSS");
        }
    }

    @Override
    public void getResponse1(List response){
        Intent intent = new Intent(LogActivity.this, MainActivity.class);
        intent.putExtra("login","aubinseb");
        intent.putExtra("token",token);
        startActivity(intent);
    }
}

