import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ProductoDto } from '../../dto/producto-dto';
import { ProductoService } from '../producto.service';
import { CiudadProductoDto } from '../../dto/ciudad-producto-dto';

@Component({
  selector: 'app-productos-list',
  imports: [CommonModule],
  templateUrl: './productos-list.component.html',
  styleUrl: './productos-list.component.css',
})
export class ProductosListComponent {
  ciudadProducto: CiudadProductoDto[] = [];
  productos : ProductoDto[] =[];
  constructor(private productoService: ProductoService) {}

  @Input()
  set id(id : number) {
    this.productoService.listCiudadProducto(id).subscribe({
      next: (listaProductos) => {
        this.ciudadProducto = listaProductos;
        console.log('Productos:', this.ciudadProducto);
      },
      error: (error) => {
        console.error('Error al obtener los productos:', error);
      },
    });
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
    this.productoService.comprarProducto(idProducto).subscribe({
      next: (productoComprado) => {
      },
      error: (error) => {
        console.error('Error al procesar compra de producto', error);
      }
    })
  }
}
