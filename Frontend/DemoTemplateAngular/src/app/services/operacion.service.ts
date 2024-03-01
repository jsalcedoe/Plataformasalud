import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OperacionService {

  private endpoint: String ='http://localhost:8080/api/';
  //private httpheaders = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http:HttpClient) { }

  getPacientes():Observable<any[]>{
    return this.http.get<any[]>(this.endpoint+'pacientes')
  }

}
