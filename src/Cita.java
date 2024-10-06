import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Cita {
    private String idCita;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String idCita, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String toCSV() {
        return idCita + "," + fechaHora.toString() + "," + motivo + "," + doctor.getIdDoctor() + "," + paciente.getIdPaciente();
    }

    public static void guardarEnArchivo(Cita cita) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("citas.csv", true))) {
            pw.println(cita.toCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar la cita: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita='" + idCita + '\'' +
                ", fechaHora=" + fechaHora +
                ", motivo='" + motivo + '\'' +
                ", doctor=" + doctor +
                ", paciente=" + paciente +
                '}';
    }
}
