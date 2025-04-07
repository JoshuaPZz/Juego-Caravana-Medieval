import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiciosDTO } from '../dto/servicios-dto';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

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
}
