import { Routes } from '@angular/router';
import { CiudadListComponent } from './ciudad/ciudad-list/ciudad-list.component';
import { CiudadViewComponent } from './ciudad/ciudad-view/ciudad-view.component';
import { ViajarCiudadComponent } from './viaje/viajar-ciudad/viajar-ciudad.component';
import { CaravanaViewComponent } from './caravana/caravana-view/caravana-view.component';
import { ProductosListComponent } from './productos/productos-list/productos-list.component';
import { RutaDestinosComponent } from './ruta/ruta-destinos/ruta-destinos.component';
import { ServicioListComponent } from './servicios/servicio-list/servicio-list.component';
import { ServicioComprarComponent } from './servicios/servicio-comprar/servicio-comprar.component';
import { HomeInicioComponent } from './home/home-inicio/home-inicio.component';
import { HomePrincipalComponent } from './home/home-principal/home-principal.component';
import { ProductoComprarComponent } from './productos/producto-comprar/producto-comprar.component';
import { CaravanaProductosListComponent } from './caravana/caravana-productos-list/caravana-productos-list.component';
import { ProductosVenderComponent } from './productos/productos-vender/productos-vender.component';
import { CaravanaInventarioComponent } from './caravana/caravana-inventario/caravana-inventario.component';
import { LoginComponent } from './authentication/login/login.component';
export const routes: Routes = [
  { path: 'ciudades/list', component: CiudadListComponent }, // Listado de ciudades
  { path: 'ciudades/view/:id', component: CiudadViewComponent }, // Vista de una ciudad
  { path: 'viajar', component: ViajarCiudadComponent }, // Viajar a una ciudad
  { path: 'productos/list/:id', component: ProductosListComponent }, // Listado de productos
  { path: 'caravana/:id', component: CaravanaViewComponent }, // Vista de la caravana
  { path: 'caravana', component: CaravanaViewComponent }, // Vista de la caravana (sin ID)
  { path: 'ciudad/:id/rutas', component: RutaDestinosComponent }, // Rutas desde una ciudad
  { path: 'ciudad/:id/servicios', component: ServicioListComponent }, // Servicios de una ciudad
  { path: 'comprar/servicio', component: ServicioComprarComponent },
  { path: '', component: LoginComponent }, // Componente de inicio
  { path: 'home', component: HomeInicioComponent },
  { path: 'inicio', component: HomePrincipalComponent }, // Componente de pantalla principal
  { path: 'comprar/producto', component: ProductoComprarComponent }, // Comprar un producto
  { path: 'inventario', component: CaravanaInventarioComponent },
  { path: 'vender/producto', component: ProductosVenderComponent },
];
