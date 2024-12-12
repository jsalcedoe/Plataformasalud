import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creafabricante',
  templateUrl: './creafabricante.component.html',
  
})
export class CreafabricanteComponent implements OnInit {
  formfabricantes: FormGroup
  city : any

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) { 
                this.formfabricantes = fb.group({
                  nomfabmedins:['',[Validators.required]],
                  dirfabmedins:['',[Validators.required]],
                  ciufabmedins_fk:['',[Validators.required]],
                })
              }

  ngOnInit(): void {
    this.getciudad()
  }

  getciudad(){
    this.service.getCity().pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('ciudades', res);
      this.city = res

    }),
    catchError((err) => {
      // Maneja el error aquí
      console.error('Error:', err);
      alert('Error ' + err.message)
      throw err; // Re-throw para que el error se propague al suscriptor
    })
  ).subscribe();
  }

  creafabricantes(){
    let struckFabricantes = {
      nomfabmedins:this.formfabricantes.value.nomfabmedins,
      dirfabmedins:this.formfabricantes.value.dirfabmedins,
      ciufabmedins_fk:{
        "idciu":this.formfabricantes.value.ciufabmedins_fk
      },
      estfabmedins_fk:{
        "idstatus": 1
      }
    }
    this.service.addfabricantemedins(struckFabricantes)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('fabricantes', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['fabricantemedins']);
        
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
