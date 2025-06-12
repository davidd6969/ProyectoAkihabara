package service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LlmServiceTest {

    @Test
    void testConsultarLLM() {
        LlmService service = new LlmService();
        String prompt = "Hola, ¿cómo estás?";

        try {
            String respuesta = service.consultarLLM(prompt);
            assertNotNull(respuesta);
            assertFalse(respuesta.isEmpty());
            System.out.println("Respuesta del LLM: " + respuesta);
        } catch (Exception e) {
            fail("La llamada a consultarLLM lanzó una excepción: " + e.getMessage());
        }
    }
}
 