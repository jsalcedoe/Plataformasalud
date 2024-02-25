import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',

})
export class RolesComponent implements OnInit {

  roles : any;
  ready : boolean = false;

  constructor(private services: ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.getRoles();
  }

  getRoles(){
    this.services.getRoles().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ROLES', res);
        this.roles = res;
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

  irCrearol(){
    this.router.navigate(['crearol'])
  }

}
