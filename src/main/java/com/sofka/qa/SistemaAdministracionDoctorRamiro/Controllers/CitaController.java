package com.sofka.qa.SistemaAdministracionDoctorRamiro.Controllers;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.*;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Services.ICitaService;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Services.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    public static String MEDICO_NO_EXISTE = "Lo sentimos Doc este ID no esta registrado en el sistema";
    public static String PACIENTE_NO_ENCONTRADO = "El paciente con ese ID no se encuentra registrado en el sistema";
    public static String ERROR_HORARIO_MEDICO = "No hay horario disponible para el doctor con este ID";
    public static String HISTORIAL_MEDICO_NO_DISPONIBLE = "El paciente aun no tiene un historial medico";



    @Autowired
    private IPersonaService personasService;
    @Autowired
    private ICitaService citasService;



    @PostMapping("/agendarcitamedica")
    ResponseEntity<?> agendarCitaMedica(@RequestBody Cita cita) {
        RespuestaHttp respuesta = citasService.agendarCitaMedica(cita);
        return new ResponseEntity<String>(respuesta.getMensaje(), respuesta.getEstadoHttp());
    }

    @PutMapping("/actualizarestadocita/estado/{estadoCita}/{idCita}")
    ResponseEntity<?> actualizarEstadoCita(@PathVariable Integer idCita, @PathVariable String estadoCita) {
        RespuestaHttp respuesta = citasService.actualizarEstadoCita(idCita, estadoCita);
        return new ResponseEntity<String>(respuesta.getMensaje(), respuesta.getEstadoHttp());
    }

    @GetMapping("/listarhistorialmedicopaciente/{pacienteId}")
    ResponseEntity<?> listarHistorialMedicoPaciente(@PathVariable Integer pacienteId) {

        Paciente pacienteTemp = personasService.obtenerPacientes()
                .stream()
                .filter(p -> p.getId().equals(pacienteId))
                .findFirst()
                .orElse(null);


        if (pacienteTemp != null) {
            List<Cita> historialMedico = citasService.listarHistorialMedicoPaciente(pacienteId);

            if (historialMedico == null || historialMedico.size() == 0) {
                return new ResponseEntity<String>(HISTORIAL_MEDICO_NO_DISPONIBLE, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<List<Cita>>(historialMedico, HttpStatus.OK);
        }

        return new ResponseEntity<String>(PACIENTE_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/establecerhorariomedico")
    ResponseEntity<?> establecerHorarioMedico(@RequestBody HorarioDisponibilidad horario) {
        RespuestaHttp respuesta = citasService.establecerHorarioMedico(horario);
        return new ResponseEntity<String>(respuesta.getMensaje(), respuesta.getEstadoHttp());
    }

    @GetMapping("/consultarhorariomedico/{idMedico}")
    ResponseEntity<?> consultarHorarioMedico(@PathVariable Integer idMedico) {
        List<Medico> listaMedicos = personasService.obtenerMedicos();

        Medico medicoTemp = listaMedicos
                .stream()
                .filter(value -> value.getId().equals(idMedico))
                .findAny()
                .orElse(null);


        if (medicoTemp != null) {
            List<HorarioDisponibilidad> horarioDisponibilidadMedico = citasService.consultarHorarioMedico(idMedico);
            if (horarioDisponibilidadMedico == null || horarioDisponibilidadMedico.size() == 0) {
                return new ResponseEntity<String>(ERROR_HORARIO_MEDICO, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<HorarioDisponibilidad>>(horarioDisponibilidadMedico, HttpStatus.OK);
        }

        return new ResponseEntity<String>(MEDICO_NO_EXISTE, HttpStatus.NOT_FOUND);
    }
}
