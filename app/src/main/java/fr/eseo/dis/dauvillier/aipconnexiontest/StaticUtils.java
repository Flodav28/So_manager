package fr.eseo.dis.dauvillier.aipconnexiontest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class StaticUtils {

    public static final List<List<String>> apiLOGON =asList( asList("q", "user", "pass"),asList("result", "api", "token"));
    public static final List<List<String>> apiLIPRJ =asList( asList("q", "user", "token"),asList("result", "api", "juries"));
    public static final List<String> juries =asList("idJury", "date", "info");
    public static final List<String> projectsLIJUR = asList("projectId","title","confid","poster","supervisor");
    public static final List<String> supervisor = asList("forename","surname");
    public static final List<String> students = asList("forename","surname");
    public static final List<List<String>> ApiMYJUR =asList( asList("q", "user", "token"),asList("result", "api", "juries"));
    public static final List<List<String>> ApiJYINF =asList( asList("q", "user","jury" ,"token"),asList("result", "api", "projects"));
    public static final List<String> projectsJYINF = asList("projectId","title","descrip","confid","poster","supervisor");
    public static final List<List<String>> apiPOSTR =asList( asList("q", "user", "proj","style","token"));
    public static final List<List<String>> apiNOTES =asList( asList("q", "user", "proj","token"),asList("result", "api", "notes"));
    public static final List<String> notes = asList("userId", "forename", "surname","mynote","avgnote");
    public static final List<List<String>> apiNEWNT =asList( asList("q", "user", "porj","student","note","token"),asList("result", "api"));
    public static final List<List<String>> apiPORTE =asList( asList("q", "user", "seed","token"),asList("result", "api","seed","projects"));
    public static final List<String> projectsPORTE = asList("idProject","title","description","poster");
    public static final Map<String,List<List<String>>> apiName = new HashMap<String,List<List<String>>>();
    static {
        apiName.put("LOGON", apiLOGON);
        apiName.put("LIPRJ", apiLIPRJ);
        apiName.put("MYJUR", ApiMYJUR);
        apiName.put("JYINF", ApiJYINF);
        apiName.put("POSTR", apiPOSTR);
        apiName.put("NOTES", apiNOTES);
        apiName.put("NEWNT", apiNEWNT);
        apiName.put("PORTE", apiPORTE);
    }
}
