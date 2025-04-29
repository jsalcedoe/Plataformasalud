import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, Subscription, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { KeyboardserviceService } from 'src/app/services/keyboardservice.service';
import { OperacionService } from 'src/app/services/operacion.service';

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
                      detevol:[''],
                      notaevol_fk:[''],
                      dettypnot:['']

                    })

                      
                 }

  ngOnInit(): void { 
    this.camposHabilitados = this.camposEvolucionMedica.map(() => false);
    this.keyboardSubscription = this.keyboardService.keydown$.subscribe(() => {
      this.habilitarCampos(); // Tu función de activación
    });  

    
    this.getDataEvoMed()
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
    this.service.getEvoMedId(id)
    .pipe(
      tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Evolucion Consultada', res);
      this.evomed = res;
      this.uploadevomed()
                        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
       ).subscribe();
  }
  uploadevomed(){
    this.FormVerifEvoMed.patchValue({
      idevol:this.evomed.idevol,
      numdocpac:this.evomed.eventevo_fk.pacevent_fk.numdocpac,      
      conseventpac:this.evomed.eventevo_fk.conseventpac,
      idpac:this.evomed.eventevo_fk.pacevent_fk.idpac,
      idevent:this.evomed.eventevo_fk.idevent,
      primernompac:this.evomed.eventevo_fk.pacevent_fk.primernompac,
      segundonompac:this.evomed.eventevo_fk.pacevent_fk.segundonompac,
      primerapepac:this.evomed.eventevo_fk.pacevent_fk.primerapepac,
      segundoapepac:this.evomed.eventevo_fk.pacevent_fk.segundoapepac,
      detevol:this.evomed.detevol,  
      notaevol_fk:this.evomed.notaevol_fk.idtypnot,
      dettypnot:this.evomed.notaevol_fk.dettypnot,
      
    })
    
  }

  updateEvoMed(){
    const rawValues = this.FormVerifEvoMed.getRawValue();
    const struckverif = {
      detevol: rawValues.detevol,
      notaevol_fk:{
        idtypnot:rawValues.notaevol_fk,
      },       
      eventevo_fk:{
        idevent:rawValues.idevent,
      },
      estnotaevol_fk:{
        idstatus:2
      }
    }
    console.log('Datos a enviar',struckverif)
    console.log('idevol a enviar',this.evomed.idevol,typeof this.evomed.idevol)
    const id = parseInt(this.evomed.idevol);
    this.service.editevomed(id,struckverif).
    pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Evolucion Medica Actualizada', res);
        alert('Evolucion Medica Actualizada')
        this.router.navigateByUrl(`/eventos`)
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
