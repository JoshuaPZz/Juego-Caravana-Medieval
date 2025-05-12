import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { RutaDTO } from '../dto/ruta-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { AuthServiceService } from '../servicios/auth-services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class RutaService {
  constructor(private httpClient: HttpClient,private authService: AuthServiceService) {}

  ciudadesDestino(id: number): Observable<CiudadDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<CiudadDto[]>(
      `${environment.serverUrl}/ciudad/ciudadesorigen`,
      { headers, withCredentials: true }
    );
  }
  rutasHaciaDestino(id: number, iddestino: number): Observable<RutaDTO[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<RutaDTO[]>(
      `${environment.serverUrl}/ciudad/rutashacia/${iddestino}`,   //CAMBIAR NO SE DEBE MANDAR EL ID DE LA CARAVANA
      { headers, withCredentials: true }
    );
  }
}
