package domain;

public class Empleado implements java.io.Serializable {
    
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String puesto;
    private float sueldo;
    private float infonavit;
    private float descuento;
    private float saldo;
    private String alta;
    private String baja;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public float getSueldo() {
        return sueldo;
    }
    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public float getInfonavit() {
        return infonavit;
    }
    public void setInfonavit(float infonavit) {
        this.infonavit = infonavit;
    }

    public float getDescuento() {
        return descuento;
    }
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getAlta() {
        return alta;
    }
    public void setAlta(String alta) {
        this.alta = alta;
    }

    public String getBaja() {
        return baja;
    }
    public void setBaja(String baja) {
        this.baja = baja;
    }
    
    public String getEstado(){
        return baja==null?"Activo":"Inactivo ("+util.DateFormat.format(baja)+")";
    }
    
    public Object[] getRow(){
        return new Object[] {id,nombre,puesto,getEstado()};
    }

}
