import { Component, Input } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { RutaService } from '../../ruta/ruta.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { CommonModule } from '@angular/common';
import { CiudadService } from '../../ciudad/ciudad.service';
import { RutaHaciaComponent } from '../ruta-hacia/ruta-hacia.component';
import { ViajarService } from '../../viaje/viajar.service';
import { FormsModule } from '@angular/forms';
import { RutaDTO } from '../../dto/ruta-dto';
import { CaravanaDto } from '../../dto/caravana-dto';

@Component({
  selector: 'app-ruta-destinos',
  imports: [CommonModule, RutaHaciaComponent, FormsModule],
  templateUrl: './ruta-destinos.component.html',
  styleUrl: './ruta-destinos.component.css',
})
export class RutaDestinosComponent {
  ciudadesDestinos: CiudadDto[] = [];
  ciudadActual!: CiudadDto;
  rutasSeleccionadas: { [ciudadId: number]: number } = {}; // ruta seleccionada para cada ciudad
  rutasPorCiudad: { [ciudadId: number]: RutaDTO[] } = {}; // Para almacenar las rutas disponibles por ciudad
  caravanaAux!: CaravanaDto;

  constructor(
    private rutaService: RutaService,
    private ciudadService: CiudadService,
    private viajarService: ViajarService,
    private router: Router
  ) {}

  @Input()
  set id(id: number) {
    this.rutaService.ciudadesDestino(id).subscribe({
      next: (ciudadesDestinos) => {
        this.ciudadesDestinos = [...ciudadesDestinos];
        console.log('Ciudades de Destino:', this.ciudadesDestinos);

        this.ciudadesDestinos.forEach((ciudad) => {
          this.rutaService.rutasHaciaDestino(id, ciudad.id).subscribe({
            next: (rutas: RutaDTO[]) => {
              this.rutasPorCiudad[ciudad.id] = rutas;
              console.log(`Rutas para ${ciudad.nombre}:`, rutas);
            },
            error: (err: any) =>
              console.error(
                `Error al obtener rutas para ${ciudad.nombre}:`,
                err
              ),
          });
        });
      },
      error: (datoError) => {
        console.error('Hubo un error', datoError);
      },
    });

    this.ciudadService.getCiudadById(id).subscribe({
      next: (ciudad) => {
        this.ciudadActual = ciudad;
        console.log('Ciudad Actual: ', ciudad);
      },
      error: (err) => console.error('Hubo un error: ', err),
    });
  }

  setRutaSeleccionada(ciudadId: number, rutaId: number) {
    this.rutasSeleccionadas[ciudadId] = rutaId;
    console.log(`Ruta seleccionada para ciudad ${ciudadId}: ${rutaId}`);
  }

  viajar(ciudadDestinoId: number) {
    const rutaId = this.rutasSeleccionadas[ciudadDestinoId];
    if (rutaId) {
      console.log(`Viajando a ciudad ${ciudadDestinoId} por ruta ${rutaId}`);
      this.viajarService.viajar(ciudadDestinoId, rutaId, 1).subscribe({
        next: (caravana) =>{
          this.caravanaAux = caravana;  
          window.location.reload();
        },
        error: (err) => console.error('Hubo un error: ', err),
      });
    } else {
      console.error('Por favor selecciona una ruta primero');
    }
  }
}
