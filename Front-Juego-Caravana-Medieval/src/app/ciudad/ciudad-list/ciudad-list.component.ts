import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CiudadService } from '../ciudad.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-ciudad-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './ciudad-list.component.html',
  styleUrl: './ciudad-list.component.css',
})
export class CiudadListComponent {
  ciudades: CiudadDto[] = [];
  ciudadSeleccionada: CiudadDto | undefined;
  constructor(private ciudadService: CiudadService) {}

  ngOnInit(): void {
    /* this.ciudadService
      .listarCiudades()
      .subscribe((listaCiudades) => (this.ciudades = listaCiudades));*/

    this.ciudadService.listarCiudades().subscribe({
      next: (listaCiudades) => {
        this.ciudades = listaCiudades;
        console.log('Ciudades:', this.ciudades);
      },
      error: (error) => {
        console.error('Error al obtener las ciudades:', error);
      },
    });
  }

  seleccionarCiudad(ciudadSeleccionada: CiudadDto) {
    this.ciudadSeleccionada = ciudadSeleccionada;
  }
}
