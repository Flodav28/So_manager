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

    private String data, url,apiName;
    private  Context context;
    private List<String> values;
    private List<List<String>> valuesResponsesL;
    private List<String> resultValues;
    private  static final String urlFirst  ="https://192.168.4.248/pfe/webservice.php?";

    public FetchDataLogon(Context context,String apiName,List values) {
        Log.d("LogActivity","vdvds")       ;

        this.context = context;
        this.values=values;
        this.data="";
        this.apiName=apiName;
        this.valuesResponsesL=getVariableList(this.apiName);
        this.url=urlFirst+addVariableName(this.valuesResponsesL,values);

    }


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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        getResultRequest(this.valuesResponsesL);
        LogActivity.getData(this.data);

    }
    public void getResultRequest(List<List<String>> variableNames){
        List <String> responseValues=null ;
        List<String> variableName =variableNames.get(1);
        JSONObject jsonObj;
        try {
            responseValues = new ArrayList<String>();
            jsonObj = new JSONObject(data);
            for(int i=0;i<variableName.size();i++){
                responseValues.add((String)jsonObj.get(variableName.get(i)));
               // System.out.println("reponse@@@@@@@@@@@:"+variableName.get(i)+" : "+jsonObj.get(variableName.get(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.resultValues= responseValues;

    }

}