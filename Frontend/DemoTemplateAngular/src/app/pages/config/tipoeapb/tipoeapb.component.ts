import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipoeapb',
  templateUrl: './tipoeapb.component.html'
})
export class TipoeapbComponent implements OnInit {

  tipoeapb : any;
  ready : boolean = false;

  constructor(private services:ConfigService) { }

  ngOnInit(): void {
    this.getTipoeapb();
  }

  getTipoeapb(){
    this.services.getTipoeapb().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE ENTIDAD', res);
        this.tipoeapb = res;
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
