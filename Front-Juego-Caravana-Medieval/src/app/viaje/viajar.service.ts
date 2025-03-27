import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CiudadService } from '../ciudad/ciudad.service';

@Injectable({
  providedIn: 'root',
})
export class ViajarService {
  constructor(
    private httpClient: HttpClient,
    private ciudadService: CiudadService
  ) {}

  viajar() {
    // Lógica para realizar la acción de viajar
    console.log(`Viajando a:`);
    // Aquí podrías agregar lógica para, por ejemplo, navegar a otra vista o realizar alguna acción adicional
  }
}
