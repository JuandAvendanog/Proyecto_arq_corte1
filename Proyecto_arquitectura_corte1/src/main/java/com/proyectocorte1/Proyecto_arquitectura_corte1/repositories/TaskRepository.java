package com.proyectocorte1.Proyecto_arquitectura_corte1.repositories;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Task;
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
public class TaskRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final TaskRepository.TaskMapper mapper = new TaskRepository.TaskMapper();

    private final JdbcTemplate template;
    private final String table = "tarea";


    public TaskRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<Task> getAllTask(int id) {
        String sql = "select * from " + table + " where historia_usuario_id = "+ id;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public String createTask(int id_user, Task newTask) {
        if(typeUser(id_user) != 1){
            return "No tienes permisos para crear taraes";
        }

        insert.execute(new MapSqlParameterSource()
                .addValue("descripcion", newTask.description)
                .addValue("usuario_tarea_id", newTask.idUserTask)
                .addValue("estado_tarea_id", newTask.status)
                .addValue("historia_usuario_id",newTask.idUserStory));

        return "Tarea creada";

    }

    public String updateTask(int id_user, int id_task, Task updateTask) {
        if(typeUser(id_user) != 1){
            return "No tienes permisos para crear taraes";
        }

        String sql = "update "+ table + " set " +
                "descripcion = :descripcion," +
                "usuario_tarea_id = :usuario_tarea_id," +
                "estado_tarea_id = :estado_tarea_id," +
                "historia_usuario_id = :historia_usuario_id" +
                " where id = " + id_task;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("descripcion", updateTask.description)
                .addValue("usuario_tarea_id", updateTask.idUserTask)
                .addValue("estado_tarea_id", updateTask.status)
                .addValue("historia_usuario_id", updateTask.idUserStory);
        namedParameterJdbcTemplate.update(sql,parameters);

        return "Se ha modificado la tarea";
    }

    public String deleteTask(int id_user, int id_task) {
        if(typeUser(id_user) != 1){
            return "No tienes permisos para crear taraes";
        }else if(statusTask(id_task) != 3){
            return "La tarea no se ha finalizado";
        }

        String sql = "delete from " + table + " where id = ?";
        template.update(sql,id_task);
        return "Se ha elimiando la tarea de usuario";
    }



    public int typeUser(int id){
        String sql = "select tipo_usuario_id from usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    public int statusTask(int id){
        String sql = "select estado_tarea_id from tarea where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    private static class TaskMapper implements RowMapper<Task>{

        @Override
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException{
            int id = rs.getInt("id");
            String description = rs.getString("descripcion");
            int idStatusTask = rs.getInt("estado_tarea_id");
            int idUserTask = rs.getInt("usuario_tarea_id");
            int idUserStory = rs.getInt("historia_usuario_id");

            return new Task(id, description, idStatusTask, idUserTask, idUserStory);
        }
}
}
