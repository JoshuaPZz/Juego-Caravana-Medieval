package co.edu.javeriana.caravana_medieval.dto;


public class CiudadProductoDTO {
    private Long id;
    private Long idCiudad;
    private Long idProducto;
    private String nombreProducto;
    private double factorDemanda;
    private double factorOferta;
    private double precioCompra;
    private double precioVenta;
    private int stock;

    public CiudadProductoDTO() {
    }

    public CiudadProductoDTO(Long id, Long idCiudad, Long idProducto, String nombreProducto, double factorDemanda,
            double factorOferta, double precioCompra, double precioVenta, int stock) {
        this.id = id;
        this.idCiudad = idCiudad;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.factorDemanda = factorDemanda;
        this.factorOferta = factorOferta;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
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



    public String getNombreProducto() {
        return nombreProducto;
    }



    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
