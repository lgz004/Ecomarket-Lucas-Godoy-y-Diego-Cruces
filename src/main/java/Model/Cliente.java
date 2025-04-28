package Model;

public class Cliente extends Usuario {
    private String idCliente;
    private String direccionEnvio;

    public Cliente() {}

    public Cliente(String nombre, String email, String idCliente, String direccionEnvio) {
        super(nombre, email);
        this.idCliente = idCliente;
        this.direccionEnvio = direccionEnvio;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}
