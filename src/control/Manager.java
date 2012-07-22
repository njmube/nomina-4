package control;
import domain.*;
import java.util.*;

public class Manager {
    
    AdeudoDAO adeudoDAO;
    EmpleadoDAO empleadoDAO;
    IdDAO idDAO;
    NominaDAO nominaDAO;
    PagoDAO pagoDAO;

    public Manager() {
        Properties jdbc=new Properties();
        try{
            jdbc.load(new java.io.FileInputStream("jdbc.properties"));
        }catch(java.io.IOException e){e.printStackTrace();}
        String driver=jdbc.getProperty("driver");
        String url=jdbc.getProperty("url");
        String user=jdbc.getProperty("user");
        String pass=jdbc.getProperty("pass");
        org.springframework.jdbc.datasource.DriverManagerDataSource source=new org.springframework.jdbc.datasource.DriverManagerDataSource(driver,url,user,pass);
        adeudoDAO = new AdeudoDAO();
        adeudoDAO.setDataSource(source);
        empleadoDAO = new EmpleadoDAO();
        empleadoDAO.setDataSource(source);
        idDAO = new IdDAO();
        idDAO.setDataSource(source);
        nominaDAO = new NominaDAO();
        nominaDAO.setDataSource(source);
        pagoDAO = new PagoDAO();
        pagoDAO.setDataSource(source);
    }

    public void insertNomina(Nomina nomina) {
        nominaDAO.insert(nomina);
    }

    public List<Adeudo> selectAdeudos(int empleado, int concepto, boolean todos) {
        return adeudoDAO.select(empleado,concepto,todos);
    }

    public Empleado selectEmpleado(int id) {
        return empleadoDAO.select(id);
    }

    public List<Empleado> selectEmpleados(String nombre, String puesto,boolean todos) {
        return empleadoDAO.select(nombre, puesto, todos);
    }

    public int insertEmpleado(String nombre, String direccion, String telefono, String puesto, float sueldo) {
        return empleadoDAO.insert(idDAO.get("empleado"), nombre, direccion, telefono, puesto, sueldo);
    }

    public void insertAdeudo(Adeudo adeudo) {
        adeudoDAO.insert(adeudo);
        empleadoDAO.update2(adeudo.getEmpleado(),-adeudo.getCantidad());
    }

    public void insertPagos(String nomina, List<Pago> pagos) {
        int i=0;
        int maxi=pagos.size();
        while(i<maxi){
            Pago pago=pagos.get(i);
            pagoDAO.insert(nomina,pago);
            empleadoDAO.update(pago.getEmpleado(),pago.getSaldoAnterior()-pago.getDescuento());
            i++;
        }
    }

    public Nomina selectNomina(String semana) {
        return nominaDAO.select(semana);
    }

    public List<Pago> selectPagos(String nomina){
        return pagoDAO.select(nomina);
    }
    
    public List<Nomina> selectNominas(){
        return nominaDAO.select();
    }

    public void updateAdeudos(String nomina, List<Adeudo> adeudos) {
        int i=0;
        int maxi=adeudos.size();
        while(i<maxi){
            adeudoDAO.update(nomina,adeudos.get(i));
            i++;
        }
    }

    public void updateEmpleado(Empleado empleado,boolean accion) {
        empleadoDAO.update(empleado,accion);
    }

    public void updateNomina(Nomina nomina) {
        nominaDAO.update(nomina);
    }

    public void updatePagos(String nomina, List<Pago> pagos) {
        int i=0;
        int maxi=pagos.size();
        while(i<maxi){
            Pago pago=pagos.get(i);
            pagoDAO.update(nomina,pago);
            empleadoDAO.update(pago.getEmpleado(),pago.getSaldoAnterior()-pago.getDescuento());
            i++;
        }
    }

}
