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
import java.util.concurrent.ExecutionException;

public class LogActivity extends MasterActivity {

    //   Button  button = findViewById(R.id.button);
    //    TextView  textView = findViewById(R.id.textView);
    private Button btnConnexion;
    private String q;
    private String user;
    private  String pass;
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
        q="LOGON";
        user="aubinseb";
        pass="Lsm5hs51s9ks";
        List<String> values=new ArrayList<String>();
        values.add(q);
        values.add(user);
        values.add(pass);
        FetchDataLogon fetchDataLogon = new FetchDataLogon(this,q,values);
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
            Intent intent = new Intent(LogActivity.this, ProjectsActivity.class);
            startActivity(intent);
        }else{
            System.out.println("BOLOSSSS");
        }
    }




}

