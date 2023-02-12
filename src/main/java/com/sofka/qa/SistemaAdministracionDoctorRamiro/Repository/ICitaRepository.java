package com.sofka.qa.SistemaAdministracionDoctorRamiro.Repository;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Cita;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.HorarioDisponibilidad;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;

import java.util.List;

public interface ICitaRepository {
    public RespuestaHttp establecerHorarioMedico(HorarioDisponibilidad horario);

    public List<HorarioDisponibilidad> consultarHorarioMedico(Integer idMedico);

    public RespuestaHttp agendarCitaMedica(Cita cita);

    public List<Cita> listarHistorialMedicoPaciente(Integer pacienteId);

    public RespuestaHttp actualizarEstadoCita(Integer idCita, String estadoCita);
}

