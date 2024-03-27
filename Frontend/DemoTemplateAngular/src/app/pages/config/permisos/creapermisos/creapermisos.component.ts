import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creapermisos',
  templateUrl: './creapermisos.component.html',
  
})
export class CreapermisosComponent implements OnInit {
  formPermisos:FormGroup


  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) { 
                this.formPermisos = fb.group({
                  namperm:['',[Validators.required]],
                  detperm:['',[Validators.required]]
                })
              }

  ngOnInit(): void {
  }

  creaPermisos(){
    let struckPermisos = {
      namperm:this.formPermisos.value.namperm,
      detperm:this.formPermisos.value.detperm,
      estperm_fk:{
        "idstatus":1
      }
    }

    this.service.addPermisos(struckPermisos)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PERMISOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['permisos']);
        
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
