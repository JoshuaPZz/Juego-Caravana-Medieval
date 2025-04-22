import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CiudadDto } from '../../dto/ciudad-dto';
import { ServiciosDTO } from '../../dto/servicios-dto';
import { ServiciosService } from '../servicios.service';
import { ViajarService } from '../../viaje/viajar.service';
import { PopupComponent } from '../../popup/popup.component';
import { MatDialog } from '@angular/material/dialog';
import { CiudadServicioDto } from '../../dto/ciudad-servicio-dto';

@Component({
  selector: 'app-servicio-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './servicio-list.component.html',
  styleUrl: './servicio-list.component.css',
})
export class ServicioListComponent {
  parametroCiudad: CiudadDto | undefined;
  servicio: ServiciosDTO[] = [];
  ciudadServicio: CiudadServicioDto[] = [];

  constructor(
    private servicioService: ServiciosService,
    private router: Router,
    private route: ActivatedRoute,
    private viajarService: ViajarService,
    private dialog: MatDialog
  ) {}

  @Input()
  set id(id: number) {
    this.servicioService.listarServicios(id).subscribe({
      next: (servicios) => {
        this.servicio = servicios;
        console.log('Servicios de la ciudad loaded:', this.servicio);
      },
      error: (error) => {
        console.error('Error fetching ciudad:', error);
      },
    });

    this.servicioService.ciudadServicios(id).subscribe({
      next: (ciudadServicio) => {
        this.ciudadServicio = ciudadServicio;
        console.log('CiudadServicios de la ciudad loaded: ', this.ciudadServicio);
      },
      error: (error) => {
        console.error('Error: ', error)
      }
    })
  }

  comprar(id: number): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;
    console.log('id del servicio: ',id)
    this.servicioService.comprar(id, caravanaId).subscribe({
      next: (response) => {
        console.log('Servicio comprado:', response);
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: 'Servicio comprado con éxito.',
          },
        });
      },
      error: (error) => {
        console.error('Error al comprar el servicio:', error);
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: error.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
        });
      },
    });
  }
}
