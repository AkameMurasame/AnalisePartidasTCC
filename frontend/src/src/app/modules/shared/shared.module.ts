import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './components/modal/modal.component';
import { AlertComponent } from './components/alert/alert.component';


@NgModule({
  declarations: [ModalComponent, AlertComponent],
  imports: [
    CommonModule,
    NgbModule,
  ],
  exports: [
    ModalComponent,
    AlertComponent
  ]
})
export class SharedModule {
}
