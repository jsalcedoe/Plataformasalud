import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipohx',
  templateUrl: './tipohx.component.html',
})
export class TipohxComponent implements OnInit {
  ready:boolean = false
  thx: any

  constructor(private services: ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.getTipoHeridas()
  }

  getTipoHeridas(){
    this.services.getthx().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE HERIDAS', res);
        this.thx = res;
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

  creathx(){
    this.router.navigate(['creatipohx'])
  }

}
