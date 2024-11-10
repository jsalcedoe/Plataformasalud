import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OperacionService {

  constructor(private http:HttpClient) { }

  private endpoint: String ='http://localhost:8080/api/';
  private httpheaders = new HttpHeaders({'Content-Type': 'application/json'});

  // Servicios para componente paciente
  getPacientes():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'pacientes')
  }

  getPacientesId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'pacientes/'+id)
  }

  getPacientesXnomOdoc():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'pacientes/buscar/${keyword}')
  }

  addPacientes(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'pacientes',datosRegistros,{headers:this.httpheaders})
  }

  // Servicios para componente Eventos

  addEventos(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'evento',datosRegistros,{headers:this.httpheaders})
  }
  getEventos():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evento')
  }

  getEventId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evento/'+id)
  }

  // Servicios para componentes de historiaclinca

  addHcpac(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'historiaclinica',datosRegistros,{headers:this.httpheaders})
  }
  getHc():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'historiaclinica')
  }
  getHCtId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'historiaclinica/'+id)
  }

  // Servicios para componente DiagnosticosAtencion

  addDxAtencion(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'diagnosticosatencion',datosRegistros,{headers:this.httpheaders})
  }
  getDxAtencion():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticosatencion')
  }
  getDxAtencionId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticosatencion/'+id)
  }

  // servicios para componente Evoluciones Medicas

  addEvoMed(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'evoluciones',datosRegistros,{headers:this.httpheaders})
  }
  getEvoMed():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evoluciones')
  }
  getEvoMedId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evoluciones/'+id)
  }

  // servicios para componente creadesqx

  adddesqx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'procedimientosqx',datosRegistros,{headers:this.httpheaders})
  }

  adddesqxcompleta(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'descripcionesquirurgicas',datosRegistros,{headers:this.httpheaders})
  }

  getdesqx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'procedimientosqx')
  }
  getdesqxId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'procedimientosqx/'+id)
  }

  

  // Servicio para componenta de equipo quirurgico

  addequipoqx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'equipoqx',datosRegistros,{headers:this.httpheaders})
  }
  getequipoqx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'equipoqx')
  }
  getequipoqxId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'equipoqx/'+id)
  }


  // servicio para componente de procedimientos ProcedimientoDescripcionQX

   addprocedimientosdescripcion(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'procedimientosdescripcion',datosRegistros,{headers:this.httpheaders})
  }
  geteprocedimientosdescripcion():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'equprocedimientosdescripcionipoqx')
  }
  getprocedimientosdescripcionXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'procedimientosdescripcion/'+id)
  }

  // servicio para componente de evolucion de enfermeria

  addevoenf(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'evolucionenfermeria',datosRegistros,{headers:this.httpheaders})
  }
  getevoenf():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evolucionenfermeria')
  }
  getevoenfXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evolucionenfermeria/'+id)
  }

  
}
