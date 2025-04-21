import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CaravanaService } from '../../caravana/caravana.service';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../../popup/popup.component';

@Component({
  selector: 'app-home-inicio',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home-inicio.component.html',
  styleUrl: './home-inicio.component.css',
})
export class HomeInicioComponent implements OnInit {
  constructor(
    private router: Router,
    private caravanaService : CaravanaService,
    private dialog : MatDialog
  ) {}

  ngOnInit(): void {
    const music = document.getElementById(
      'backgroundMusic'
    ) as HTMLAudioElement;
    if (music) {
      music.volume = 0.8;
    }
  }

  startGame(): void {
    this.caravanaService.nuevoJuego().subscribe({
      next: (caravanaReiniciada) => {
        console.log('Caravana reiniciada:', caravanaReiniciada);
        this.router.navigate(['/inicio']);
      },
      error: (err) => {
        console.error('Error al reiniciar la caravana:', err);
        this.dialog.open(PopupComponent, {
          width: '400px',
          data: {
            message: err.error?.errorString ?? 'Ocurrió un error inesperado.',
          },
        })
      }
    });
  }
  continuar(): void {
    this.router.navigate(['/inicio']);
  }
}
