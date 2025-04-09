export class CiudadServicioDto {
    constructor(
        public id: number,
        public idCiudad: number,
        public nombreServicio: string,
        public idServicio: string,
        public precio: number
    ) {}
}
