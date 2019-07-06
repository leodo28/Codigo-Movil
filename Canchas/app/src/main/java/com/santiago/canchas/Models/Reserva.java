package com.santiago.canchas.Models;

public class Reserva {
    int usuario;
    int cancha;
    int numero_cancha;
    String fecha;
    String hora;

    public Reserva(int usuario, int cancha, int numero_cancha, String fecha, String hora) {
        this.usuario = usuario;
        this.cancha = cancha;
        this.numero_cancha = numero_cancha;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getCancha() {
        return cancha;
    }

    public void setCancha(int cancha) {
        this.cancha = cancha;
    }

    public int getNumero_cancha() {
        return numero_cancha;
    }

    public void setNumero_cancha(int numero_cancha) {
        this.numero_cancha = numero_cancha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
