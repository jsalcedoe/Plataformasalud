import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ComparteinfService } from 'src/app/services/comparteinf.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creahistoriaclinica',
  templateUrl: './creahistoriaclinica.component.html',
  
})
export class CreahistoriaclinicaComponent implements OnInit {

  formHC:FormGroup
  eventSeleccionado:any
  imchcpac:number

  constructor(private service:OperacionService,
    private router:Router,
    private datoscompartidos:ComparteinfService,
    private fb:FormBuilder) { this.formHC = fb.group ({
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
      conseventpac:['',[Validators.required]],
      numdocpac: ['', [Validators.required]],
      primernompac: ['', [Validators.required]],
      segundonompac: ['', [Validators.nullValidator]],
      primerapepac: ['', [Validators.required]],
      segundoapepac: ['', [Validators.nullValidator]]
    })}

  ngOnInit(): void {
    // Suscríbete a los cambios en el servicio para obtener los detalles del paciente seleccionado
    /*this.datoscompartidos.getEventSeleccionado().subscribe((event) => {
      this.eventSeleccionado = event;
      console.log('evento seleccionado',this.eventSeleccionado)
      // Actualiza los valores del formulario con los detalles del paciente seleccionado
      if (this.eventSeleccionado){
        this.formHC.patchValue({
          conseventpac:this.eventSeleccionado.conseventpac,
          numdocpac: this.eventSeleccionado.pacevent_fk.numdocpac,
          primernompac: this.eventSeleccionado.pacevent_fk.primernompac,
          segundonompac: this.eventSeleccionado.pacevent_fk.segundonompac,
          primerapepac: this.eventSeleccionado.pacevent_fk.primerapepac,
          segundoapepac: this.eventSeleccionado.pacevent_fk.segundoapepac,
        });
      }
      
  })*/
  }
  calculaIMC(pesohcpac:number,estaturahcpac:number){
    if (pesohcpac > 0 && estaturahcpac > 0) {
      let imc = (pesohcpac/100) /(estaturahcpac * estaturahcpac);
      this.imchcpac=imc
  
      console.log('imc',this.imchcpac)
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
          });
          return this.router.navigate(['diagnosticosatencion']);
          
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

   /* redirigir(hc:any){
      this.router.navigate(['diagnosticosatencion'], { state: { hctInfo: hc } });
      console.log('hc',hc)
    }*/
  
    iradddx(){
      this.router.navigate(['diagnosticosatencion'])
    }

}
