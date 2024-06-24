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

        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(datos, writer);
            return fullPath;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al generar el archivo " + fullPath, e);
            throw new IOException("Error al generar el archivo " + fullPath, e);
        }
    }
    public List<T> cargar(String filename, TypeToken<List<T>> typeToken) throws IOException {
        String fullPath = getFullPath(filename);
        File file = new File(fullPath);
        if (!file.exists()) {
            logger.log(Level.WARNING, "El archivo no existe: " + fullPath);
            return new ArrayList<>();
        }
        try {
            Gson gson = gson();
            String json = Files.readString(Paths.get(fullPath));
            return gson.fromJson(json, typeToken.getType());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo " + fullPath, e);
            throw new IOException("Error al leer el archivo " + fullPath, e);

        }

        }

}
