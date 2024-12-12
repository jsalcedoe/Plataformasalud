import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-fabricantemedins',
  templateUrl: './fabricantemedins.component.html',
  
})
export class FabricantemedinsComponent implements OnInit {
  ready:boolean = false
  fabmedins: any

  constructor(private services: ConfigService, 
              private router:Router) { }

  ngOnInit(): void {
    this.getfabricantemedins()
  }

  getfabricantemedins(){
    this.services.getfabricantemedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('fabricantes', res);
        this.fabmedins = res;
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

  ircreafabricantes(){
    this.router.navigate(['creafabricante'])
  }
  

}
