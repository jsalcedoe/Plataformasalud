import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, switchMap, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';
import { ConfigService } from 'src/app/services/config.service';
@Component({
  selector: 'app-creahistoriaclinica',
  templateUrl: './creahistoriaclinica.component.html',
  
})
export class CreahistoriaclinicaComponent implements OnInit {

  formHC:FormGroup
  eventSeleccionado:any
  imchcpac:any
  idevent:string
  idhcpac:any
  loading: boolean = false; // Variable para controlar el estado de carga
  tipdx:any
  diagnosticos: any[] = []; // Lista completa de diagnósticos
  diagnosticosFiltrados: any[] = []; // Diagnósticos filtrados según la consulta incremental

   camposPersonales = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'text'},
    {label:'Consecutivo del paciente',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',type:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',type:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',type:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',type:'text'}
    
  ]

  
  constructor(private service:OperacionService,
              private serviciodx:ConfigService,
              private router:Router,
              private fb:FormBuilder,
              private paramsrouter: ActivatedRoute,
  ) { 
      //asignamos el valor del evento que traemos de la URL a la variable this.idevent
      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
      console.log('idevent del snapshop',this.idevent)
      this.formHC = fb.group ({
        // Datos de la historia clinica
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
      // Datos personales del paciente
      idevent:['',[Validators.required]],//
      conseventpac:['',[Validators.required]],//
      idpac:[''],//
      numdocpac:['',[Validators.required]],//
      primernompac:['',[Validators.required]],//
      segundonompac:['',[]],//
      primerapepac:['',[Validators.required]],//
      segundoapepac:['',[]],//
      diagnosticos: this.fb.array([]) // FormArray para los diagnósticos dinámicos
   
    })
    this.addDiagnostico();
  }

  ngOnInit(): void {

    if(this.idevent != null){
      
    console.log('id del evento que llega a la hc',this.idevent,'tipo de dato en creahistoria:', typeof this.idevent)
    this.getDataEvent();    
    }else{
      this.clearForm();
    }
    this.consultaTipoDx();
    this.consultaDx();
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

  consultaTipoDx(){
      this.serviciodx.getTipoDx()
      .pipe(
        tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('Consulta tipo de diagnostico', res);
          this.tipdx = res;
          
        }),
        catchError((err) => {
          // Maneja el error aquí
          console.error('Error:', err);
          alert('Error ' + err.message);
          throw err; // Re-throw para que el error se propague al suscriptor
        })
      ).subscribe();
  
    }
  consultaDx(){
    this.serviciodx.getDx()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta Diagnosticos', res);
        this.diagnosticos = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; 
      })
    ).subscribe();
  }

   get diagnosticosArray(): FormArray {
        return this.formHC.get('diagnosticos') as FormArray;
      }
    
      filtrarDiagnosticos(consulta: string, index:number) {
        if (consulta.trim() !== '') {
          this.diagnosticosFiltrados[index] = this.diagnosticos.filter(diagnostico =>
            diagnostico.nomdx.toLowerCase().includes(consulta.toLowerCase())
          );
        } else {
          this.diagnosticosFiltrados[index] = [];
        }
      }
    
    addDiagnostico() {
        const diagnosticos = this.formHC.get('diagnosticos') as FormArray;
        diagnosticos.push(this.fb.group({
          typdxhcpac_fk: ['', Validators.required],
          dxhcpac_fk: ['', Validators.required],
          nomdx:['']
        }));
      }
    
    removeDiagnostico(index: number) {
        const diagnosticos = this.formHC.get('diagnosticos') as FormArray;
        diagnosticos.removeAt(index);
        delete this.diagnosticosFiltrados[index]; 
      }
    
    seleccionarDiagnostico(diagnostico: any, index: number) {
        const diagnosticos = this.formHC.get('diagnosticos') as FormArray;
        diagnosticos.at(index).patchValue({
          dxhcpac_fk: diagnostico.clavedx,
          nomdx: diagnostico.nomdx 
        });
        this.diagnosticosFiltrados[index] = [];
      }

   creahcpac() {
     const structHcPac = {
      hcdto: {
      pesohcpac: this.formHC.value.pesohcpac,
      estaturahcpac: this.formHC.value.estaturahcpac,
      fchcpac: this.formHC.value.fchcpac,
      frhcpac: this.formHC.value.frhcpac,
      temphcpac: this.formHC.value.temphcpac,
      tahcpac: this.formHC.value.tahcpac,
      motconshcpac: this.formHC.value.motconshcpac,
      enfacthcpac: this.formHC.value.enfacthcpac,
      antpathcpac: this.formHC.value.antpathcpac,
      antqxhcpac: this.formHC.value.antqxhcpac,
      antalerhcpac: this.formHC.value.antalerhcpac,
      antfarmhcpac: this.formHC.value.antfarmhcpac,
      antfamyhcpac: this.formHC.value.antfamyhcpac,
      anttxhcpac: this.formHC.value.anttxhcpac,
      objhcpac: this.formHC.value.objhcpac,
      analisishcpac: this.formHC.value.analisishcpac,
      planmanejhcpac: this.formHC.value.planmanejhcpac,
      eventpac_fk: {
        idevent: this.eventSeleccionado.idevent
      },
      esthcpac_fk: {
        idstatus: 1
      }
    },
    dxhcdto: this.formHC.value.diagnosticos.map((dx: any) => ({
      typdxhcpac_fk: { idtypdx: dx.typdxhcpac_fk },
      dxhcpac_fk: { clavedx: dx.dxhcpac_fk },
      estdxhcpac: { idstatus: 1 },
    }))
  };

  console.log('Datos a enviar:', structHcPac); // Verifica la estructura antes de enviar

  this.service.addhcpac(structHcPac)
    .pipe(
      tap((res) => {
        console.log('Respuesta del servidor:', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje
        }).then(() => {
          this.router.navigateByUrl(`/eventos`);
        });
      }),
      catchError((err) => {
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error en la operación',
          text: err?.error?.mensaje || err.message
        });
        throw err;
      })
    ).subscribe();
}

      
    iradddx(){
     
    }
    

    clearForm() {
      this.formHC.reset();
    }

}
