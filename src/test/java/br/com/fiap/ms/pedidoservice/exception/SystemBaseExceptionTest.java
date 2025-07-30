package br.com.fiap.ms.pedidoservice.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemBaseExceptionTest {

    static class FakeSystemBaseException extends SystemBaseException {
        @Override
        public String getCode() {
            return "fake.code";
        }

        @Override
        public Integer getHttpStatus() {
            return 400;
        }

        @Override
        public String getMessage() {
            return "Mensagem fake de erro";
        }
    }

    @Test
    void deveRetornarValoresCorretosDosMetodos() {
        SystemBaseException exception = new FakeSystemBaseException();

        assertEquals("fake.code", exception.getCode());
        assertEquals(400, exception.getHttpStatus());
        assertEquals("Mensagem fake de erro", exception.getMessage());
    }
}
