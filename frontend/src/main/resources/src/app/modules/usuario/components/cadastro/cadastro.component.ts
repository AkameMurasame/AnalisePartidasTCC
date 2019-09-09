import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../../../shared/components/modal/modal.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfigModal } from 'src/app/modules/shared/components/modal/configModal';
import { UsuarioServices } from 'src/app/services/usuario.services';
import { User } from 'src/app/models/user';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'cadastro-component',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  @ViewChild(ModalComponent, { static: true })
  modal: ModalComponent;

  cadastroForm: FormGroup;
  usuario: User;
  configModal: ConfigModal;

  cssFooter = 'modal-footer';
  cssHeader = 'modal-header justify-content-center';
  cssBody = 'modal-body';
  textHeader = 'Cadastro';

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioServices, private router: Router, private authenticationService: AuthenticationService) {
    this.configModal = new ConfigModal(this.cssFooter, this.cssHeader, this.cssBody, this.textHeader);
  }

  ngOnInit() {
    this.cadastroForm = this.formBuilder.group({
      usuario: ['', Validators.required],
      senha: ['', Validators.required],
      email: ['', Validators.required]
    });
  }

  get form() { return this.cadastroForm.controls; }

  save() {
    if (this.cadastroForm.invalid) {
      this.modal.openVerticallyCentered();
      return;
    }

    this.usuario = new User();
    this.usuario.username = this.form.usuario.value;
    this.usuario.password = this.form.senha.value;
    this.usuario.email = this.form.email.value;

    this.usuarioService.registroUsuario(this.usuario)
      .pipe(first())
      .subscribe(
        data => {
          this.modal.closeModal();
          this.authenticationService.login(data.username, data.password)
            .pipe(first())
            .subscribe(
              usuario => {
                this.modal.closeModal();
                //Insert Invocador
                //this.router.navigate(['dashboard']);
              },
              error => {
                setTimeout(() => {
                  this.modal.openVerticallyCentered();
                }, 3000);
                console.log(error);
                console.log('to aqui');
              });
        },
        error => {
          setTimeout(() => {
            this.modal.openVerticallyCentered();
          }, 3000);
          console.log(error);
          console.log('to aqui');
        });
  }

  openModal() {
    this.modal.openVerticallyCentered();
  }
}
