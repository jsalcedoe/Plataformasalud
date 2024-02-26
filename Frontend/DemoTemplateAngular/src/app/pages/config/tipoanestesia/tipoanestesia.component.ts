import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipoanestesia',
  templateUrl: './tipoanestesia.component.html',

})
export class TipoanestesiaComponent implements OnInit {

  tipoanest : any;
  ready : boolean;

  constructor(private services:ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.getTipoAnest();
  }
  getTipoAnest(){
    this.services.getTipoAnestesia().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE ANESTESIA', res);
        this.tipoanest = res;
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

  irCreatipoanest(){
    this.router.navigate(['creatipoanest'])
  }

}
