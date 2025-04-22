import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JugadorDto } from '../dto/jugador-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class JugadorService {
  constructor(private httpClient: HttpClient) {}

  getJugadorById(id: number): Observable<JugadorDto> {
    return this.httpClient.get<JugadorDto>(
      `${environment.serverUrl}/jugador/${id}`
    );
  }

  getCaravanaIdByJugadorId(id: number): Observable<string> {
    return this.httpClient.get<string>(
      `${environment.serverUrl}/jugador/${id}/caravana`
    );
  }
}
