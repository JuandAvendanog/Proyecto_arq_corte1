package com.proyectocorte1.Proyecto_arquitectura_corte1.repositories;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Project;
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
public class ProjectRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final ProjectRepository.ProyectoMapper mapper = new ProjectRepository.ProyectoMapper();

    private final JdbcTemplate template;
    private final String table = "proyecto";

    public ProjectRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<Project> getAllProjects(int id) {
        String sql = "select * from " + table + " where gerente_id = "+ id;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public String createProject(Project newProject, int id) {

        if (id != 1){
            return "No tienes permiso";
        }
        insert.execute( new MapSqlParameterSource()
                .addValue("nombre", newProject.name)
                .addValue("descripcion", newProject.description)
                .addValue("fechainicio", newProject.startDate)
                .addValue("gerente_id", id));
        return "Proyecto creado";
    }

    public String updateProject(int id_user, int id_project, Project updateProject) {
        if(id_user != 1){
            return "No tienes permiso para actualizar proyectos";
        }

        String sql = "update "+ table + " set " +
                "nombre = :nombre," +
                "descripcion = :descripcion," +
                "fechainicio = :fechainicio," +
                "gerente_id = :gerente_id" +
                " where id = " + id_project;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("nombre", updateProject.name)
                .addValue("descripcion", updateProject.description)
                .addValue("fechainicio", updateProject.startDate)
                .addValue("gerente_id", id_user);

        namedParameterJdbcTemplate.update(sql, parameters);
        return "Proyecto actualizado";

    }

    public String deleteProject(int id_user, int id_project) {
        if(id_user != 1){
            return "No tienes permiso para eliminar proyectos";
        }
        String sql = "delete from " + table + " where id = ?";
        template.update(sql,id_project);
        return "Proyecto eliminado";
    }


    private static class ProyectoMapper implements RowMapper<Project> {

        @Override
        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("nombre");
            String description = rs.getString("descripcion");
            Date startDate = rs.getDate("fechainicio");
            int manager = rs.getInt("gerente_id");

            return new Project(id, name, description, startDate, manager);
        }
    }
}
