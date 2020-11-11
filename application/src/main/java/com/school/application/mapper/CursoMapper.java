package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Curso;

import org.springframework.jdbc.core.RowMapper;

/*  Heredamos de RowMapper de Spring
    para poder recuperar o mapear los datos enviados desde 
    la Base de Datos
*/
public class CursoMapper implements RowMapper<Curso> {

    @Override
    public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
        Curso curso = new  Curso();

        curso.setIdCurso(rs.getInt("idCurso"));
        curso.setIdEscuela(rs.getInt("idEscuela"));
        curso.setIdGrado(rs.getInt("idGrado"));
        curso.setParalelo(rs.getString("paralelo"));
        curso.setGestion(rs.getInt("gestion"));

        return curso;
    }
    
}
