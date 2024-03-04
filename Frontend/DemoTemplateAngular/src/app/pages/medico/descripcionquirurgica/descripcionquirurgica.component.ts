import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-descripcionquirurgica',
  templateUrl: './descripcionquirurgica.component.html',
  
})
export class DescripcionquirurgicaComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  iraddpx(){
    this.router.navigate(['procedimientos']);
  }

}
