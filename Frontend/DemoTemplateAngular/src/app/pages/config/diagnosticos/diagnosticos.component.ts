import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-diagnosticos',
  templateUrl: './diagnosticos.component.html'
})
export class DiagnosticosComponent implements OnInit {

  dx:any
  ready:boolean = false

  constructor(private service:ConfigService) { }

  ngOnInit(): void {
    this.getDiagnosticos();
  }

  getDiagnosticos(){
    this.service.getDx().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('DIAGNOSTICOS', res);
        this.dx = res;
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
