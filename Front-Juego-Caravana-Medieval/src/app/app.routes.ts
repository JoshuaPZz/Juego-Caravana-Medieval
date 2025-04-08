import { Routes } from '@angular/router';
import { CiudadListComponent } from './ciudad/ciudad-list/ciudad-list.component';
import { CiudadViewComponent } from './ciudad/ciudad-view/ciudad-view.component';
import { ViajarCiudadComponent } from './viaje/viajar-ciudad/viajar-ciudad.component';
import { CaravanaViewComponent } from './caravana/caravana-view/caravana-view.component';
import { ProductosListComponent } from './productos/productos-list/productos-list.component';
import { RutaDestinosComponent } from './ruta/ruta-destinos/ruta-destinos.component';
import { ServicioListComponent } from './servicios/servicio-list/servicio-list.component';
import { ServicioComprarComponent } from './servicios/servicio-comprar/servicio-comprar.component';
export const routes: Routes = [
  { path: 'ciudades/list', component: CiudadListComponent }, // Listado de ciudades
  { path: 'ciudades/view/:id', component: CiudadViewComponent }, // Vista de una ciudad
  { path: 'viajar', component: ViajarCiudadComponent }, // Viajar a una ciudad
  { path: 'productos/list', component: ProductosListComponent }, // Listado de productos
  { path: 'caravana/:id', component: CaravanaViewComponent }, // Vista de la caravana
  { path: 'caravana', component: CaravanaViewComponent }, // Vista de la caravana (sin ID)
  { path: 'ciudad/:id/rutas', component: RutaDestinosComponent }, // Rutas desde una ciudad

  { path: 'ciudad/:id/servicios', component: ServicioListComponent }, // Servicios de una ciudad

  { path: 'comprar/servicio', component: ServicioComprarComponent },
];
