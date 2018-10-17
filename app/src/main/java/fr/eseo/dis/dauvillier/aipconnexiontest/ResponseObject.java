package fr.eseo.dis.dauvillier.aipconnexiontest;

import java.util.List;

public class ResponseObject {

    List<String> reponse;
    String api;
    public ResponseObject(List<String> reponse,String api){
        this.reponse=reponse;
        this.api=api;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
