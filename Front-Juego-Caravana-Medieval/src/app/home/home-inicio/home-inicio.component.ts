import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home-inicio',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home-inicio.component.html',
  styleUrl: './home-inicio.component.css',
})
export class HomeInicioComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {
    const music = document.getElementById(
      'backgroundMusic'
    ) as HTMLAudioElement;
    if (music) {
      music.volume = 0.4;
    }
  }

  startGame(): void {
    this.router.navigate(['/principal']);
  }
}
