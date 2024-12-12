import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipomedins',
  templateUrl: './tipomedins.component.html',
})
export class TipomedinsComponent implements OnInit {

  ready:boolean = false
  tmedins: any

  constructor(private services: ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.gettipomedins()
  }

  gettipomedins(){
    this.services.gettmedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPOS DE MEDICAMENTOS O INSUMOS', res);
        this.tmedins = res;
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

  ircreatipomedins(){
    this.router.navigate(['creatipomedins'])
  }

}
