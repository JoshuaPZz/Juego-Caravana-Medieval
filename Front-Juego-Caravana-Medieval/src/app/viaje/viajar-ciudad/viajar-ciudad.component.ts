import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CiudadService } from '../../ciudad/ciudad.service';
import { ViajarService } from '../../viaje/viajar.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';
import { RutaDestinosComponent } from '../../ruta/ruta-destinos/ruta-destinos.component';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../../popup/popup.component'; // Ajusta la ruta según donde se encuentre tu componente

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
    private route: ActivatedRoute,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.viajarService.ciudadActual(caravanaId).subscribe({
      next: (ciudad) => {
        this.ciudadActual = ciudad;
        console.log('Ciudad actual:', this.ciudadActual);
      },
      error: (error) => {
        console.error('Error al obtener la ciudad actual:', error);
        // Abrir el pop-up con el mensaje de error
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: error.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
        });
      },
    });
  }

  back(): void {
    //this.router.navigate(['/ciudades/list']);
  }
}
