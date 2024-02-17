import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-origendestino',
  templateUrl: './origendestino.component.html'
})
export class OrigendestinoComponent implements OnInit {

  origdes: any
  ready:boolean = false

  constructor(private services:ConfigService) { }

  ngOnInit(): void {

    this.getOrigendestino();
  }

  getOrigendestino(){
    this.services.getOrigendestino().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ORIGENES Y DESTINOS', res);
        this.origdes = res;
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
