import { Component, OnInit } from '@angular/core';
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
  

  datosRegistros : any = {
    coddep : String,
    nomdep : String
 
  }

  

  constructor(private service:ConfigService) { }

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

  /*public postDepart(): void {
    this.service.insertarRegistro(this.datosRegistros).subscribe({
      next: respuesta => {
        console.log('Registro insertado correctamente:', respuesta);
        }
    });


    
  }*/

}
