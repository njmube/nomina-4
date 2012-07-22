package control;
import domain.Empleado;
import java.util.*;

public class EmpleadoDAO extends org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport {

    private static class Mapper implements org.springframework.jdbc.core.simple.ParameterizedRowMapper <Empleado> {
        public Empleado mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Empleado e = new Empleado();
            e.setAlta(rs.getString("alta"));
            e.setBaja(rs.getString("baja"));
            e.setDescuento(rs.getFloat("descuento"));
            e.setDireccion(rs.getString("direccion"));
            e.setId(rs.getInt("id"));
            e.setInfonavit(rs.getFloat("infonavit"));
            e.setNombre(rs.getString("nombre"));
            e.setPuesto(rs.getString("puesto"));
            e.setSaldo(rs.getFloat("saldo"));
            e.setSueldo(rs.getFloat("sueldo"));
            e.setTelefono(rs.getString("telefono"));
            return e;
        }
    }

    public List<Empleado> select(String nombre, String puesto,boolean todos) {
        return getSimpleJdbcTemplate().query("select * from empleado where nombre like :nombre and puesto like :puesto"+(todos?"":" and baja is null"), new Mapper(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("nombre", "%"+nombre+"%")
                .addValue("puesto", "%"+puesto+"%"));
    }

    public int insert(int id, String nombre, String direccion, String telefono, String puesto, float sueldo) {
        getSimpleJdbcTemplate().update("insert into empleado " +
                "(id,nombre,direccion,telefono,puesto,sueldo,alta) " +
                "values (:id,:nombre,:direccion,:telefono,:puesto,:sueldo,curdate())", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("id", id)
            .addValue("nombre", nombre)
            .addValue("direccion", direccion)
            .addValue("telefono", telefono)
            .addValue("puesto", puesto)
            .addValue("sueldo", sueldo));
        return id;
    }

    public void update(Empleado empleado,boolean accion) {
        getSimpleJdbcTemplate().update("update empleado set " +
                "nombre=:nombre," +
                "direccion=:direccion," +
                "telefono=:telefono," +
                "puesto=:puesto," +
                "sueldo=:sueldo," +
                "infonavit=:infonavit," +
                "descuento=:descuento," +
                "saldo=:saldo" +
                (accion?empleado.getBaja()==null?", baja=curdate()":",alta=curdate(), baja=null":"") +
                " where id=:id", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("id", empleado.getId())
            .addValue("nombre", empleado.getNombre())
            .addValue("direccion", empleado.getDireccion())
            .addValue("telefono", empleado.getTelefono())
            .addValue("puesto", empleado.getPuesto())
            .addValue("sueldo", empleado.getSueldo())
            .addValue("infonavit", empleado.getInfonavit())
            .addValue("descuento", empleado.getDescuento())
            .addValue("saldo", empleado.getSaldo()));
    }

    public void update(int id, float saldo) {
        getSimpleJdbcTemplate().update("update empleado set " +
                "saldo=:saldo" +
                " where id=:id", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("saldo", saldo)
            .addValue("id", id));
    }

    public void update2(int id, float saldo) {
        getSimpleJdbcTemplate().update("update empleado set " +
                "saldo=saldo-:saldo" +
                " where id=:id", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("saldo", saldo)
            .addValue("id", id));
    }

    Empleado select(int id) {
        List<Empleado>pacientes=getSimpleJdbcTemplate().query("select * from empleado where id=:id"
                ,new Mapper(),new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("id",id));
        return pacientes.size()==0?null:pacientes.get(0);
    }

}
