import { Component } from '@angular/core';
import { CaravanaDto } from '../../dto/caravana-dto';
import { CaravanaService } from '../caravana.service';
import { ActivatedRoute } from '@angular/router';

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
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;
    //necesitamos tener el id de la caravana en algun lado ya, lo mejor seria instanciarlo en un DTO en todo el momento, pero hacer esto desde una pantalla principal
    this.caravanaService.caravanaActual(caravanaId).subscribe((caravana) => {
      this.caravana = caravana;
      console.log('Caravana actual:', this.caravana);
    });
  }
}
