import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  
})
export class UsuariosComponent implements OnInit {

  user : any;
  ready : boolean = false;

  constructor(private services: ConfigService) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(){
    this.services.getUsuarios().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('USUARIOS', res);
        this.user = res;
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
