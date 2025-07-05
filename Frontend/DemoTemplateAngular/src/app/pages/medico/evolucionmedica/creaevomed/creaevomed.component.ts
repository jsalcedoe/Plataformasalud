import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';
import { CreadiagnosticosatencionComponent } from '../../diagnosticosatencion/creadiagnosticosatencion/creadiagnosticosatencion.component';

@Component({
  selector: 'app-creaevomed',
  templateUrl: './creaevomed.component.html',
  
})
export class CreaevomedComponent implements OnInit {

  //@ViewChild('diagnosticosComp') diagnosticosComponent: CreadiagnosticosatencionComponent;

  formEvoMedi:FormGroup
  eventSeleccionado:any
  idevent:string
  idhcpac:any
  tiponota:any

  constructor(private router:Router,
    private service:OperacionService,
    private services:ConfigService,
    private fb:FormBuilder,
    private paramsrouter: ActivatedRoute,
) {
      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')

      this.formEvoMedi = fb.group({
      idevent:['',[Validators.required]],
      conseventpac:['',[Validators.required]],
      idpac:['',[Validators.required]],
      numdocpac:['',[Validators.required]],
      primernompac:['',[Validators.required]],
      segundonompac:['',[Validators.required]],
      primerapepac:['',[Validators.required]],
      segundoapepac:['',[Validators.required]],
      detevol:['',[Validators.required]],
      notaevol_fk:['',[Validators.required]]
      })
}

  ngOnInit(): void {
    this.getDataEvent()
    this.getTipoNota()
  }

  getDataEvent() {
    console.log(this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario',res);
     this.eventSeleccionado = res;
     this.formEvoMedi.patchValue({
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

   getTipoNota(){
    this.services.getTiponota()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de notas', res);
        this.tiponota = res
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
   }

   creaevomed(){
    let struckEvoluciones={
      detevol:this.formEvoMedi.value.detevol,
      notaevol_fk:{
        "idtypnot":this.formEvoMedi.value.notaevol_fk
      },
      eventevo_fk:{
        "idevent":this.eventSeleccionado.idevent
      },
      estnotaevol_fk:{
        "idstatus": 1
      }
    }
    this.service.addEvoMed(struckEvoluciones)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Evolucion Medica', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        }).then (() =>{
          this.router.navigateByUrl(`/eventos`)
         
        });
        
        
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

  checkFormValidation() {
    for (const key in this.formEvoMedi.controls) {
      if (this.formEvoMedi.controls.hasOwnProperty(key)) {
        const control = this.formEvoMedi.get(key);
        if (control.invalid) {
          console.log(`Field ${key} is invalid. Value: ${control.value}, Errors: ${JSON.stringify(control.errors)}`);
        }
      }
    }
  }

  irCreaDxAtencion(idevent:any){
    this.router.navigateByUrl(`/creadiagnosticosatencion/${idevent}`)
  }

  guardarTodo(){
    this.creaevomed()
    //this.diagnosticosComponent.creaDxAtencion()
  }

}
