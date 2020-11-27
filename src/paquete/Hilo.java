package paquete;

import java.sql.*;

public class Hilo extends Thread{

    public int inicio;
    public int rango;
    public int totalCalculado;

    public Hilo(int inicio, int rango){

        this.inicio = inicio;
        this.rango = rango;
    }

    @Override
    public void run(){leerRegistros(inicio, rango);}

    private void leerRegistros(int inicio, int rango){

        synchronized (this) {

            int totalCalculado=0;

            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
                PreparedStatement preparacion = conexion.prepareStatement("select INGRESOS from empleados where ID>="+inicio+" and ID<"+rango);
                ResultSet resultado = preparacion.executeQuery();
                while (resultado.next()){
                    totalCalculado += resultado.getInt("INGRESOS");
                }
                setTotalCalculado(totalCalculado);
            }catch(SQLException err){
                err.printStackTrace();
            }
        }
    }

    public void setTotalCalculado(int totalCalculado) {
        this.totalCalculado = totalCalculado;
    }

    public int getTotalCalculado() {
        return totalCalculado;
    }
}
