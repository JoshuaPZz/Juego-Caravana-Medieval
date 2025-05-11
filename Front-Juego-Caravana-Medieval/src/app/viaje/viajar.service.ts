import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CaravanaDto } from '../dto/caravana-dto';

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

  viajar(ciudadDestinoId: number, rutaId: number, caravanaId: number): Observable<CaravanaDto>{
    return this.httpClient.put<CaravanaDto>(
      `${environment.serverUrl}/viaje/${ciudadDestinoId}/${rutaId}`,
      null
    );
  }
}
