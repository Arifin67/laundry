package model;

public class ModelLoginResult {
    private boolean isAuthenticated;
    private String username;
    private String role;

    public ModelLoginResult(boolean isAuthenticated, String username, String role) {
        this.isAuthenticated = isAuthenticated;
        this.username = username;
        this.role = role;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
}