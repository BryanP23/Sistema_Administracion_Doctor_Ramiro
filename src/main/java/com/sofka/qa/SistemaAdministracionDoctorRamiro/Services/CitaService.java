package com.sofka.qa.SistemaAdministracionDoctorRamiro.Services;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Cita;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.HorarioDisponibilidad;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Repository.ICitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private ICitaRepository citasRepository;

    @Override
    public List<HorarioDisponibilidad> consultarHorarioMedico(Integer idMedico) {
        return citasRepository.consultarHorarioMedico(idMedico);
    }
    @Override
    public RespuestaHttp actualizarEstadoCita(Integer idCita, String estadoCita) {
        return citasRepository.actualizarEstadoCita(idCita, estadoCita);
    }
    @Override
    public List<Cita> listarHistorialMedicoPaciente(Integer pacienteId) {
        return citasRepository.listarHistorialMedicoPaciente(pacienteId);
    }
    @Override
    public RespuestaHttp establecerHorarioMedico(HorarioDisponibilidad horario) {
        return citasRepository.establecerHorarioMedico(horario);
    }

    @Override
    public RespuestaHttp agendarCitaMedica(Cita cita) {
        return citasRepository.agendarCitaMedica(cita);
    }




}
