package Model;

public class Arbitro extends Persona {

    private int licencia;

    public int getLicencia() {
        return licencia;
    }

    public void setLicencia(int licencia) {
        this.licencia = licencia;
    }

    @Override
    public String toString() {
        return "Arbitro: " + this.getNombre() + " " + getApellidos() + "\n";
    }

}