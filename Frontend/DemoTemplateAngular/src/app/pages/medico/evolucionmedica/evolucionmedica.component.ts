import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-evolucionmedica',
  templateUrl: './evolucionmedica.component.html',
  
})
export class EvolucionmedicaComponent implements OnInit {

  constructor( ) { }

  ngOnInit(): void {}

  

}


