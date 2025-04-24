import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
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
  idevomed: string;
  evomed: any;
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

  constructor(private router:Router,
                private service:OperacionService,
                private keyboardService: KeyboardserviceService,                 
                private services:ConfigService,
                private fb:FormBuilder,
                private paramsrouter: ActivatedRoute) {
                  this.idevomed = this.paramsrouter.snapshot.paramMap.get('idevent')
                    console.log('idevent del snapshop',this.idevomed)
                 }

  ngOnInit(): void {
  }
  gettipoevolucion(){}
  

}
