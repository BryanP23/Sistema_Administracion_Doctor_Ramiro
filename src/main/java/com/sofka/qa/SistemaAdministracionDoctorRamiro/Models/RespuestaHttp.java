package com.sofka.qa.SistemaAdministracionDoctorRamiro.Models;

import org.springframework.http.HttpStatus;

public class RespuestaHttp {
    private String mensaje;

    private HttpStatus estadoHttp;

    public RespuestaHttp() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HttpStatus getEstadoHttp() {
        return estadoHttp;
    }

    public void setEstadoHttp(HttpStatus estadoHttp) {
        this.estadoHttp = estadoHttp;
    }
}