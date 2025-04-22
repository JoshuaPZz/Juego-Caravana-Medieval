import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ProductoDto } from '../../dto/producto-dto';
import { ProductoService } from '../producto.service';
import { CiudadProductoDto } from '../../dto/ciudad-producto-dto';
import { CaravanaProductoDto } from '../../dto/caravana-producto-dto';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../../popup/popup.component';

@Component({
  selector: 'app-productos-list',
  imports: [CommonModule],
  templateUrl: './productos-list.component.html',
  styleUrl: './productos-list.component.css',
})
export class ProductosListComponent {
  ciudadProducto: CiudadProductoDto[] = [];
  productos : ProductoDto[] =[];
  caravanaProducto : CaravanaProductoDto | undefined;
  constructor(
    private productoService: ProductoService,
    private dialog : MatDialog
  ) {}

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

   
  comprarProducto(index : number, cantidad : string) {
    const caravanaProducto : CaravanaProductoDto = {
      idProducto: this.productos[index].id,
      cantidad : Number(cantidad)
    }
    this.productoService.comprarProducto(caravanaProducto).subscribe({
      next: (productoComprado) => {
        console.log('Producto comprado:', productoComprado);
                this.dialog.open(PopupComponent, {
                  width: '400px',
                  data: {
                    message: 'Producto comprado con éxito.',
                  },
                });
      },
      error: (error) => {
        console.error('Error al comprar el producto:', error);
        // Abrir el pop-up con el mensaje de error
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: error.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
        });
      }
    })
  }
}
