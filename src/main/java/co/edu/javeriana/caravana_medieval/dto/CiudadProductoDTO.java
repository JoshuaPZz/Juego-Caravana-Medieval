package co.edu.javeriana.caravana_medieval.dto;


public class CiudadProductoDTO {
    private Long idCiudad;
    private Long idProducto;
    private double factorDemanda;
    private double factorOferta;
    private double precioCompra;
    private double precioVenta;

    public CiudadProductoDTO(){}
    public CiudadProductoDTO(Long idCiudad, Long idProducto, double factorDemanda, double factorOferta,
            double precioCompra, double precioVenta) {
        this.idCiudad = idCiudad;
        this.idProducto = idProducto;
        this.factorDemanda = factorDemanda;
        this.factorOferta = factorOferta;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
    public Long getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public double getFactorDemanda() {
        return factorDemanda;
    }
    public void setFactorDemanda(double factorDemanda) {
        this.factorDemanda = factorDemanda;
    }
    public double getFactorOferta() {
        return factorOferta;
    }
    public void setFactorOferta(double factorOferta) {
        this.factorOferta = factorOferta;
    }
    public double getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
    public double getPrecioVenta() {
        return precioVenta;
    }
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    
}
