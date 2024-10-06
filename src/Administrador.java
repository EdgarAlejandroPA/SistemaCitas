import java.util.HashMap;
import java.util.Map;

public class Administrador {
    // Simulamos una base de datos de administradores con nombres de usuario y contraseñas
    private static Map<String, String> administradores = new HashMap<>();

    // Bloque estático para inicializar algunos administradores por defecto
    static {
        administradores.put("admin1", "password1");
        administradores.put("admin2", "password2");
    }

    // Método para verificar si el login es correcto
    public static boolean login(String username, String password) {
        String storedPassword = administradores.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            System.out.println("Acceso concedido.");
            return true;
        } else {
            System.out.println("Acceso denegado. Usuario o contraseña incorrectos.");
            return false;
        }
    }
}
