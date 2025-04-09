import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ProductoDto } from '../../dto/producto-dto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-productos-list',
  imports: [CommonModule],
  templateUrl: './productos-list.component.html',
  styleUrl: './productos-list.component.css',
})
export class ProductosListComponent {
  productos: ProductoDto[] = [];
  constructor(private productoService: ProductoService) {}

  @Input()
  set id(id : number) {
    this.productoService.listProductsCiudad(id).subscribe({
      next: (listaProductos) => {
        this.productos = listaProductos;
        console.log('Productos:', this.productos);
      },
      error: (error) => {
        console.error('Error al obtener los productos:', error);
      },
    });
  }
  
  comprarProducto(idProducto : number) {
    console.log("hola");
    this.productoService.comprarProducto(idProducto)
  }
}
