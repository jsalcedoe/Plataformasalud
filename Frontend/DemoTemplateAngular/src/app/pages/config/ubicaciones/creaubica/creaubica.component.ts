import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaubica',
  templateUrl: './creaubica.component.html',
  
})
export class CreaubicaComponent implements OnInit {
  formUbicaciones:FormGroup

  constructor(private router:Router,
              private service:ConfigService,
              private fb:FormBuilder) {
                this.formUbicaciones = fb.group({
                  nomubicaciones:['',[Validators.required,Validators.maxLength(6),Validators.minLength(3)]],
                  detubica:['',[Validators.required,Validators.maxLength(50),Validators.minLength(10)]]
                })
               }

  ngOnInit(): void {
  }

  creaUbicacion(){
    let struckUbicaciones={
      nomubicaciones:this.formUbicaciones.value.nomubicaciones,
      detubica: this.formUbicaciones.value.detubica,
      estubica_fk: {
        "idstatus":1
     }

    }
    this.service.addUbicaciones(struckUbicaciones)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('UBICACIONES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['ubicaciones']);
        
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
