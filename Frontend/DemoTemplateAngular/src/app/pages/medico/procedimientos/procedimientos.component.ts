import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-procedimientos',
  templateUrl: './procedimientos.component.html'
})
export class ProcedimientosComponent implements OnInit {

  ready:boolean = false
  pxdesc: String 

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  iradddx(){
    this.router.navigate(['diagnosticosatencion'])
  }

}
