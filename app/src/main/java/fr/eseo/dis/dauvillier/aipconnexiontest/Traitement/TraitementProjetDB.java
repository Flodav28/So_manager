package fr.eseo.dis.dauvillier.aipconnexiontest.Traitement;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Projets;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjetsDao;

public class TraitementProjetDB {

    private List<Projets> lProjets;
    private ProjetsDao projetsDao;
    private JSONObject jsonProjet;
    private Projets projet;

    public TraitementProjetDB(Activity activity, JSONObject jsonEleve){
        this.projetsDao = ProjectsDatabase.getDatabase(activity).projetsDao();
        this.lProjets = this.projetsDao.getAllProjets();
        this.jsonProjet = jsonEleve;
    }

    public boolean existInDB(Projets projet){
        boolean isInDB=false;
        for (Projets projets :this.lProjets){
            if (projets.getTitle().equals(projet.getTitle())) {
                isInDB = true;
                if (null == projets.getJury() && null != projet.getJury()){
                    projets.setJury(projet.getJury());
                    this.projetsDao.updateProjet(projets);
                }
            }
        }
        return isInDB;
    }


    public void insertProjet(Projets projets){
        this.projetsDao.insertProjet(projets);
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
            if( this.jsonProjet.get("result").equals("OK")){
                bool=true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public void traitement(String apiName,int idUser,Integer idJury) throws JSONException {
        int id = idUser;
        Integer idJ=idJury;

        idJ=idJury;
        projet = new Projets(
            jsonProjet.getInt("projectId"),
            jsonProjet.getString("title"),
            jsonProjet.getString("descrip"),
            jsonProjet.getString("confid"),
            jsonProjet.getBoolean("poster"),
            idUser, idJ
        );

        if (!this.existInDB(projet)) {
            this.insertProjet(projet);
        }
    }

    public void traitementLIJUR(String apiName,int idUser,Integer idJury) throws JSONException {
        int id = idUser;
        Integer idJ=idJury;

        idJ=idJury;
        projet = new Projets(
                jsonProjet.getInt("projectId"),
                jsonProjet.getString("title"),
                null,
                jsonProjet.getString("confid"),
                jsonProjet.getBoolean("poster"),
                idUser,
                idJ
        );

        if (!this.existInDB(projet)) {
            this.insertProjet(projet);
        }
    }

    public List<Projets> getlProjet() {
        return lProjets;
    }

    public void setProjets(List<Projets> projets) {
        this.lProjets = projets;
    }

    public ProjetsDao getProjetsDao() {
        return projetsDao;
    }

    public void setProjetsDao(ProjetsDao projetsDao) {
        this.projetsDao = projetsDao;
    }

    public JSONObject getJsonProjet() {
        return jsonProjet;
    }

    public void setJsonProjet(JSONObject jsonProjet) {
        this.jsonProjet = jsonProjet;
    }

    public Projets getProjet() {
        return projet;
    }

    public void setProjet(Projets projet) {
        this.projet = projet;
    }
}
