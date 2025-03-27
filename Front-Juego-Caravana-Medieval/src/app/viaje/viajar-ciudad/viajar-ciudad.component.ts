// viajar-ciudad.component.ts
import { Component, OnInit } from '@angular/core';
import { CiudadService } from '../../ciudad/ciudad.service';
import { ViajarService } from '../../viaje/viajar.service'; // Inyectamos el servicio

@Component({
  selector: 'app-viajar-ciudad',
  templateUrl: './viajar-ciudad.component.html',
  styleUrls: ['./viajar-ciudad.component.css'],
})
export class ViajarCiudadComponent implements OnInit {
  ciudadActual: any;
  parametroCiudad: any;
  rutas: any[] = [];

  constructor(
    private ciudadService: CiudadService,
    private viajarService: ViajarService // Inyectamos el servicio
  ) {}

  ngOnInit() {
    // Inicializamos los datos
    this.ciudadActual = { nombre: 'Ciudad Ejemplo', impuesto: 10 };
    this.parametroCiudad = { impuesto: 15 };
    this.rutas = [
      { nombre: 'Ruta 1', ciudadDestino: 'Ciudad A' },
      { nombre: 'Ruta 2', ciudadDestino: 'Ciudad B' },
    ];
  }

  viajar() {
    // Usamos el método viajar del servicio
    this.viajarService.viajar();
  }
}
