import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';
import { ViajarService } from '../../viaje/viajar.service';
import { PopupComponent } from '../../popup/popup.component';
import { CiudadDto } from '../../dto/ciudad-dto';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-home-principal',
  imports: [CaravanaViewComponent],
  templateUrl: './home-principal.component.html',
  styleUrl: './home-principal.component.css',
})
export class HomePrincipalComponent {
  ciudadActual!: CiudadDto;
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
  viajar(): void {
    this.router.navigate(['/viajar']);
  }
  comprarProductos(): void {
    this.router.navigate(['/comprar/producto']);
  }
  comprarServicios(): void {
    this.router.navigate(['/comprar/servicio']);
  }
  venderProducto(): void {
    this.router.navigate(['/vender/producto']);
  }
  verInventario(): void {
    this.router.navigate(['/list/caravana/productos']);
  }
}
