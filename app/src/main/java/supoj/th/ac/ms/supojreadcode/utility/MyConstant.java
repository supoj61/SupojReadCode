package supoj.th.ac.ms.supojreadcode.utility;

/**
 * Created by SUPOJ on 22.03.2018.
 */

public class MyConstant {

//    About url
    private String urlPostUserString="http://ms.ac.th/postUser.php";
    private String urlGetAllUser="http://ms.ac.th/getAllUser.php";

    //About Array
    private String[] loginStrings = new String[]{"id","Name","User","Password"};


    public String[] getLoginStrings() {
        return loginStrings;
    }

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String getUrlPostUserString() {
        return urlPostUserString;
    }
}   //Main Class
