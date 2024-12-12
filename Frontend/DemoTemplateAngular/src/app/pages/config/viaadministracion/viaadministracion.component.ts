import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-viaadministracion',
  templateUrl: './viaadministracion.component.html'
})
export class ViaadministracionComponent implements OnInit {

  viaadministracion : any;
  ready : boolean = false;

  constructor(private services: ConfigService,
              private router:Router) { }

  ngOnInit(): void {
    this.getviaadministracion()
  }
  getviaadministracion(){
    this.services.getviadm()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('via administracion', res);
        this.viaadministracion = res;
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
  ircreaviadm(){
    this.router.navigate(['creaviaadm'])
  }

}
