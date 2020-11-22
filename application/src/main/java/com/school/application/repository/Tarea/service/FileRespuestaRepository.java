package com.school.application.repository.Tarea.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileRespuestaRepository implements FileRespuesta {

    private final Path root = Paths.get("RespuestasTarea");

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo iniciar la carpeta");
        }
    }

    @Override
    public void save(int idTarea, int idEstudiante, MultipartFile file) {
        try {
            String codigo = String.valueOf(idEstudiante) + String.valueOf(idTarea) + "-";
            Files.copy(file.getInputStream(), this.root.resolve(codigo + file.getOriginalFilename()));
            String name = codigo + file.getOriginalFilename().toString();
            String url = "http://localhost:8080/api/v1/respuesta/files/" + name;
            String sql = String.format("INSERT INTO respuestatarea (idTarea, idEstudiante, documento, url) VALUES ('%d', '%d', '%s', '%s')"
            , idTarea, idEstudiante, name, url);
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("No se puede guarda el Archivo");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer el Archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());    
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("No se puede cargar los archivos");
        }
    }

    @Override
    public String deleteFile(String filename, int idTarea, int idEstudiante) {
        try {
            Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
            if (delete) {
                String sql = String.format("DELETE FROM respuestatarea WHERE idTarea = '%d'AND idEstudiante = '%d' AND documento = '%s'"
                , idTarea, idEstudiante, filename);
                jdbcTemplate.execute(sql);
                return "Borrado";
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error borrando";
        }
    }
}