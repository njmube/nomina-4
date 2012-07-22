package domain;

public class Adeudo implements java.io.Serializable {
    
    private int id;
    private int empleado;
    private Float cantidad;
    private String concepto;
    private String fecha;
    private String nomina;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleado() {
        return empleado;
    }
    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public float getCantidad() {
        return cantidad;
    }
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNomina() {
        return nomina;
    }

    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

}
