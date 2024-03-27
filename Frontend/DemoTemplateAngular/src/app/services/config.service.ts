import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor(private http:HttpClient) { }

  private endpoint: String ='http://localhost:8080/api/';
  private httpheaders = new HttpHeaders({'Content-Type': 'application/json'});

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

//metodos post para insertar registros

  insertarRegistro(datosRegistros: any): Observable<any> {
    return this.http.post<any>(this.endpoint + 'departamentos', datosRegistros,{headers:this.httpheaders})

  }
  addStatus(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'estados',datosRegistros,{headers:this.httpheaders})
  }

  addCamas(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'camas',datosRegistros,{headers:this.httpheaders})
  }
  addCargos(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'cargos',datosRegistros,{headers:this.httpheaders})
  }
  addCiudades(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'ciudades',datosRegistros,{headers:this.httpheaders})
  }
  addDx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'diagnosticos',datosRegistros,{headers:this.httpheaders})
  }
  addEntidades(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'entidades',datosRegistros,{headers:this.httpheaders})
  }
  addOrigenDestino(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'origendestino',datosRegistros,{headers:this.httpheaders})
  }
  addPermisoRol(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'permisosporrol',datosRegistros,{headers:this.httpheaders})
  }
  addPermisos(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'permisos',datosRegistros,{headers:this.httpheaders})
  }
  addPlantillas(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'plantillas',datosRegistros,{headers:this.httpheaders})
  }
  addPrestadorServicio(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'prestadorservicio',datosRegistros,{headers:this.httpheaders})
  }
  addProcedimientoexamenes(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'procedimientosyexamenes',datosRegistros,{headers:this.httpheaders})
  }
  addRepresentanteLegal(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'representantelegal',datosRegistros,{headers:this.httpheaders})
  }
  addRoles(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'roles',datosRegistros,{headers:this.httpheaders})
  }
  addTipoAnestesia(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipoanestesia',datosRegistros,{headers:this.httpheaders})
  }
  addTipoDocumentos(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipodocumento',datosRegistros,{headers:this.httpheaders})
  }
  addTipoDx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipodx',datosRegistros,{headers:this.httpheaders})
  }
  addTipoEAPB(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipoentidades',datosRegistros,{headers:this.httpheaders})
  }
  addTipoNota(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tiponotas',datosRegistros,{headers:this.httpheaders})
  }
  addTipoPaciente(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipopaciente',datosRegistros,{headers:this.httpheaders})
  }
  addTipoPlantilla(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipoplantilla',datosRegistros,{headers:this.httpheaders})
  }
  addTipoPx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipoprocedimiento',datosRegistros,{headers:this.httpheaders})
  }
  addUbicaciones(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'ubicaciones',datosRegistros,{headers:this.httpheaders})
  }
  addUser(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'usuarios',datosRegistros,{headers:this.httpheaders})
  }
  
      
      
    
    




 
  

 
}
