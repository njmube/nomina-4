package control;

public class AdeudoDAO extends org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport {

    private static class Mapper implements org.springframework.jdbc.core.simple.ParameterizedRowMapper <domain.Adeudo> {
        public domain.Adeudo mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            domain.Adeudo e = new domain.Adeudo();
            e.setCantidad(rs.getFloat("cantidad"));
            e.setConcepto(rs.getInt("concepto")==0?"Golpe":"Préstamo");
            e.setEmpleado(rs.getInt("empleado"));
            e.setFecha(rs.getString("fecha"));
            e.setId(rs.getInt("id"));
            e.setNomina(rs.getString("nomina"));
            return e;
        }
    }

    public java.util.List<domain.Adeudo> select(int empleado, int concepto,boolean todos) {
        return getSimpleJdbcTemplate().query("select * from adeudo where empleado=:empleado and concepto=:concepto"+(todos?"":" and nomina is null"), new Mapper(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("empleado", empleado)
                .addValue("concepto", concepto));
    }
    
    public void insert(domain.Adeudo adeudo){
        getSimpleJdbcTemplate().update("insert into adeudo " +
                "(empleado,cantidad,concepto,fecha) " +
                "values (:empleado,:cantidad,:concepto,curdate())", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("empleado", adeudo.getEmpleado())
            .addValue("cantidad", adeudo.getCantidad())
            .addValue("concepto", adeudo.getConcepto().equals("Golpe")?0:1));
    }

    public void update(String nomina, domain.Adeudo adeudo) {
        getSimpleJdbcTemplate().update("update adeudo set " +
                "nomina=:nomina" +
                " where id=:id", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("nomina", nomina)
            .addValue("id", adeudo.getId()));
    }

}
