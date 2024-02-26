import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-tipodocumentos',
  templateUrl: './tipodocumentos.component.html'
})
export class TipodocumentosComponent implements OnInit {

  tipodocument : any;
  ready : boolean = false;

  constructor(private services:ConfigService, private router: Router) { }

  ngOnInit(): void {
    this.getTipoDoc();
  }

  getTipoDoc(){
    this.services.getTipoDocumentos().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE DOCUMENTOS', res);
        this.tipodocument = res;
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

  irCreatipodoc(){
    this.router.navigate(['creatipodoc'])
  }

}
