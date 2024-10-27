import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipohx',
  templateUrl: './creatipohx.component.html',

})
export class CreatipohxComponent implements OnInit {

  formtyphx: FormGroup

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formtyphx = fb.group({
                  nomthx:['',[Validators.required,Validators.maxLength(6)]],
                  dethx:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  creatipoHx(){
    let struckTipohx= {
      nomthx:this.formtyphx.value.nomthx,
      dethx:this.formtyphx.value.dethx,
      estadothx_fk: {
        "idstatus":1
      }
    }

    this.service.addthx(struckTipohx)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE HERIDA', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipohx']);
        
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
