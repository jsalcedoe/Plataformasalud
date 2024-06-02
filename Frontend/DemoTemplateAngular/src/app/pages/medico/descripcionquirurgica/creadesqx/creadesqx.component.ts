import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-creadesqx',
  templateUrl: './creadesqx.component.html',
  
})
export class CreadesqxComponent implements OnInit {

  formdesqx:FormGroup
  formEvoMedi:FormGroup
  eventSeleccionado:any
  idevent:string

  constructor(private router:Router,
    private service:OperacionService,
    private services:ConfigService,
    private fb:FormBuilder,
    private paramsrouter: ActivatedRoute,) { 
      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')

        this.formEvoMedi = fb.group({
        idevent:['',[Validators.required]],
        conseventpac:['',[Validators.required]],
        idpac:['',[Validators.required]],
        numdocpac:['',[Validators.required]],
        primernompac:['',[Validators.required]],
        segundonompac:['',[Validators.required]],
        primerapepac:['',[Validators.required]],
        segundoapepac:['',[Validators.required]],
        detevol:['',[Validators.required]],
        notaevol_fk:['',[Validators.required]]
        })
    }

  ngOnInit(): void {
  }

}
