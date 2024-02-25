import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-cargos',
  templateUrl: './cargos.component.html',
  
})
export class CargosComponent implements OnInit {

  cargos:any
  ready:boolean=false

  constructor(private service:ConfigService, private route:Router) { }

  ngOnInit(): void {
    this.getCargos();
  }

  getCargos(){
    this.service.getCargos().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CARGOS', res);
        this.cargos = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('ERROR:', err);
        alert('Error' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  irCrearCargos(){
    this.route.navigate(['creacargos'])
  }

}
