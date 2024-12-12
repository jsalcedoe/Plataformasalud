import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipomedins',
  templateUrl: './creatipomedins.component.html',
})
export class CreatipomedinsComponent implements OnInit {

  formtipomedins: FormGroup

  constructor(private service:ConfigService,
    private router:Router,
    private fb:FormBuilder) {
      this.formtipomedins = fb.group({
        typmedins:['',[Validators.required,Validators.maxLength(8)]],
        detypmedins:['',Validators.required]
      })
     }

  ngOnInit(): void {
  }

  creatipomedins(){
    let struckTipomedins= {
      typmedins:this.formtipomedins.value.typmedins,
      detypmedins:this.formtipomedins.value.detypmedins,
      esttymedins_fk: {
        "idstatus":1
      }
    }

    this.service.addtmedins(struckTipomedins)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE MEDICAMENTO', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipomedins']);
        
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
