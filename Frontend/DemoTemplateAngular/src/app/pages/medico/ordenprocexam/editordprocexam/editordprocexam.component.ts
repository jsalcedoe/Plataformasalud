import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { KeyboardserviceService } from 'src/app/services/keyboardservice.service';
import { OperacionService } from 'src/app/services/operacion.service';
import { Subscription } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editordprocexam',
  templateUrl: './editordprocexam.component.html',
  
})
export class EditordprocexamComponent implements OnInit {

  private keyboardSubscription: Subscription;

  FormEditOrdProcExam:FormGroup
  idordprocexam:string
  ordprocexam:any
  nompxex:any[]=[]
  detproc:any[]=[]
  camposHabilitados:boolean[] = []

  camposPaciente = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'text'},
    {label:'Consecutivo del paciente',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',type:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',type:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',type:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',type:'text'},
    
  ]

  camposOrdenProcExam = [
    
    {label:'Tipo Procedimiento',nombre:'detproc',type:'text',editable:false}, 
    {label:'Procedimiento o examen',nombre:'nompxex',type:'text',editable:false},    
    {label:'Cantidad',nombre:'canordprocexam',type:'text',editable:false},          
    {label:'Observacion',nombre:'obsordprocexam',type:'text',editable:false},
    
  ]
  constructor(private router:Router,
                  private service:OperacionService,
                  private keyboardService: KeyboardserviceService,                 
                  private services:ConfigService,
                  private fb:FormBuilder,
                  private paramsrouter: ActivatedRoute) {
                    this.idordprocexam = this.paramsrouter.snapshot.paramMap.get('idordprocexam')
                    console.log('idordprocexam del snapshop',this.idordprocexam)
                    this.FormEditOrdProcExam = this.fb.group({
                      numdocpac:[''],
                      conseventpac:[''],
                      idpac:[''],
                      idevent:[''],
                      primernompac:[''],
                      segundonompac:[''],
                      primerapepac:[''],
                      segundoapepac:[''],
                      idordprocexam:[''],
                      canordprocexam:[{ value: '', disabled: true }, Validators.required],
                      obsordprocexam:[{ value: '', disabled: true }, Validators.required],
                      tordprocexam_fk:[{ value: '', disabled: true }, Validators.required],
                      detproc:[{ value: '', disabled: true }, Validators.required],
                      pordprocexam_fk:[{ value: '', disabled: true }, Validators.required],
                      nompxex:[{ value: '', disabled: true }, Validators.required],
                    })
                   }

  ngOnInit(): void {
    this.getDataOrdProcExam();
    this.camposHabilitados = this.camposOrdenProcExam.map(() => false);
    this.keyboardSubscription = this.keyboardService.keydown$.subscribe(() => {
      this.habilitarCampos(); // Tu función de activación
    });
  }

  habilitarCampos(): void {
    this.camposHabilitados = this.camposHabilitados.map(() => true);
    this.camposOrdenProcExam.forEach((campo, index) => {
      const control = this.FormEditOrdProcExam.get(campo.nombre);
      if (control) {
        control.enable();
        this.camposHabilitados[index] = true;
      }
    });
  }
  
  getDataOrdProcExam(){
    this.service.getordprocexamXId(this.idordprocexam)
    .pipe(
          tap((res) => {
            // Maneja la respuesta exitosa aquí
            console.log('Orden medica por ID', res);
            this.ordprocexam = res;
            this.cargarOrdenProcExam();
            
          }),
          catchError((err) => {
            // Maneja el error aquí
            console.error('Error:', err);
            alert('Error ' + err.message);
            throw err; // Re-throw para que el error se propague al suscriptor
          })
        ).subscribe();
  }
  cargarOrdenProcExam(){
    console.log('Datos del patchValue',this.ordprocexam)
    this.FormEditOrdProcExam.patchValue({
      idordprocexam: this.ordprocexam.idordprocexam,
      idevent: this.ordprocexam.eventordprocexam_fk.idevent,
      conseventpac: this.ordprocexam.eventordprocexam_fk.conseventpac,
      idpac: this.ordprocexam.eventordprocexam_fk.pacevent_fk.idpac,
      numdocpac: this.ordprocexam.eventordprocexam_fk.pacevent_fk.numdocpac,
      primernompac: this.ordprocexam.eventordprocexam_fk.pacevent_fk.primernompac,
      segundonompac: this.ordprocexam.eventordprocexam_fk.pacevent_fk.segundonompac,
      primerapepac: this.ordprocexam.eventordprocexam_fk.pacevent_fk.primerapepac,
      segundoapepac: this.ordprocexam.eventordprocexam_fk.pacevent_fk.segundoapepac,
      canordprocexam: this.ordprocexam.canordprocexam,
      obsordprocexam: this.ordprocexam.obsordprocexam,
      pordprocexam_fk: this.ordprocexam.pordprocexam_fk.idpmedins,
      nompxex: this.ordprocexam.pordprocexam_fk.nompxex,
      tordprocexam_fk: this.ordprocexam.tordprocexam_fk.idtproc,
      detproc: this.ordprocexam.tordprocexam_fk.detproc,
    })
  }

  getTipoProcedimientosExamenes(termino:string){
    if(termino.length > 2){
      this.services.getTipopxBydetproc(termino)
      .pipe(
              tap((res: any[]) => {
                 this.detproc = res.filter(dpe =>
                 dpe.detproc.toLowerCase().includes(termino.toLowerCase())
                 )                 
                  }),
                   
                   catchError((err) => {
                     // Maneja el error aquí
                     console.error('Error al buscar el tipo de procedimiento o examen:', err);
                     alert('Error ' + err.message);
                     return of([]);
                   })
                 ).subscribe();
    }else{
      this.detproc = []
    }
    
  }

  selecttipoprocexam(tpe:any):void{
    console.log('procedimiento o examen seleccionado:', tpe);
    this.FormEditOrdProcExam.get('tordprocexam_fk')?.setValue(tpe.idtproc);
    this.FormEditOrdProcExam.get('detproc')?.setValue(tpe.detproc);
    this.detproc = []; // Limpia los resultados después de seleccionar
  }

  getProcedimientosExamenes(termino:string){
    if(termino.length > 2){
      this.services.getProcedimientoexamenBynompxex(termino)
      .pipe(
        tap((res: any[]) => {
          this.nompxex = res.filter(pe =>
            pe.nompxex.toLowerCase().includes(termino.toLowerCase())
          );
          
        }),
       
       catchError((err) => {
         // Maneja el error aquí
         console.error('Error al buscar el procedimiento o examen:', err);
         alert('Error ' + err.message);
         return of([]);
       })
     ).subscribe();
    }else{
            this.nompxex = []
          }

  }

  selectprocexam(procexam:any):void{
    console.log('procedimiento o examen seleccionado: ',procexam);
    this.FormEditOrdProcExam.get('pordprocexam_fk')?.setValue(procexam.idpxex);
    this.FormEditOrdProcExam.get('nompxex')?.setValue(procexam.nompxex);
    this.nompxex = []; // Limpia los resultados después de seleccionar ok
  }

  updateordprocexam(){
    const rawValues = this.FormEditOrdProcExam.getRawValue(); // Incluye deshabilitados
    const struckordprocexam = {
      canordprocexam: rawValues.canordprocexam,
      obsordprocexam: rawValues.obsordprocexam,
      tordprocexam_fk: {
       idtproc: rawValues.tordprocexam_fk
      },
      pordprocexam_fk: {
        idpxex:rawValues.pordprocexam_fk
      } ,
      eventordprocexam_fk: {
        idevent:rawValues.idevent
      },
      estordprocexam_fk:{
        idstatus: 2
      }
    }
    console.log('Estructura para editar orden medica',struckordprocexam)
      let id = parseInt(this.paramsrouter.snapshot.paramMap.get('idordprocexam'))
      console.log('ID de la orden medica',id)
      this.service.editordprocexam(id,struckordprocexam)
      .pipe(
                  tap((res) => {
                    // Maneja la respuesta exitosa aquí
                    console.log('edicion de orden de procedimientos o examenes', res);
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
