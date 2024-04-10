import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaeventos',
  templateUrl: './creaeventos.component.html',
  
})
export class CreaeventosComponent implements OnInit {
  formEvent:FormGroup
  numdocpac:any
  idpac:number

  constructor(private service:OperacionService,
              private router:Router,
              private activaterouter:ActivatedRoute,
              private fb:FormBuilder
  ) { this.formEvent = fb.group({
    conseventpac:['',[Validators.required]],
    detevent:['',[Validators.required]],
    pacevent_fk:['',[Validators.required]],
  })}

  ngOnInit(): void {
    this.idpac=this.activaterouter.snapshot.params['idpac'];
    let paciente = this.service.getPacientesId()
  }

  

  

  creaEvento(){
    let structEvento = {
      conseventpac:this.formEvent.value.conseventpac,
      detevent:this.formEvent.value.detevent,
      pacevent_fk:{
        "idpac":this.formEvent.value.pacevent_fk
      },
      estevent_fk:{
        "idstatus": 1
      }

    }

    this.service.addEventos(structEvento)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Eventos', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['']);
        
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
