package com.proyectocorte1.Proyecto_arquitectura_corte1.repositories;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Project;
import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserStory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserStoryRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final UserStoryRepository.UserStoryMapper mapper = new UserStoryRepository.UserStoryMapper();

    private final JdbcTemplate template;
    private final String table = "historia_usuario";


    public UserStoryRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<UserStory> getAllUserStory(int id_project) {
        String sql = "select * from " + table + " where proyecto_id = " + id_project;
        return namedParameterJdbcTemplate.query(sql, mapper);
    }

    public String createUserStory(UserStory newUserStory, int id_user) {
        if(typeUser(id_user) !=1){
            return "No tienes permiso para crear historias de usuario";
        }

        insert.execute(new MapSqlParameterSource()
                .addValue("detalles",newUserStory.details)
                .addValue("criterios_aceptacion", newUserStory.criteria)
                .addValue("usuario_historia_id", newUserStory.idUserStory)
                .addValue("estado_id", newUserStory.idStatus)
                .addValue("proyecto_id", newUserStory.idProject));
        return "Historia de usuario creada";

    }

    public String updateUserStory(int id_user, int id_userStory, UserStory updateUserStory){
        if (typeUser(id_user) != 1){
            return "No tienes permiso para modificar las historias de usuario";
        }

        String sql = "update "+ table + " set " +
                "detalles = :detalles," +
                "criterios_aceptacion = :criterios_aceptacion," +
                "usuario_historia_id = :usuario_historia_id," +
                "estado_id = :estado_id," +
                "proyecto_id = :proyecto_id" +
                " where id = " + id_userStory;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("detalles", updateUserStory.details)
                .addValue("criterios_aceptacion", updateUserStory.criteria)
                .addValue("usuario_historia_id", updateUserStory.idUserStory)
                .addValue("estado_id", updateUserStory.idStatus)
                .addValue("proyecto_id", updateUserStory.idProject);
        namedParameterJdbcTemplate.update(sql,parameters);

        return"Se ha modificado la historia de usuario";
    }

    public String deleteUserStory(int id_user, int id_userStory) {
        if (typeUser(id_user) != 1){
            return "No tienes permiso para eliminar las historias de usuario";
        }else if(statusStoryUser(id_userStory) != 3){
            return "La hisotira de usuario no se ha finalizado";
        }

        String sql = "delete from " + table + " where id = ?";
        template.update(sql,id_userStory);
        return "Se ha elimiando la historia de usuario";
    }

    public int typeUser(int id){
        String sql = "select tipo_usuario_id from usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    public int statusStoryUser(int id){
        String sql = "select estado_id from historia_usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }


    private static class UserStoryMapper implements RowMapper<UserStory>{
        @Override
        public UserStory mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String details = rs.getString("detalles");
            String criteria = rs.getString("criterios_aceptacion");
            int storyUser = rs.getInt("usuario_historia_id");
            int status = rs.getInt("estado_id");
            int project = rs.getInt("proyecto_id");

            return new UserStory(id, details, criteria, storyUser, status, project);
        }

    }
}


