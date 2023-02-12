package com.sofka.qa.SistemaAdministracionDoctorRamiro.Services;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Medico;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Paciente;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;

import java.util.List;

public interface IPersonaService {

    public List<Medico> obtenerMedicos();

    public List<Paciente> obtenerPacientes();
    public RespuestaHttp crearPaciente(Paciente paciente);
    public RespuestaHttp eliminarPaciente(Integer id);

    public RespuestaHttp actualizarPaciente(Paciente paciente);


}
