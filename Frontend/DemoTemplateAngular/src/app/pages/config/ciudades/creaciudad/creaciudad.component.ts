import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaciudad',
  templateUrl: './creaciudad.component.html',
  
})
export class CreaciudadComponent implements OnInit {
  formCiudad:FormGroup;
  depart:any;

  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router
              ) {
                this.formCiudad = fb.group({
                  codciu:['',[Validators.required,Validators.maxLength(5)]],
                  nomciu:['',[Validators.required]],
                  depciu_fk:['',[Validators.required]]
              })
               }

  ngOnInit(): void {
    this.consultadep();
  }

  consultadep(){
    this.service.getDepart()
                .pipe(
                  tap((res) => {
                    // Maneja la respuesta exitosa aquí
                    console.log('Consulta Departamentos', res);
                    this.depart = res;
                    
                  }),
                  catchError((err) => {
                    // Maneja el error aquí
                    console.error('Error:', err);
                    alert('Error ' + err.message);
                    throw err; // Re-throw para que el error se propague al suscriptor
                  })
                ).subscribe();
  }
  
  creaCiudad(){
    let structciudad = {
      codciu: this.formCiudad.value.codciu,
      nomciu: this.formCiudad.value.nomciu,
      depciu_fk: {
                    "coddep":this.formCiudad.value.depciu_fk
                  },
      estciu_fk: {
                   "idstatus":1
                }
    }
    this.service.addCiudades(structciudad)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CIUDADES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['ciudades']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
    
  }
      
}

