package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Escuela;
import com.school.application.repository.Escuela.EscuelaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class EscuelaTest {
    
    @Autowired
    private EscuelaRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void Insertar(){
        try {
            Escuela escuela = new Escuela();

            escuela.setNombre("Esteban Arze");
            escuela.setDescripcion("Escuela de nivel Primario");
            escuela.setTelefono("44452167");
            escuela.setCorreo("estebanArze@gmail.com");
            escuela.setContrasena("EstebanArze");
    
            boolean result = repository.save(escuela);
    
            if(result){
                log.info("Se Insertaron los datos correctamente");
                log.info("\n\n\t\t-------------------------------- Paso el test Update ------------------------------------------\n");
            }else{
                log.warn("\n\n\t\t***************************** No paso el test Update ***************************************** \n");
            }
        } catch (Exception e) {
            log.warn(e);
        }
    }

    @Test
    public void update(){
        Escuela escuela = new Escuela();

        escuela.setIdEscuela(3);
        escuela.setNombre("Esteban Arze");
        escuela.setDescripcion("UPDATE");
        escuela.setTelefono("44452167");
        escuela.setCorreo("estebanUpdate@gmail.com");
        escuela.setContrasena("EstebanArze");

        boolean result = repository.update(escuela);

        if(result){
            log.info("Se Actualizaron los datos correctamente");
            log.info("\n\n\t\t-------------------------------- Paso el test Update ------------------------------------------\n");
        }else{
            log.warn("\n\n\t\t***************************** No paso el test Update ***************************************** \n");
        }
    }

    @Test
    public void findAll(){
        try {
            SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
            List<Escuela> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No se encontro ningun Archivo en esta Tabla");
            }else{
                for(Escuela escuela : all){
                    log.info(escuela.getNombre());
                }
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findAll ------------------------------------------\n");
        } catch (Exception e) {
            log.warn("\n\n\t\t***************************** No paso el test findAll ***************************************** \n" + e);
        }
    }

    @Test
    public void findById(){
        try {
            Escuela find = repository.findId(3);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getIdEscuela() + " : " + find.getNombre());
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findById ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al ejecutarse el Test findById Curso " + e);      
        }
    }
}
