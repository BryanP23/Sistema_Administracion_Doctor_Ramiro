package com.sofka.qa.SistemaAdministracionDoctorRamiro.Repository;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Medico;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Paciente;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;

import java.util.List;

public interface IPersonaRepository {
    public RespuestaHttp crearPaciente(Paciente paciente);
    public RespuestaHttp actualizarPaciente(Paciente paciente);
    public List<Paciente> obtenerPacientes();
    public RespuestaHttp eliminarPaciente(Integer id);
    public List<Medico> obtenerMedicos();
}
