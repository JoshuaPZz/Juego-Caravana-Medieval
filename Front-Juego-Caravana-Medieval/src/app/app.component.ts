import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CiudadListComponent } from './ciudad/ciudad-list/ciudad-list.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CiudadListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Front-Juego-Caravana-Medieval';
}
