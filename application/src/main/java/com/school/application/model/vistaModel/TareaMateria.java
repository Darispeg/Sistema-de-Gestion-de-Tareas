package com.school.application.model.vistaModel;

    /* Vista Tarea Materia Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=7hdOQH08_l-T#?folder_id=home&browser=icon */
    
    /* Vista Tarea Materia Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=~XdOBMzCrmb.#?folder_id=home&browser=icon */

// Solo para Vista
public class TareaMateria {
    
    private String paralelo;
    private long idTarea;
    private long idMateria;
    private String nombre;
    private String titulo;
    private String descripcion;
    private boolean estado;

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
