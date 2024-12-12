import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-presentacionmedins',
  templateUrl: './presentacionmedins.component.html'
})
export class PresentacionmedinsComponent implements OnInit {

  ready:boolean = false
  pmedins: any

  constructor(private services: ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.getpmedins()
  }

  getpmedins(){
    this.services.getpmedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PRESENTACION MEDICAMENTOS O INSUMOS', res);
        this.pmedins = res;
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

  ircreapmedins(){
    this.router.navigate(['creapresentacionmedins'])
  }

}
