package com.school.application.controller.rest.TareaRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.school.application.model.RespuestaTarea;
import com.school.application.model.TareaFile;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Tarea.respuestas.RespuestaRepository;
import com.school.application.repository.Tarea.service.FileRespuesta;
import com.school.application.util.message.FileMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/* Para la vista Detalles de Tarea - Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=dHdO1Vea6BbX#?folder_id=home&browser=icon */

@RestController
@RequestMapping(value = "/api/v1/respuesta")
public class RespuestaRestController {
    
    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private FileRespuesta fileRespuesta;


    @GetMapping(value = "/tarea/{idTarea}")
    public ResponseEntity<List<RespuestaTarea>> findAllRespuestasTarea(@PathVariable int idTarea){
        return ResponseEntity.ok().body(repository.findAllRespuestas(idTarea));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("idTarea") String idTarea, @RequestParam("idEstudiante") String idEstudiante, @RequestParam("files")MultipartFile[] files){
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                fileRespuesta.save(Integer.parseInt(idTarea), Integer.parseInt(idEstudiante), file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Se subieron todos los Archivos" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {
            message = "Fallo al subir los documentos";

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

    @GetMapping(value = "/files")
    public ResponseEntity<List<TareaFile>> getFiles(){
        List<TareaFile> fileInfo = fileRespuesta.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileRestController.class, "getFile",
                path.getFileName().toString()).build().toString();
            return new TareaFile(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping(value = "files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileRespuesta.load(filename);
        return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, 
        "attachment; filename=\"" + file.getFilename() + "\"").body(file); 
    }

    @DeleteMapping(value="/delete")
    public ResponseEntity<FileMessage> deleteFile(@RequestParam("filename") String filename, @RequestParam("idTarea") String idTarea, @RequestParam("idEstudiante") String idEstudiante) {
        String message = "";
        try {
            message = fileRespuesta.deleteFile(filename, Integer.parseInt(idTarea), Integer.parseInt(idEstudiante));
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

    @GetMapping(value = "/estudiante")
    public ResponseEntity<List<RespuestaTarea>> findRespuestaEstudiante(@RequestParam("idTarea") String idTarea, @RequestParam("idEstudiante") String idEstudiante){
        return ResponseEntity.ok().body(repository.findRespuestaEstudiante(Integer.parseInt(idTarea), Integer.parseInt(idEstudiante)));
    }

    @PostMapping(value = "/estudiante/calificar")
    public ResponseEntity<RepBase> calificarTarea(@RequestBody RespuestaTarea rTarea){
        return ResponseEntity.ok().body(new RepBase(repository.calificarTarea(rTarea)));
    }
}