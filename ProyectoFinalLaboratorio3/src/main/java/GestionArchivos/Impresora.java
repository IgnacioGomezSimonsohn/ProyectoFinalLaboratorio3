package GestionArchivos;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import personas.Administrador;
import personas.Cliente;
import personas.Persona;
import prendas.*;

import java.io.IOException;



public class Impresora {
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

    public String guardarPersonas(List<Persona> personas, String filename) throws IOException {
        Gson gson = gson();

        File directory = new File(DEFAULT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fullPath = getFullPath(filename);
        logger.log(Level.INFO, "Guardando en archivo: " + fullPath);

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Administrador", gson.toJsonTree(personas.stream().filter(p -> p instanceof Administrador).collect(Collectors.toList())));
        jsonObject.add("Cliente", gson.toJsonTree(personas.stream().filter(p -> p instanceof Cliente).collect(Collectors.toList())));

        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(jsonObject, writer);
            return fullPath;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al generar el archivo " + fullPath, e);
            throw new IOException("Error al generar el archivo " + fullPath, e);
        }
    }

    public List<Persona> cargarPersonas(String filename) throws IOException {
        String fullPath = getFullPath(filename);
        File file = new File(fullPath);

        if (!file.exists() || file.length() == 0) {
            logger.log(Level.WARNING, "El archivo no existe o está vacío: " + fullPath);
            return new ArrayList<>();
        }

        try {
            Gson gson = gson();
            logger.log(Level.INFO, "Cargando desde archivo: " + fullPath);

            String json = Files.readString(Paths.get(fullPath));
            if (json.isEmpty()) {
                logger.log(Level.WARNING, "El archivo está vacío: " + fullPath);
                return new ArrayList<>();
            }

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            List<Persona> clientes = gson.fromJson(jsonObject.get("Cliente"), new TypeToken<List<Cliente>>() {}.getType());
            List<Persona> administradores = gson.fromJson(jsonObject.get("Administrador"), new TypeToken<List<Administrador>>() {}.getType());

            List<Persona> personas = new ArrayList<>();
            if (administradores != null) {
                personas.addAll(administradores);
            }
            if (clientes != null) {
                personas.addAll(clientes);
            }

            return personas;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo " + filename, e);
            throw new IOException("Error al leer el archivo " + filename, e);
        }
    }

    public String guardarPrendas(List<Prenda> prendas, String filename) throws IOException {
        Gson gson = gson();

        File directory = new File(DEFAULT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fullPath = getFullPath(filename);
        logger.log(Level.INFO, "Guardando en archivo: " + fullPath);

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Buzo", gson.toJsonTree(prendas.stream().filter(p -> p instanceof Buzo).collect(Collectors.toList())));
        jsonObject.add("Remera", gson.toJsonTree(prendas.stream().filter(p -> p instanceof Remera).collect(Collectors.toList())));
        jsonObject.add("Pantalon", gson.toJsonTree(prendas.stream().filter(p -> p instanceof Pantalon).collect(Collectors.toList())));
        jsonObject.add("Media", gson.toJsonTree(prendas.stream().filter(p -> p instanceof Media).collect(Collectors.toList())));

        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(jsonObject, writer);
            return fullPath;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al generar el archivo " + fullPath, e);
            throw new IOException("Error al generar el archivo " + fullPath, e);
        }
    }

    public List<Prenda> cargarPrendas(String filename) throws IOException {
        String fullPath = getFullPath(filename);
        File file = new File(fullPath);

        if (!file.exists() || file.length() == 0) {
            logger.log(Level.WARNING, "El archivo no existe o está vacío: " + fullPath);
            return new ArrayList<>();
        }

        try {
            Gson gson = gson();
            logger.log(Level.INFO, "Cargando desde archivo: " + fullPath);

            String json = Files.readString(Paths.get(fullPath));
            if (json.isEmpty()) {
                logger.log(Level.WARNING, "El archivo está vacío: " + fullPath);
                return new ArrayList<>();
            }

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            List<Prenda> buzos = gson.fromJson(jsonObject.get("Buzo"), new TypeToken<List<Buzo>>() {}.getType());
            List<Prenda> remera = gson.fromJson(jsonObject.get("Remera"), new TypeToken<List<Remera>>() {}.getType());
            List<Prenda> pantalon = gson.fromJson(jsonObject.get("Pantalon"), new TypeToken<List<Pantalon>>() {}.getType());
            List<Prenda> media = gson.fromJson(jsonObject.get("Media"), new TypeToken<List<Media>>() {}.getType());

            List<Prenda> prendas = new ArrayList<>();
            if (buzos != null) {
                prendas.addAll(buzos);
            }
            if (remera != null) {
                prendas.addAll(remera);
            }
            if (pantalon != null) {
                prendas.addAll(pantalon);
            }
            if (media != null) {
                prendas.addAll(media);
            }

            return prendas;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo " + filename, e);
            throw new IOException("Error al leer el archivo " + filename, e);
        }
    }
}