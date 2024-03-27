import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creadep',
  templateUrl: './creadep.component.html',
  
})
export class CreadepComponent implements OnInit {
  formDep:FormGroup

  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router) {
                this.formDep = fb.group({
                  coddep: ['',[Validators.required,Validators.maxLength(2)]],
                  nomdep: ['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  creaDep(){
    let struckDep = {
      coddep:this.formDep.value.coddep,
      nomdep:this.formDep.value.nomdep,
      estdep_fk:{
                    "idstatus": 1
                }

    }
    this.service.insertarRegistro(struckDep)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('DEPARTAMENTOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['departamentos']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error en la Operación',
          text: err.message // Mostrar el mensaje recibido desde el backend
        });
       
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();	
  }

}
