package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.vistaModel.ProfesorMateria;
import com.school.application.repository.Materia.ProfMateriaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class ProfMateriaTest {
    
        
    @Autowired
    private ProfMateriaRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void saveTest(){
        try {
            ProfesorMateria pMateria = new ProfesorMateria();
            pMateria.setIdMateria(5);
            pMateria.setIdProfesor(101);
    
            boolean result = repository.save(pMateria);
    
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
            ProfesorMateria pMateria = new ProfesorMateria();
            pMateria.setIdMateria(5);
            pMateria.setIdProfesor(101);

            boolean result = repository.update(pMateria);
            
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
    public void findProfesorMateriaTest(){
        try {
            List<ProfesorMateria> all = repository.findMateriasProfesor(126);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (ProfesorMateria pMateria: all) {
                    log.info(pMateria.getMateria() + " : " + pMateria.getCi());
                }            
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findEstudiante ------------------------------------------\n");            
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findEstudiante ***************************************** \n" + e);
        }
    }
}
