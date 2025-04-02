import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductoDto } from '../../dto/producto-dto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-productos-list',
  imports: [CommonModule],
  templateUrl: './productos-list.component.html',
  styleUrl: './productos-list.component.css'
})
export class ProductosListComponent {

  productos: ProductoDto[] = [];

  constructor(
    private productoService : ProductoService
  ) {}

  ngOnInit() : void {
    console.log("sigma")
    this.productoService.listProducts()
    .subscribe(listaProductos => this.productos = listaProductos);
  }
}
