import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCitas {
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private Scanner scanner;

    public SistemaCitas() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarDoctores();
        cargarPacientes();
        cargarCitas();
    }

    // Método para cargar doctores desde el archivo CSV
    private void cargarDoctores() {
        try (BufferedReader br = new BufferedReader(new FileReader("doctores.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Doctor doctor = new Doctor(datos[0], datos[1], datos[2]);
                doctores.add(doctor);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar doctores: " + e.getMessage());
        }
    }

    // Método para cargar pacientes desde el archivo CSV
    private void cargarPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Paciente paciente = new Paciente(datos[0], datos[1]);
                pacientes.add(paciente);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }
    }

    // Método para cargar citas desde el archivo CSV
    private void cargarCitas() {
        try (BufferedReader br = new BufferedReader(new FileReader("citas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Doctor doctor = doctores.stream().filter(d -> d.getIdDoctor().equals(datos[3])).findFirst().orElse(null);
                Paciente paciente = pacientes.stream().filter(p -> p.getIdPaciente().equals(datos[4])).findFirst().orElse(null);
                LocalDateTime fechaHora = LocalDateTime.parse(datos[1]);
                Cita cita = new Cita(datos[0], fechaHora, datos[2], doctor, paciente);
                citas.add(cita);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar citas: " + e.getMessage());
        }
    }

    public void darDeAltaDoctor() {
        System.out.println("Ingrese el ID del doctor:");
        String id = scanner.nextLine();
        System.out.println("Ingrese el nombre completo del doctor:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la especialidad del doctor:");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.add(doctor);
        Doctor.guardarEnArchivo(doctor);  // Guardar el doctor en el archivo CSV
        System.out.println("Doctor dado de alta exitosamente.");
    }

    public void darDeAltaPaciente() {
        System.out.println("Ingrese el ID del paciente:");
        String id = scanner.nextLine();
        System.out.println("Ingrese el nombre completo del paciente:");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre);
        pacientes.add(paciente);
        Paciente.guardarEnArchivo(paciente);  // Guardar el paciente en el archivo CSV
        System.out.println("Paciente dado de alta exitosamente.");
    }

    public void crearCita() {
        System.out.println("Ingrese el ID de la cita:");
        String idCita = scanner.nextLine();

        System.out.println("Ingrese la fecha y hora de la cita (formato: YYYY-MM-DDTHH:MM):");
        String fechaHoraStr = scanner.nextLine();
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);

        System.out.println("Ingrese el motivo de la cita:");
        String motivo = scanner.nextLine();

        System.out.println("Seleccione el doctor por ID:");
        for (Doctor doctor : doctores) {
            System.out.println(doctor.getIdDoctor() + " - " + doctor.getNombreCompleto());
        }
        String idDoctor = scanner.nextLine();
        Doctor doctorSeleccionado = doctores.stream()
                .filter(doc -> doc.getIdDoctor().equals(idDoctor))
                .findFirst()
                .orElse(null);

        System.out.println("Seleccione el paciente por ID:");
        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getIdPaciente() + " - " + paciente.getNombreCompleto());
        }
        String idPaciente = scanner.nextLine();
        Paciente pacienteSeleccionado = pacientes.stream()
                .filter(pac -> pac.getIdPaciente().equals(idPaciente))
                .findFirst()
                .orElse(null);

        if (doctorSeleccionado != null && pacienteSeleccionado != null) {
            Cita cita = new Cita(idCita, fechaHora, motivo, doctorSeleccionado, pacienteSeleccionado);
            citas.add(cita);
            Cita.guardarEnArchivo(cita);  // Guardar la cita en el archivo CSV
            System.out.println("Cita creada exitosamente.");
        } else {
            System.out.println("Doctor o paciente no encontrado.");
        }
    }

    public void mostrarCitas() {
        for (Cita cita : citas) {
            System.out.println(cita);
        }
    }

    public static void main(String[] args) {
        SistemaCitas sistema = new SistemaCitas();

        if (!Administrador.login("admin1", "password1")) {
            return;
        }

        sistema.darDeAltaDoctor();
        sistema.darDeAltaPaciente();
        sistema.crearCita();
        sistema.mostrarCitas();
    }
}
