/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.*;

/**
 *
 * @author dev
 */
@Entity
@Table(name = "ListaDeCompra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private String suministro;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getSuministro() {
        return suministro;
    }

    public void setSuministro(String suministro) {
        this.suministro = suministro;
    }

    public Compra(int cantidad, String suministro) {
        this.cantidad = cantidad;
        this.suministro = suministro;
    }
    public Compra() {
       
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", cantidad=" + cantidad + ", suministro=" + suministro + '}';
    }


      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      
}
