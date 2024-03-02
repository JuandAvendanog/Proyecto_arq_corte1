package com.proyectocorte1.Proyecto_arquitectura_corte1.repositories;


import com.proyectocorte1.Proyecto_arquitectura_corte1.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository

public class UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final UsuarioMapper mapper = new UsuarioMapper();

    private final JdbcTemplate template;

    private final String table = "usuario";



    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<User> getAllUsers(){
        String sql = "select * from " + table;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public int createUser(User newUser) {
        return insert.executeAndReturnKey( new MapSqlParameterSource()
                        .addValue("nombre", newUser.name)
                        .addValue("correo", newUser.mail)
                        .addValue("contrasena", newUser.getPassword())
                        .addValue("tipo_usuario_id",newUser.userType)
                ).intValue();
    }

    public int deleteUser(int id) {
        String sql = "delete from usuario where id = ?";
        return template.update(sql,id);
    }

    public int updateUser(int id, User updatedUser) {
        String sql = "update usuario set " +
                "nombre = :nombre," +
                " correo = :correo, " +
                "contrasena = :contrasena, " +
                "tipo_usuario_id = :tipo_usuario_id " +
                "WHERE id = " + id;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("nombre", updatedUser.name)
                .addValue("correo", updatedUser.mail)
                .addValue("contrasena", updatedUser.getPassword())
                .addValue("tipo_usuario_id", updatedUser.userType);

        return namedParameterJdbcTemplate.update(sql, parameters);
    }


    private static class UsuarioMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("nombre");
            String mail = rs.getString("correo");
            String password = rs.getString("contrasena");
            int userType = rs.getInt("tipo_usuario_id");

            return new User(id, name, mail, password, userType);
        }
    }
}
