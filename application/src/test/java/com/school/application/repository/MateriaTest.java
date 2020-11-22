package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Materia;
import com.school.application.repository.Materia.MateriaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class MateriaTest {
    
    @Autowired
    private MateriaRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void saveTest(){
        try {
            Materia materia = new Materia();
            materia.setIdCurso(1);
            materia.setNombre("Lenguaje");
            materia.setDescripcion("Comunicacion Social");
    
            boolean result = repository.save(materia);
    
            if(!result)
            {
                throw new RuntimeException("\n\n\t\t***************************** No paso el test Save ***************************************** \n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test save ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test Save ***************************************** \n" + e);
        }
    }

    @Test
    public void updateTest(){
        try {
            Materia materia = new Materia();

            materia.setIdMateria(5);
            materia.setNombre("Lenguaje");
            materia.setDescripcion("Update");

            boolean result = repository.update(materia);
            
            if(!result)
            {
                throw new RuntimeException("\n\n\t\t***************************** No paso el test Update ***************************************** \n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test Update ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test Update ***************************************** \n" + e);
        }
    }

    @Test
    public void findAllTest(){
        try {
            SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
            List<Materia> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (Materia materia : all) {
                    log.info(materia.getIdMateria() + " : " + materia.getNombre());
                }            
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findAll ------------------------------------------\n");            
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findAll ***************************************** \n" + e);
        }
    }

    @Test
    public void findByIdTest(){
        try {
            Materia find = repository.findId(4);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getIdMateria() + " : " + find.getNombre());
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findById ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findById ***************************************** \n" + e);
        }
    }
}
