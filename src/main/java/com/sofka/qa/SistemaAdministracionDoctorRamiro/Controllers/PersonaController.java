package com.sofka.qa.SistemaAdministracionDoctorRamiro.Controllers;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Medico;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Paciente;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Services.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    public static String PACIENTES_NO_DISPONIBLES = "Actualmente no se encuentran pacientes en el sistema";

    public static String MEDICOS_NO_DISPONIBLES = "Actualmente no se encuentran medicos en el sistema";

    @Autowired
    private IPersonaService personasService;


    @GetMapping("/listar-medicos")
    public ResponseEntity<?> obtenerMedicos() {
        List<Medico> medicosDisponibles = personasService.obtenerMedicos();
        if (medicosDisponibles == null || medicosDisponibles.size() == 0) {
            return new ResponseEntity<String>(MEDICOS_NO_DISPONIBLES, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Medico>>(medicosDisponibles, HttpStatus.OK);
    }

    @PutMapping("/actualizar-paciente")
    public ResponseEntity<?> actualizarPaciente(@RequestBody Paciente paciente) {
        RespuestaHttp respuesta = personasService.actualizarPaciente(paciente);
        return new ResponseEntity<String>(respuesta.getMensaje(), respuesta.getEstadoHttp());
    }

    @DeleteMapping("/eliminar-paciente/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) {
        RespuestaHttp respuesta = personasService.eliminarPaciente(id);
        return new ResponseEntity<String>(respuesta.getMensaje(), respuesta.getEstadoHttp());
    }
}