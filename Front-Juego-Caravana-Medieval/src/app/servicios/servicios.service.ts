import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiciosDTO } from '../dto/servicios-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CiudadServicioDto } from '../dto/ciudad-servicio-dto';
import { AuthServiceService } from './auth-services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class ServiciosService {
  constructor(private httpClient: HttpClient, private authService: AuthServiceService) {}

  listarServicios(id: number): Observable<ServiciosDTO[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<ServiciosDTO[]>(
      `${environment.serverUrl}/ciudad/${id}/servicios/list`,
      { headers, withCredentials: true }
    );
  }

  comprar(idServicio: number, idCaravana: number): Observable<any> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.put<any>(
      `${environment.serverUrl}/comprar/servicios/${idServicio}`,
      {},
      { headers, withCredentials: true }
    );
  }

  ciudadServicios(id: number): Observable<CiudadServicioDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<CiudadServicioDto[]>(
      `${environment.serverUrl}/ciudad/${id}/ciudadservicios`,
      { headers, withCredentials: true }
    )
  }

  
}
