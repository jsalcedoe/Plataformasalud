import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, switchMap, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaestados',
  templateUrl: './creaestados.component.html',
  
})
export class CreaestadosComponent implements OnInit {

  formstatus:FormGroup;
  st:any;
  ready:boolean=false;
  estadoID:any

  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private activerouted: ActivatedRoute,
              private router:Router) {
                this.formstatus = fb.group({
                  nomstatus:['',[Validators.required,Validators.maxLength(4),Validators.minLength(3)]],
                  detstatus:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
    this.activerouted.params.subscribe(params => {
      console.log('params', params)
      this.estadoID = params['idstatus'];
      console.log('estadoID', this.estadoID)
      if (this.estadoID) {
        // Llamar a un método para cargar la información del estado
        this.getEstados(this.estadoID);
      }
    });
  }
  /* EL ORDEN DE INGRESO DE LOS ESTADOS ES:
  1. CREADO
  2. ACTIVO
  3. EDITADO
  4. INACTIVO
   */
  insertstatus(){
    
    let structstatus={
        nomstatus:this.formstatus.value.nomstatus,  
        detstatus:this.formstatus.value.detstatus
      }

      this.service.addStatus(structstatus)
      .pipe(
        tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('ESTADOS', res);
          Swal.fire({
            icon: 'success',
            title: 'Operación exitosa',
            text: res.mensaje // Mostrar el mensaje recibido desde el backend
          });
          return this.router.navigate(['estados']);
          
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
  

    getEstados(idstatus:number){
      this.service.getEstadosById(idstatus).subscribe(res =>{
        console.log('muestra estados',res)
        this.estadoID = res
        this.formstatus.patchValue({
          nomstatus:this.estadoID.nomstatus,
          detstatus:this.estadoID.detstatus,
        })
       
      })
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
    }

    ActualizaEstados(){

      let structstatusActualizada={
        nomstatus:this.estadoID.nomstatus,  
        detstatus:this.estadoID.detstatus
      }
      this.service.editEstados(this.estadoID.idstatus,structstatusActualizada)
      .pipe(
        tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ESTADOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Estado Actualizada Exitosamente',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        console.log('El estado actualizado es:',res)
        return this.router.navigate(['estados']);
        
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
    
