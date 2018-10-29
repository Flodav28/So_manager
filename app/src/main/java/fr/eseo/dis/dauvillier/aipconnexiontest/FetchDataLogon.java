package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class FetchDataLogon extends AsyncTask<Void, Void, Void> {

    public String data, url,apiName;
    private MasterActivity masterActivity;
    private  Context context;
    private List<String> values;
    private List<List<String>> valuesResponsesDico;
    private List<String> reponseActivite=null;
    private  static final String urlFirst  ="https://192.168.4.248/pfe/webservice.php?";

    public FetchDataLogon(MasterActivity masterActivity,String apiName,List values) {
        this.context = masterActivity.getApplicationContext();
        this.values=values;
        this.data="";
        this.masterActivity=masterActivity;
        this.apiName=apiName;
        this.valuesResponsesDico=getVariableList(this.apiName);
        this.url=urlFirst+addVariableName(this.valuesResponsesDico,values);

    }
/*    public FetchDataLogon(ProjectsActivity masterActivity,String apiName,List values) {
        this.context = masterActivity.getApplicationContext();
        this.values=values;
        this.data=""; this.masterActivity=masterActivity;
        this.apiName=apiName;
        this.valuesResponsesDico=getVariableList(this.apiName);
        this.url=urlFirst+addVariableName(this.valuesResponsesDico,values);


}*/
    public List<List<String>>  getVariableList(String apiName){
        return StaticUtils.apiName.get(apiName);
    }
    public String addVariableName(List<List<String>> apiVariableNameList,List values){
        List<String> apiVarList= apiVariableNameList.get(0);
        String result="";
        for(int i=0;i<apiVarList.size();i++){
            result+="&"+apiVarList.get(i)+"="+values.get(i);
        }
        return result;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.context);
        try {
            URL url = new URL(this.url);
            //Créer une connection
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(trustManager.getSSLContext().getSocketFactory());
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            String beforeData="";
            while (line != null) {
                line = bufferedReader.readLine();
                 beforeData= beforeData +line;
            }
            this.data=beforeData;
            //getResultRequest(this.valuesResponsesDico);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       ResultFactory res = new ResultFactory(this.apiName,this.data,this.masterActivity);
    }
 /*   public void getResultRequest(List<List<String>> variableNames){
        List <String> responseValues=null ;

        List<String> variableName =variableNames.get(1);
        JSONObject jsonObj;
        try {
            responseValues = new ArrayList<String>();
            jsonObj = new JSONObject(data);
            for(int i=0;i<variableName.size();i++){
                responseValues.add((String)jsonObj.get(variableName.get(i)));
               // jsonObj.getJSONArray().
            }
            *//*switch (this.apiName)
            /*{
                case "LIPRJ":
                    List<String> tempL= new ArrayList<>();
                    tempL.addAll(responseValues,addList(responseValues,StaticUtils.projectsLIPRJ));
                    break;
                case 10:
                    System.out.println("Vous avez juste la moyenne.");
                    break;
                case 20:
                    System.out.println("Parfait !");
                    break;
                default:
                    System.out.println("Il faut davantage travailler.");
            }*//*
            this.reponseActivite=responseValues;



    } catch (JSONException e) {
            e.printStackTrace();
        }
//    public List<String> addList(List<String> listToAdd){
//        List <String> responseValues= new ArrayList<String>();
//        for(int i=0;i< values;i++){
//            responseValues.add((String)jsonObj.get(variableName.get(i)));
//        }*/


//    }





}