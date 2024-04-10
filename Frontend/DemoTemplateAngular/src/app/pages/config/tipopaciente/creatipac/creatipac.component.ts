import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipac',
  templateUrl: './creatipac.component.html',
  
})
export class CreatipacComponent implements OnInit {
  formTipoPac: FormGroup

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formTipoPac=fb.group({
                  nomtipac:['',[Validators.required, Validators.maxLength(6)]],
                  dettipac:['',[Validators.required]]
                })
                
               }

  ngOnInit(): void {
  }

  /* 
  COTIZANTE 
  BENEFICIARIO
  NO AFILIADO
  */

  creaTipoPac(){
    let structTipoPac = {
      nomtipac:this.formTipoPac.value.nomtipac,
      dettipac:this.formTipoPac.value.dettipac,
      esttippac:{
        "idstatus":1
      }

    }

    this.service.addTipoPaciente(structTipoPac)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE PACIENTES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipopaciente']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: err.message // Mostrar el mensaje recibido desde el backend
        });
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

}
