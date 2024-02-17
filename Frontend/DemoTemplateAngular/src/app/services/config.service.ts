import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ConfigService {


  constructor(private http:HttpClient) { }

  endpoint:String='http://localhost:8080/api/';

  /* metodos Get del service para cada uno de las tablas del config */

  getCamas():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'camas')
  }

  getCargos():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'cargos')
  }

  getCity():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'ciudades')
  }

  getDepart():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'departamentos')
  }

  getDx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticos')
  }

  getEntidades():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'entidades')
  }

  getEstados():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'estados')
  }

  getOrigendestino():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'origendestino')
  }

  getPermisorol():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'permisosporrol')
  }

  getPermisos():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'permisos')
  }

  getPlantillas():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'plantillas')
  }

  getPrestadorservicio():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'prestadorservicio')
  }

  getProcedimientoexamenes():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'procedimientosyexamenes')
  }

  getRepresentanteLegal():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'representantelegal')
  }

  getRoles():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'roles')
  }

  getTipoAnestesia():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipoanestesia')
  }

  getTipoDocumentos():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipodocumento')
  }

  getTipoDx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipodx')
  }

  getTipoeapb():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipoentidades')
  }

  getTiponota():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tiponotas')
  }

  getTipopaciente():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipopaciente')
  }

  getTipoPlantilla():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipoplantilla')
  }

  getTipopx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipoprocedimiento')
  }

  getUbicaciones():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'ubicaciones')
  }

  getUsuarios():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'usuarios')
  }



 
  

 
}
