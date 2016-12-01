import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import ch.aplu.turtle.*;

/**
 * Created by dylanlafrenz on 11/29/16.
 */
import javax.swing.*;



/**
 * Created by Dylan on 10/6/2016.
 */
public class server {
    private static int port = 23669;
    private static ServerSocket serversocket;
    private static Turtle turt;
    private static double distance;
    private static Socket clientSocket;


    public static void main(String[] args) throws Exception{
        turt = new Turtle();

        distance = 100;
        server.serversocket = new ServerSocket(port);
        System.out.printf("Server Started\n");
        clientSocket=null;
        while(true){
            clientSocket=serversocket.accept();
            if(clientSocket!=null){
                System.out.println("Client accepted");
                handle_client(clientSocket);
            }

        }
    }
    public static void handle_client(Socket clientSocket)throws Exception{
        InputStream in= clientSocket.getInputStream();
        String f="";
        String r="";

        int j;
        while((j =in.read())!='\n') {
            char temp = (char)j;
            f=(Character.toString(temp));
            //System.out.printf("%s\n",f);
            r+=f;

        }
        System.out.println(r.trim());
        handle_turtle(r.trim(),clientSocket);
        clientSocket.close();
        clientSocket=null;




    }
    public static void handle_turtle(String command,Socket clientsocket) throws NumberFormatException,IOException{

        int temp;
        temp =command.indexOf("Z");
        PrintWriter x = new PrintWriter(clientsocket.getOutputStream(),true);

        if (command.equals("North")){
            if((turt.distance(turt.getX(),200))<distance){
                x.write("Cant do that will run off screen");
            }
            else{
                turt.heading(0);
                turt.forward(distance);
            }
        }
        if(command.equals("East")){
            if((turt.distance(200,turt.getY())<distance)){
                x.write("Cant do that will run off screen");
            }
            else{
                turt.heading(90);
                turt.forward(distance);
            }
        }
        if(command.equals("South")){
            if((turt.distance(turt.getX(),-200)<distance)){
                x.write("Cant do that will run off screen");
            }
            else{
                turt.heading(180);
                turt.forward(distance);
            }
        }
        if(command.equals("West")){
            if((turt.distance(-200,turt.getY())<distance)){
                x.write("Cant do that will run off screen");
            }
            else{
                turt.heading(270);
                turt.forward(distance);
            }
        }
        if(temp==0){
            command=command.replace("Z","0");
            temp=Integer.parseInt(command);
            if(temp>100){
                temp=100;
            }
            distance=temp;
            System.out.println(distance);
        }
        if (command.equals("Lift")){
            if(!turt.isPenUp()){
                turt.penUp();
            }
            else{
                x.write("Pen is already up");
            }
        }
        if(command.equals("Lower")){
            if (turt.isPenUp()){
                turt.pd();
            }
            else{
                x.write("Pen is already lowered");
            }

        }
        if(command.equals("Reset")){
            turt.clean();
            turt.setPos(0,0);
        }


    }
}
