package domain;

import java.io.Serializable;
import java.util.Calendar;

import util.DateFormat;

/**
 * Bean que representa la nomina total de una semana en particular.
 * Cada nomina es identificada de forma unica por la semana a la que representa.
 * @author David Barba
 * @author Rafael Diaz
 */
public class Nomina implements Serializable {
  /**
   * Serial version UID auto-generado.
   */
  private static final long serialVersionUID = 8515973537201231522L;

  /**
   * Fecha de creacion de la nomina.
   */
  private String creacion;
  /**
   * Identificador de la semana que representa a la nomina.
   * Corresponde al inicio de la semana, i.e. al lunes.
   */
  private String monday;
  /**
   * Corresponde al fin de la semana, i.e. al domingo.
   */
  private String sunday;
  /**
   * Total de la nomina.
   */
  private float total;

  /**
   * Crea una nueva nomina. La fecha de creacion corresponde al dia en curso; y la semana
   * a la que la nomina se vincula se representa por el lunes de la semana que comprende a
   * la fecha de creacion.
   */
  public Nomina() {
    this(Calendar.getInstance());
  }
  Nomina(Calendar date) {
    // Dado que en US, que es el valor por default, el primer dia de la semana es domingo
    // se necesita hacer un cambio para compaginar el calendario con el primer dia de la
    // semana usado en el sistema.
    // De otra forma cualquier nomina generada en domingos tendria valores incorrectos.
    date.setFirstDayOfWeek(Calendar.MONDAY);
    // El primer dia de la semana es el lunes.
    date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    setSemana(dateToString(date));
  }

  /**
   * Metodo que da formato a las fechas.
   * El formato que se sigue es: "y-m-d".
   */
  private String dateToString(Calendar date) {
    return date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" +
      date.get(Calendar.DAY_OF_MONTH);
  }

  public String getCreacion() {
    return creacion;
  }

  public void setCreacion(String creacion) {
    this.creacion = creacion;
  }

  public String getSemana() {
    return monday;
  }

  public void setSemana(String monday) {
    this.monday = monday;
    // El domingo se ajusta en el setSemana dado que cuando los datos se recuperan de la
    // base de datos se utiliza el setter en vez del constructor para ajustar la fecha
    // de identificacion de la nomina.
    Calendar date = Calendar.getInstance();
    date.setFirstDayOfWeek(Calendar.MONDAY);
    // Es necesario recuperar los valores del dia, mes y anho para poder construir el
    // domingo.
    String[] mondaySplit = monday.split("-");
    date.set(Calendar.YEAR, Integer.parseInt(mondaySplit[0]));
    date.set(Calendar.MONTH, Integer.parseInt(mondaySplit[1]) - 1);
    date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(mondaySplit[2]));
    // Una vez que hemos reproducido el lunes, ahora podemos ajustar la fecha a domingo
    // Debido a un bug en la version de java es necesario hacer el get del DAY_OF_WEEK
    // para refrescarlo antes de hacer el SET a domingo
    date.get(Calendar.DAY_OF_WEEK);
    date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    sunday = dateToString(date);
  }

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  public void addTotal(float subtotal) {
    total += subtotal;
  }

  @Override
  public String toString() {
    return "Nomina del " + DateFormat.format(monday) + " al " + DateFormat.format(sunday);
  }

  public String getFile() {
    return "nomina" + monday.replaceAll("-", "") + ".txt";
  }
}
