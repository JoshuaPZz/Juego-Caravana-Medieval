import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { AuthServiceService } from '../servicios/auth-services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class CiudadService {
  constructor(private httpClient: HttpClient, private authService: AuthServiceService) {}

  listarCiudades(): Observable<CiudadDto[]> {
    const rawToken = this.authService.token();
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<CiudadDto[]>(
      `${environment.serverUrl}/ciudades/list`,
      { headers, withCredentials: true }
    );
  }

  getCiudadById(id: number): Observable<CiudadDto> {
    const rawToken = this.authService.token();
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<CiudadDto>(
      `${environment.serverUrl}/ciudades/${id}`,
      { headers, withCredentials: true }
    );
  }
}
