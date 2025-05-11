import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiciosDTO } from '../dto/servicios-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CiudadServicioDto } from '../dto/ciudad-servicio-dto';

@Injectable({
  providedIn: 'root',
})
export class ServiciosService {
  constructor(private httpClient: HttpClient) {}

  listarServicios(id: number): Observable<ServiciosDTO[]> {
    return this.httpClient.get<ServiciosDTO[]>(
      `${environment.serverUrl}/ciudad/${id}/servicios/list`
    );
  }

  comprar(idServicio: number, idCaravana: number): Observable<any> {
    return this.httpClient.put<any>(
      `${environment.serverUrl}/comprar/servicios/${idServicio}`,
      null
    );
  }

  ciudadServicios(id: number): Observable<CiudadServicioDto[]> {
    return this.httpClient.get<CiudadServicioDto[]>(
      `${environment.serverUrl}/ciudad/${id}/ciudadservicios`
    )
  }

  
}
