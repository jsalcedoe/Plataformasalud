import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipoanest',
  templateUrl: './creatipoanest.component.html',
  
})
export class CreatipoanestComponent implements OnInit {
  formTipoAnestesia: FormGroup

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formTipoAnestesia = fb.group({
                  nomtipanest:['',[Validators.required,Validators.maxLength(6)]],
                  desctipanest:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }
  creaTipoAnestesia(){
    let struckTipoAnestesia = {
      nomtipanest:this.formTipoAnestesia.value.nomtipanest,
      desctipanest:this.formTipoAnestesia.value.desctipanest,
      esttypanest:{
        "idstatus":1
      }
    }

    this.service.addTipoAnestesia(struckTipoAnestesia)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE ANESTESIA', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipoanestesia']);
        
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
