package com.sofka.qa.SistemaAdministracionDoctorRamiro.Services;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Cita;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.HorarioDisponibilidad;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;

import java.util.List;

public interface ICitaService {
    public RespuestaHttp actualizarEstadoCita(Integer idCita, String estadoCita);
    public RespuestaHttp establecerHorarioMedico(HorarioDisponibilidad horario);
    public List<Cita> listarHistorialMedicoPaciente(Integer pacienteId);
    public RespuestaHttp agendarCitaMedica(Cita cita);
    public List<HorarioDisponibilidad> consultarHorarioMedico(Integer idMedico);
}