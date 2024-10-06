import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Paciente {
    private String idPaciente;
    private String nombreCompleto;

    public Paciente(String idPaciente, String nombreCompleto) {
        this.idPaciente = idPaciente;
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String toCSV() {
        return idPaciente + "," + nombreCompleto;
    }

    public static void guardarEnArchivo(Paciente paciente) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("pacientes.csv", true))) {
            pw.println(paciente.toCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar el paciente: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente='" + idPaciente + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                '}';
    }
}
