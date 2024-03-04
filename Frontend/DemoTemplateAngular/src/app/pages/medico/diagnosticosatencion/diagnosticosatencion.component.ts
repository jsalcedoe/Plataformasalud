import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-diagnosticosatencion',
  templateUrl: './diagnosticosatencion.component.html'
})
export class DiagnosticosatencionComponent implements OnInit {
  ready:boolean=false
  dxate:any;

  constructor() { }

  ngOnInit(): void {
  }

}
