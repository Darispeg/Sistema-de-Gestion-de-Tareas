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
public class FileServiceRepository implements FileService {

    private final Path root = Paths.get("TareaDocuementos");

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo inicializar el Storege");
        }
    }

    @Override
    public void save(int idTarea ,MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            String name = file.getOriginalFilename().toString();
            String url = "http://localhost:8080/files/" + name;
            String sql = String.format("INSERT INTO tareadetalle (idTarea, documeneto, url) VALUES ('%d', '%s', '%s')",
                                        idTarea, name, url);
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
    public String deleteFile(String filename) {
        try {
            Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
            if (delete) {
                return "Borrado";
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error borrando";
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
