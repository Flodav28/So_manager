package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends  MasterActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        Intent intent = getIntent();
        String login=intent.getStringExtra("login");
        String token=intent.getStringExtra("token");
        List<String> values= new ArrayList<String>();
            values.add("LIPRJ");
            values.add(login);
            values.add(token);
        //FetchDataLogon fetchDataLogon = new FetchDataLogon(this,"LIPRJ",values);
        //fetchDataLogon.execute();
        }
    @Override
    public void getResponse(List response){
        setContentView(R.layout.activity_projects);
        Intent intent = getIntent();
        String login=intent.getStringExtra("login");
        String token=intent.getStringExtra("token");
        List<String> values= new ArrayList<String>();
        values.add("MYPRJ");
        values.add(login);
        values.add(token);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
        FetchDataLogon fetchDataLogon = new FetchDataLogon(this,"LIPRJ",values);
        fetchDataLogon.execute();

    }
    @Override
    public void getResponse1(List response){
        setContentView(R.layout.activity_projects);
        Intent intent = getIntent();
        String login=intent.getStringExtra("login");
        String token=intent.getStringExtra("token");
        List<String> values= new ArrayList<String>();
        values.add("LIJUR");
        values.add(login);
        values.add(token);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
        FetchDataLogon fetchDataLogon = new FetchDataLogon(this,"LIJUR",values);
        fetchDataLogon.execute();

    }
}
