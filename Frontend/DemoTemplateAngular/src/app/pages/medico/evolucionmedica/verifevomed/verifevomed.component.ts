import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, Subscription, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { KeyboardserviceService } from 'src/app/services/keyboardservice.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-verifevomed',
  templateUrl: './verifevomed.component.html',
  
})
export class VerifevomedComponent implements OnInit {
  private keyboardSubscription: Subscription;
  
  FormVerifEvoMed: any;
  idevol: string;
  evomed: any;
  tipnot: any [] = []
  detypdx:any [] = []
  detdx: any [] = []
  diagnosticosFormArray: FormArray;
  camposHabilitados:boolean[] = []

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
                private keyboardService: KeyboardserviceService,                 
                private services:ConfigService,
                private fb:FormBuilder,
                private paramsrouter: ActivatedRoute) {
                  this.idevol = this.paramsrouter.snapshot.paramMap.get('idevol')
                    console.log('idevol del snapshop',this.idevol)
                    this.FormVerifEvoMed = fb.group({
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
                    });  
                    this.diagnosticosFormArray = this.FormVerifEvoMed.get('diagnosticos') as FormArray;
                      
                 }

  ngOnInit(): void { 
    this.getDataEvoMed()
    this.camposHabilitados = this.camposEvolucionMedica.map(() => false);
    this.keyboardSubscription = this.keyboardService.keydown$.subscribe(() => {
      this.habilitarCampos(); // Tu función de activación
    });  
    
  }

  habilitarCampos():void{
    this.camposHabilitados = this.camposHabilitados.map(() => true);
    this.camposEvolucionMedica.forEach((campo, index) => {
      const control = this.FormVerifEvoMed.get(campo.nombre);
      if (control) {
        control.enable();
        this.camposHabilitados[index] = true;
      }
    });
    this.diagnosticosFormArray.controls.forEach((grupo) => {
    grupo.get('detypdx')?.enable();
    grupo.get('descdx')?.enable();
    grupo.get('typdxevopac_fk')?.enable();
    grupo.get('dxevopac_fk')?.enable();
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
    this.FormVerifEvoMed.get('notaevol_fk')?.setValue(tn.idtypnot);
    this.FormVerifEvoMed.get('dettypnot')?.setValue(tn.dettypnot);
    this.tipnot = []; // Limpia los resultados después de seleccionar
  }

  getDataEvoMed(){
    const id = parseInt(this.idevol);
    this.service.getEvoMedCompletaId(id)
    .pipe(
      tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Evolucion Consultada', res);
      this.evomed = res;
      this.uploadevomed()
      this.cargarDiagnosticos();
                        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
       ).subscribe();
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
      this.detdx = [];
}
  uploadevomed(){
    console.log('Datos de evolucion en Upload', this.evomed);
    this.FormVerifEvoMed.patchValue({
      idevol:this.evomed.evoeventdto.idevol,
      numdocpac:this.evomed.evoeventdto.eventevo_fk.pacevent_fk.numdocpac,      
      conseventpac:this.evomed.evoeventdto.eventevo_fk.conseventpac,
      idpac:this.evomed.evoeventdto.eventevo_fk.pacevent_fk.idpac,
      idevent:this.evomed.evoeventdto.eventevo_fk.idevent,
      primernompac:this.evomed.evoeventdto.eventevo_fk.pacevent_fk.primernompac,
      segundonompac:this.evomed.evoeventdto.eventevo_fk.pacevent_fk.segundonompac,
      primerapepac:this.evomed.evoeventdto.eventevo_fk.pacevent_fk.primerapepac,
      segundoapepac:this.evomed.evoeventdto.eventevo_fk.pacevent_fk.segundoapepac,
      dettypnot:this.evomed.evoeventdto.notaevol_fk.dettypnot,    
      notaevol_fk:this.evomed.evoeventdto.notaevol_fk.idtypnot,
      detevol:this.evomed.evoeventdto.detevol, 
    })
    
  }
  cargarDiagnosticos() {
    // Limpiamos el array existente
    while (this.diagnosticosFormArray.length !== 0) {
      this.diagnosticosFormArray.removeAt(0);
    }

    // Agregamos cada diagnóstico al FormArray
    if (this.evomed.dxevendto && this.evomed.dxevendto.length > 0) {
      this.evomed.dxevendto.forEach((diagnostico: any) => {

        this.diagnosticosFormArray.push(this.fb.group({
        iddxevopac: [diagnostico.iddxevopac],
        typdxevopac_fk: [{ value: diagnostico.typdxevopac_fk.idtypdx, disabled: true }],
        detypdx: [{ value: diagnostico.typdxevopac_fk.detypdx, disabled: true }],
        dxevopac_fk: [{ value: diagnostico.dxevopac_fk.clavedx, disabled: true }],
        descdx: [{ value: diagnostico.dxevopac_fk.descdx, disabled: true }]
    }));
      });
    }
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
        const diagnosticos = this.FormVerifEvoMed.get('diagnosticos') as FormArray;
        diagnosticos.removeAt(index);
        
      }


  updateEvoMed(){

    // Validar campos requeridos
  if (!this.FormVerifEvoMed.get('notaevol_fk')?.value) {
    alert('Debe seleccionar un tipo de nota');
    return;
  }

  const rawValues = this.FormVerifEvoMed.getRawValue();
  
  // Asegurar que el idtypnot sea un número válido
  const idtypnot = Number(rawValues.notaevol_fk);
  if (isNaN(idtypnot)) {
    alert('El tipo de nota no es válido');
    return;
  }

        
   // const rawValues = this.FormVerifEvoMed.getRawValue();
    console.log('Valores del formulario antes de enviar:', rawValues);

    const struckverif = {
      evoeventdto:{
          detevol: rawValues.detevol,
          notaevol_fk:{
              idtypnot:idtypnot//rawValues.notaevol_fk.idtypnot
          },       
          eventevo_fk:{
              idevent:rawValues.idevent,
          },
          estnotaevol_fk:{
              idstatus:2
          }
    },
    dxevendto:this.FormVerifEvoMed.value.diagnosticos.map((dx: any) => ({
      typdxevopac_fk: { 
          idtypdx: dx.typdxevopac_fk
       },
      dxevopac_fk: { 
        clavedx: dx.dxevopac_fk 
      },
      estdxevopac: { 
        idstatus: 2 
      },
    }))
    
    }
    console.log('Datos a enviar:', struckverif);
    //const id = parseInt(this.evomed.idevol);
    const id =this.evomed.evoeventdto.idevol;
    console.log('ID de la evolución médica:', id, 'tipo de dato', typeof id);
    this.service.editevomedCompleta(id,struckverif).
    pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Evolucion Medica Actualizada', res);
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
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
    
  }



  

}
