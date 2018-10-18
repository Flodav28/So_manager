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
import java.util.concurrent.ExecutionException;

public class LogActivity extends MasterActivity {

    //   Button  button = findViewById(R.id.button);
    //    TextView  textView = findViewById(R.id.textView);
    private static final String apiName = "LOGON";

    private Button btnConnexion;
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
        values.add(user);
        values.add(password);

        FetchDataLogon fetchDataLogon = new FetchDataLogon(this, apiName,values);
        fetchDataLogon.execute();
       /* try {
            fetchDataLogon.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // getData(data);
    }

    @Override
    public void getResponse(List response){
        if(response.get(0).equals("OK")){
        //getData(data);
        Log.d("LogActivity","ouias");

        String ok = "OK";

        if(ok == "OK"){
            Intent intent = new Intent(LogActivity.this, ProjectsActivity.class);
            startActivity(intent);
        }else{
            System.out.println("BOLOSSSS");
        }
        }
    }
}

