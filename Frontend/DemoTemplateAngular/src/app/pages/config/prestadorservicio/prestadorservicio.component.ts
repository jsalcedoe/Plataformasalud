import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-prestadorservicio',
  templateUrl: './prestadorservicio.component.html'
})
export class PrestadorservicioComponent implements OnInit {

  prestador : any;
  ready : boolean = false;

  constructor(private services:ConfigService) { }

  ngOnInit(): void {
    this.getPrestador();
  }

  getPrestador(){
    this.services.getPrestadorservicio().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PRESTADOR DE SERVICIO', res);
        this.prestador = res;
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

}
