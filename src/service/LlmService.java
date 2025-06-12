package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import config.ConfigLoader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Servicio para interactuar con la ia

public class LlmService {

    private final String apiKey; // Clave de autenticación para la API
    private final String model; // Nombre del modelo LLM a utilizar

 // Constructor que carga clave API y modelo desde la configuración externa
    public LlmService() {
        ConfigLoader config = new ConfigLoader();
        this.apiKey = config.getApiKey();
        this.model = config.getModel();
    }

 // Método que consulta al LLM enviando un prompt y devuelve la respuesta
    public String consultarLLM(String prompt) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

     // Crear el mensaje para enviar
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", prompt);
     // Poner el mensaje en un array
        JsonArray messages = new JsonArray();
        messages.add(message);

     // Crear la solicitud con modelo y mensajes
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("model", model);
        jsonBody.add("messages", messages);

        String requestBody = new Gson().toJson(jsonBody); // Convertir a JSON
        
        
// Petición Http
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
     // Procesar la respuesta
        if (response.statusCode() == 200) {
            JsonObject responseJson = new Gson().fromJson(response.body(), JsonObject.class);
            JsonArray choices = responseJson.getAsJsonArray("choices");
            if (choices != null && choices.size() > 0) {
                JsonObject firstChoice = choices.get(0).getAsJsonObject();
                JsonObject messageObj = firstChoice.getAsJsonObject("message");
                if (messageObj != null && messageObj.has("content")) {
                    return messageObj.get("content").getAsString();
                }
            }
            return "No se encontró respuesta del LLM.";
        } else {
            return "Error en la respuesta del API: " + response.statusCode() + " - " + response.body();
        }
    }
}
