import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-home-inicio',
  imports: [CommonModule, RouterModule],
  templateUrl: './home-inicio.component.html',
  styleUrl: './home-inicio.component.css',
})
export class HomeInicioComponent {
  constructor(private router: Router) {}

  startGame(): void {
    this.router.navigate(['/principal']);
  }
}
