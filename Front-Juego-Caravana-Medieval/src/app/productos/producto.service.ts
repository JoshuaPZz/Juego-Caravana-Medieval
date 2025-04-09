import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoDto } from '../dto/producto-dto';
import { environment } from '../../environments/environment.development';

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

  comprarProducto(idProducto : number) : Observable<any>{
    console.log(`${environment.serverUrl}/comprar/productos/${idProducto}/1`)
    return this.http.put<any>(
      `${environment.serverUrl}/comprar/productos/${idProducto}/1`,null
    )
  } 
}
