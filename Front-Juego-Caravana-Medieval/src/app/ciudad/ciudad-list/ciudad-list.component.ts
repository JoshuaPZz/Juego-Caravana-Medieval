import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CiudadService } from '../ciudad.service';
import { CiudadDto } from '../../dto/ciudad-dto';

@Component({
  selector: 'app-ciudad-list',
  imports: [CommonModule],
  templateUrl: './ciudad-list.component.html',
  styleUrl: './ciudad-list.component.css',
})
export class CiudadListComponent {
  ciudades: CiudadDto[] = [];
  constructor(private ciudadService: CiudadService) {}

  ngOnInit(): void {
    this.ciudadService
      .listarCiudades()
      .subscribe((listaCiudades) => (this.ciudades = listaCiudades));
  }
}
