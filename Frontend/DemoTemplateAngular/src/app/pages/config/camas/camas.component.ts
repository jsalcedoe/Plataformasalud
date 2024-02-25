import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-camas',
  templateUrl: './camas.component.html',
 
})
export class CamasComponent implements OnInit {

  cam:any
  ready:boolean=false


  constructor(private service:ConfigService,private route:Router) { }

  ngOnInit(): void {

    this.getCamas()

  
  }

  getCamas(){
    this.service.getCamas().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CAMAS', res);
        this.cam = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('ERROR:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  irCreacamas(){
    this.route.navigate(['creacamas'])

  }

}
