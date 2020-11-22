package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.vistaModel.EstMateria;
import com.school.application.repository.Materia.EstMateriaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class EstMateriaTest {
    
        
    @Autowired
    private EstMateriaRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void saveTest(){
        try {
            EstMateria estMateria = new EstMateria();
            estMateria.setIdEstudiante(104);
            estMateria.setIdMateria(5);
    
            boolean result = repository.save(estMateria);
    
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
            EstMateria estMateria = new EstMateria();
            estMateria.setIdEstudiante(104);
            estMateria.setIdMateria(5);

            boolean result = repository.update(estMateria);
            
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
    public void findEstudianteMateriaTest(){
        try {
            List<EstMateria> all = repository.findEstudiante(105);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (EstMateria eMateria: all) {
                    log.info(eMateria.getMateria() + " : " + eMateria.getCiEstudiante());
                }            
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findEstudiante ------------------------------------------\n");            
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findEstudiante ***************************************** \n" + e);
        }
    }
}
