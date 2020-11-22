package com.school.application.model.vistaModel;

import java.util.Date;
            /* VISTA Detalle - Tarea  */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=dHdO1Vea6BbX#?folder_id=home&browser=icon */

public class DetalleTarea {

    private long idTarea;    
    private long idDetalle;
    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinal;
    private String documento;
    private String url;

    public long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
