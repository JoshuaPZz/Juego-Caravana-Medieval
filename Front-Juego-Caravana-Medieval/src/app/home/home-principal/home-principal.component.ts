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

  getCiudadImagen(): string {
    const idsCaelid = [1, 10, 20, 30, 40, 50, 60, 70, 80, 90];
    const idsAnorlondo = [2, 11, 21, 31, 41, 51, 61, 71, 81, 91];
    const idsClubpenguin = [3, 12, 22, 32, 42, 52, 62, 72, 82, 92];
    const idsDeathstar = [4, 13, 23, 33, 43, 53, 63, 73, 83, 93];
    const idsHoradeaventuras = [5, 14, 24, 34, 44, 54, 64, 74, 84, 94];
    const idsTatooine = [6, 15, 25, 35, 45, 55, 65, 75, 85, 95];
    const idsTorresauron = [7, 16, 26, 36, 46, 56, 66, 76, 86, 96];
    const idsUnshowmas = [8, 17, 27, 37, 47, 57, 67, 77, 87, 97];
    const idsZelda = [9, 18, 28, 38, 48, 58, 68, 78, 88, 98];
    const idsMinecraft = [19, 29, 39, 49, 59, 69, 79, 89, 99, 100];
    if (idsCaelid.includes(this.ciudadActual?.id)) {
      return 'assets/images/caelid.png';
    }
    if (idsAnorlondo.includes(this.ciudadActual?.id)) {
      return 'assets/images/anorlondo.png';
    }
    if (idsClubpenguin.includes(this.ciudadActual?.id)) {
      return 'assets/images/clubpenguin.png';
    }
    if (idsDeathstar.includes(this.ciudadActual?.id)) {
      return 'assets/images/deathstar.png';
    }
    if (idsHoradeaventuras.includes(this.ciudadActual?.id)) {
      return 'assets/images/horadeaventura.png';
    }
    if (idsTatooine.includes(this.ciudadActual?.id)) {
      return 'assets/images/tatooine.png';
    }
    if (idsTorresauron.includes(this.ciudadActual?.id)) {
      return 'assets/images/torresauron.png';
    }
    if (idsUnshowmas.includes(this.ciudadActual?.id)) {
      return 'assets/images/unshowmas.png';
    }
    if (idsZelda.includes(this.ciudadActual?.id)) {
      return 'assets/images/zelda.png';
    }
    if (idsMinecraft.includes(this.ciudadActual?.id)) {
      return 'assets/images/minecraft.png';
    }

    return '1';
  }
}
