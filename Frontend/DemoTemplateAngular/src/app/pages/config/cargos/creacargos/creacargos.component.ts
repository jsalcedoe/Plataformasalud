import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creacargos',
  templateUrl: './creacargos.component.html'
})
export class CreacargosComponent implements OnInit {
  formCargos:FormGroup
  cargos:any
  
  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router) {
                this.formCargos = fb.group({
                  nomcarg:['',[Validators.required, Validators.maxLength(6)]],
                  detcarguser:['',[Validators.required, Validators.maxLength(45)]],
                  roluser:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
    this.consultaRol();
  }

  consultaRol(){
    this.service.getRoles()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta cargos', res);
        this.cargos = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();

  }

  creaCargos(){
    let structCargos = {
      nomcarg:this.formCargos.value.nomcarg,
      detcarguser:this.formCargos.value.detcarguser,
      roluser:{
        "idrol":this.formCargos.value.roluser
      },
      estado_carguser:{
        "idstatus":1
      }

    }
    this.service.addCargos(structCargos)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CARGOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['cargos']);
        
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
