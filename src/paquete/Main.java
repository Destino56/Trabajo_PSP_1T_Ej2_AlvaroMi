package paquete;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        leerSecuencial();
        leer5Hilos();

    }

    private static void leerSecuencial(){

        int total=0;

        try {

            long time_1 = System.currentTimeMillis();
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
            PreparedStatement preparacion = conexion.prepareStatement("select INGRESOS from empleados");
            ResultSet resultado = preparacion.executeQuery();

            while (resultado.next()) {
                total += resultado.getInt("INGRESOS");
            }
            System.out.println("\nTotal calculado secuencialmente: "+total+"€");
            long time_2 =System.currentTimeMillis();
            int finalTime = (int) (time_2 - time_1);
            System.out.println("He tardado "+finalTime+" milisegundos en terminar.");

        }catch(SQLException e){
            System.out.println("Fallo en el SQL");
        }
    }

    private static void leer5Hilos(){

        try{

            long time_3 = System.currentTimeMillis();
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
            PreparedStatement preparacion = conexion.prepareStatement("select count(INGRESOS) total from empleados");
            ResultSet resultado = preparacion.executeQuery();

            resultado.next();
            int rango = resultado.getInt("total")/5;
            int resto = rango % 5;
            int totalCalculado = 0;

            ArrayList<Hilo> threadList = new ArrayList<>();
            Hilo hilo;

            for(int i = 0 ; i < 5 ; i++){
                if (i == 4){
                    threadList.add(hilo = new Hilo(rango*i, rango*(i+1)+resto));
                }else{
                    threadList.add(hilo = new Hilo(rango*i, rango*(i+1)));
                }
            }
            for(Hilo thread : threadList){
                thread.start();
            }
            threadList.forEach(hilos -> {
                try {
                    hilos.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            for(Hilo thread : threadList){
                totalCalculado += thread.getTotalCalculado();
            }

            long time_4 =System.currentTimeMillis();
            int finalTime = (int) (time_4 - time_3);
            System.out.println("\nTotal calculado con 5 Hilos: "+totalCalculado+"€");
            System.out.println("He tardado "+finalTime+" milisegundos en terminar.");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
