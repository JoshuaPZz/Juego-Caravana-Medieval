import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CaravanaViewComponent } from '../../caravana/caravana-view/caravana-view.component';

@Component({
  selector: 'app-home-principal',
  imports: [CaravanaViewComponent],
  templateUrl: './home-principal.component.html',
  styleUrl: './home-principal.component.css'
})
export class HomePrincipalComponent {

  constructor(
    private router : Router
  ) {}
  viajar() : void {
    this.router.navigate(['/viajar'])
  }
  comprarProductos() : void {
    this.router.navigate(['/comprar/producto'])
  }
  comprarServicios() : void {
    this.router.navigate(['/comprar/servicio'])
  }
  venderProducto() : void {
    this.router.navigate([''])
  }
  verInventario() : void {
    this.router.navigate([''])
  }

}
