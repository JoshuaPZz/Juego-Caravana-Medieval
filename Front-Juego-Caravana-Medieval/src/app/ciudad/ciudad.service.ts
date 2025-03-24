import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CiudadService {
  constructor(private httpClient: HttpClient) {}

  listarCiudades(): Observable<CiudadDto[]> {
    return this.httpClient.get<CiudadDto[]>(
      'http://localhost:8088/ciudades/list'
    );
  }
}
