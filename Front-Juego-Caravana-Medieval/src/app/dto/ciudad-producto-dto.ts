export class CiudadProductoDto {

    constructor(
        public id : number,
        public idCiudad : number,
        public idProducto : number,
        public nombreProducto : string,
        public factorDemanda : number,
        public factorOferta : number,
        public precioCompra : number,
        public precioVenta : number,
        public stock : number,
    ) {}
}
    
    
