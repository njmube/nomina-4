package control;
import domain.Pago;
import java.util.*;

public class PagoDAO extends org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport {

    private static class Mapper implements org.springframework.jdbc.core.simple.ParameterizedRowMapper <Pago> {
        public Pago mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Pago e = new Pago();
            e.setDescuento(rs.getFloat("descuento"));
            e.setEmpleado(rs.getInt("empleado"));
            e.setFaltas(rs.getInt("faltas"));
            e.setGolpes(rs.getFloat("golpes"));
            e.setInfonavit(rs.getFloat("infonavit"));
            e.setNomina(rs.getString("nomina"));
            e.setPrestamos(rs.getFloat("prestamos"));
            e.setSaldoAnterior(rs.getFloat("saldoAnterior"));
            e.setSueldo(rs.getFloat("sueldo"));
            e.setVale(rs.getFloat("vale"));
            return e;
        }
    }

    public void insert(String nomina, Pago pago) {
        getSimpleJdbcTemplate().update("insert into pago " +
                "(nomina,empleado,sueldo,faltas,infonavit,descuento,vale,golpes,prestamos,saldoAnterior) " +
                "values (:nomina,:empleado,:sueldo,:faltas,:infonavit,:descuento,:vale,:golpes,:prestamos,:saldoAnterior)", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("nomina",nomina)
            .addValue("empleado",pago.getEmpleado())
            .addValue("sueldo",pago.getSueldo())
            .addValue("faltas",pago.getFaltas())
            .addValue("infonavit",pago.getInfonavit())
            .addValue("descuento",pago.getDescuento())
            .addValue("vale",pago.getVale())
            .addValue("golpes",pago.getGolpes())
            .addValue("saldoAnterior",pago.getSaldoAnterior())
            .addValue("prestamos",pago.getPrestamos()));
    }

    List<Pago> select(String nomina){
        return getSimpleJdbcTemplate().query("select * from pago where nomina=:nomina"
                ,new Mapper(),new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("nomina",nomina));
    }

    void update(String nomina, Pago pago){
        getSimpleJdbcTemplate().update("update pago set"+
                " sueldo=:sueldo"+
                ",faltas=:faltas"+
                ",infonavit=:infonavit"+
                ",descuento=:descuento"+
                ",vale=:vale"+
                " where nomina=:nomina and empleado=:empleado"
                ,new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("sueldo",pago.getSueldo())
                .addValue("faltas",pago.getFaltas())
                .addValue("vale",pago.getVale())
                .addValue("descuento",pago.getDescuento())
                .addValue("infonavit",pago.getInfonavit())
                .addValue("nomina",nomina)
                .addValue("empleado",pago.getEmpleado()));
    }

}
