import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CiudadService } from '../../ciudad/ciudad.service';
import { ViajarService } from '../../viaje/viajar.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';

@Component({
  selector: 'app-viajar-ciudad',
  imports: [CaravanaViewComponent],
  templateUrl: './viajar-ciudad.component.html',
  styleUrls: ['./viajar-ciudad.component.css'],
})
export class ViajarCiudadComponent implements OnInit {
  ciudadActual!: CiudadDto;
  rutas: any[] = [];

  constructor(
    private router: Router,
    private viajarService: ViajarService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;
    //necesitamos tener el id de la caravana en algun lado ya, lo mejor seria instanciarlo en un DTO en todo el momento, pero hacer esto desde una pantalla principal
    this.viajarService.ciudadActual(caravanaId).subscribe((ciudad) => {
      this.ciudadActual = ciudad;
      console.log('Ciudad actual:', this.ciudadActual);
    });

    this.rutas = [
      { nombre: 'Ruta 1', ciudadDestino: 'Ciudad A' },
      { nombre: 'Ruta 2', ciudadDestino: 'Ciudad B' },
    ];
  }

  viajar() {
    this.viajarService.viajar();
  }

  back(): void {
    //this.router.navigate(['/ciudades/list']);
  }
}
