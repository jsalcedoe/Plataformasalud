import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaunimedins',
  templateUrl: './creaunimedins.component.html',
})
export class CreaunimedinsComponent implements OnInit {
  formunimedins: FormGroup

  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router) {
                this.formunimedins = fb.group({
                  unimedi:['',[Validators.required,Validators.maxLength(8)]],
                  detunimedi:['',[Validators.required]],
                })
               }

  ngOnInit(): void {
  }

  creaunimedins(){
    let struckunimedins = {
      unimedi:this.formunimedins.value.unimedi,
      detunimedi:this.formunimedins.value.detunimedi,
      estunimed_fk:{
        "idstatus":1
      }
    }
    this.service.addunidadmedida(struckunimedins)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('UNIDADES DE MEDIDA', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['unidadmedidamedins']);
        
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
