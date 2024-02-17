import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipopaciente',
  templateUrl: './tipopaciente.component.html'
})
export class TipopacienteComponent implements OnInit {

  tipopac : any;
  ready : boolean = false;

  constructor(private services:ConfigService) { }

  ngOnInit(): void {
    this.getTipoPac();
  }

  getTipoPac(){
    this.services.getTipopaciente().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE PACIENTE', res);
        this.tipopac = res;
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
