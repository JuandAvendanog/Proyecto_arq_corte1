package com.proyectocorte1.Proyecto_arquitectura_corte1.repositories;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Project;
import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserProject;
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
import java.util.Date;
import java.util.List;

@Repository
public class UserProjectRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final UserProjectRepository.UserProjectMapper mapper = new UserProjectRepository.UserProjectMapper();

    private final JdbcTemplate template;
    private final String table = "usuario_proyecto";


    public UserProjectRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }
    public List<UserProject> getAllUserProject(int id_project) {
        String sql = "select * from " + table + " where proyecto_id = "+ id_project;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public String addUserProject(int id_user,int id_project, UserProject newUserProject) {

        if (typeUser(id_user) != 1){
            return "No tienes permiso para agregar usuarios";
        }
        insert.execute( new MapSqlParameterSource()
                .addValue("id", newUserProject.id)
                .addValue("fechainicio", newUserProject.startDate)
                .addValue("usuario_id", newUserProject.idUser)
                .addValue("proyecto_id", newUserProject.idProject));
        return "Usuario agregado al proyecto " + id_project;
    }

    public String updateUserProject(int id_user, int id_userProject, UserProject updateUserProject) {
        if(typeUser(id_user) != 1){
            return "No tienes permiso para actualizar usuarios";
        }

        String sql = "update "+ table + " set " +
                "fechainicio = :fechainicio," +
                "usuario_id = :usuario_id," +
                "proyecto_id = :proyecto_id" +
                " where id = " + id_userProject;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("fechainicio", updateUserProject.startDate)
                .addValue("usuario_id", updateUserProject.idUser)
                .addValue("proyecto_id", updateUserProject.idProject);

        namedParameterJdbcTemplate.update(sql, parameters);
        return "Usuario actualizado";
    }

    public String deleteUserProject(int id_user, int id_userProject) {
        if( typeUser(id_user) != 1 ){
            return "No tienes permiso para eliminar usuarios";
        }
        String sql = "delete from " + table + " where id = ?";
        template.update(sql,id_userProject);
        return "Usuario eliminado";
    }


    public int typeUser(int id){
        String sql = "select tipo_usuario_id from usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    private static class UserProjectMapper implements RowMapper<UserProject> {

        @Override
        public UserProject mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            Date date = rs.getDate("fechainicio");
            int idUser = rs.getInt("usuario_id");
            int idProject = rs.getInt("proyecto_id");

            return new UserProject(id, date,idUser, idProject);
        }
    }

}
