package ejemploSockets;

import ejemploSockets.LaminaMarcoCliente.PaqueteEnvio;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
                
                Thread mihilo = new Thread (this);
                mihilo.start();
		
		}
	
	private	JTextArea areatexto;

    @Override
    public void run() {/*metodo run que se ejecuta continuamente en segundo plano*/
            try {
                //System.out.println("estoy escuchando");
                
                ServerSocket servidor =new ServerSocket(9999);
                /*abre la conexion a traves del puerto especificado*/
                
                String nick,ip,mensaje; 
                /*creamos variable para almacenar la informacion que le llega*/
                
                PaqueteEnvio paqueteRecibido;/*objeto construido para recibir la informacion*/
                
                while(true){ 
                    /*creamos bucle infinito para poder enviar varios mensajes
                    y que se muestren en la pantalla servidor*/
                
                    Socket misocket = servidor.accept();
                    /*aceptar las conexiones que vengan del exterior con el metodo "accept"*/
                    
                    ObjectInputStream paqueteDatosRecibido = new ObjectInputStream (misocket.getInputStream());
                    /*recibe objectos que vengan en en flujo de datos*/
                    
                    paqueteRecibido= (PaqueteEnvio) paqueteDatosRecibido.readObject();
                    /*lee con readObject, lo que esta almacenado en el flujo de datos "paqueteDatosRecibido"
                    y lo almacene en "paqueteRecibido"*/
                    
                    nick = paqueteRecibido.getNick();
                    /*guardamos dentro de la variable nick,lo que ha llegado en el paquete recibido*/
                    
                    ip = paqueteRecibido.getIp();
                    /*guardamos dentro de la variable ip,lo que ha llegado en el paquete recibido*/
                    
                    mensaje = paqueteRecibido.getMensaje();
                    /*guardamos dentro de la variable mensaje,lo que ha llegado en el paquete recibido*/
                    
            
                    /*DataInputStream  flujoEntrada = new DataInputStream(misocket.getInputStream());
                    /* - lee lo que viene dentro del flujo de datos
                    - hay un flujo de datos que usara como medio de transporte el socket "misocket.getInputStream" 
                    que afecta todas las conexiones
                
                
                    String mensajeTexto= flujoEntrada.readUTF();
                    tenemos alamacenada en la variable "mensajeTexto"
                    lo que viaja por el flujo de datos que viene del cliente
                
                    areatexto.append("\n"+ mensajeTexto);
                    escribe lo que esta almacenado en "mensajeTexto"
                    cada que enviamos un mensaje , lo escriba en una linea diferente*/
                    
                    areatexto.append("\n"+ nick );
                    misocket.close();
                    /*cerramos la conexion*/
                 }
                
            } catch (IOException |ClassNotFoundException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
