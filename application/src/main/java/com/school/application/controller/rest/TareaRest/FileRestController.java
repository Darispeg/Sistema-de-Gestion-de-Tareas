package com.school.application.controller.rest.TareaRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.school.application.model.TareaFile;
import com.school.application.repository.Tarea.service.FileService;
import com.school.application.util.message.FileMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@CrossOrigin(value = "*")
public class FileRestController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("idTarea") String idTarea, @RequestParam("files")MultipartFile[] files){
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                fileService.save(Integer.parseInt(idTarea), file);
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
        List<TareaFile> fileInfo = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileRestController.class, "getFile",
                path.getFileName().toString()).build().toString();
            return new TareaFile(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping(value = "files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, 
        "attachment; filename=\"" + file.getFilename() + "\"").body(file); 
    }

    @GetMapping(value="delete/{filename:.+}")
    public ResponseEntity<FileMessage> deleteFile(@PathVariable String filename) {
        String message = "";
        try {
            message = fileService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }
}