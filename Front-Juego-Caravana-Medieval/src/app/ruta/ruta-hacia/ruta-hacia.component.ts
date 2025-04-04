import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { RutaDTO } from '../../dto/ruta-dto';
import { RutaService } from '../ruta.service';

@Component({
  selector: 'app-ruta-hacia',
  imports: [CommonModule],
  templateUrl: './ruta-hacia.component.html',
  styleUrl: './ruta-hacia.component.css',
})
export class RutaHaciaComponent implements OnChanges {
  @Input() idO: number = 0;
  @Input() idD: number = 0;

  rutasHaciaDestino: RutaDTO[] = [];

  constructor(private rutaService: RutaService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['idO'] || changes['idD']) {
      console.log('Nuevos valores recibidos:', this.idO, this.idD);
      this.cargarRutas();
    }
  }

  private cargarRutas() {
    console.log('Ejecutando cargarRutas() con:', this.idO, this.idD);

    if (this.idO && this.idD) {
      this.rutaService.rutasHaciaDestino(this.idO, this.idD).subscribe({
        next: (rutas) => {
          this.rutasHaciaDestino = rutas;
          console.log('Rutas cargadas:', this.rutasHaciaDestino);
        },
        error: (err) => console.error('Hubo un error', err),
      });
    }
  }
}
