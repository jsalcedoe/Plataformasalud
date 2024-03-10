import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-evolucionmedica',
  templateUrl: './evolucionmedica.component.html',
  
})
export class EvolucionmedicaComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  iradddx(){
    this.router.navigate(['diagnosticosatencion'])
  }

}
