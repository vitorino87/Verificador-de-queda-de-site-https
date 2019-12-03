/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificador.de.queda.de.sites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tiago.lucas
 */
public class VerificadorDeQuedaDeSitesTest {
    
    public VerificadorDeQuedaDeSitesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testMethodIniciar(){
        System.out.println("Metodo que inicia a verificação");
        VerificadorDeQuedaDeSites v = new VerificadorDeQuedaDeSites();
        assertEquals(true|false,v.iniciar());
    }
    
    @Test
    public void testMethodPing(){
        System.out.println("Metodo que pinga");        
        assertEquals(true|false,VerificadorDeQuedaDeSites.pingHost("https://sig.ufabc.edu.br/sipac/", 1000));
    }
}
