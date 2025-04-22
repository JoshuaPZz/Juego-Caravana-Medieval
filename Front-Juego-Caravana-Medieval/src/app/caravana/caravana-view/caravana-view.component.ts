import { Component } from '@angular/core';
import { CaravanaDto } from '../../dto/caravana-dto';
import { CaravanaService } from '../caravana.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-caravana-view',
  imports: [],
  templateUrl: './caravana-view.component.html',
  styleUrl: './caravana-view.component.css',
})
export class CaravanaViewComponent {
  caravana!: CaravanaDto;

  constructor(
    private caravanaService: CaravanaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.caravanaService.caravanaActual(caravanaId).subscribe({
      next: (caravana) => {
        this.caravana = caravana;
        console.log('Caravana actual:', this.caravana);
      },
      error: (error) => {
        console.error('Error fetching caravana:', error);
      },
    });
  }
  getTiempoFormateado(segundos: number): string {
    const horas = Math.floor(segundos / 3600);
    const minutos = Math.floor((segundos % 3600) / 60);
    const dias = Math.floor(horas / 24);
    const horasRestantes = horas % 24;
  
    return `${dias}d ${horasRestantes}h ${minutos}m`;
  }
}
