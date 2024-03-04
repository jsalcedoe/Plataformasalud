import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-creapac',
  templateUrl: './creapac.component.html',
  
})
export class CreapacComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  ircreaevento(){
    this.router.navigate(['eventos'])
  }

}
