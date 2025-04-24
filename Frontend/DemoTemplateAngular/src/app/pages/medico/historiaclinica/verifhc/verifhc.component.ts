import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfigService } from 'src/app/services/config.service';
import { KeyboardserviceService } from 'src/app/services/keyboardservice.service';
import { OperacionService } from 'src/app/services/operacion.service';
import { catchError, Subscription, tap } from 'rxjs';  
import Swal from 'sweetalert2';

@Component({
  selector: 'app-verifhc',
  templateUrl: './verifhc.component.html',
  
})
export class VerifhcComponent implements OnInit {

  private keyboardSubscription: Subscription;

  FormVerifHC:FormGroup
  idevent:string
  hcpac:any
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
                this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')
                    console.log('idevent del snapshop',this.idevent)
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
                    })
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
  }

  getDataVerifHC(){
    const id = parseInt(this.idevent);
    console.log('idevent que se consulta en el service:', id, 'Tipo de dato:', typeof id);
    
    this.service.getHCXIdEvent(this.idevent)
    .pipe(
            tap((res) => {
            // Maneja la respuesta exitosa aquí
              console.log('Historia Clinica Consultada', res);
              this.hcpac = res;
              this.cargarhcpac();
                
            }),
              catchError((err) => {
                // Maneja el error aquí
                console.error('Error:', err);
                alert('Error ' + err.message);
                throw err; // Re-throw para que el error se propague al suscriptor
              })
            ).subscribe();
  }
  cargarhcpac(){
    this.FormVerifHC.patchValue({
      numdocpac: this.hcpac.eventpac_fk.pacevent_fk.numdocpac,
      conseventpac:this.hcpac.eventpac_fk.conseventpac,
      idpac:this.hcpac.eventpac_fk.pacevent_fk.idpac,    
      idevent:this.hcpac.eventpac_fk.idevent,
      idhcpac:this.hcpac.idhcpac,
      primernompac:this.hcpac.eventpac_fk.pacevent_fk.primernompac,
      segundonompac:this.hcpac.eventpac_fk.pacevent_fk.segundonompac,
      primerapepac:this.hcpac.eventpac_fk.pacevent_fk.primerapepac,
      segundoapepac:this.hcpac.eventpac_fk.pacevent_fk.segundoapepac,
      pesohcpac:this.hcpac.pesohcpac,
      estaturahcpac: this.hcpac.estaturahcpac,
      fchcpac: this.hcpac.fchcpac,
      frhcpac: this.hcpac.frhcpac,
      temphcpac: this.hcpac.temphcpac,
      tahcpac: this.hcpac.tahcpac,
      motconshcpac: this.hcpac.motconshcpac,
      enfacthcpac: this.hcpac.enfacthcpac,
      antpathcpac: this.hcpac.antpathcpac,
      antqxhcpac: this.hcpac.antqxhcpac,
      antalerhcpac: this.hcpac.antalerhcpac,
      antfarmhcpac: this.hcpac.antfarmhcpac,
      antfamyhcpac: this.hcpac.antfamyhcpac,
      anttxhcpac: this.hcpac.anttxhcpac, 
      planmanejhcpac: this.hcpac.planmanejhcpac, 
      analisishcpac:this.hcpac.analisishcpac,
      objhcpac: this.hcpac.objhcpac,
    })
  }
  updatehcpac(){
    const rawValues = this.FormVerifHC.getRawValue();
    const struckverif = {
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
    }
    console.log('Valores a enviar al backend:', struckverif);
    //let id = parseInt(this.paramsrouter.snapshot.paramMap.get('idevent'))
    let idhcpac = this.hcpac.idhcpac
    this.service.editHC(idhcpac,struckverif)
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
