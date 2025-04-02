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
}
