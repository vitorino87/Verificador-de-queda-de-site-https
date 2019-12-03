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
 *Esse programa verificará a cada 15 minutos se um certo site está no ar
 * Pelo fato do site ser https é necessário adicionar o certificado no cacert
 * por meio da ferramenta keytool
 * @author tiago.lucas
 */
public class VerificadorDeQuedaDeSites {

    final int TIMEOUT = 60000;    //timeout em milessegundos, 1 min
    final static int INTERVAL = 240000; //intervalo em milissegundos, 4 minutos
    final String HOST = "https://sig.ufabc.edu.br/sipac/"; //host ou página https
    //final String HOST = "http://localhost:8080"; //host ou página https
    
    PaginaForaDoAr p = new PaginaForaDoAr();
    
    
    public static void main(String[] args) throws InterruptedException {        
        VerificadorDeQuedaDeSites v = new VerificadorDeQuedaDeSites();
        while(true){            
            if(!v.iniciar()){
                Thread.sleep(1000); //se v.iniciar() retornar falso, significa que o site está fora do ar e por isso o site será checado a cada 1 segundos.
            }else{
                Thread.sleep(INTERVAL); //se v.iniciar() retornar true, significa que o site está no ar, e a próxima verificação será em 4 minutos
            }            
        }        
    }        
    
    public boolean iniciar(){
        boolean b = pingHost(HOST,TIMEOUT);
        if(!b){
            p.setStatus(true);
            p.setVisible(true);                  
        }else{
            p.setStatus(false);
            p.setVisible(false);
            //System.out.println("Ok!");                
        }   
        return b;
    }
    
    public static boolean pingHost(String url, int timeout) {        
        //url = url.replaceFirst("^https", "http"); //utilize essa linha caso deseje que o programa substitua https por http
        try {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD"); //método utilizado, o padrão é get, pode utilizar post, delete, etc. neste caso utilizo o head para receber menos informações
        //connection.connect(); //realiza-se a conexão
        connection.setConnectTimeout(timeout);     //definindo timeout
        connection.setReadTimeout(timeout);        //definindo timeout
        int responseCode = connection.getResponseCode(); //Codigo de conexão recebido 
        //String test =  connection.getResponseMessage(); //Mensagem de conexão recebida
        return (200 <= responseCode && responseCode <= 399);
    } catch (IOException exception) {
        return false;
    }
        
        // O CÓDIGO ABAIXO É ÚTIL PARA PINGAR UM IP OU HOST
    //try (Socket socket = new Socket()) {
    //    socket.connect(new InetSocketAddress(host, port), timeout);
    //    return true;
    //} catch (IOException e) {
    //    return false; // Either timeout or unreachable or failed DNS lookup.
    //}
}
    
}
