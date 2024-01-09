/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.entrega1orm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Compra;
import pojo.CompraPojo;

/**
 *
 * @author dev
 */
public class Entrega1ORM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String archivo;
        CompraPojo cp = new CompraPojo();
        // System.out.println(cp.mostrarAllCompra());

        String numerasion;
        System.out.println("Bienvenido al sistema de gestión de suministros.");

        while (true) {
            System.out.println("Por favor, ingrese una de las siguientes operaciones:");
            System.out.println("cargar | listar | usar | hay suministro | adquirir | salir");

            numerasion = sc.nextLine().toLowerCase();

            switch (numerasion) {
                case "cargar":
                    System.out.print("Que archivo quieres ingresar a la base de datos: ");
                    archivo = sc.nextLine();
                    leerCSV(archivo);
                case "listar":
                    listarSuministros();
                    break;
                case "usar":
                    usarSuministro(sc);
                    break;
                case "hay suministro":
                    haySuministro(sc);
                    break;
                case "adquirir":
                    adquirirSuministro(sc);
                    break;
                case "salir":
                    System.out.println("¡bye bye disfrutón!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Operación no válida. Por favor, inténtelo de nuevo.");
            }
        }
    }

    public static void leerCSV(String archivo) {
        CompraPojo cp = new CompraPojo();
        //Compra compra = new Compra(cantidad, suministro);
        Map<String, Integer> suministros = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String[] rotos = new String[2];
            bufferedReader.readLine();
            while ((linea = bufferedReader.readLine()) != null) {
                rotos = linea.split(";");
                if (rotos.length == 2) {
                    int cantidad = Integer.parseInt(rotos[0]);
                    String suministro = rotos[1].toLowerCase();
                    if (suministros.containsKey(suministro)) {
                        int cantidadExistente = suministros.get(suministro);
                        suministros.put(suministro, cantidadExistente + cantidad);
                    } else {
                        suministros.put(suministro, cantidad);
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : suministros.entrySet()) {
                Compra compra = new Compra(entry.getValue(), entry.getKey());
                cp.addCompra(compra);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    


    private static void listarSuministros() {
        System.out.println("Suministros disponibles:");
        CompraPojo cp = new CompraPojo();
        List<Compra> compras = cp.mostrarAllCompra();
        for (Compra compra : compras) {
            System.out.println(compra.getSuministro() + ": " + compra.getCantidad());
        }
    }

    private static void usarSuministro(Scanner sc) {

        CompraPojo cp = new CompraPojo();
        System.out.println("Ingrese el suministro que desea usar:");
        String suministro = sc.nextLine().toLowerCase();
        System.out.println("Ingrese la cantidad que desea usar:");
        int cantidad = Integer.parseInt(sc.nextLine());

        if (suministro.equals(suministro)) {
            cantidad -= cantidad;
            Compra compra = new Compra(cantidad, suministro);
            cp.updateCompra(compra);
        }

    }

    private static void haySuministro(Scanner sc) {
       // Compra compra;
        CompraPojo cp = new CompraPojo();
        System.out.println("Ingrese el suministro que desea buscar:");
        String suministro = sc.nextLine().toLowerCase();

        int cantidadTotal = cp.getCompraBySuministro(suministro);

        //  System.out.println(cp.getCompraBySuministro(suministro));
        System.out.println("Existen " + cantidadTotal + " unidades de " + suministro);
    }

    private static void adquirirSuministro(Scanner sc) {
        CompraPojo cp = new CompraPojo();
        System.out.println("Ingrese el suministro que desea adquirir:");
        String suministro = sc.nextLine().toLowerCase();
        System.out.println("Ingrese la cantidad que desea adquirir:");
        int cantidad = Integer.parseInt(sc.nextLine());

        Compra compra = new Compra(cantidad, suministro);
         cp.updateCompra(compra);

    }
}
