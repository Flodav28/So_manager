package fr.eseo.dis.dauvillier.so_manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class LogActivity extends MasterActivity {


    private  static final String MY_PREFS_NAME="sessionUser";
    private Button btnConnexion;

    private String forename;
    private String surname;
    private String userName;
    private String token;
    private int role;
    private String password;
    public List<String> list;
    public int idUser;

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
        EditText passwordValue = (EditText) findViewById(R.id.password);
        List<String> values=new ArrayList<String>();
        this.password=passwordValue.getText().toString();
        this.password="Lsm5hs51s9ks";
        userName = userValue.getText().toString();
        userName="aubinseb";
        values.add("LOGON");
        values.add(userName);
        values.add("Lsm5hs51s9ks");

        if(isOnline()){
            FetchDataLogon fetchDataLogon = new FetchDataLogon(this, "LOGON", values);
            fetchDataLogon.execute();
        }else{
            Toast.makeText(LogActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getResponse(List<Object> response){
        if(response.get(0).equals("OK")){

            token=(String)response.get(1);
            List<String> values1=new ArrayList<String>();
            values1.add("MYINF");
            values1.add(userName);
            values1.add(token);

            if(isOnline()){
                FetchDataLogon fetchDataInfo = new FetchDataLogon(this,"MYINF",values1);
                fetchDataInfo.execute();
            }else{
                Toast.makeText(LogActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
            btnConnexion.setEnabled(true);
        }
    }

    @Override
    public void getResponse1(List response){
        userName=(String)response.get(0);
        forename=(String)response.get(1);
        surname=(String)response.get(2);
        role=Integer.parseInt((String)response.get(3));
        idUser=Integer.parseInt((String)response.get(4));
        List<String> values1=new ArrayList<String>();
        values1.add("LIPRJ");
        values1.add(userName);
        values1.add(token);
        FetchDataLogon fetchDataPROJET = new FetchDataLogon(this, "LIPRJ", values1);
        fetchDataPROJET.execute();
    }

    public void getResponse2(List response){
        List<String> values1=new ArrayList<String>();
        values1.add("LIJUR");
        values1.add(userName);
        values1.add(token);
        FetchDataLogon fetchDataLIJUR = new FetchDataLogon(this, "LIJUR", values1);
        fetchDataLIJUR.execute();
    }

    public void getResponse3(List response){
        Intent intent = new Intent(LogActivity.this, MainActivity.class);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("userName",userName);
        editor.putString("token",token);
        editor.putString("forename",forename);
        editor.putString("surname",surname);
        editor.putInt("role",role);
        editor.putString("password",password);
        editor.putString("userName",userName);
        editor.putInt("idUser",idUser);
        editor.apply();

        /*intent.putExtra("userName",userName);
        intent.putExtra("token",token);
        intent.putExtra("forename",forename);
        intent.putExtra("surname",surname);
        intent.putExtra("role",role);
        intent.putExtra("password",password);
        intent.putExtra("idUser",idUser);*/
        startActivity(intent);
    }
}

