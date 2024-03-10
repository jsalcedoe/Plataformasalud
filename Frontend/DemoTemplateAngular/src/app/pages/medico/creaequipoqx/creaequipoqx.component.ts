import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-creaequipoqx',
  templateUrl: './creaequipoqx.component.html',
  
})
export class CreaequipoqxComponent implements OnInit {
  equipo:any;
  ready:boolean=false;

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  iradddxate(){
    this.router.navigate(['diagnosticosatencion'])
  }

}
