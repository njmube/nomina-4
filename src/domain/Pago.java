package domain;

public class Pago implements java.io.Serializable {
    
    private String nomina;
    private int empleado;
    private float sueldo;
    private int faltas;
    private float infonavit;
    private float descuento;
    private float vale;
    private float golpes;
    private float prestamos;
    private float saldoAnterior;
    
    public void addAdeudo(Adeudo adeudo) {
        float cantidad=adeudo.getCantidad();
        saldoAnterior+=cantidad;
        if(adeudo.getConcepto().equals("Golpe")){
            golpes+=cantidad;
        }else{
            prestamos+=cantidad;
        }
    }

    public String getNomina() {
        return nomina;
    }
    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    public int getEmpleado() {
        return empleado;
    }
    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public float getSueldo() {
        return sueldo;
    }
    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public int getFaltas() {
        return faltas;
    }
    public void setFaltas(int faltas) {
        this.faltas = faltas;
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

    public float getVale() {
        return vale;
    }
    public void setVale(float vale) {
        this.vale = vale;
    }

    public float getGolpes() {
        return golpes;
    }
    public void setGolpes(float golpes) {
        this.golpes = golpes;
    }

    public float getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(float prestamos) {
        this.prestamos = prestamos;
    }
    
    public float getSubtotal(){
        return sueldo*((float)(7-faltas)/7)-infonavit-descuento-vale;
    }

    public float getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(float saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

}
