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

  getHCXIdEvent(idevent:any):Observable<any[]>{
    return this.http.get<any[]>(`${this.endpoint}historiaclinica/search/${idevent}`)
  }

  editHC(idhcpac: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}historiaclinica/${idhcpac}`, datosRegistros, { headers: this.httpheaders });
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

  //Servicio para componente de DiagnosticoDescripcionQx

  addDxDescQx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'diagnosticodescqx',datosRegistros,{headers:this.httpheaders})
  }
  getDxDescQx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticodescqx')
  }
  getDxDescQxXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticodescqx/'+id)
  }

  //Servicio para componente de DiagnosticoEvolucion

  addDxEvoMed(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'diagnosticoevolucion',datosRegistros,{headers:this.httpheaders})
  }
  getDxEvoMed():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticoevolucion')
  }
  getDxEvoMedXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticoevolucion/'+id)
  }

  // Servicio para componente de DiagnosticoHistoriaClinica

  addDxHcPac(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'diagnosticohcpac',datosRegistros,{headers:this.httpheaders})
  }
  getDxHcPac():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticohcpac')
  }
  getDxHcPacXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticohcpac/'+id)
  }

  
  // servicios para componente creadesqx 

  adddesqx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'procedimientosqx',datosRegistros,{headers:this.httpheaders})
  }
  // servicios para componente descripcion quirurgica completa
    
  adddesqxcompleta(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'descripcionquirurgica',datosRegistros,{headers:this.httpheaders})
  }

  getdesqxCompletaXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'procedimientosqx/'+id)
  }

  editdesqxcompleta(idqx: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}descripcionquirurgica/${idqx}`, datosRegistros, { headers: this.httpheaders });
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

  // servicio para componente registro de signos vitales

  addsignosvitales(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'regsignosvitales',datosRegistros,{headers:this.httpheaders})
  }
  getsignosvitales():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'regsignosvitales')
  }
  getsignosvitalesXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'regsignosvitales/'+id)
  }

  // servicio para componente registro para aplicacion de medicamentos

  addregistromedicamentos(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'registromedicamentos',datosRegistros,{headers:this.httpheaders})
  }
  getregistromedicamentos():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'registromedicamentos')
  }
  getregistromedicamentosesXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'registromedicamentos/'+id)
  }

  // Servicio para componente de epicrisis

  getEpicrisis(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'epicrisis/'+id)
  }

  // Servicio para componente de ordenes de medicamentos o insumos

  addordenmedins(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'ordenmedicamentoinsumo',datosRegistros,{headers:this.httpheaders})
  }
  getordenmedins():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'ordenmedicamentoinsumo')
  }
  getordenmedinsXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'ordenmedicamentoinsumo/'+id)
  }
  getordenmedinsXIdEvent(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'ordenmedicamentoinsumo/listar/'+id)
  }
  editordmedins(idordmedins: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}ordenmedicamentoinsumo/${idordmedins}`, datosRegistros, { headers: this.httpheaders });
  }



  // Servicio para componente de ordenes de procedimientos y examenes


  addordprocexam(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'ordenesprocedimientos',datosRegistros,{headers:this.httpheaders})
  }
  getordprocexam():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'ordenesprocedimientos')
  }
  getordprocexamXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'ordenesprocedimientos/'+id)
  }
  getordenprocexamXIdEvent(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'ordenesprocedimientos/listar/'+id)
  }

  editordprocexam(idordprocexam: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}ordenesprocedimientos/${idordprocexam}`, datosRegistros, { headers: this.httpheaders });
  }

  // Servicio para componente de consentimiento de pacientes

  addconsinfpac(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'consentimientopaciente',datosRegistros,{headers:this.httpheaders})
  }
  getconsinfpac():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'consentimientopaciente')
  }
  getconsinfpacXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'consentimientopaciente/'+id)
  }
  getconsinfpacXIdEvent(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'consentimientopaciente/listar/'+id)
  }

  // Servicio para componente de historia clinica completa
  
   addhcpac(datosRegistros:any): Observable<any> {
   return this.http.post<any>(this.endpoint + 'historiaclinicacompleta',datosRegistros,{headers:this.httpheaders})
    
  }
   gethcpacXId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'historiaclinicacompleta/'+id)
  }
   edithcpac(id: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}historiaclinicacompleta/${id}`, datosRegistros, { headers: this.httpheaders });
  }

  // Servicios para componente de evolucion medica completa

  addEvoMedCompleta(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'evolucionclinicacompleta',datosRegistros,{headers:this.httpheaders})
  }
  getEvoMedCompletaId(id:any):Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'evolucionclinicacompleta/'+id)
  }
  editevomedCompleta(idevol: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}evolucionclinicacompleta/${idevol}`, datosRegistros, { headers: this.httpheaders });
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
  editevomed(idevol: Number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}evoluciones/${idevol}`, datosRegistros, { headers: this.httpheaders });
  }

  
}
