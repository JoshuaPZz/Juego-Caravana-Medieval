import { Routes } from '@angular/router';
import { CiudadListComponent } from './ciudad/ciudad-list/ciudad-list.component';
import { CiudadViewComponent } from './ciudad/ciudad-view/ciudad-view.component';

export const routes: Routes = [
  { path: 'ciudades/list', component: CiudadListComponent },
  { path: 'ciudades/view/:id', component: CiudadViewComponent },
];
