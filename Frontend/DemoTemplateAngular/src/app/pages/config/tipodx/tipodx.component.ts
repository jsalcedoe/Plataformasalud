import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipodx',
  templateUrl: './tipodx.component.html',

})
export class TipodxComponent implements OnInit {

  tipdx : any;
  ready : boolean = false;

  constructor(private services:ConfigService) { }

  ngOnInit(): void {
    this.getTipoDx();
  }

  getTipoDx(){
    this.services.getTipoDx().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE DIAGNOSTICO', res);
        this.tipdx = res;
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
