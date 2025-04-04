import { Routes } from '@angular/router';
import { CiudadListComponent } from './ciudad/ciudad-list/ciudad-list.component';
import { CiudadViewComponent } from './ciudad/ciudad-view/ciudad-view.component';
import { ViajarCiudadComponent } from './viaje/viajar-ciudad/viajar-ciudad.component';
import { CaravanaViewComponent } from './caravana/caravana-view/caravana-view.component';
import { RutaDestinosComponent } from './ruta/ruta-destinos/ruta-destinos.component';
import { RutaHaciaComponent } from './ruta/ruta-hacia/ruta-hacia.component';

export const routes: Routes = [
  { path: 'ciudades/list', component: CiudadListComponent },
  { path: 'ciudades/view/:id', component: CiudadViewComponent },
  { path: 'viaje/ciudad', component: ViajarCiudadComponent },
  { path: 'caravana', component: CaravanaViewComponent },
  { path: 'ciudad/:id/rutas', component: RutaDestinosComponent},
  { path: 'ciudad/:idOrigen/rutashacia/:idDestino', component: RutaHaciaComponent}
];
