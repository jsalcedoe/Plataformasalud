import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creapermisorol',
  templateUrl: './creapermisorol.component.html',

})
export class CreapermisorolComponent implements OnInit {
  formpermrol:FormGroup
  permisos:any
  rol:any

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) { 
                this.formpermrol = fb.group({
                  rolpermrol_fk:['',[Validators.required]],
                  permrol_fk:['',[Validators.required]]
                })
              }

  ngOnInit(): void {
    this.consultaPermiso(),
    this.consultaRol()
  }
  consultaPermiso(){
    this.service.getPermisos()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta permisos', res);
        this.permisos = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }
  consultaRol(){
    this.service.getRoles()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta roles', res);
        this.rol = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaPermRol(){
    let struckPermRol = {
     
      rolpermrol_fk:{
        "idrol":this.formpermrol.value.rolpermrol_fk
      },
      permrol_fk:{
        "idperm":this.formpermrol.value.permrol_fk   
      },
      estpermrol_fk:{
        "idstatus":1
      }
    }

    this.service.addPermisoRol(struckPermRol)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PERMISO POR ROL', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['permisorol']);
        
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
