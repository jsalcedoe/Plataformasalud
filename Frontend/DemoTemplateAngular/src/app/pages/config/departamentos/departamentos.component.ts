import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-departamentos',
  templateUrl: './departamentos.component.html'
})
export class DepartamentosComponent implements OnInit {

  depart:any
  ready:boolean=false

  coddep: any;
  nomdep: any;  
  

  constructor(private service:ConfigService,private router:Router) { }

  ngOnInit(): void {
    this.getDepart();
   }

 public getDepart(){
  this.service.getDepart().pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('DEPARTAMENTOS', res);
      this.depart = res;
      this.ready = true;
    }),
    catchError((err) => {
      // Maneja el error aquí
      console.error('Error:', err);
      alert('Error ' + err.message)
      throw err; // Re-throw para que el error se propague al suscriptor
    })
  ).subscribe();
  }

  irCrearDep(){
    this.router.navigate(['creadep']);
  }
  
}