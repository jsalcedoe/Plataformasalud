import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creacamas',
  templateUrl: './creacamas.component.html',
  
})
export class CreacamasComponent implements OnInit {
  formcamas:FormGroup
  buscaubica:any
  mensajes: string

  constructor(private router:Router,
              private service:ConfigService,
              private fb:FormBuilder
              ) {
                this.formcamas = fb.group({
                  nomhab:['',[Validators.required,Validators.minLength(3)]],
                  dethab:['',[Validators.required, Validators.minLength(10)]],
                  ubicahab:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
    this.consultaubica();

  }
  consultaubica(){
    this.service.getUbicaciones()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta Ubicaciones', res);
        this.buscaubica = res
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaCamas(){
    let struckCamas = {
      nomhab:this.formcamas.value.nomhab,
      dethab:this.formcamas.value.dethab,
      ubicahab:{
        "idubica": this.formcamas.value.ubicahab
      },
      esthab_fk: {
        "idstatus": 1
    }

    }
    this.service.addCamas(struckCamas)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CAMAS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['camas']);
        
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
