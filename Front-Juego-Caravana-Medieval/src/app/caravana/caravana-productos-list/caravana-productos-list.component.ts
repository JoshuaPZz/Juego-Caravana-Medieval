import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ProductoDto } from '../../dto/producto-dto';
import { CaravanaService } from '../caravana.service';
import { CaravanaViewComponent } from '../caravana-view/caravana-view.component';
import { CiudadProductoDto } from '../../dto/ciudad-producto-dto';
import { ViajarService } from '../../viaje/viajar.service';
import { CiudadDto } from '../../dto/ciudad-dto';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../../popup/popup.component';
import { ProductoService } from '../../productos/producto.service';
import { CaravanaProductoDto } from '../../dto/caravana-producto-dto';

@Component({
  selector: 'app-caravana-productos-list',
  imports: [CommonModule, RouterModule, CaravanaViewComponent],
  templateUrl: './caravana-productos-list.component.html',
  styleUrl: './caravana-productos-list.component.css',
})
export class CaravanaProductosListComponent {
  productos: ProductoDto[] = [];
  ciudadActual!: CiudadDto;
  caravanaProductos: CaravanaProductoDto[] = [];
  constructor(
    private router: Router,
    private caravanaService: CaravanaService,
    private route: ActivatedRoute,
    private viajarService: ViajarService,
    private dialog: MatDialog,
    private productoService: ProductoService
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
    this.caravanaService.caravanaProductos(caravanaId).subscribe({
      next: (listaCaravanaProductos) => {
        this.caravanaProductos = listaCaravanaProductos;
        console.log('CaravanaProductos:', this.caravanaProductos);
      },
      error: (error) => {
        console.error('Error al obtener los caravana productos: ', error);
      },
    });
    this.viajarService.ciudadActual(caravanaId).subscribe({
      next: (ciudad) => {
        this.ciudadActual = ciudad;
        console.log('Ciudad actual:', this.ciudadActual);
      },
      error: (error) => {
        console.error('Error al obtener la ciudad actual:', error);
        // Abrir el pop-up con el mensaje de error
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: error.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
        });
      },
    });
  }

  venderProducto(index: number, cantidad: string) {
    const ciudadProducto: CiudadProductoDto = {
      idCiudad: this.ciudadActual.id,
      idProducto: this.productos[index].id,
      nombreProducto: this.productos[index].nombre,
      factorDemanda: 0,
      factorOferta: 0,
      precioCompra: 0,
      precioVenta: 0,
      stock: Number(cantidad),
    };
    this.productoService.venderProducto(ciudadProducto).subscribe({
      next: (productoVendido) => {
        console.log('Producto vendido:', productoVendido);
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: 'Producto vendido con éxito.',
          },
        });
      },
      error: (error) => {
        console.error('Error al vender el producto:', error);
        // Abrir el pop-up con el mensaje de error
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: error.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
        });
      },
    });
  }

  navigateToInicio(): void {
    this.router.navigate(['/inicio']);
  }
}
