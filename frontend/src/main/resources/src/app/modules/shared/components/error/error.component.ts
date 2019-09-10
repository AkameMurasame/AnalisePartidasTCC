import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { ModalComponent } from '../modal/modal.component';
import { ConfigModal } from '../modal/configModal';

@Component({
  selector: 'error-component',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent {

  @ViewChild(ModalComponent, { static: true })
  modal: ModalComponent;

  constructor() { 
    console.log(this.modal);
  }

  openModal() {
    this.modal.openVerticallyCentered();
  }
}
