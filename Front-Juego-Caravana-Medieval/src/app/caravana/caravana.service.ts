import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CiudadService } from '../ciudad/ciudad.service';
import { CaravanaDto } from '../dto/caravana-dto';
import { ProductoDto } from '../dto/producto-dto';

@Injectable({
  providedIn: 'root',
})
export class CaravanaService {
  constructor(private httpClient: HttpClient) {}

  caravanaActual(id: number): Observable<CaravanaDto> {
    return this.httpClient.get<CaravanaDto>(
      `${environment.serverUrl}/caravana/${id}`
    );
  }

  productosCaravana(id: number): Observable<ProductoDto[]> {
    return this.httpClient.get<ProductoDto[]>(
      `${environment.serverUrl}/caravana/${id}/productos`
    );
  }
}
