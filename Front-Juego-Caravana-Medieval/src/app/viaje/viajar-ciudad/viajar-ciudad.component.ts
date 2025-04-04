import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CiudadService } from '../../ciudad/ciudad.service';
import { ViajarService } from '../../viaje/viajar.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';
import { RutaDestinosComponent } from '../../ruta/ruta-destinos/ruta-destinos.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-viajar-ciudad',
  imports: [CaravanaViewComponent, RutaDestinosComponent, CommonModule],
  templateUrl: './viajar-ciudad.component.html',
  styleUrls: ['./viajar-ciudad.component.css'],
})
export class ViajarCiudadComponent implements OnInit {
  ciudadActual!: CiudadDto;
  ciudadesDestino: any[] = [];
  rutas: any[] = [];

  constructor(
    private router: Router,
    private viajarService: ViajarService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;

    //necesitamos tener el id de la caravana en algun lado ya, lo mejor seria instanciarlo en un DTO en todo el momento, pero hacer esto desde una pantalla principal

    this.viajarService.ciudadActual(caravanaId).subscribe({
      next: (ciudad) => {
        this.ciudadActual = ciudad;
        console.log('Ciudad actual:', this.ciudadActual);
      },
      error: (error) => {
        console.error('Error al obtener la ciudad actual:', error);
      },
    });
  }

  back(): void {
    //this.router.navigate(['/ciudades/list']);
  }
}
