package objectData;

import common.User;

public class Account {

    public String email;
    public String password;
    public Account(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Account() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Account setAccount(User user){
        return new Account(user.getEmail(), user.getPassword());
    }
}
