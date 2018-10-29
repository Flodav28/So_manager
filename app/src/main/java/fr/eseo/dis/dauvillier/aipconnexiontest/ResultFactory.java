package fr.eseo.dis.dauvillier.aipconnexiontest;

import org.json.JSONException;

public class ResultFactory {

    public ResultFactory(String apiName,String data,MasterActivity masterActivity)  {
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "LIJUR":
                try {
                    ResultLIJUR resultLIJUR = new ResultLIJUR(data,masterActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case "MYPRJ":
                try {
                    ResultMPRJ resultMPRJ= new ResultMPRJ(data,masterActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case "JYINF":
                try {
                    ResultJYINF resultJYINF= new ResultJYINF(data,masterActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case "MYJUR":
                try {
                    ResultMYJUR resultMYJUR= new ResultMYJUR(data,masterActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                System.out.println("Bonjoir ! :p");
        }
    }

}
