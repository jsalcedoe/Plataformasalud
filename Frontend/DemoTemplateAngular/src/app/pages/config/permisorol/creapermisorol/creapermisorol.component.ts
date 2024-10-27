import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creapermisorol',
  templateUrl: './creapermisorol.component.html',
})
export class CreapermisorolComponent implements OnInit {
  formpermrol: FormGroup;
  permisos: any;
  rol: any;

  constructor(
    private service: ConfigService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.formpermrol = this.fb.group({
      items: this.fb.array([]), // FormArray para múltiples permisos y roles
    });
  }

  ngOnInit(): void {
    this.consultaPermiso();
    this.consultaRol();
    this.addPermRol(); // Agregar la primera fila por defecto
  }

  get items(): FormArray {
    return this.formpermrol.get('items') as FormArray;
  }

  addPermRol() {
    this.items.push(
      this.fb.group({
        rolpermrol_fk: ['', Validators.required],
        permrol_fk: ['', Validators.required],
      })
    );
  }

  removePermRol(index: number) {
    this.items.removeAt(index);
  }

  consultaPermiso() {
    this.service.getPermisos()
      .pipe(
        tap((res) => {
          console.log('Consulta permisos', res);
          this.permisos = res;
        }),
        catchError((err) => {
          console.error('Error:', err);
          alert('Error ' + err.message);
          throw err;
        })
      )
      .subscribe();
  }

  consultaRol() {
    this.service.getRoles()
      .pipe(
        tap((res) => {
          console.log('Consulta roles', res);
          this.rol = res;
        }),
        catchError((err) => {
          console.error('Error:', err);
          alert('Error ' + err.message);
          throw err;
        })
      )
      .subscribe();
  }

  creaPermRol() {
    const formValues = this.formpermrol.value.items;

    formValues.forEach((item: any) => {
      let struckPermRol = {
        rolpermrol_fk: {
          idrol: item.rolpermrol_fk
        },
        permrol_fk: {
          idperm: item.permrol_fk
        },
        estpermrol_fk: {
          idstatus: 1
        }
      };

      this.service.addPermisoRol(struckPermRol)
        .pipe(
          tap((res) => {
            console.log('PERMISO POR ROL', res);
            Swal.fire({
              icon: 'success',
              title: 'Operación exitosa',
              text: res.mensaje
            });
            return this.router.navigate(['permisorol']);
          }),
          catchError((err) => {
            console.error('Error:', err);
            Swal.fire({
              icon: 'error',
              title: 'Error en la operación',
              text: err.message
            });
            throw err;
          })
        )
        .subscribe();
    });
  }
}
