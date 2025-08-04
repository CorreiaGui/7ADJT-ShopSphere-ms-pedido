package br.com.fiap.ms.pedidoservice.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PedidoConstantsTest {

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<PedidoConstants> constructor = PedidoConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        PedidoConstants instance = constructor.newInstance();
        assertNotNull(instance);
    }

    @Test
    void constantesEstaoCorretas() {
        assertEquals("/api/v1/pedidos/processar-pagamento", PedidoConstants.V1_PEDIDOS_PAGAMENTOS);
        assertEquals("/api/v1/pedidos", PedidoConstants.V1_PEDIDOS);
        assertEquals("application/json", PedidoConstants.PRODUCES);
    }

}
