package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;

import static android.content.Context.*;
import static android.support.v4.content.ContextCompat.getSystemService;

public class ResultFactory {

    private Activity activity;
    public ResultFactory(String apiName,String data,MasterActivity masterActivity)  {
        activity=masterActivity;
        getResultFactory(apiName,data,masterActivity);
    }

    public void getResultFactory(String apiName,String data,MasterActivity masterActivity)   {
        String var = apiName;

        switch(var) {
            case "LOGON":
                ResultLOGON resultLOGON = new ResultLOGON(data,masterActivity);
                break;
            case "LIPRJ":
                try {
                    ResultLIPRJ resultLIPRJ = new ResultLIPRJ(data,masterActivity);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "LIJUR":
                try {
                    ResultLIJUR resultLIJUR = new ResultLIJUR(data,masterActivity);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case "MYPRJ":
                try {
                    ResultMPRJ resultMPRJ= new ResultMPRJ(data,masterActivity);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case "JYINF":
                try {
                    ResultJYINF resultJYINF= new ResultJYINF(data,masterActivity);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case "NOTES":
                try {
                    ResultNOTE resultNOTE= new ResultNOTE(data,masterActivity);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            case "MYINF":
                    ResultMYINF resultMYINF= new ResultMYINF(data,masterActivity);
                break;

            default:
                System.out.println("Bonjoir ! :p");
        }
    }




}
