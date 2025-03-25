import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class CiudadService {
  constructor(private httpClient: HttpClient) {}

  listarCiudades(): Observable<CiudadDto[]> {
    return this.httpClient.get<CiudadDto[]>(
      `${environment.serverUrl}/ciudades/list`
    );
  }
}
