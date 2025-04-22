import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CaravanaProductosListComponent } from "../caravana-productos-list/caravana-productos-list.component";

@Component({
  selector: 'app-caravana-inventario',
  imports: [CaravanaProductosListComponent,
    CaravanaProductosListComponent
  ],
  templateUrl: './caravana-inventario.component.html',
  styleUrl: './caravana-inventario.component.css'
})
export class CaravanaInventarioComponent {
  constructor(private router: Router) {}
  navigateToInicio(): void {
    this.router.navigate(['/inicio']);
  }
}
