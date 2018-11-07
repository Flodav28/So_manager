package fr.eseo.dis.dauvillier.aipconnexiontest.Traitement;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.JuryDao;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Projets;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjetsDao;

public class TraitementJuryDB {
    private List<Jury> lJury;
    private JuryDao juryDao;
    private JSONObject jsonJury;
    private Jury jury;






    public TraitementJuryDB(Activity activity, JSONObject jsonEleve){
        this.juryDao= ProjectsDatabase.getDatabase(activity).juryDao();
        this.lJury =this.juryDao.getAllJury();
        this.jsonJury=jsonEleve;

    }
    public  boolean   existInDB(Jury jury){
        boolean isInDB=false;
        for (Jury juries :this.lJury){
            if (juries.getIdJury()==jury.getIdJury()){
                isInDB=true;
                juries=jury;
                this.juryDao.updateJury(juries);
            }
        }
        return isInDB;
    }
    public void insertProjet(Jury jury){
        this.juryDao.insertJury(jury);

    }
    public static JSONObject getJsonObject(String data){
        JSONObject jsonObj=null;
        try {
            jsonObj = new JSONObject(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public boolean resultOk(){
        boolean bool=false;
        try {
            if( this.jsonJury.get("result").equals("OK")){
                bool=true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bool;
    }
    public void traitement() throws JSONException {


             String dateString=   jsonJury.getString("date");
             Date date= convertStrToDate(dateString);
        jury = new Jury(
                jsonJury.getInt("idJury"),
                date
        );

        if (!this.existInDB(jury)) {
            this.insertProjet(jury);
        }
    }
    private Date convertStrToDate(String date) {
        Date dateF = null;
        try {
            dateF = new SimpleDateFormat("yyyy-mm-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateF;
    }

    public List<Jury> getlJury() {
        return lJury;
    }

    public void setlJury(List<Jury> lJury) {
        this.lJury = lJury;
    }

    public JuryDao getJuryDao() {
        return juryDao;
    }

    public void setJuryDao(JuryDao juryDao) {
        this.juryDao = juryDao;
    }

    public JSONObject getJsonJury() {
        return jsonJury;
    }

    public void setJsonJury(JSONObject jsonJury) {
        this.jsonJury = jsonJury;
    }

    public Jury getJury() {
        return jury;
    }

    public void setJury(Jury jury) {
        this.jury = jury;
    }
}
