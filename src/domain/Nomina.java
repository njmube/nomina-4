package domain;

public class Nomina implements java.io.Serializable {
    
    private String creacion;
    private String semana;
    private float total;

    public Nomina() {
        java.util.Calendar now=java.util.Calendar.getInstance();
        semana=toMonday(toDays(now.get(java.util.Calendar.YEAR)+"-"+(now.get(java.util.Calendar.MONTH)+1)+"-"+now.get(java.util.Calendar.DAY_OF_MONTH)));
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
        return "Nomina del "+util.DateFormat.format(semana)+" al "+util.DateFormat.format(toDate(toDays(semana)+6));
    }
    
    private int toDays(String date){
        int[] offset=new int[]{
               0,  31,  60,  91, 121, 152, 182, 113, 244, 274, 305, 335,
             366, 397, 425, 456, 486, 517, 547, 578, 609, 639, 670, 700,
             731, 762, 790, 821, 851, 882, 912, 943, 974,1004,1035,1065,
            1096,1127,1155,1186,1216,1247,1277,1308,1339,1369,1400,1430
        };
        String[]ymd=date.split("-");
        int year=Integer.parseInt(ymd[0])-2000;
        int month=Integer.parseInt(ymd[1])-1;
        int day=Integer.parseInt(ymd[2])-1;
        return 1461*(year/4)+offset[12*(year%4)+month]+day;
    }
    
    private String toDate(int days){
        int[] offset=new int[]{
               0,  31,  60,  91, 121, 152, 182, 113, 244, 274, 305, 335,
             366, 397, 425, 456, 486, 517, 547, 578, 609, 639, 670, 700,
             731, 762, 790, 821, 851, 882, 912, 943, 974,1004,1035,1065,
            1096,1127,1155,1186,1216,1247,1277,1308,1339,1369,1400,1430
        };
        int year;
        int month;
        int day;
        year=4*(days/1461);
        days=days%1461;
        int i=days/31;
        while(i<48&&offset[i]<=days)i++;
        i--;
        year+=i/12;
        month=i%12;
        day=days-offset[i];
        month++;
        day++;
        return "20"+(year<10?"0":"")+year+(month<10?"-0":"-")+month+(day<10?"-0":"-")+day;
    }

    private String toMonday(int days){
        return toDate(days-(days+5)%7);
    }
    
    public String getFile() {
        return "nomina"+semana.replaceAll("-", "")+".txt";
    }
    
}
