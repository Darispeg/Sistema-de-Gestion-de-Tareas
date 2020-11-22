package com.school.application.repository.Tarea.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface FileRespuesta {
    public void init();

    public void save(int idTarea, int idEstudiante, MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    public String deleteFile(String filename, int idTarea, int idEstudiante);
}
