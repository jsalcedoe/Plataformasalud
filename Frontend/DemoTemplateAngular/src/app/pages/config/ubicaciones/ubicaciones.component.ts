import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-ubicaciones',
  templateUrl: './ubicaciones.component.html',
  
})
export class UbicacionesComponent implements OnInit {

  ubica : any;
  ready : Boolean = false;

  constructor(private services: ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.getUbica();
  }

  getUbica(){
    this.services.getUbicaciones().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('UBICACIONES', res);
        this.ubica = res;
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

  irCreaUbica(){
    this.router.navigate(['creaubica'])
  }

}
