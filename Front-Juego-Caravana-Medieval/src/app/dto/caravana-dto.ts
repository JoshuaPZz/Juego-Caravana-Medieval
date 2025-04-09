export class CaravanaDto {
  constructor(
    public id: number,
    public nombre: string,
    public velocidad: number,
    public capacidadMax: number,
    public dineroDisponible: number,
    public puntosVida: number,
    public horaViaje: string,
    public tieneGuardias: boolean
  ) {}
}
