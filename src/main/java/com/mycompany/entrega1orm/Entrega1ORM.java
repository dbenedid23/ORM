/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.entrega1orm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        List<Compra> al = null;
        CompraPojo cp = new CompraPojo();

        String numerasion;
        System.out.println("Bienvenido al sistema de gestión de suministros.");

        while (true) {
            System.out.println("Por favor, ingrese una de las siguientes operaciones:");
            System.out.println("cargar | listar | usar | hay suministro | adquirir | salir");

            numerasion = sc.nextLine().toLowerCase();

            switch (numerasion) {
                case "cargar":
                    cagarAgain(sc, cp, al);
                case "listar":
                    listarSuministros(cp);
                    break;
                case "usar":
                    usarSuministro(sc);
                    break;
                case "hay suministro":
                    haySuministro(sc, cp);
                    break;
                case "adquirir":
                    adquirirSuministro(sc, al, cp);
                    break;
                case "salir":
                    System.out.println("¡bye bye disfrutón!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Operación no válida. Inténtslo de nuevo.");
            }
        }
    }

    public static void cagarAgain(Scanner sc, CompraPojo cp, List<Compra> al) {
        Compra c;
        String archivo;
        System.out.println("Que archivo quieres introducir: ");
        archivo = sc.nextLine();
        ArrayList<Compra> alTodo = leerCSV(archivo);

        try {
            for (Compra compra : alTodo) {
                al = cp.getCompra2(compra.getSuministro());
                if (al != null) {
                    if (!al.isEmpty()) {
                        c = al.get(0);
                        c.setCantidad(c.getCantidad() + compra.getCantidad());
                        cp.updateCompra(c);
                    } else {
                        cp.addCompra(compra);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Compra> leerCSV(String ruta) {
        String linea;
        File f = new File(ruta);
        Compra c;
        ArrayList<Compra> allIn = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            br.readLine();
            while ((linea = br.readLine()) != null) {
                c = new Compra();
                c.setCantidad(Integer.parseInt(linea.split(";")[0]));
                c.setSuministro(linea.split(";")[1].toLowerCase());
                allIn.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allIn;
    }

    private static void listarSuministros(CompraPojo cp) {
        System.out.println("Suministros disponibles:");
        List<Compra> compras = cp.mostrarAllCompra();
        for (Compra compra : compras) {
            System.out.println(compra.getSuministro() + ": " + compra.getCantidad());
        }
    }

    private static void usarSuministro(Scanner sc) {

        CompraPojo cp = new CompraPojo();
        System.out.println("Ingresa el suministro que desea usar:");
        String suministro = sc.nextLine().toLowerCase();
        System.out.println("Ingresa la cantidad que desea usar:");
        int cantidad = Integer.parseInt(sc.nextLine());

        if (cantidad > 0) {
            int usado;
            for (Compra compra : cp.getCompra2(suministro)) {
                System.out.println(compra);
                if (cantidad > compra.getCantidad()) {
                    System.out.println("Quedan " + compra.getCantidad() + " de " + suministro);
                } else if (cantidad == compra.getCantidad()) {
                    cp.deleteCompra(compra);
                } else {
                    usado = compra.getCantidad() - cantidad;
                    compra.setCantidad(usado);
                    cp.updateCompra(compra);
                    System.out.println("nuevo: " + compra);
                }
            }
        } else {
            System.out.println("Tienes que introducir un numero positivo");
        }

    }

    private static void haySuministro(Scanner sc, CompraPojo cp) {
        System.out.println("Ingrese el suministro que desea buscar:");
        String suministro = sc.nextLine().toLowerCase();
        if (cp.getCompraBySuministro(suministro).isEmpty()) {
            System.out.println("No hay de ese producto");
        } else {
            for (Compra compra : cp.getCompraBySuministro(suministro)) {
                System.out.println("Queda " + compra.getCantidad() + " de " + compra.getSuministro());
            }
        }

        //  System.out.println(cp.getCompraBySuministro(suministro));
    }

    private static void adquirirSuministro(Scanner sc, List<Compra> al, CompraPojo cp) {
        System.out.println("Ingrese el suministro que desea adquirir:");
        String suministro = sc.nextLine().toLowerCase();
        System.out.println("Ingrese la cantidad que desea adquirir:");
        int cantidad = Integer.parseInt(sc.nextLine());

        if (cantidad > 0) {
            Compra nuevo = new Compra();
            nuevo.setSuministro(suministro);
            nuevo.setCantidad(cantidad);
            al = cp.getCompra2(nuevo.getSuministro());
            if (al != null) {
                if (!al.isEmpty()) {
                    nuevo = al.get(0);
                    nuevo.setCantidad(nuevo.getCantidad() + cantidad);
                    cp.updateCompra(nuevo);
                } else {
                    cp.addCompra(nuevo);
                }
            }
            for (Compra compra : cp.getCompra2(nuevo.getSuministro())) {
                System.out.println(compra);
            }
        } else {
            System.out.println("Introducir cantidad positiva");
        }

    }
}
