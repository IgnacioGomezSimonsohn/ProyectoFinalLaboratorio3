package prendas;

import com.google.gson.annotations.Expose;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import java.util.Objects;

public class Prenda {
    @Expose
    private String id;
    @Expose
    private Talle talle;
    @Expose
    private Color color;
    @Expose
    private Genero genero;
    @Expose
    private Double precio;
    @Expose
    private int stock;

    public Prenda(Talle talle, Color color, Genero genero, Double precio, int stock) {
        this.id= color.name().substring(0,3)+genero.name().substring(03);
        this.talle = talle;
        this.color = color;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }
    protected void setId(String id){
        this.id=id;
    }

    public Talle getTalle() {
        return talle;
    }

    public void setTalle(Talle talle) {
        this.talle = talle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public void restarStock(){
        this.stock = this.stock -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenda prendas = (Prenda) o;
        return Objects.equals(id, prendas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(talle, color, genero, precio, stock);
    }
    @Override
    public String toString(){
        return String.format("""
                | %s | %s | %s | $%.2f | %d | id: %s
                """,this.talle, this.color, this.genero, this.precio,this.stock,this.id);
    }
}
