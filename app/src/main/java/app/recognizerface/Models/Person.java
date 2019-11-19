package app.recognizerface.Models;


public class Person {
//
    private String uid;
    private String nome;
    private String dir;
    private String cedula;
    private String sickness;
    private String telf;
    private String bloodtype;
    private String takeMedicine;


    public Person() {
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSickness() { return sickness; }

    public void setSickness(String sickness) {this.sickness = sickness;}

    public String getTelf() { return telf; }

    public void setTelf(String telf) { this.telf = telf; }

    public String getBloodtype() { return bloodtype; }

    public void setBloodtype(String bloodtype) { this.bloodtype = bloodtype; }

    public String isTakeMedicine() { return takeMedicine;   }

    public void setTakeMedicine(String takeMedicine) { this.takeMedicine = takeMedicine; }

    public String getCedula() { return cedula;
    }

    public void setCedula(String cedula) {this.cedula = cedula;
    }

    @Override
    public String toString() {
        return nome;
    }
}
