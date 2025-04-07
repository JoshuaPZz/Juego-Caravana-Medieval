import { Component, OnInit } from '@angular/core';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';
import { CommonModule } from '@angular/common';
import { CiudadDto } from '../../dto/ciudad-dto';
import { ViajarService } from '../../viaje/viajar.service';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { PopupComponent } from '../../popup/popup.component';
import { ServicioListComponent } from '../servicio-list/servicio-list.component';

@Component({
  selector: 'app-servicio-comprar',
  imports: [CaravanaViewComponent, CommonModule, ServicioListComponent],
  templateUrl: './servicio-comprar.component.html',
  styleUrl: './servicio-comprar.component.css',
})
export class ServicioComprarComponent implements OnInit {
  ciudadActual!: CiudadDto;

  constructor(
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
}
