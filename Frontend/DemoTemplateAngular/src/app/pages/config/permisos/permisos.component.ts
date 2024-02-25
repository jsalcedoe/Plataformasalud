import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-permisos',
  templateUrl: './permisos.component.html',
  
})
export class PermisosComponent implements OnInit {

  permiso : any
  ready : boolean = false

  constructor(private services: ConfigService, private route:Router) { }

  ngOnInit(): void {
    this.getPermisos();
  }

  getPermisos(){
    this.services.getPermisos().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PERMISOS', res);
        this.permiso = res;
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

  irCreapermisos(){

    this.route.navigate(['creapermisos'])

  }

}
