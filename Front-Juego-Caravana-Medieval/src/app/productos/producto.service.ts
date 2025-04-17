import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoDto } from '../dto/producto-dto';
import { environment } from '../../environments/environment.development';
import { CiudadProductoDto } from '../dto/ciudad-producto-dto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(
    private http : HttpClient
  ) { }

  listProducts() : Observable<ProductoDto[]> {
    return this.http.get<ProductoDto[]>(
      `${environment.serverUrl}/productos/list`
    );
  }
  listProductsCiudad(idCiudad : number) : Observable<ProductoDto[]> {
    return this.http.get<ProductoDto[]>(
      `${environment.serverUrl}/productoCiudad/list/${idCiudad}`
    )
  }

  listCiudadProducto(idCiudad: number) : Observable<CiudadProductoDto[]> {
    return this.http.get<CiudadProductoDto[]>(
      `${environment.serverUrl}/productoCiudad/list/ciudadProducto/${idCiudad}`
    )
  }
  comprarProducto(idProducto : number) : Observable<any>{
    return this.http.put<any>(`${environment.serverUrl}/comprar/productos/1/${idProducto}`, {})
  } 
}
