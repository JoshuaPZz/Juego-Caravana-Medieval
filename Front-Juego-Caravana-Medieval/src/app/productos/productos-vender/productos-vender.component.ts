import { Component, OnInit } from '@angular/core';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';
import { CommonModule } from '@angular/common';
import { ProductoService } from '../producto.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ViajarService } from '../../viaje/viajar.service';
import { PopupComponent } from '../../popup/popup.component';
import { CiudadDto } from '../../dto/ciudad-dto';

@Component({
  selector: 'app-vender-productos',
  imports: [CaravanaViewComponent, CommonModule, ViajarService],
  templateUrl: './vender-productos.component.html',
  styleUrl: './vender-productos.component.css',
})
export class ProductosVenderComponent implements OnInit {
  ciudadActual!: CiudadDto;
  constructor(
    private productoService: ProductoService,
    private viajarService: ViajarService,
    private route: ActivatedRoute,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    const idCaravana: number =
      Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.viajarService.ciudadActual(idCaravana).subscribe({
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
