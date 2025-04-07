import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CiudadListComponent } from './ciudad/ciudad-list/ciudad-list.component';

@Component({
  selector: 'app-root',
  imports: [RouterModule, CiudadListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
/*@NgModule({
  declarations: [AppComponent, PopupComponent],
  imports: [BrowserModule, BrowserAnimationsModule, MatDialogModule],
  providers: [],
  bootstrap: [AppComponent],
})*/
export class AppComponent {
  title = 'Front-Juego-Caravana-Medieval';
}
