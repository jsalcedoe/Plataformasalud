import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipopx',
  templateUrl: './creatipopx.component.html',
  
})
export class CreatipopxComponent implements OnInit {
  formtipopx: FormGroup


  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router) {
                this.formtipopx = fb.group({
                  nomtproc:['',[Validators.required,Validators.maxLength(4)]],
                  detproc:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  crearTipoPx(){
    let struckTipoPx = {
      nomtproc:this.formtipopx.value.nomtproc,
      detproc:this.formtipopx.value.detproc,
      estypx_fk:{
        "idstatus":1
      }
    }

    this.service.addTipoPx(struckTipoPx)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE PROCEDIMIENTOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipopx']);
        
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
