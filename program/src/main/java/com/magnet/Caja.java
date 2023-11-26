package com.magnet;

import java.util.ArrayList;
import java.util.List;

public class Caja {

    private double saldo;
    private List<Double> transacciones;

    public Caja() {
        this.saldo = 0.0;
        this.transacciones = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void agregarVenta(double monto) {
        transacciones.add(monto);
        saldo += monto;
    }

    public void realizarDevolucion(double monto) {
        if (transacciones.contains(monto)) {
            transacciones.remove(monto);
            saldo -= monto;
        } else {
            System.out.println("La transacción no existe en el historial.");
        }
    }

    public List<Double> obtenerHistorialTransacciones() {
        return new ArrayList<>(transacciones);
    }
}
