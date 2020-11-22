package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Persona;
import com.school.application.repository.Persona.PersonaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class PersonaTest {
    
    @Autowired
    private PersonaRepository repository;

    Log log = LogFactory.getLog(getClass());
        
    Persona persona = new Persona();
    
    @Test
    public void saveTest(){
        try {
            persona.setNombre("New");
            persona.setaPaterno("New");
            persona.setaMaterno("Test");
            persona.setEdad(32);
            persona.setSexo("H");
            persona.setTelefono("44444444");
            boolean result = repository.save(persona);

            if(!result)
            {
                throw new RuntimeException("\n\n\t\t***************************** No paso el test Save ***************************************** \n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test save ------------------------------------------\n");
        } catch(RuntimeException e){
            throw new RuntimeException("Error en el test Update Persona");
        }  
    }

    @Test
    public void updateTest(){
        try {
            persona.setCi(101);
            persona.setNombre("Update");
            persona.setaPaterno("Test");
            persona.setaMaterno("Test");
            persona.setEdad(32);
            persona.setSexo("H");
            persona.setTelefono("44444444");
            boolean result = repository.update(persona);

            if(!result)
            {
                throw new RuntimeException("\n\n\t\t***************************** No paso el test Update ***************************************** \n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test Update ------------------------------------------\n");
        } catch(RuntimeException e){
            throw new RuntimeException("\n\n\t\t***************************** No paso el test Save ***************************************** \n" + e);
        }  
    }

    @Test
    public void findAllTest(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        try{
            List<Persona> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (Persona p : all) {
                    log.info(p.getCi() + " : " + p.getNombre());
                }    
            }        
            log.info("\n\n\t\t-------------------------------- Paso el test findAll ------------------------------------------\n");      
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findAll ***************************************** \n" + e);
        }
    }

    @Test
    public void findById(){
        try {
            Persona find = repository.findId(101);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getCi() + " : " + find.getNombre());
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findById ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findById ***************************************** \n" + e);
        }
    }
}
