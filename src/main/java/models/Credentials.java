package models;

public class Credentials {
    private String email;
    private String password;

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

    public Credentials(String login, String password) {
        this.email = login;
        this.password = password;
    }

    public Credentials() {

    }

    public static Credentials from(User courier) {
        return new Credentials(courier.getEmail(), courier.getPassword());
    }
}
