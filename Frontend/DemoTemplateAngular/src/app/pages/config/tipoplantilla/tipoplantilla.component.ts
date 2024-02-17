import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipoplantilla',
  templateUrl: './tipoplantilla.component.html'
})
export class TipoplantillaComponent implements OnInit {

  tipotemp : any;
  ready : boolean = false;

  constructor(private services: ConfigService) { }

  ngOnInit(): void {
    this.getTipoTemp();
  }

  getTipoTemp(){
    this.services.getTipoPlantilla().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE PLANTILLA', res);
        this.tipotemp = res;
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
