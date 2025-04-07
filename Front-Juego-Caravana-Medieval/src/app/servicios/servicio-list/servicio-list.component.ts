import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CiudadDto } from '../../dto/ciudad-dto';
import { ServiciosDTO } from '../../dto/servicios-dto';
import { ServiciosService } from '../servicios.service';

@Component({
  selector: 'app-servicio-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './servicio-list.component.html',
  styleUrl: './servicio-list.component.css',
})
export class ServicioListComponent implements OnInit {
  parametroCiudad: CiudadDto | undefined;
  servicio: ServiciosDTO[] = [];

  constructor(
    private servicioService: ServiciosService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.servicioService.listarServicios(id).subscribe({
      next: (servicios) => {
        this.servicio = servicios;
        console.log('Servicios de la ciudad loaded:', this.servicio);
      },
      error: (error) => {
        console.error('Error fetching ciudad:', error);
      },
    });
  }
}
