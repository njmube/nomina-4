package domain;

public class Nomina implements java.io.Serializable {
    
    private String creacion;
    private String semana;
    private float total;

    public Nomina() {
        java.util.Calendar now=java.util.Calendar.getInstance();
        now.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY);
        semana=now.get(java.util.Calendar.YEAR)+"-"+(now.get(java.util.Calendar.MONTH)+1)+"-"+now.get(java.util.Calendar.DAY_OF_MONTH);
    }

    public String getCreacion() {
        return creacion;
    }
    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public String getSemana() {
        return semana;
    }
    public void setSemana(String semana) {
        this.semana = semana;
    }

    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public void addTotal(float subtotal) {
        total+=subtotal;
    }
    
    @Override
    public String toString(){
        java.util.Calendar sunday=java.util.Calendar.getInstance();
        sunday.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY);
        return "Nomina del "+util.DateFormat.format(semana)+" al "+util.DateFormat.format(sunday.get(java.util.Calendar.YEAR)+"-"+(sunday.get(java.util.Calendar.MONTH)+1)+"-"+sunday.get(java.util.Calendar.DAY_OF_MONTH));
    }
            
    public String getFile() {
        return "nomina"+semana.replaceAll("-", "")+".txt";
    }
    
}