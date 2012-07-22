package domain;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link Nomina}.
 * @author rafael.diaz
 */
public class NominaTest {
  @Test
  public void testCreateSunday() {
    // Create nomina on Sunday.
    Nomina sundayNomina = new Nomina(getCalendar(2012, 7, 22));
    Assert.assertEquals(sundayNomina.getSemana(), "2012-7-16");
    Assert.assertEquals(sundayNomina.getFile(), "nomina2012716.txt");
    Assert.assertEquals(sundayNomina.toString(), "Nomina del 16-jul-2012 al 22-jul-2012");
  }
  @Test
  public void testCreateMonday() {
    // Create nomina on Monday.
    Nomina sundayNomina = new Nomina(getCalendar(2012, 7, 23));
    Assert.assertEquals(sundayNomina.getSemana(), "2012-7-23");
    Assert.assertEquals(sundayNomina.getFile(), "nomina2012723.txt");
    Assert.assertEquals(sundayNomina.toString(), "Nomina del 23-jul-2012 al 29-jul-2012");
  }
  @Test
  public void testCreateFirstDayConflict() {
    // Set the calendar first day of week to other value than Monday (e.g. Wednesday)
    Calendar calendar = getCalendar(2012, 7, 23);
    calendar.setFirstDayOfWeek(Calendar.WEDNESDAY);
    Nomina sundayNomina = new Nomina(calendar);
    Assert.assertEquals(sundayNomina.getSemana(), "2012-7-23");
    Assert.assertEquals(sundayNomina.getFile(), "nomina2012723.txt");
    Assert.assertEquals(sundayNomina.toString(), "Nomina del 23-jul-2012 al 29-jul-2012");
  }

  @Test
  public void testCreacion() {
    Nomina nomina = new Nomina();
    // By default the creacion field is not set on creation time.
    Assert.assertNull(nomina.getCreacion());
    // There is no validation for the creacion field. We can literally set any value.
    nomina.setCreacion("abc");
    Assert.assertEquals(nomina.getCreacion(), "abc");
    // Setting creacion value multiple times
    nomina.setCreacion("xyz");
    Assert.assertEquals(nomina.getCreacion(), "xyz");
  }

  @Test
  public void testTotal() {
    Nomina nomina = new Nomina();
    // By default the total of a new nomina is zero.
    Assert.assertEquals(nomina.getTotal(), 0f, 0f);
    // Adding value to the nomina total.
    nomina.addTotal(1.1f);
    Assert.assertEquals(nomina.getTotal(), 1.1f, 0f);
    // There is no restriction about adding negative values and getting negative totals.
    nomina.addTotal(-2.2f);
    Assert.assertEquals(nomina.getTotal(), -1.1f, 0f);
  }

  /**
   * Test utility method for creating calendar instances.
   * @param year
   * @param month valid expected values are from 1-12
   * @param day valid expected values are from 1-..
   */
  private Calendar getCalendar(int year, int month, int day) {
    Calendar instance = Calendar.getInstance();
    instance.set(Calendar.YEAR, year);
    instance.set(Calendar.MONTH, month - 1);
    instance.set(Calendar.DAY_OF_MONTH, day);
    return instance;
  }
}
