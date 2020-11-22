package com.school.application.repository;

import java.util.Calendar;
import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Tarea;
import com.school.application.model.vistaModel.DetalleTarea;
import com.school.application.model.vistaModel.TareaMateria;
import com.school.application.repository.Materia.TareaMateriaRepository;
import com.school.application.repository.Tarea.DetalleTareaRepository;
import com.school.application.repository.Tarea.TareaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class TareaTest {
    
    @Autowired
    private TareaRepository tRepository;

    @Autowired
    private DetalleTareaRepository detallesRepository;

    @Autowired
    private TareaMateriaRepository tMateriaRepository;

    Log log = LogFactory.getLog(getClass());

    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    @Test
    public void saveTest(){
        try {
            Tarea tarea = new Tarea(); // tarea (idCurso, idMateria, titulo, descripcion, fechaFinal)
            tarea.setIdCurso(1);
            tarea.setIdMateria(5);
            tarea.setTitulo("Practica #5");
            tarea.setDescripcion("Resolver los Ejercicios");
            tarea.setFechaFinal(date);
    
            boolean result = tRepository.save(tarea);
    
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
    public void updateTest(){ //  titulo = '%s', descripcion = '%s', fechaFinal = '%s' WHERE idTarea = '%d'
        try {
            Tarea tarea = new Tarea();
            tarea.setTitulo("Update");
            tarea.setDescripcion("Modificado");
            tarea.setFechaFinal(date);
            tarea.setIdTarea(5);

            boolean result = tRepository.update(tarea);
            
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
    public void findTareasMateria(){
        try {
            List<TareaMateria> all = tMateriaRepository.findTarea(3);
            if(all.isEmpty()){
                throw new RuntimeException("\n\n\t***************************** No se encontraron datos con esta llave ***************************************** \n");
            }else{
                for (TareaMateria tMateria : all) {
                    log.info(tMateria.getParalelo() + " : " + tMateria.getTitulo());
                }            
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findTareasMateria ------------------------------------------\n");            
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findTareasMateria ***************************************** \n" + e);
        }
    }

    @Test
    public void findDetallesTarea(){
        try {
            DetalleTarea find = detallesRepository.findTarea(2);
            if(find == null){
                throw new RuntimeException("\n\n\t***************************** No se encontraron datos con esta llave ***************************************** \n");
            }else{
                log.info("\n\n" + find.getTitulo() + " : \n" + find.getDescripcion() + "\n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findDetallesTarea ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findDetallesTarea ***************************************** \n" + e);
        }
    }
}
