import { Component, Input} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RutaService } from '../../ruta/ruta.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { CommonModule } from '@angular/common';
import { CiudadService } from '../../ciudad/ciudad.service';
import { RutaHaciaComponent } from '../ruta-hacia/ruta-hacia.component';

@Component({
  selector: 'app-ruta-destinos',
  imports: [CommonModule, RutaHaciaComponent],
  templateUrl: './ruta-destinos.component.html',
  styleUrl: './ruta-destinos.component.css',
})
export class RutaDestinosComponent{
  ciudadesDestinos: CiudadDto[] = [];
  ciudadActual!: CiudadDto;

  constructor(
    private rutaService: RutaService,
    private ciudadService: CiudadService
  ) {}

  @Input()
  set id(id: number) {
    this.rutaService.ciudadesDestino(id).subscribe(
      {
        next: ciudadesDestinos => {
          for (let ciudad of ciudadesDestinos){
            this.ciudadesDestinos.push(ciudad)
          }
          console.log('Ciudades de Destino:', this.ciudadesDestinos);
        },
        error: datoError =>{
          console.error("Hubo un error", datoError)
        } 
      }
    );
    this.ciudadService.getCiudadById(id).subscribe(
      {
        next: ciudad => {
          this.ciudadActual = ciudad;
          console.log('Ciudad Actual: ', ciudad);
        },
        error: (err) => console.error('Hubo un error: ', err),
      }
    )
  }
}
