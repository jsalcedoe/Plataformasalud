import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-creaequipoqx',
  templateUrl: './creaequipoqx.component.html',
  
})
export class CreaequipoqxComponent implements OnInit {
  equipo:any;
  ready:boolean=false;
  formequipoqx:FormGroup
  idevent:string
  ideventseleccionado:any

  constructor(private router:Router,
              private service:OperacionService,
              private fb:FormBuilder,
              private servicio:ConfigService,
              private paramsrouter: ActivatedRoute
              ) { 
                this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
                console.log('idevent del snapshop',this.idevent)
                this.formequipoqx = fb.group({
                  idevent:['',[Validators.required]],
                  conseventpac:['',[Validators.required]],
                  idpac:['',[Validators.required]],
                  numdocpac:['',[Validators.required]],
                  primernompac:['',[Validators.required]],
                  segundonompac:['',[Validators.required]],
                  primerapepac:['',[Validators.required]],
                  segundoapepac:['',[Validators.required]],

                })
              }

  ngOnInit(): void {
  }

  iradddxate(){
    this.router.navigate(['diagnosticosatencion'])
  }

}
