import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-ciudades',
  templateUrl: './ciudades.component.html'
})
export class CiudadesComponent implements OnInit {

  ciudad:any
  ready:boolean=false

  
  
  constructor(private service:ConfigService) { }

  ngOnInit(): void { 
    this.getCiudades();
  }

  getCiudades(){
    this.service.getCity().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CIUDADES', res);
        this.ciudad = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }



  

}
