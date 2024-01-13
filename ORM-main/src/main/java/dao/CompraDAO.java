/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Compra;

/**
 *
 * @author dev
 */
public interface CompraDAO {
    List<Compra> mostrarAllCompra(); // mostrar todo lo que nos piden
    void updateCompra(Compra compra); // usar x suministro
    List<Compra> getCompraBySuministro(String suministro); //hay x suministro 
    List<Compra> getCompra2(String suministro);
    void addCompra(Compra compra); //  adquirir x suministro
    int getIdByname(Compra compra);
    void deleteCompra(Compra compra);
    //int getId(Compra compra);
}
