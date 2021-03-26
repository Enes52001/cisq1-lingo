package nl.hu.cisq1.lingo.trainer.domain;

public class Player {
    private String password;
    private String username;

    public Player(String uname, String pass){
        this.username = uname;
        this.password = pass;
    }
    public boolean login(String uname, String pass){
        if(uname == username & pass == password){
            return true;
        }
        return false;
    }
}
