import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipodoc',
  templateUrl: './creatipodoc.component.html',
  
})
export class CreatipodocComponent implements OnInit {
  formTipoDoc: FormGroup

  constructor(private service:ConfigService,
              private router:Router,
              fb:FormBuilder) { 
                this.formTipoDoc = fb.group({
                  tipdoc:['',[Validators.required, Validators.maxLength(5)]],
                  detatipdoc:['',[Validators.required]]
                })
              }

  ngOnInit(): void {
  }
  /* 
  1.'CEDULA DE CIUDADANIA'
  2.'NUMERO DE IDENTIFICACION TRIBUTARIA'
  3.'REGISTRO CIVIL'
  4.'TARJETA DE IDENTIDAD'
  5.'PERMISO DE PERMANENCIA TEMPORAL'
  6.'CEDULA DE EXTRANJERIA'
  7.'PASAPORTE'
  8.'PERMISO ESPECIAL DE PERMANENCIA'

  */
  creaTipoDoc(){
    let struckTipoDoc = {
      tipdoc:this.formTipoDoc.value.tipdoc,
      detatipdoc:this.formTipoDoc.value.detatipdoc,

    }
    this.service.addTipoDocumentos(struckTipoDoc)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE DOCUMENTOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipodocumentos']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error en la operación',
          text: err.message // Mostrar el mensaje recibido desde el backend
        });
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

}
