import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CiudadDto } from '../dto/ciudad-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { CiudadService } from '../ciudad/ciudad.service';
import { CaravanaDto } from '../dto/caravana-dto';
import { ProductoDto } from '../dto/producto-dto';
import { CaravanaProductoDto } from '../dto/caravana-producto-dto';
import { AuthServiceService } from '../servicios/auth-services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class CaravanaService {
  constructor(private httpClient: HttpClient, private authService: AuthServiceService) {}

  caravanaActual(id : number): Observable<CaravanaDto> {
    const rawToken = this.authService.token();
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<CaravanaDto>(
      `${environment.serverUrl}/caravana/actual`,
      { headers, withCredentials: true }
    );
  }

  productosCaravana(id: number): Observable<ProductoDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.httpClient.get<ProductoDto[]>(
      `${environment.serverUrl}/caravana/${id}/productos`, //CAMBIAR NO SE DEBE MANDAR EL ID DE LA CARAVANA
      { headers, withCredentials: true }
    );
  }

  caravanaProductos(id: number): Observable<CaravanaProductoDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);      
    return this.httpClient.get<CaravanaProductoDto[]>(
      `${environment.serverUrl}/caravana/${id}/caravanaproductos`,      //CAMBIAR NO SE DEBE MANDAR EL ID DE LA CARAVANA
      { headers, withCredentials: true }
    );
  }

  nuevoJuego(): Observable<any>{
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);   
    return this.httpClient.put<any>(
      `${environment.serverUrl}/caravana/nuevojuego`, 
      {},
      { headers, withCredentials: true }
    );
  }
}
