package omarp.pruebatecnica.dto;



import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Usuario requerido")
    private String username;

    @NotBlank(message = "Contraseña requerida")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}