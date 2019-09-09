import { Component, OnInit } from '@angular/core';
import { InvocadorService } from 'src/app/services/invocador.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Invocador } from 'src/app/models/invocador.class';
import { first, map } from 'rxjs/operators';

@Component({
  selector: 'cadastro-invocador-component',
  templateUrl: './cadastro-invocador.component.html',
  styleUrls: ['./cadastro-invocador.component.scss']
})
export class CadastroInvocadorComponent implements OnInit {

  user: string;
  invocador: Invocador;
  invocadorForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private invocadorService: InvocadorService, private router: Router) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (!this.user) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.invocadorForm = this.formBuilder.group({
      invocador: ['', Validators.required]
    });
  }

  registrarInvocador() {
    if (this.invocadorForm.invalid) {
      return;
    }
    console.log(this.invocadorForm.value);

    this.invocadorService.registrarInvocador(this.invocadorForm.controls.invocador.value)
      .pipe(first()).subscribe(summoner => {
        console.log(summoner);
      });
  }

}
