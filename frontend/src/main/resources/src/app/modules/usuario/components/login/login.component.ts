import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../../../shared/components/modal/modal.component';
import { ConfigModal } from '../../../shared/components/modal/configModal';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { ErrorComponent } from '../../../shared/components/error/error.component';

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  @ViewChild(ModalComponent, { static: true })
  modal: ModalComponent;

  @ViewChild(ModalComponent, { static: true })
  errorModal: ErrorComponent;

  loginForm: FormGroup;

  configModal: ConfigModal;

  submitted = false;

  cssFooter = "modal-footer";
  cssHeader = "modal-header justify-content-center";
  cssBody = "modal-body";
  textHeader = "Login";

  validationErros: any;

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) {
    this.configModal = new ConfigModal(this.cssFooter, this.cssHeader, this.cssBody, this.textHeader);
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usuario: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  get f() { return this.loginForm.controls; }

  save() {
    this.modal.closeModal();
    this.submitted = true;

    if (this.loginForm.invalid) {
      this.modal.openVerticallyCentered();
      return;
    }

    this.authenticationService.login(this.f.usuario.value, this.f.senha.value)
      .pipe(first())
      .subscribe(
        data => {
          this.modal.closeModal();
          this.router.navigate(['dashboard']);
        },
        error => {
          setTimeout(() => {
            this.modal.openVerticallyCentered();
          }, 3000);
          console.log(error);
          console.log("to aqui");
        });
  }

  openModal() {
    this.modal.openVerticallyCentered();
  }
}
