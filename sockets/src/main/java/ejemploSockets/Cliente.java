package ejemploSockets;

import javax.swing.*;

import java.awt.event.*;
import java.io.DataOutputStream; /*paquete para guardar texto en un archivo*/
import java.io.IOException;
import java.io.ObjectOutputStream;/*paquete para guardar objetos en archivos*/
import java.io.Serializable; 
/*paquete para serializar (convertir en bytes) una clase y su contenido*/

import java.net.*; // paquete para usar "SOCKET"
import java.util.logging.Level;
import java.util.logging.Logger;



public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
               
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
            
                nick= new JTextField(5); 
                /*se crea el cuadro de texto nick con longitud 5*/
                
                add (nick); 
                /*se añade el cuadro de texto*/
	
		JLabel texto=new JLabel("=== CHAT ===");
		
		add(texto);
                
                ip= new JTextField(8); 
                /*se crea variable IP con longitud 8*/
                
                add (ip);
                /*se añade el cuadro de texto*/
                
                 
                campochat = new JTextArea (12,20);
                /*se crea la variable campo chat y se le asigna
                la coordenada donde estara ubicado*/
                
                add(campochat);
                /*se añade campo chat*/
	
		campo1=new JTextField(20);
                /*se crea la variable campo 1 , que sera el campo de texto*/
	
		add(campo1);/*se añade el cuadro de texto*/		
	
		miboton=new JButton("Enviar");
                enviaTexto mievento = new enviaTexto();
                miboton.addActionListener(mievento);
		
		add(miboton);	
		
	}
	private class enviaTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket misocket = new Socket("192.168.1.33",9999);
                /*se crea el socket, definiendo hacia donde ira conectado (dir IP)
                y que puerto usara para conectarse al destinatario*/
                
                /*DataOutputStream flujoSalida = new DataOutputStream(misocket.getOutputStream());
                
                flujoSalida.writeUTF(campo1.getText());
                
                flujoSalida.close();*/
                PaqueteEnvio datos = new PaqueteEnvio ();
                /*instanciamos la clase, le damos un nombre "datos"*/
                
                datos.setNick(nick.getText());
                /*con la instancia "datos", 
                -almacenamos lo que hay dentro del cuadro de texto "nick
                
                - con el metodo getText , obtengo lo que hay dentro del cuadro de texto nick
                  y lo almaceno en setNick"*/
                
                datos.setIp(ip.getText());
                /*con la instancia "datos", 
                -almacenamos lo que hay dentro del cuadro de texto "ip
                
                - con el metodo getText , obtengo lo que hay dentro del cuadro de texto ip
                  y lo almaceno en setip"*/
                
                datos.setMensaje(campo1.getText());
                /*con la instancia "datos", 
                -almacenamos lo que hay dentro del cuadro de texto "campo1"
                que es donde escribo el mensaje
                
                - con el metodo getText , obtengo lo que hay dentro del cuadro de texto campo1
                  y lo almaceno en setMensaje"*/
                
                ObjectOutputStream paqueteDatos = new ObjectOutputStream (misocket.getOutputStream());
                /*intancio la clase ObjectOutput Stream,
                para crear un flujo de datos de salida para poder enviar
                el objeto*/
                
                paqueteDatos.writeObject(datos);
                /*usamos writeObject, para indicarle a nuestro flujo de datos "paqueteDatos"
                que envie nuestro objeto "datos"*/
                
                misocket.close();/*cerramos el socket*/
                
                /*SERIALIZACION: le dice a una clase o a los objetos de la clase
                que puede convertirse en una serie de bytes (sucesion de 0 y 1 10001110100)*/
                
                //System.out.println(campo1.getText());// mensaje que aparece al oprimir el boton ENVIAR
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
            
        }
	
	
		
		
		
	private JTextField campo1, nick, ip;
        /*JTextField ,cuadros de texto*/
        
        private JTextArea campochat;
        /*JTextArea,espacio donde se va a mostrar el chat*/
	
	private JButton miboton;
        /*JButton ,boton enviar*/
        
        
        class PaqueteEnvio implements Serializable{
            private String nick, ip, mensaje;

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
        }
	
}