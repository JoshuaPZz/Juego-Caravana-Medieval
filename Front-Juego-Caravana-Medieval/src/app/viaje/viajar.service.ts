import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ViajarService {
  constructor(private httpClient: HttpClient) {}

  ciudadActual(id: number): Observable<CiudadDto> {
    return this.httpClient.get<CiudadDto>(
      `${environment.serverUrl}/viaje/ciudadActual/${id}`
    );
  }

  viajar() {
    // Lógica para realizar la acción de viajar
    console.log(`Viajando a:`);
    // Aquí se puede agregar lógica para, por ejemplo, navegar a otra vista o realizar alguna acción adicional
  }
}
