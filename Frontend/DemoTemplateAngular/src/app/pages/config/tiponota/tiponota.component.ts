import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tiponota',
  templateUrl: './tiponota.component.html'
})
export class TiponotaComponent implements OnInit {

  tiponota : any;
  ready : boolean = false;

  constructor(private services: ConfigService) { }

  ngOnInit(): void {
    this.getTipoNota();
  }

  getTipoNota(){
    this.services.getTiponota().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE NOTAS', res);
        this.tiponota = res;
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
