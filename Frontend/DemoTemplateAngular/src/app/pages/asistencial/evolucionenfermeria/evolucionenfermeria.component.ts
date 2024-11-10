import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-evolucionenfermeria',
  templateUrl: './evolucionenfermeria.component.html',

})
export class EvolucionenfermeriaComponent implements OnInit {

  formevoenf: FormGroup
  idevent:string
  eventSeleccionado: any

  constructor(private router:Router,
    private service:OperacionService,
    private services:ConfigService,
    private fb:FormBuilder,
    private paramsrouter: ActivatedRoute,) { 

      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')

      this.formevoenf = fb.group({
      idevent:['',[Validators.required]],
      conseventpac:['',[Validators.required]],
      idpac:['',[Validators.required]],
      numdocpac:['',[Validators.required]],
      primernompac:['',[Validators.required]],
      segundonompac:['',[Validators.required]],
      primerapepac:['',[Validators.required]],
      segundoapepac:['',[Validators.required]],
      detevoenf:['',[Validators.required]],
      
      })
    }

  ngOnInit(): void {
    this.getDataEvent()
  }

  getDataEvent() {
    console.log(this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario',res);
     this.eventSeleccionado = res;
     this.formevoenf.patchValue({
       idevent:this.eventSeleccionado.idevent, 
       conseventpac:this.eventSeleccionado.conseventpac,
       idpac: this.eventSeleccionado.pacevent_fk.idpac,
       numdocpac:this.eventSeleccionado.pacevent_fk.numdocpac,
       primernompac:this.eventSeleccionado.pacevent_fk.primernompac,
       segundonompac:this.eventSeleccionado.pacevent_fk.segundonompac,
       primerapepac:this.eventSeleccionado.pacevent_fk.primerapepac,
       segundoapepac:this.eventSeleccionado.pacevent_fk.segundoapepac,
     })
     
    })
    
   }

   creaevoenf(){
    let struckEvoenf={
      detevoenf:this.formevoenf.value.detevoenf,
      
      eventevoenf_fk:{
        "idevent":this.eventSeleccionado.idevent
      },
      estevoenf_fk:{
        "idstatus": 1
      }
    }
    this.service.addevoenf(struckEvoenf)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Evolucion de Enfermeria', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        })/*.then (() =>{
          this.router.navigateByUrl(`/creadiagnosticosatencion/${this.idevent}`)
         
        });*/
        
        
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
