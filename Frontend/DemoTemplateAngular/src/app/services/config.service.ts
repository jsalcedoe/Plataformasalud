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

  // CRUD CAMAS
  //----------------------------------------------------------------------------
  getCamas():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'camas')
  }

  getCamasByIdHab(idhab: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}camas/${idhab}`);
  }

  addCamas(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'camas',datosRegistros,{headers:this.httpheaders})
  }
  editCamas(idhab: number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}camas/${idhab}`, datosRegistros, { headers: this.httpheaders });
  }

//----------------------------------------------------------------------------------
 

  getCargos():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'cargos')
  }

  getCity():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'ciudades')
  }

  getDepart():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'departamentos')
  }
// CRUD DIAGNOSTICOS ------------------------------------------------------------------------------

  getDx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'diagnosticos')
  }

  getDxfindByNomdx(term: string): Observable<any[]> {
    const url = `${this.endpoint}diagnosticos/searchxname/${term}`;
    console.log('URL de la solicitud:', url); // Imprime la URL de la solicitud en la consola
    return this.http.get<any[]>(url);
  }

  addDx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'diagnosticos',datosRegistros,{headers:this.httpheaders})
  }

  getEntidades():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'entidades')
  }
// CRUD ESTADOS
//----------------------------------------------------------------------------------------------
  getEstados():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'estados')
  }

  addStatus(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'estados',datosRegistros,{headers:this.httpheaders})
  }

  getEstadosById(idstatus: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}estados/${idstatus}`);
  }

  editEstados(idstatus: number, datosRegistros: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}estados/${idstatus}`, datosRegistros, { headers: this.httpheaders });
  }
  //-----------------------------------------------------------------------------------------------

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
// CRUD TIPOS DE DIAGNOSTICOS ----------------------------------------------

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



  insertarRegistro(datosRegistros: any): Observable<any> {
    return this.http.post<any>(this.endpoint + 'departamentos', datosRegistros,{headers:this.httpheaders})

  }
  
  addCargos(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'cargos',datosRegistros,{headers:this.httpheaders})
  }
  addCiudades(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'ciudades',datosRegistros,{headers:this.httpheaders})
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

  // CRUD tipo de heridas

  addthx(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipoherida',datosRegistros,{headers:this.httpheaders})
  }

  getthx():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipoherida')
  }
  
  getthxByIdthx(idthx: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}tipoherida/${idthx}`);
  }

  //CRUD conductas

  addcondpac(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'conducta',datosRegistros,{headers:this.httpheaders})
  }

  getcondpac():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'conducta')
  }
  
  getcondpacById(idcondpac: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}conducta/${idcondpac}`);
  }

  //CRUD para Tipo de medicamento e insumos

  addtmedins(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'tipomedicamentosinsumos',datosRegistros,{headers:this.httpheaders})
  }

  gettmedins():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'tipomedicamentosinsumos')
  }
  
  gettmedinsById(idtmedins: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}tipomedicamentosinsumos/${idtmedins}`);
  }

  // CRUD para Presentacion de Medicamentos

  addpmedins(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'pmedins',datosRegistros,{headers:this.httpheaders})
  }

  getpmedins():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'pmedins')
  }
  
  getpmedinsById(idpmedins: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}pmedins/${idpmedins}`);
  }

  getmedinsBypmedins(detpmedins: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}pmedins/search/${detpmedins}`);
  }

  // CRUD para Unidad de Medida

  addunidadmedida(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'unidadesmedida',datosRegistros,{headers:this.httpheaders})
  }

  getunidadmedida():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'unidadesmedida')
  }
  
  getunidadmedidaById(idunimed: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}unidadesmedida/${idunimed}`);
  }

  getmedinsByUmedins(detunimedin: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}unidadesmedida/search/${detunimedin}`);
  }

  // CRUD para Fabricante de medicamentos e insumos

  addfabricantemedins(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'fabricantes',datosRegistros,{headers:this.httpheaders})
  }

  getfabricantemedins():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'fabricantes')
  }
  
  getfabricantemedinsById(idfabmedins: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}fabricantes/${idfabmedins}`);
  }

  // CRUD para medicamentos

  addmedins(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'medicamentosinsumos',datosRegistros,{headers:this.httpheaders})
  }

  getmedins():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'medicamentosinsumos')
  }
  
  getmedinsById(idmedins: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}medicamentosinsumos/${idmedins}`);
  }

  getmedinsByMedins(medins: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}medicamentosinsumos/search/${medins}`);
  }

  // CRUD para vias de administracion

  addviadm(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'viasadministracion',datosRegistros,{headers:this.httpheaders})
  }

  getviadm():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'viasadministracion')
  }
  
  getviadmById(idviadm: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}viasadministracion/${idviadm}`);
  }

  // CRUD para configuracion de consentimientos informados

  addconinf(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'consentimientos',datosRegistros,{headers:this.httpheaders})
  }

  getconinf():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint + 'consentimientos')
  }
  
  getconinfById(idconsinf: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.endpoint}consentimientos/${idconsinf}`);
  }


   
   
    
    




 
  

 
}
