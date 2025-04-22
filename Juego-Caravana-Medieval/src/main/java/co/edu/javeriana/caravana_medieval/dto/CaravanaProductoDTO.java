package co.edu.javeriana.caravana_medieval.dto;

public class CaravanaProductoDTO {
    private Long idProducto;
    private int cantidad; 

    public CaravanaProductoDTO() {

    }

    public CaravanaProductoDTO(String idCaravana, Long idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}