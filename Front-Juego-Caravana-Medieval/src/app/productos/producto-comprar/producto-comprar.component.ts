import { Component } from '@angular/core';
import { ViajarService } from '../../viaje/viajar.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CiudadDto } from '../../dto/ciudad-dto';
import { PopupComponent } from '../../popup/popup.component';
import { ProductosListComponent } from '../productos-list/productos-list.component';
import { CommonModule } from '@angular/common';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';

@Component({
  selector: 'app-producto-comprar',
  imports: [ProductosListComponent, CommonModule, CaravanaViewComponent],
  templateUrl: './producto-comprar.component.html',
  styleUrl: './producto-comprar.component.css'
})
export class ProductoComprarComponent {
  ciudadActual!: CiudadDto;

  constructor(
    private viajarService : ViajarService,
    private route: ActivatedRoute,
    private dialog: MatDialog
  ) {}
  
  ngOnInit() : void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;
    this.viajarService.ciudadActual(caravanaId).subscribe({
      next : (ciudad) => {
        this.ciudadActual = ciudad;
        console.log('CiudadActual: ', this.ciudadActual)
      },
      error: (error) => {
        console.error('Error al obtener la ciudad actual: ', error);
      this.dialog.open(PopupComponent, {
        width: '400px',
          data: {
            message: error.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
      });
    }
  });
  }
}
