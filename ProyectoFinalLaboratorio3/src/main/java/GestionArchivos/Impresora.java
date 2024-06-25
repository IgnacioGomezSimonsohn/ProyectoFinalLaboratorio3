package GestionArchivos;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import personas.Administrador;
import personas.Cliente;
import personas.Persona;

import java.io.IOException;



public class Impresora<T> {
    public static final Logger logger = Logger.getLogger(Impresora.class.getName());
    public static final String DESKTOP_PATH = System.getProperty("user.home") + "/Desktop";
    public static final String DEFAULT_FOLDER = "AppEcomerce";
    public static final String DEFAULT_PATH = DESKTOP_PATH + "/" + DEFAULT_FOLDER;
    public static final String JSON_EXTENSION = ".json";

    private Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    private String getFullPath(String filename) {
        return DEFAULT_PATH + "/" + filename + JSON_EXTENSION;
    }

    public String guardar(List<T> datos, String filename) throws IOException {
        Gson gson = gson();

        File directory = new File(DEFAULT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fullPath = getFullPath(filename);
        logger.log(Level.INFO, "Guardando en archivo: " + fullPath);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Administradores", gson.toJsonTree(datos.stream().filter(Persona::isAdministrador).collect(Collectors.toList())));
        ;
        jsonObject.add("Clientes", gson.toJsonTree(datos.stream().filter(Persona::isCliente).collect(Collectors.toList())));

        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(jsonObject, writer);
            return fullPath;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al generar el archivo " + fullPath, e);
            throw new IOException("Error al generar el archivo " + fullPath, e);
        }
    }

    public List<Persona> cargar(String filename) throws IOException {
        try {
            Gson gson = gson();
            String json = Files.readString(Paths.get(filename));
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            List<Persona> clientes = gson.fromJson(jsonObject.get("Clientes"), new TypeToken<List<Cliente>>() {
            }.getType());
            List<Persona> administradores = gson.fromJson(jsonObject.get("Administradores"), new TypeToken<List<Administrador>>() {
            }.getType());

            List<Persona> personas = new ArrayList<>();
            personas.addAll(administradores);
            personas.addAll(clientes);
            ;

            return personas;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo " + filename, e);
            throw new IOException("Error al leer el archivo " + filename, e);
        }

    }
}