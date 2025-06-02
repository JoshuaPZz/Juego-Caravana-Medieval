import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoDto } from '../dto/producto-dto';
import { environment } from '../../environments/environment.development';
import { CiudadProductoDto } from '../dto/ciudad-producto-dto';
import { CaravanaProductoDto } from '../dto/caravana-producto-dto';
import { AuthServiceService } from '../servicios/auth-services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  constructor(private http: HttpClient, private authService: AuthServiceService) {}

  listProducts(): Observable<ProductoDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.http.get<ProductoDto[]>(
      `${environment.serverUrl}/productos/list`,
      { headers, withCredentials: true }
    );
  }

  venderProducto(ciudadProducto : CiudadProductoDto) : Observable<any> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.http.put<any>(
      `${environment.serverUrl}/vender/productos`, ciudadProducto,
      { headers, withCredentials: true });
  }
  listProductsCiudad(idCiudad : number) : Observable<ProductoDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.http.get<ProductoDto[]>(
      `${environment.serverUrl}/productoCiudad/list/${idCiudad}`,
      { headers, withCredentials: true }
    )
  }

  listCiudadProducto(idCiudad: number) : Observable<CiudadProductoDto[]> {
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.http.get<CiudadProductoDto[]>(
      `${environment.serverUrl}/productoCiudad/list/ciudadProducto/${idCiudad}`,
      { headers, withCredentials: true }
    )
  }
  comprarProducto(caravanaProducto : CaravanaProductoDto) : Observable<any>{
    const rawToken = this.authService.token(); 
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${rawToken}`);
    return this.http.put<any>(`${environment.serverUrl}/comprar/productos`, caravanaProducto,
      { headers, withCredentials: true }
    )
  } 
}
