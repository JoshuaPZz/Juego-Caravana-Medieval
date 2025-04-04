import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { RutaDTO } from '../dto/ruta-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class RutaService {
  constructor(private httpClient: HttpClient) {}

  ciudadesDestino(id: number): Observable<CiudadDto[]> {
    return this.httpClient.get<CiudadDto[]>(
      `${environment.serverUrl}/ciudad/${id}/ciudadesorigen`
    );
  }
  rutasHaciaDestino(id: number, iddestino: number): Observable<RutaDTO[]> {
    return this.httpClient.get<RutaDTO[]>(
      `${environment.serverUrl}/ciudad/${id}/rutashacia/${iddestino}`
    );
  }
}
