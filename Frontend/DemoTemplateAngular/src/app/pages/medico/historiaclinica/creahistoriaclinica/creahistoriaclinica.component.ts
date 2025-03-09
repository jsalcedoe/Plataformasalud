import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';
import { CreadiagnosticosatencionComponent } from '../../diagnosticosatencion/creadiagnosticosatencion/creadiagnosticosatencion.component';

@Component({
  selector: 'app-creahistoriaclinica',
  templateUrl: './creahistoriaclinica.component.html',
  
})
export class CreahistoriaclinicaComponent implements OnInit {

  @ViewChild('diagnosticosComp') diagnosticosComponent: CreadiagnosticosatencionComponent;
  

  formHC:FormGroup
  eventSeleccionado:any
  imchcpac:any
  idevent:string
  idhcpac:any
  
  constructor(private service:OperacionService,
              private router:Router,
              private fb:FormBuilder,
              private paramsrouter: ActivatedRoute,
  ) { 
      //asignamos el valor del evento que traemos de la URL a la variable this.idevent
      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
      console.log('idevent del snapshop',this.idevent)
      this.formHC = fb.group ({
      pesohcpac:['',[Validators.required]],
      estaturahcpac:['',[Validators.required]],
      fchcpac:['',[Validators.required]],
      frhcpac:['',[Validators.required]],
      temphcpac:['',[Validators.required]],
      tahcpac:['',[Validators.required]],
      motconshcpac:['',[Validators.required]],
      enfacthcpac:['',[Validators.required]],
      antpathcpac:['',[Validators.required]],
      antqxhcpac:['',[Validators.required]],
      antalerhcpac:['',[Validators.required]],
      antfarmhcpac:['',[Validators.required]],
      antfamyhcpac:['',[Validators.required]],
      anttxhcpac:['',[Validators.required]],
      objhcpac:['',[Validators.required]],
      analisishcpac:['',[Validators.required]],
      planmanejhcpac:['',[Validators.required]],
      idevent:['',[Validators.required]],
      conseventpac:['',[Validators.required]],
      idpac:[''],
      numdocpac:['',[Validators.required]],
      primernompac:['',[Validators.required]],
      segundonompac:['',[]],
      primerapepac:['',[Validators.required]],
      segundoapepac:['',[]],
   
    })}

  ngOnInit(): void {

    if(this.idevent != null){
      
    console.log('id del evento que llega a la hc',this.idevent,'tipo de dato en creahistoria:', typeof this.idevent)
    this.getDataEvent();    
    }else{
      this.clearForm();
    }
  }



 getDataEvent() {
    console.log(this.idevent,'tipo de dato en getdataevent en creahistoriaclinica',typeof this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de historia clinica',res);
     this.eventSeleccionado = res;
     this.formHC.patchValue({
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

  calculaIMC(){
    let pesohcpac = this.formHC.value.pesohcpac;
    console.log('peso',pesohcpac)
    let estaturahcpac = this.formHC.value.estaturahcpac;
    console.log('estatura')
    if (pesohcpac > 0 && estaturahcpac > 0) {
      let imc = (pesohcpac/((estaturahcpac/100)*(estaturahcpac/100))).toFixed(2);
      //this.imchcpac = parseInt(imc); // Convertimos nuevamente a número flotante
      this.imchcpac=imc
  
      console.log('imc',this.imchcpac)
    }else {
      this.imchcpac = null;
    }
    
  
  }
  
    creahcpac(){
      let structHcPac={
        pesohcpac:this.formHC.value.pesohcpac,
        estaturahcpac:this.formHC.value.estaturahcpac,
        fchcpac:this.formHC.value.fchcpac,
        frhcpac:this.formHC.value.frhcpac,
        temphcpac:this.formHC.value.temphcpac,
        tahcpac:this.formHC.value.tahcpac,
        motconshcpac:this.formHC.value.motconshcpac,
        enfacthcpac:this.formHC.value.enfacthcpac,
        antpathcpac:this.formHC.value.antpathcpac,
        antqxhcpac:this.formHC.value.antqxhcpac,
        antalerhcpac:this.formHC.value.antalerhcpac,
        antfarmhcpac:this.formHC.value.antfarmhcpac,
        antfamyhcpac:this.formHC.value.antfamyhcpac,
        anttxhcpac:this.formHC.value.anttxhcpac,
        objhcpac:this.formHC.value.objhcpac,
        analisishcpac:this.formHC.value.analisishcpac,
        planmanejhcpac:this.formHC.value.planmanejhcpac,
        eventpac_fk:{
          "idevent":this.eventSeleccionado.idevent
          
        },
        esthcpac_fk:{
          "idstatus":1
        }
      }
      this.service.addHcpac(structHcPac)
      .pipe(
        tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('Historia Clinica', res);
          Swal.fire({
            icon: 'success',
            title: 'Operación exitosa',
            text: res.mensaje // Mostrar el mensaje recibido desde el backend
          }).then (() =>{
            this.router.navigateByUrl(`/eventos`)
            console.log('La historia clinica almacenada es:', res)
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
    iradddx(){
     
    }

    guardarTodo(){
      this.creahcpac(),
      this.diagnosticosComponent.creaDxAtencion()
    }

    clearForm() {
      this.formHC.reset();
    }

}
