import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ProductoDto } from '../../dto/producto-dto';
import { CaravanaService } from '../caravana.service';
import { CaravanaViewComponent } from '../caravana-view/caravana-view.component';

@Component({
  selector: 'app-caravana-productos-list',
  imports: [CommonModule, RouterModule, CaravanaViewComponent],
  templateUrl: './caravana-productos-list.component.html',
  styleUrl: './caravana-productos-list.component.css',
})
export class CaravanaProductosListComponent {
  productos: ProductoDto[] = [];
  constructor(
    private caravanaService: CaravanaService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const caravanaId = Number(this.route.snapshot.paramMap.get('id')) || 1;
    this.caravanaService.productosCaravana(caravanaId).subscribe({
      next: (listaProductos) => {
        this.productos = listaProductos;
        console.log('Productos:', this.productos);
      },
      error: (error) => {
        console.error('Error al obtener los productos:', error);
      },
    });
  }
}
