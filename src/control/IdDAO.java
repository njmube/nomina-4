package control;

public class IdDAO extends org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport {

    private static class Mapper implements org.springframework.jdbc.core.simple.ParameterizedRowMapper <Integer> {
        public Integer mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Integer(rs.getInt("val"));
        }
    }

    public int get(String id) {
        java.util.List <Integer> index = getSimpleJdbcTemplate().query("select val from id where id=:id", new Mapper(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("id", id));
        int currentIndex = index.get(0);
        getSimpleJdbcTemplate().update("update id set val=val+1 where id=:id", new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()
            .addValue("id", id));
        return currentIndex;
    }

}
