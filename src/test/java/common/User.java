package common;

public enum User {
    TANAKA("tanaka","tanaka@gmail.com","123456789"),
    SUZUKI("suzuki","suzuki@yahoo.com","zoomping1144"),
    YAMOTO("yamoto","yamoto.kuruma@st.com","seikattu2023"),
    YAMAHA("yamaha","yamaha.z100@kikai.com","mensettu98"),
    HONDA("honda","honda@moto.com","vision2022"),
    WRONGEMAIL("wrongemail", "Hokane.edogawa@gmail.com", "zoomping1144"),
    WRONGPASSWORD("wrongpassword", "tanaka@gmail.com", "Wrongpassword"),
    WRONGEMAILANDPASSWORD("wrongemailandpassword", "tomioka.giyu@gmail.com", "wrongpassword"),
    EMAILBLANK("emailblank","","vison2022"),
    PASSWORDBLANK("passwordblank","Honda@moto.com", "");


    public final String name;
    public final String email;
    public final String password;
    User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
}



