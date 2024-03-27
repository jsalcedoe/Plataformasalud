import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaplantillas',
  templateUrl: './creaplantillas.component.html',

})
export class CreaplantillasComponent implements OnInit {
  formTemp:FormGroup
  typtemp:any


  constructor(private service: ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                  this.formTemp = fb.group({
                    nametemp:['',[Validators.required, Validators.maxLength(6)]],
                    dettemp:['',[Validators.required]],
                    typtemp_fk:['',[Validators.required]]                    
                  })
               }

  ngOnInit(): void {
    this.consultaTipTemp();
  }
  consultaTipTemp(){
    this.service.getTipoPlantilla()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de plantillas', res);
        this.typtemp = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaTemp(){
    let struckTemp = {
      nametemp:this.formTemp.value.nametemp,
      dettemp:this.formTemp.value.dettemp,
      typtemp_fk:{
        "idtytemp":this.formTemp.value.typtemp_fk
      },
      esttemp_fk:{
        "idstatus": 1
      }
    }

    this.service.addPlantillas(struckTemp)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CIUDADES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['plantillas']);
        
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
