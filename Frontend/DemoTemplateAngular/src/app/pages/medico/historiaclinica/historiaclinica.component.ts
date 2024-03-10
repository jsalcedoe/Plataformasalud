import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-historiaclinica',
  templateUrl: './historiaclinica.component.html',
  
})
export class HistoriaclinicaComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  iradddx(){
    this.router.navigate(['diagnosticosatencion'])
  }

}
