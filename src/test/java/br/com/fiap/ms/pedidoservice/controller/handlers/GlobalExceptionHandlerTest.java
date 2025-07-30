package br.com.fiap.ms.pedidoservice.controller.handlers;

import br.com.fiap.ms.pedidoservice.exception.SystemBaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void deveTratarSystemBaseException() {
        SystemBaseException ex = mock(SystemBaseException.class);
        when(ex.getHttpStatus()).thenReturn(422);
        when(ex.getCode()).thenReturn("pedido.invalido");
        when(ex.getMessage()).thenReturn("Pedido não encontrado");

        ResponseEntity<Map<String, Object>> response = handler.handleSystemBaseException(ex);

        assertEquals(422, response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertEquals("pedido.invalido", body.get("code"));
        assertEquals("Pedido não encontrado", body.get("message"));
        assertEquals(422, body.get("status"));
        assertTrue(body.containsKey("timestamp"));
    }

    @Test
    void deveTratarHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("JSON inválido");

        ResponseEntity<Map<String, Object>> response = handler.handleHttpMessageNotReadableException(ex);

        assertEquals(400, response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertEquals("bad.request", body.get("code"));
        assertEquals(
                "O corpo da requisição é obrigatório ou está inválido (JSON esperado).",
                body.get("message")
        );
        assertEquals(400, body.get("status"));
        assertTrue(body.containsKey("timestamp"));
    }
}
