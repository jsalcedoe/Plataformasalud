import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creamedins',
  templateUrl: './creamedins.component.html',
})
export class CreamedinsComponent implements OnInit {
  formmedins:FormGroup
  medins:any
  tmedins:any
  pmedins:any
  unimed:any
  fmedins:any


  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) { 
                this.formmedins = fb.group({
                  cummedins:['',[Validators.required]],
                  medins:['',[Validators.required]],
                  consmedins:[''],
                  atcmedins:[''],
                  reginvmedinv:['',[Validators.required]],
                  tmedins_fk:['',[Validators.required]],
                  pmedins_fk:['',[Validators.required]],
                  umedins_fk:['',[Validators.required]],
                  fmedins_fk:['',[Validators.required]],
                })
              }

  ngOnInit(): void {
    this.gettmedins(),
    this.getpmedins(),
    this.getunimedins(),
    this.getfmedins()    
  }
  gettmedins(){
    this.service.gettmedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('tmedins', res);  
        this.tmedins = res
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }
  getpmedins(){
    this.service.getpmedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('pmedins', res); 
        this.pmedins = res 
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }
  getunimedins(){
    this.service.getunidadmedida().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('unimedins', res);  
        this.unimed = res
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }
  getfmedins(){
    this.service.getfabricantemedins().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('fmedins', res);  
        this.fmedins = res
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creamedins(){
    let struckMedins = {
      cummedins:this.formmedins.value.cummedins,
      medins:this.formmedins.value.medins,
      consmedins:this.formmedins.value.consmedins,
      atcmedins:this.formmedins.value.atcmedins,
      reginvmedinv:this.formmedins.value.reginvmedinv,
      tmedins_fk:{
        "idtmedins":this.formmedins.value.tmedins_fk
      },
      pmedins_fk:{
        "idpmedins":this.formmedins.value.pmedins_fk
      },
      umedins_fk:{
        "idunimed":this.formmedins.value.umedins_fk
      },
      fmedins_fk:{
        "idfabmedins":this.formmedins.value.fmedins_fk
      },
      estmedins_fk:{
        "idstatus":1
      }
    }
    this.service.addmedins(struckMedins)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('medins', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['medins']);
        
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
