package nl.hu.cisq1.lingo.trainer.domain;

public class Player {
    private String password;
    private String username;

    public boolean login(String uname, String pass){
        if(uname == username & pass == password){
            return true;
        }
        return false;
    }
}
