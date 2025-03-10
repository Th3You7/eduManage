package app.com.edumanager.models;

public class Student {
    private  int id ;
    private  int birthdate;
    private  String name;
    private  String email;

    public Student(int id, int birthdate, String name, String email) {
        this.id = id;
        this.birthdate = birthdate;
        this.name = name;
        this.email = email;
    }

    public Student( int birthdate, String name, String email) {
        this.birthdate = birthdate;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
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
}
