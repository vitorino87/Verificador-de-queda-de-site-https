/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificador.de.queda.de.sites;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import view.PaginaForaDoAr;

/**
 *Esse programa verificar� a cada 15 minutos se um certo site est� no ar
 * Pelo fato do site ser https � necess�rio adicionar o certificado no cacert
 * por meio da ferramenta keytool
 * @author tiago.lucas
 */
public class VerificadorDeQuedaDeSites {

    final int TIMEOUT = 20000;    //timeout em milessegundos, 20s
    final int INTERVAL = 900000; //intervalo em milissegundos, 15 minutos
    final String HOST = "https://sig.ufabc.edu.br/sipac/"; //host ou p�gina https
    /**
     * @param args the command line arguments
     */
    PaginaForaDoAr p = new PaginaForaDoAr();
    public static void main(String[] args) throws InterruptedException {        
        VerificadorDeQuedaDeSites v = new VerificadorDeQuedaDeSites();
        while(true){            
            v.iniciar();
            Thread.sleep(1000);
        }        
    }        
    
    public void iniciar(){
        boolean b = pingHost(HOST,10000);
        if(!b){
            p.setStatus(true);
            p.setVisible(true);                  
        }else{
            p.setStatus(false);
            p.setVisible(false);
            //System.out.println("Ok!");                
        }   
    }
    
    public static boolean pingHost(String url, int timeout) {        
        //url = url.replaceFirst("^https", "http"); //utilize essa linha caso deseje que o programa substitua https por http
        try {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD"); //m�todo utilizado, o padr�o � get, pode utilizar post, delete, etc. neste caso utilizo o head para receber menos informa��es
        //connection.connect(); //realiza-se a conex�o
        connection.setConnectTimeout(timeout);     //definindo timeout
        connection.setReadTimeout(timeout);        //definindo timeout
        int responseCode = connection.getResponseCode(); //Codigo de conex�o recebido 
        //String test =  connection.getResponseMessage(); //Mensagem de conex�o recebida
        return (200 <= responseCode && responseCode <= 399);
    } catch (IOException exception) {
        return false;
    }
        
        // O C�DIGO ABAIXO � �TIL PARA PINGAR UM IP OU HOST
    //try (Socket socket = new Socket()) {
    //    socket.connect(new InetSocketAddress(host, port), timeout);
    //    return true;
    //} catch (IOException e) {
    //    return false; // Either timeout or unreachable or failed DNS lookup.
    //}
}
    
}
