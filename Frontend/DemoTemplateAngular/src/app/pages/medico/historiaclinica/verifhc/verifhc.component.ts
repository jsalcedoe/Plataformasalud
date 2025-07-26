import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfigService } from 'src/app/services/config.service';
import { KeyboardserviceService } from 'src/app/services/keyboardservice.service';
import { OperacionService } from 'src/app/services/operacion.service';
import { catchError, of, Subscription, tap } from 'rxjs';  
import Swal from 'sweetalert2';

@Component({
  selector: 'app-verifhc',
  templateUrl: './verifhc.component.html',
  
})
export class VerifhcComponent implements OnInit {

  private keyboardSubscription: Subscription;

  FormVerifHC:FormGroup
  id:string
  hcpac:any
  detypdx:any [] = []
  detdx: any [] = []
  diagnosticosFormArray: FormArray;
  camposHabilitados:boolean[] = []


  camposPaciente = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'text'},
    {label:'Consecutivo del paciente',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Clave HC',nombre:'idhcpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',type:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',type:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',type:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',type:'text'},
    
  ]
  camposHistoriaClinica = [
    {label:'Peso',nombre:'pesohcpac',type:'text'},
    {label:'Estatura',nombre:'estaturahcpac',type:'text'},
    {label:'Frecuencia Cardiaca',nombre:'fchcpac',type:'text'},
    {label:'Frecuencia Respiratoria',nombre:'frhcpac',type:'text'},
    {label:'Temperatura',nombre:'temphcpac',type:'text'},
    {label:'Tension Arterial',nombre:'tahcpac',type:'text'},   
    {label:'Motivo de Consulta',nombre:'motconshcpac',type:'text'},
    {label:'Enfermedad Actual',nombre:'enfacthcpac',type:'text'},
    {label:'Antecedentes Patologicos',nombre:'antpathcpac',type:'text'},
    {label:'Antecedentes Quirurgicos',nombre:'antqxhcpac',type:'text'},
    {label:'Antecedentes Alergicos',nombre:'antalerhcpac',type:'text'},
    {label:'Antecedentes Farmacologicos',nombre:'antfarmhcpac',type:'text'},
    {label:'Antecedentes Familiares',nombre:'antfamyhcpac',type:'text'},
    {label:'Antecedentes Traumaticos',nombre:'anttxhcpac',type:'text'},
    {label:'Objetivo Consulta',nombre:'objhcpac',type:'textarea'},
    {label:'Analisis',nombre:'analisishcpac',type:'textarea'},
    {label:'Plan de manejo',nombre:'planmanejhcpac',type:'textarea'},
   
  ]

  constructor(private router:Router,
              private service:OperacionService,
              private keyboardService: KeyboardserviceService,                 
              private services:ConfigService,
              private fb:FormBuilder,
              private paramsrouter: ActivatedRoute) { 
                this.id = this.paramsrouter.snapshot.paramMap.get('id')
                    console.log('idhcpac del snapshop',this.id)
                    this.FormVerifHC = this.fb.group({
                      numdocpac:[''],
                      conseventpac:[''],
                      idpac:[''],
                      idevent:[''],
                      idhcpac:[''],
                      primernompac:[''],
                      segundonompac:[''],
                      primerapepac:[''],
                      segundoapepac:[''],
                      pesohcpac:[{ value: '', disabled: true }, Validators.required],
                      estaturahcpac:[{ value: '', disabled: true }, Validators.required],
                      fchcpac:[{ value: '', disabled: true }, Validators.required],
                      frhcpac:[{ value: '', disabled: true }, Validators.required],
                      temphcpac:[{ value: '', disabled: true }, Validators.required],
                      tahcpac:[{ value: '', disabled: true }, Validators.required], 
                      motconshcpac:[{ value: '', disabled: true }, Validators.required],
                      enfacthcpac:[{ value: '', disabled: true }, Validators.required],
                      antpathcpac:[{ value: '', disabled: true }, Validators.required],
                      antqxhcpac:[{ value: '', disabled: true }, Validators.required],
                      antalerhcpac:[{ value: '', disabled: true }, Validators.required],
                      antfarmhcpac:[{ value: '', disabled: true }, Validators.required],
                      antfamyhcpac:[{ value: '', disabled: true }, Validators.required],
                      anttxhcpac:[{ value: '', disabled: true }, Validators.required],
                      objhcpac:[{ value: '', disabled: true }, Validators.required],
                      analisishcpac:[{ value: '', disabled: true }, Validators.required],
                      planmanejhcpac:[{ value: '', disabled: true }, Validators.required],
                      diagnosticos: this.fb.array([]) // Agregamos el FormArray para diagnósticos
                    })
                    this.diagnosticosFormArray = this.FormVerifHC.get('diagnosticos') as FormArray;
              }

  ngOnInit(): void {
    this.getDataVerifHC();
    this.camposHabilitados = this.camposHistoriaClinica.map(() => false);
    this.keyboardSubscription = this.keyboardService.keydown$.subscribe(() => {
      this.habilitarCampos(); // Tu función de activación
    });
  }

  habilitarCampos():void{
    this.camposHabilitados = this.camposHabilitados.map(() => true);
    this.camposHistoriaClinica.forEach((campo, index) => {
      const control = this.FormVerifHC.get(campo.nombre);
      if (control) {
        control.enable();
        this.camposHabilitados[index] = true;
      }
    });
    this.diagnosticosFormArray.controls.forEach((grupo) => {
    grupo.get('detypdx')?.enable();
    grupo.get('descdx')?.enable();
    grupo.get('typdxhcpac_fk')?.enable();
    grupo.get('dxhcpac_fk')?.enable();
  })
  }

  getDataVerifHC(){
    const id = parseInt(this.id);
    console.log('idhcpac que se consulta en el service:', id, 'Tipo de dato:', typeof id);
    
    this.service.gethcpacXId(this.id)
    .pipe(
            tap((res) => {
            // Maneja la respuesta exitosa aquí
              console.log('Historia Clinica Consultada', res);
              this.hcpac = res;
              this.cargarhcpac();
              this.cargarDiagnosticos(); // Llamamos al método para cargar diagnósticos
                
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
      grupo.get('typdxhcpac_fk')?.setValue(tdx.idtypdx);
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
      grupo.get('dxhcpac_fk')?.setValue(dx.clavedx);
      grupo.get('descdx')?.setValue(dx.descdx);
      this.detdx = [];
}

    
  cargarhcpac(){
    this.FormVerifHC.patchValue({
      numdocpac: this.hcpac.hcdto.eventpac_fk.pacevent_fk.numdocpac,
      conseventpac:this.hcpac.hcdto.eventpac_fk.conseventpac,
      idpac:this.hcpac.hcdto.eventpac_fk.pacevent_fk.idpac,    
      idevent:this.hcpac.hcdto.eventpac_fk.idevent,
      idhcpac:this.hcpac.hcdto.idhcpac,
      primernompac:this.hcpac.hcdto.eventpac_fk.pacevent_fk.primernompac,
      segundonompac:this.hcpac.hcdto.eventpac_fk.pacevent_fk.segundonompac,
      primerapepac:this.hcpac.hcdto.eventpac_fk.pacevent_fk.primerapepac,
      segundoapepac:this.hcpac.hcdto.eventpac_fk.pacevent_fk.segundoapepac,
      pesohcpac:this.hcpac.hcdto.pesohcpac,
      estaturahcpac: this.hcpac.hcdto.estaturahcpac,
      fchcpac: this.hcpac.hcdto.fchcpac,
      frhcpac: this.hcpac.hcdto.frhcpac,
      temphcpac: this.hcpac.hcdto.temphcpac,
      tahcpac: this.hcpac.hcdto.tahcpac,
      motconshcpac: this.hcpac.hcdto.motconshcpac,
      enfacthcpac: this.hcpac.hcdto.enfacthcpac,
      antpathcpac: this.hcpac.hcdto.antpathcpac,
      antqxhcpac: this.hcpac.hcdto.antqxhcpac,
      antalerhcpac: this.hcpac.hcdto.antalerhcpac,
      antfarmhcpac: this.hcpac.hcdto.antfarmhcpac,
      antfamyhcpac: this.hcpac.hcdto.antfamyhcpac,
      anttxhcpac: this.hcpac.hcdto.anttxhcpac, 
      planmanejhcpac: this.hcpac.hcdto.planmanejhcpac, 
      analisishcpac:this.hcpac.hcdto.analisishcpac,
      objhcpac: this.hcpac.hcdto.objhcpac,
    })
  }
  cargarDiagnosticos() {
    // Limpiamos el array existente
    while (this.diagnosticosFormArray.length !== 0) {
      this.diagnosticosFormArray.removeAt(0);
    }

    // Agregamos cada diagnóstico al FormArray
    if (this.hcpac.dxhcdto && this.hcpac.dxhcdto.length > 0) {
      this.hcpac.dxhcdto.forEach((diagnostico: any) => {
        this.diagnosticosFormArray.push(this.fb.group({
        iddxhcpac: [diagnostico.iddxhcpac],
        typdxhcpac_fk: [{ value: diagnostico.typdxhcpac_fk.idtypdx, disabled: true }],
        detypdx: [{ value: diagnostico.typdxhcpac_fk.detypdx, disabled: true }],
        dxhcpac_fk: [{ value: diagnostico.dxhcpac_fk.clavedx, disabled: true }],
        descdx: [{ value: diagnostico.dxhcpac_fk.descdx, disabled: true }]
}));
      });
    }console.log('Diagnósticos cargados:', this.diagnosticosFormArray.value);
  }

  agregarDiagnostico(): void {
  this.diagnosticosFormArray.push(this.fb.group({
    typdxhcpac_fk: [null],
    detypdx: [''],
    dxhcpac_fk: [null],
    descdx: ['']
  }));
}

  removeDiagnostico(index: number) {
        const diagnosticos = this.FormVerifHC.get('diagnosticos') as FormArray;
        diagnosticos.removeAt(index);
        
      }

  
  updatehcpac(){
    const rawValues = this.FormVerifHC.getRawValue();
    const struckverif = {
       hcdto:{
            pesohcpac: rawValues.pesohcpac,
            estaturahcpac: rawValues.estaturahcpac,
            fchcpac: rawValues.fchcpac,
            frhcpac: rawValues.frhcpac,
            temphcpac: rawValues.temphcpac,
            tahcpac: rawValues.tahcpac,
            motconshcpac: rawValues.motconshcpac,
            enfacthcpac: rawValues.enfacthcpac,
            antpathcpac: rawValues.antpathcpac,
            antqxhcpac: rawValues.antqxhcpac,
            antalerhcpac: rawValues.antalerhcpac,
            antfarmhcpac: rawValues.antfarmhcpac,
            antfamyhcpac: rawValues.antfamyhcpac,
            anttxhcpac: rawValues.anttxhcpac,  
            objhcpac: rawValues.objhcpac,
            analisishcpac: rawValues.analisishcpac,
            planmanejhcpac: rawValues.planmanejhcpac,
            eventpac_fk:{
              idevent: rawValues.idevent,
            },
            esthcpac_fk:{
              idstatus: 2,
            }
       },
      dxhcdto: this.FormVerifHC.value.diagnosticos.map((dx: any) => ({
      typdxhcpac_fk: { 
        idtypdx: dx.typdxhcpac_fk
       },
      dxhcpac_fk: { 
        clavedx: dx.dxhcpac_fk 
      },
      estdxhcpac: { idstatus: 2 },
    }))

      
    }
    console.log('Valores a enviar al backend:', struckverif);
    const idhcpac = this.FormVerifHC.get('idhcpac')?.value;
    console.log('idhcpac a editar:',this.hcpac.idhcpac)
    this.service.edithcpac(idhcpac,struckverif)
    .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('Verificación Exitosa', res);
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
