package com.sofka.qa.SistemaAdministracionDoctorRamiro.Repository;

import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Medico;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.Paciente;
import com.sofka.qa.SistemaAdministracionDoctorRamiro.Models.RespuestaHttp;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonaRepository implements IPersonaRepository {
    public static String PACIENTE_REGISTRADO = "El paciente se ha registrado!";
    public static String PACIENTE_NO_REGISTRADO = "El paciente ya se encuentra registrado";
    public static String PACIENTE_ACTUALIZADO = "El paciente se ha actualizado!";
    public static String PACIENTE_ELIMINADO_CON_EXITO = "El paciente se ha eliminado!";
    public static String PACIENTE_NO_ENCONTRADO = "El paciente con esa identificacion no se encuentra en el sistema";

    private ArrayList<Paciente> listaPacientes;

    public static String OCURRIO_ERROR_INESPERADO = "Ocurrio un error inesperado, comuniquese con el administrador";

    private HashMap<String, Medico> mapMedicos = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        this.listaPacientes = new ArrayList<>();
        Medico medico1 = new Medico();
        medico1.setId(1);
        medico1.setNombres("Nombre Medico");
        medico1.setApellidos("apellido medico");
        medico1.setDireccion("Medellin");
        medico1.setTelefono("148788");
        medico1.setSexo("femenino");

        Medico medico2 = new Medico();
        medico2.setId(2);
        medico2.setNombres("Nombre medico 2");
        medico2.setApellidos("Medico apellido 2");
        medico2.setDireccion("Medellinn");
        medico2.setTelefono("8569123");
        medico2.setSexo("Masculino");
        this.mapMedicos.put("medico 1 ", medico1);
        this.mapMedicos.put("medico 2 ", medico2);
    }

    @Override
    public RespuestaHttp crearPaciente(Paciente paciente) {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Paciente pacienteTemp = this.listaPacientes.stream().filter(value -> value.getId().equals(paciente.getId())).findAny().orElse(null);
            System.out.println("pacienteTemp : " + pacienteTemp);
            if (pacienteTemp == null) {
                this.listaPacientes.add(paciente);
                respuesta.setMensaje(PACIENTE_REGISTRADO);
                respuesta.setEstadoHttp(HttpStatus.CREATED);
            } else {
                respuesta.setMensaje(PACIENTE_NO_REGISTRADO);
                respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            respuesta.setMensaje(OCURRIO_ERROR_INESPERADO);
            respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

    public List<Paciente> obtenerPacientes() {

        System.out.println(" ---- Pacientes disponibles -------");
        this.listaPacientes.stream().forEach((pacienteDto -> {
            System.out.println("Id " + pacienteDto.getId());
            System.out.println("Nombres " + pacienteDto.getNombres());
            System.out.println("Apellidos " + pacienteDto.getApellidos());
        }));
        System.out.println(" ----------------------------------");
        return this.listaPacientes;
    }

    @Override
    public List<Medico> obtenerMedicos() {
        System.out.println(" ---- Medicos disponibles -------");
        this.mapMedicos.forEach((key, value) -> {
            System.out.println("\n Id " + value.getId());
            System.out.println("Nombres " + value.getNombres());
            System.out.println("Apellidos " + value.getApellidos() + " \n ");
        });
        System.out.println(" ----------------------------------");
        return this.mapMedicos.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public RespuestaHttp actualizarPaciente(Paciente paciente) {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Paciente pacienteTemp = this.listaPacientes.stream().filter(value -> value.getId().equals(paciente.getId())).findAny().orElse(null);


            if (pacienteTemp != null) {

                this.listaPacientes.stream().filter(value -> value.getId().equals(paciente.getId())).forEach(p -> {
                    p.setId(paciente.getId());
                    p.setNombres(paciente.getNombres());
                    p.setApellidos(paciente.getApellidos());
                    p.setDireccion(paciente.getDireccion());
                    p.setTelefono(paciente.getTelefono());
                    p.setSexo(paciente.getSexo());
                });
                respuesta.setEstadoHttp(HttpStatus.OK);
                respuesta.setMensaje(PACIENTE_ACTUALIZADO);
            } else {

                respuesta.setMensaje(PACIENTE_NO_ENCONTRADO);
                respuesta.setEstadoHttp(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            respuesta.setMensaje(OCURRIO_ERROR_INESPERADO);
            respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

    @Override
    public RespuestaHttp eliminarPaciente(Integer id) {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Paciente pacienteTemp = this.listaPacientes.stream().filter(value -> value.getId().equals(id)).findAny().orElse(null);

            if (pacienteTemp != null) {
                this.listaPacientes.remove(pacienteTemp);
                respuesta.setEstadoHttp(HttpStatus.OK);
                respuesta.setMensaje(PACIENTE_ELIMINADO_CON_EXITO);
            } else {
                respuesta.setMensaje(PACIENTE_NO_ENCONTRADO);
                respuesta.setEstadoHttp(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            respuesta.setMensaje(OCURRIO_ERROR_INESPERADO);
            respuesta.setEstadoHttp(HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }
}
