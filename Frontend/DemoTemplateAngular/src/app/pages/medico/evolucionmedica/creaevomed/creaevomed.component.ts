import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';
import { CreadiagnosticosatencionComponent } from '../../diagnosticosatencion/creadiagnosticosatencion/creadiagnosticosatencion.component';

@Component({
  selector: 'app-creaevomed',
  templateUrl: './creaevomed.component.html',
  
})
export class CreaevomedComponent implements OnInit {

  formEvoMedi:FormGroup
  eventSeleccionado:any
  idevent:string  
  tipnot: any [] = []
  detypdx:any [] = []
  detdx: any [] = []
  diagnosticosFormArray: FormArray;
  //camposHabilitados:boolean[] = []

  camposPaciente = [
    {label:'Id Evolucion',nombre:'idevol',type:'text'},
    {label:'Documento Paciente',nombre:'numdocpac',type:'text'},
    {label:'Consecutivo del paciente',nombre:'conseventpac',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},        
    {label:'Primer Nombre',nombre:'primernompac',type:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',type:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',type:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',type:'text'},
    
  ]
  camposEvolucionMedica = [
        {label:'Tipo de Nota para Evolucion',nombre:'dettypnot',type:'text'},
        {label:'Evolucion',nombre:'detevol',type:'textarea'},
  ]
 

  constructor(private router:Router,
    private service:OperacionService,
    private services:ConfigService,
    private fb:FormBuilder,
    private paramsrouter: ActivatedRoute,
) {
      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')

      this.formEvoMedi = fb.group({
        idevol:[''],
        numdocpac:[''],
        conseventpac:[''],
        idpac:[''],
        idevent:[''],                      
        primernompac:[''],
        segundonompac:[''],
        primerapepac:[''],
        segundoapepac:[''],
        dettypnot:[''],
        notaevol_fk:[''],
        detevol:[''],
        diagnosticos: this.fb.array([])
      })
      this.diagnosticosFormArray = this.formEvoMedi.get('diagnosticos') as FormArray;
      this.agregarDiagnostico(); // Inicializar con un diagnóstico vacío
}

  ngOnInit(): void {
    this.getDataEvent()
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


  gettiponota(termino:string){
     if(termino.length > 2){
       this.services.getByXTipoNota(termino)
       .pipe(
               tap((res: any[]) => {
               this.tipnot = res.filter(tn =>
               tn.dettypnot.toLowerCase().includes(termino.toLowerCase())
             )                 
               }),
                          
               catchError((err) => {
               // Maneja el error aquí
               console.error('Error al buscar el tipo de nota:', err);
               alert('Error ' + err.message);
               return of([]);
              })
            ).subscribe();
           }else{
             this.tipnot = []
           }
   }
 
  selectTipoNota(tn:any):void{
     
     console.log('tipo de nota seleccionado:', tn);
     this.formEvoMedi.get('notaevol_fk')?.setValue(tn.idtypnot);
     this.formEvoMedi.get('dettypnot')?.setValue(tn.dettypnot);
     this.tipnot = []; // Limpia los resultados después de seleccionar
   }
  getTipoDxDetalle(term:string, index: number){
       if(term.length > 2){
         this.services.getTipoDxXDet(term)
         .pipe(
                 tap((res: any[]) => {
                    this.detypdx = res.filter(ddx =>
                    ddx.detypdx.toLowerCase().includes(term.toLowerCase())
                    )                 
                     }),
                      
                      catchError((err) => {
                        // Maneja el error aquí
                        console.error('Error al buscar el tipo de diagnostico:', err);
                        alert('Error ' + err.message);
                        return of([]);
                      })
                    ).subscribe();
       }else{
         this.detypdx = []
       }
       
     }
 
  selecttipDx(tdx: any, index: number): void {
       const grupo = this.diagnosticosFormArray.at(index);
       grupo.get('typdxevopac_fk')?.setValue(tdx.idtypdx);
       grupo.get('detypdx')?.setValue(tdx.detypdx);
       console.log('Tipo de diagnostico seleccionado:', tdx);
       this.detypdx = [];
 }
 
  getDxDetalle(term:string, index: number){
       if(term.length > 2){
         this.services.getDxfindByNomdx(term)
         .pipe(
                 tap((res: any[]) => {
                    this.detdx = res.filter(ddx =>
                    ddx.descdx.toLowerCase().includes(term.toLowerCase())
                    )                 
                     }),
                      
                      catchError((err) => {
                        // Maneja el error aquí
                        console.error('Error al buscar el diagnostico:', err);
                        alert('Error ' + err.message);
                        return of([]);
                      })
                    ).subscribe();
       }else{
         this.detdx = []
       }
       
     }
 
  selectDx(dx: any, index: number): void {
       const grupo = this.diagnosticosFormArray.at(index);
       grupo.get('dxevopac_fk')?.setValue(dx.clavedx);
       grupo.get('descdx')?.setValue(dx.descdx);
       console.log('Diagnostico seleccionado:', dx);
       this.detdx = [];
  }
  agregarDiagnostico(): void {
  this.diagnosticosFormArray.push(this.fb.group({
    typdxevopac_fk: [null],
    detypdx: [''],
    dxevopac_fk: [null],
    descdx: ['']
    }));
  }

  removeDiagnostico(index: number) {
        const diagnosticos = this.formEvoMedi.get('diagnosticos') as FormArray;
        diagnosticos.removeAt(index);
        
      }


  creaevomed(){
    let struckEvoluciones={
      evoeventdto:{
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
      },
      dxevendto: this.formEvoMedi.value.diagnosticos.map((dx: any) => ({
      dxevopac_fk: { 
        clavedx: dx.dxevopac_fk
      },
      typdxevopac_fk: { 
        idtypdx:dx.typdxevopac_fk
      },
      estdxevopac: { 
        idstatus: 1 
      },
    }))
     
    }
    console.log('Estructura de Evolucion Medica', struckEvoluciones);
    this.service.addEvoMedCompleta(struckEvoluciones)
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

 

}
