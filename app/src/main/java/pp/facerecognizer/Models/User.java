package pp.facerecognizer.Models;

public class User
{
    private String name,email,password,tempo;
    private int puntaje;
    public User() {
        this.puntaje=0;
    }

    public User(String name, String email, String password, int puntaje) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
