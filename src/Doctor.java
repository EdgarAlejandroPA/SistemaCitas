import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Doctor {
    private String idDoctor;
    private String nombreCompleto;
    private String especialidad;

    public Doctor(String idDoctor, String nombreCompleto, String especialidad) {
        this.idDoctor = idDoctor;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String toCSV() {
        return idDoctor + "," + nombreCompleto + "," + especialidad;
    }

    public static void guardarEnArchivo(Doctor doctor) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("doctores.csv", true))) {
            pw.println(doctor.toCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar el doctor: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "idDoctor='" + idDoctor + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
