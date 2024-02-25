import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-entidades',
  templateUrl: './entidades.component.html'
})
export class EntidadesComponent implements OnInit {

  entidades: any
  ready: boolean= false

  constructor(private services:ConfigService, private route:Router) { }

  ngOnInit(): void {
    this.getEntidades();
  }

  getEntidades(){
    this.services.getEntidades().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ENTIDADES', res);
        this.entidades = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert ('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  irCreaentidad(){
    this.route.navigate(['creaentidad'])
    
  }

}
