import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { CadastroComponent } from './components/cadastro/cadastro.component';
import { CadastroInvocadorComponent } from './components/cadastro-invocador/cadastro-invocador.component';

@NgModule({
  declarations: [LoginComponent, CadastroComponent, CadastroInvocadorComponent],
  imports: [
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
  ],
  exports: [
    LoginComponent,
    CadastroComponent,
    CadastroInvocadorComponent
  ]
})
export class UsuarioModule { }
