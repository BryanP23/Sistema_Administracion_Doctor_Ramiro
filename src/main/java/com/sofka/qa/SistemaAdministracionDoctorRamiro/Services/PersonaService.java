package com.sofka.qa.SistemaAdministracionDoctorRamiro.Services;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Medico;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Paciente;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements IPersonaService {

    @Override
    public RespuestaHttp actualizarPaciente(Paciente paciente) {
        return personaRepository.actualizarPaciente(paciente);
    }
    @Override
    public RespuestaHttp crearPaciente(Paciente paciente) {
        return personaRepository.crearPaciente(paciente);
    }
    @Autowired
    private IPersonaRepository personaRepository;

    @Override
    public List<Paciente> obtenerPacientes() {
        return personaRepository.obtenerPacientes();
    }
    @Override
    public RespuestaHttp eliminarPaciente(Integer id) {
        return personaRepository.eliminarPaciente(id);
    }
    @Override
    public List<Medico> obtenerMedicos() {
        return personaRepository.obtenerMedicos();
    }


}
