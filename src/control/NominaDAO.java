package control;
import domain.Nomina;
import java.util.*;

public class NominaDAO extends org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport {

    private static class Mapper implements org.springframework.jdbc.core.simple.ParameterizedRowMapper <Nomina> {
        public Nomina mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Nomina e = new Nomina();
            e.setCreacion(rs.getString("creacion"));
            e.setTotal(rs.getFloat("total"));
            e.setSemana(rs.getString("semana"));
            return e;
        }
    }

    public void insert(Nomina nomina) {
        getSimpleJdbcTemplate().update("insert into nomina " +
                "(creacion,semana,total) " +
                "values (curdate(),:semana,:total)", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("semana", nomina.getSemana())
            .addValue("total", nomina.getTotal()));
    }

    List<Nomina> select() {
        return getSimpleJdbcTemplate().query("select * from nomina", new Mapper(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource());
    }

    Nomina select(String semana) {
        List<Nomina>pacientes=getSimpleJdbcTemplate().query("select * from nomina where semana=:semana"
                ,new Mapper(),new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("semana",semana));
        return pacientes.size()==0?null:pacientes.get(0);
    }

    void update(Nomina nomina) {
        getSimpleJdbcTemplate().update("update nomina set"+
                " total=:total"+
                ",creacion=curdate()"+
                " where semana=:semana"
                ,new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
                .addValue("semana",nomina.getSemana())
                .addValue("total",nomina.getTotal()));
    }

}
