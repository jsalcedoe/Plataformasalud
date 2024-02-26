import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipopx',
  templateUrl: './tipopx.component.html'
  
})
export class TipopxComponent implements OnInit {

  tipopx : any;
  ready : boolean = false;

  constructor(private services: ConfigService,private router:Router) { }

  ngOnInit(): void {
    this.getTipopx();
  }

  getTipopx(){
    this.services.getTipopx().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE PROCEDIMIENTO', res);
        this.tipopx = res;
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
  irCreatipopx(){
    this.router.navigate(['creatipopx'])
  }

}
