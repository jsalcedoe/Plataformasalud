import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-unidadmedidamedins',
  templateUrl: './unidadmedidamedins.component.html',
  
})
export class UnidadmedidamedinsComponent implements OnInit {
  unimedins : any;
  ready : boolean = false;

  constructor(private services: ConfigService,
              private router:Router) { }


  ngOnInit(): void {
    this.getunimedins()
  }

  getunimedins(){
    this.services.getunidadmedida().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('unidades de medida', res);
        this.unimedins = res;
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
  irCreaunimedins(){
    this.router.navigate(['creaunimedins'])
  }

}
