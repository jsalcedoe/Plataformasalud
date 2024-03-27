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

  getPacientes():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'pacientes')
  }
  addPacientes(datosRegistros:any):Observable<any>{
    return this.http.post<any>(this.endpoint + 'pacientes',datosRegistros,{headers:this.httpheaders})
  }

}
