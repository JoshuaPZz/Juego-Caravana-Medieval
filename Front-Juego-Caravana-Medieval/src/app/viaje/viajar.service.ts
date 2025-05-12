import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CaravanaDto } from '../dto/caravana-dto';
import { AuthServiceService } from '../servicios/auth-services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class ViajarService {
  constructor(private httpClient: HttpClient, private authService: AuthServiceService) {}

  ciudadActual(id:number): Observable<CiudadDto> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
  
    return this.httpClient.get<CiudadDto>(
      `${environment.serverUrl}/viaje/ciudadActual`,
      { headers, withCredentials: true }
    );
  }
  

  viajar(ciudadDestinoId: number, rutaId: number, caravanaId: number): Observable<CaravanaDto>{
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.put<CaravanaDto>(
      `${environment.serverUrl}/viaje/${ciudadDestinoId}/${rutaId}`,
      {},
      { headers, withCredentials: true }
    );
  }
}
