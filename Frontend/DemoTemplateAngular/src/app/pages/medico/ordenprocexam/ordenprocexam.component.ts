import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-ordenprocexam',
  templateUrl: './ordenprocexam.component.html',
  
})
export class OrdenprocexamComponent implements OnInit {
  ordprocexam:any
  ready : boolean=false
  idevent: string


  constructor(private servicio:OperacionService,
              private router:Router,
              private paramsrouter: ActivatedRoute) { 
                      this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')
                      console.log('idevent del snapshop',this.idevent)
                 }

  ngOnInit() {
    this.getordprocexam()
  }
  getordprocexam(){
    const id = this.idevent; 
    console.log('id del evento',id)
    this.servicio.getordenprocexamXIdEvent(id)
    .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('Ordenes de procedimientos y examenes', res);
              this.ordprocexam = res;
              this.ready = true;
            }),
            catchError((err) => {
              // Maneja el error aquí
              console.error('ERROR:', err);
              alert('Error ' + err.message)
              throw err; // Re-throw para que el error se propague al suscriptor
            })
          ).subscribe();
    
  }

  irEditOrdProcExam(idordprocexam:any){
    this.router.navigateByUrl(`/editordprocexam/${idordprocexam}`)  
    console.log('orden a editar que va desde el index',idordprocexam)    
  }

}
