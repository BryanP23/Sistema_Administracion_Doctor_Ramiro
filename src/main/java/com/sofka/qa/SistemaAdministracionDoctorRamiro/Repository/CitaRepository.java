package com.sofka.qa.SistemaAdministracionDoctorRamiro.Repository;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Cita;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.HorarioDisponibilidad;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CitaRepository implements ICitaRepository {

    public static String CITA_ACTUALIZADA_CON_EXITO = "La cita se ha actualizado ";
    public static String ERROR_REGISTRANDO_EL_HORARIO = "No es posible registrar el horario del medico";
    public static String HORARIO_REGISTRADO_CON_EXITO = "Horario Registrado!";
    public static String CITA_NO_ENCONTRADA = "La cita con este identificador no se encuentra registrado en el sistema";
    public static String FRANJA_NO_DISPONIBLE = "El doctor se encuentra ocupado en este horario";
    public static String CITA_REGISTRADA_CON_EXITO = "Cita registrada!!";
    public static String OCURRIO_ERROR_INESPERADO = "Ocurrio un error inesperado, comuniquese con el administrador";

    private Set<HorarioDisponibilidad> horarioDisponibilidadMedicos;

    private List<Cita> listadoCitas;

    @PostConstruct
    public void postConstruct() {
        this.horarioDisponibilidadMedicos = new HashSet<>();
        this.listadoCitas = new ArrayList<>();
    }

    @Override
    public RespuestaHttp establecerHorarioMedico(HorarioDisponibilidad horario) {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {

            if (validarRegistroHorarioMedico(horario)) {
                this.horarioDisponibilidadMedicos.add(horario);
                respuesta.setMensaje(HORARIO_REGISTRADO_CON_EXITO);
                respuesta.setEstadoHttp(HttpStatus.CREATED);
            } else {
                respuesta.setMensaje(FRANJA_NO_DISPONIBLE);
                respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            respuesta.setMensaje(ERROR_REGISTRANDO_EL_HORARIO);
            respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

    public boolean validarRegistroHorarioMedico(HorarioDisponibilidad horario) {

        HorarioDisponibilidad horarioTemp = this.horarioDisponibilidadMedicos.stream().filter(ob -> ob.getId().equals(horario.getId()) || ob.getFecha().equals(horario.getFecha()) && (ob.getHoraInicio().equals(horario.getHoraInicio()) || ob.getHoraFinal().equals(horario.getHoraFinal()))).findAny().orElse(null);


        if (horarioTemp != null) {
            return false;
        }
        return true;
    }

    @Override
    public List<HorarioDisponibilidad> consultarHorarioMedico(Integer idMedico) {
        List<HorarioDisponibilidad> horarioLista = this.horarioDisponibilidadMedicos.stream().filter(c -> c.getMedico().getId().equals(idMedico)).collect(Collectors.toList());
        return horarioLista;
    }

    @Override
    public RespuestaHttp agendarCitaMedica(Cita cita) {
        RespuestaHttp respuesta = new RespuestaHttp();

        LocalDateTime fechaSitema = LocalDateTime.now();
        cita.setFechaRegistro(fechaSitema.toLocalDate());
        cita.setHoraRegistro(fechaSitema.toLocalTime());
        cita.setFechaModificacion(fechaSitema.toLocalDate());
        cita.setHoraModificacion(fechaSitema.toLocalTime());
        // TODO falta validar registro de cita medica (solo horarios disponibles)
        respuesta.setMensaje(CITA_REGISTRADA_CON_EXITO);
        respuesta.setEstadoHttp(HttpStatus.CREATED);
        this.listadoCitas.add(cita);
        return respuesta;
    }

    @Override
    public List<Cita> listarHistorialMedicoPaciente(Integer pacienteId) {
        List<Cita> listaCitasPaciente = this.listadoCitas.stream().filter(pac -> pac.getPaciente().getId().equals(pacienteId)).collect(Collectors.toList());
        return listaCitasPaciente;
    }

    @Override
    public RespuestaHttp actualizarEstadoCita(Integer idCita, String estadoCita) {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {

            Cita citaTemp = this.listadoCitas.stream().filter(c -> c.getId().equals(idCita)).findAny().orElse(null);

            if (citaTemp != null) {
                this.listadoCitas.stream().filter(c -> c.getId().equals(idCita)).forEach(c -> {
                    if (estadoCita.equals("Cancelada")) {
                        c.setEstado("Disponible");
                    } else {
                        c.setEstado(estadoCita);
                    }
                });
                respuesta.setEstadoHttp(HttpStatus.OK);
                respuesta.setMensaje(CITA_ACTUALIZADA_CON_EXITO);
            } else {
                respuesta.setMensaje(CITA_NO_ENCONTRADA);
                respuesta.setEstadoHttp(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            respuesta.setMensaje(OCURRIO_ERROR_INESPERADO);
            respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }
}
