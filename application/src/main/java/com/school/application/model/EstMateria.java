package com.school.application.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstMateria {

    private long id;

	@Positive
	@NotNull
    private long idEstudiante;

	@Positive
	@NotNull
    private long idMateria;

    private boolean estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(long idMateria) {
		this.idMateria = idMateria;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
