package com.upc.gmt.bean;

/**
 * Created by MALEX on 14/09/2017.
 */

public class ProductoBean{

    private String nombre;
    private String codigo;
    private String precio;
    private String nombreImagen;

    public ProductoBean() {
    }

    public ProductoBean(String nombre, String codigo, String precio, String nombreImagen) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.nombreImagen = nombreImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
}
