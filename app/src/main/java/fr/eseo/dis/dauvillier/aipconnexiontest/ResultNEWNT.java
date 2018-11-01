package fr.eseo.dis.dauvillier.aipconnexiontest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementEleveDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementNoteDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementProjetDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementUtilisateurDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Notation;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.NotationDao;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Projets;

public class ResultNEWNT {
    private static final String  API="NEWNT";

    public String data;
    public MasterActivity activity;
    public JSONObject jsonNote;
    public TraitementNoteDB traitementNoteDB;
    public NotationDao notationDAO;
    public List<Object> values;




    public ResultNEWNT(String data, MasterActivity activity,List<Object> values) throws JSONException {
        this.data=data;
        this.activity=activity;
        this.jsonNote= TraitementProjetDB.getJsonObject(data);
        this.values=values;
        getResultRequest();
    }
    public JSONObject getJson(String data) throws JSONException {

        return  new JSONObject(data);
    }
    public void getResultRequest()   {
        List<String> responseValues=new ArrayList<>();

        int idUser=0;
        int note=0;

        if(this.traitementNoteDB.resultOk()){
            idUser=Integer.parseInt((String)values.get(1));
            note=Integer.parseInt((String)values.get(0));
            Notation notation = this.notationDAO.getNoteEleve(idUser);
            notation.setNote(note);
            this.notationDAO.updateNote(notation);

        }
        List<Notation> lnote =this.notationDAO.getAllNotes();
        activity.getResponse1(responseValues);
    }
}
