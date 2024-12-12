import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-medins',
  templateUrl: './medins.component.html',
})
export class MedinsComponent implements OnInit {

  ready:boolean = false
  medins: any

  constructor(private services: ConfigService, 
              private route: Router) { }

  ngOnInit(): void {
    this.getmedins()
  }

  getmedins(){
    this.services.getmedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('medins', res);
        this.medins = res;
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

  ircreamedins(){
   // this.router.navigate(['creafabricante'])
   this.route.navigate(['creamedins'])
  }
  

}
