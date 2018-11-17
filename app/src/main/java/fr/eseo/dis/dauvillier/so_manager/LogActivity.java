package fr.eseo.dis.dauvillier.so_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class LogActivity extends MasterActivity {


    private static final String apiName = "LOGON";

    private Button btnConnexion;

    private String forename;
    private String surname;
    private String userName;
    private String token;
    private String role;
    private String password;
    public List<String> list;

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
        userName = userValue.getText().toString();
        EditText passwordValue = (EditText) findViewById(R.id.password);
        String password = passwordValue.getText().toString();
        List<String> values=new ArrayList<String>();
        values.add(apiName);
        values.add("aubinseb");
        values.add("Lsm5hs51s9ks");
        password="Lsm5hs51s9ks";
        if(isOnline()){
            FetchDataLogon fetchDataLogon = new FetchDataLogon(this, apiName, values);
            fetchDataLogon.execute();
        }else{
            Toast.makeText(LogActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getResponse(List response){
        if(response.get(0).equals("OK")){
            List<String> values1=new ArrayList<String>();
            values1.add("MYINF");
            values1.add("aubinseb");
            token=(String)response.get(1);
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
        role=(String)response.get(3);
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
        intent.putExtra("userName",userName);
        intent.putExtra("token",token);
        intent.putExtra("forename",forename);
        intent.putExtra("surname",surname);
        intent.putExtra("role",role);
        intent.putExtra("password",password);
        startActivity(intent);
    }
}

